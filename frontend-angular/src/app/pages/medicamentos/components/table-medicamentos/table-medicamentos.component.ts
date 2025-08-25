import { CurrencyPipe, DatePipe } from '@angular/common';
import { afterNextRender, Component, inject, signal } from '@angular/core';
import { rxResource } from '@angular/core/rxjs-interop';
import { DialogService } from '@ngneat/dialog';
import { ConfirmationService } from 'primeng/api';
import { ButtonModule } from 'primeng/button';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { IconFieldModule } from 'primeng/iconfield';
import { InputIconModule } from 'primeng/inputicon';
import { InputTextModule } from 'primeng/inputtext';
import { TableModule, TablePageEvent } from 'primeng/table';
import { catchError, of, tap } from 'rxjs';
import {
  IHttpGlobalResponse,
  IPaginationResponse,
} from '../../../../shared/interfaces/http.model';
import { IMedicamento } from '../../../../shared/interfaces/medicamento.model';
import { IVenta } from '../../../../shared/interfaces/ventas.model';
import { ConfirmationModalComponent } from '../../../../shared/modals/confirmacion-modal/confirmacion-modal.component';
import { VenderMedicamentoModalComponent } from '../../modals/vender-medicamento-modal/vender-medicamento-modal.component';
import { MedicamentosService } from '../../services/medicamentos.service';
import { HotToastService } from '@ngxpert/hot-toast';
import { CrearEditarMedicamentoModalComponent } from '../../modals/crear-editar-medicamento-modal/crear-editar-medicamento.component';

@Component({
  selector: 'app-table-medicamentos',
  imports: [
    TableModule,
    ButtonModule,
    InputTextModule,
    IconFieldModule,
    InputIconModule,
    DatePipe,
    CurrencyPipe,
    ConfirmDialogModule
  ],
  providers: [ConfirmationService, DialogService],
  templateUrl: './table-medicamentos.component.html',
  styleUrls: ['./table-medicamentos.component.scss']
})
export class TableMedicamentosComponent {

  /**
   * Servicio para gestionar los medicamentos
   */
  medicamentosService = inject(MedicamentosService);

  /**
   * Servicio para gestionar los dialogos
   */
  dialogService = inject(DialogService);

  /**
   * Servicio para gestionar las notificaciones toast
   */
  hotToastService = inject(HotToastService);

  /**
   * Página actual del paginador
   */
  paginaActual = signal(0);

  /**
   * Cantidad de registros por página
   */
  cantidadRegistros = signal(2);

  /**
   * Total de registros
   */
  totalRegistros = signal<number>(0);

  /**
   * Recurso para obtener los medicamentos
   */
  medicamentos = rxResource<
    IHttpGlobalResponse<IPaginationResponse<IMedicamento>>,
    void
  >({
    params: () => ({
      pagina: this.paginaActual(),
      cantidadRegistros: this.cantidadRegistros(),
    }),
    stream: () =>
      this.medicamentosService
        .getMedicamentos(this.paginaActual(), this.cantidadRegistros())
        .pipe(
          catchError((error) => {
            this.hotToastService.error(error.error.message);

            return of({
              message: error.error.message,
            })
          }),
          tap((response) => {
            this.totalRegistros.set(response.data?.totalElements!);
          })
        ),
    defaultValue: {
      message: '',
    },
  });

  /**
   * Paginador de medicamentos
   * @param pagina
   */
  paginarMedicamentos(pagina: TablePageEvent) {
    this.paginaActual.set(pagina.first);
    this.cantidadRegistros.set(pagina.rows);

    this.medicamentos.reload();
  }

  /**
   * Elimina un medicamento
   * @param medicamento Medicamento a eliminar
   */
  eliminarMedicamento(medicamento: IMedicamento) {
    this.dialogService.open(ConfirmationModalComponent, {
      data: {
        titulo: 'Eliminar medicamento',
        mensaje: '¿Estás seguro de querer eliminar este medicamento?',
      },
    }).afterClosed$.subscribe(confirmacion => {
      if (confirmacion) {
        this.medicamentosService.eliminarMedicamento(medicamento.id!).subscribe({
          next: (response) => {
            this.hotToastService.success(response.message);
            this.medicamentos.reload();
          },
          error: (error) => {
            this.hotToastService.error(error.error.message);
          }
        });
      }
    });
  }

  /**
   * Vende un medicamento
   * @param medicamento Medicamento a vender
   */
  venderMedicamento(medicamento: IMedicamento) {
    this.dialogService.open(VenderMedicamentoModalComponent, {
      data: medicamento,
    }).afterClosed$.subscribe(cantidadVenta => {
      if (cantidadVenta) {
        const venta: IVenta = {
          fechaHora: new Date(),
          cantidadVenta: cantidadVenta,
          valorTotal: cantidadVenta * medicamento.valorUnitario,
          idMedicamento: medicamento.id!,
          valorUnitario: medicamento.valorUnitario
        }
        this.medicamentosService.venderMedicamento(venta).subscribe({
          next: (response) => {
            this.hotToastService.success(response.message);
            this.medicamentos.reload();
          },
          error: (error) => {
            this.hotToastService.error(error.error.message);
          }
        })
      }
    });
  }

  /**
   * Edita un medicamento
   * @param medicamentoEditar Medicamento a editar
   */
  editarMedicamento(medicamentoEditar: IMedicamento) {
    this.dialogService.open(CrearEditarMedicamentoModalComponent, {
      data: medicamentoEditar,
    }).afterClosed$.subscribe(medicamento => {
      if (medicamento) {
        const medicamentoActualizado: IMedicamento = {
          ...medicamentoEditar,
          ...medicamento
        }

        this.medicamentosService.editarMedicamento(medicamentoActualizado).subscribe({
          next: (response) => {
            this.hotToastService.success(response.message);
            this.medicamentos.reload();
          },
          error: (error) => {
            this.hotToastService.error(error.error.message);
          }
        });
      }
    });
  }

  /**
   * Crea un medicamento
   */
  crearMedicamento() {
    this.dialogService.open(CrearEditarMedicamentoModalComponent).afterClosed$.subscribe(medicamento => {
      if (medicamento) {
        this.medicamentosService.crearMedicamento(medicamento).subscribe({
          next: (response) => {
            this.hotToastService.success(response.message);
            this.medicamentos.reload();
          },
          error: (error) => {
            this.hotToastService.error(error.error.message);
          }
        });
      }
    });
  }
}
