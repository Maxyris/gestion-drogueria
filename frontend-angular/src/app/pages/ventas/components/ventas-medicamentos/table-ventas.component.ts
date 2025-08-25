import { CurrencyPipe, DatePipe } from '@angular/common';
import { afterNextRender, Component, DestroyRef, inject, signal } from '@angular/core';
import { rxResource, takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { DialogService } from '@ngneat/dialog';
import { HotToastService } from '@ngxpert/hot-toast';
import { ButtonModule } from 'primeng/button';
import { FloatLabelModule } from 'primeng/floatlabel';
import { IconFieldModule } from 'primeng/iconfield';
import { InputIconModule } from 'primeng/inputicon';
import { InputTextModule } from 'primeng/inputtext';
import { Table, TableModule, TablePageEvent } from 'primeng/table';
import { catchError, debounceTime, of, tap } from 'rxjs';
import { IHttpGlobalResponse, IPaginationResponse } from '../../../../shared/interfaces/http.model';
import { IVenta } from '../../../../shared/interfaces/ventas.model';
import { VentasService } from '../../services/ventas.service';
import { FormBuilder, FormsModule, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-table-ventas',
  imports: [
    TableModule,
    ButtonModule,
    InputTextModule,
    IconFieldModule,
    InputIconModule,
    DatePipe,
    CurrencyPipe,
    FloatLabelModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [DialogService],
  templateUrl: './table-ventas.component.html',
  styleUrls: ['./table-ventas.component.scss']
})
export class TableVentasComponent {
  /**
   * Servicio para gestionar las ventas
   */
  ventasService = inject(VentasService);

  /**
   * Servicio para gestionar las notificaciones toast
   */
  hotToastService = inject(HotToastService);

  /**
   * Servicio para gestionar los formularios
   */
  formBuilder = inject(FormBuilder);

  /**
   * Referencia para destruir el componente
   */
  destroyRef = inject(DestroyRef);

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
   * Formulario para filtrar las ventas
   */
  formFiltros = this.formBuilder.group({
    fechaInicio: [new Date().toISOString().split('T')[0]],
    fechaFin: [new Date().toISOString().split('T')[0]],
  });

  /**
   * Recurso para obtener las ventas
   */
  ventas = rxResource<
    IHttpGlobalResponse<IPaginationResponse<IVenta>>,
    void
  >({
    params: () => ({
      pagina: this.paginaActual(),
      cantidadRegistros: this.cantidadRegistros(),
    }),
    stream: () =>
      this.ventasService
        .getVentas(this.paginaActual(), this.cantidadRegistros(), this.fechaInicio?.value!, this.fechaFin?.value!)
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

  ngOnInit() {
    this.formFiltros?.valueChanges.pipe(
      debounceTime(200),
      takeUntilDestroyed(this.destroyRef),
      tap(() => this.ventas.reload())
    ).subscribe();
  }

  /**
   * Paginador de ventas
   * @param pagina Evento de paginación
   */
  paginarVentas(pagina: TablePageEvent) {
    this.paginaActual.set(pagina.first);
    this.cantidadRegistros.set(pagina.rows);

    this.ventas.reload();
  }

  /**
   * Abre el selector de fecha
   * @param input Input del selector de fecha
   */
  openDatePicker(input: HTMLInputElement) {
    try {
      input.showPicker();
    } catch (error) {}
  }

  /**
   * Fecha de inicio del formulario
   */
  get fechaInicio() {
    return this.formFiltros.get('fechaInicio');
  }

  /**
   * Fecha de fin del formulario
   */
  get fechaFin() {
    return this.formFiltros.get('fechaFin');
  }
}
