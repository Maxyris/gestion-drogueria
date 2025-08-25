import { Component, inject, OnInit } from "@angular/core";
import { IMedicamento } from "../../../../shared/interfaces/medicamento.model";
import { DialogRef } from "@ngneat/dialog";
import { FormBuilder, FormsModule, ReactiveFormsModule, Validators } from "@angular/forms";
import { FloatLabelModule } from "primeng/floatlabel";
import { InputTextModule } from "primeng/inputtext";
import { ButtonModule } from "primeng/button";

@Component({
  selector: 'app-crear-editar-medicamento-modal',
  templateUrl: './crear-editar-medicamento.component.html',
  styleUrls: ['./crear-editar-medicamento.component.scss'],
  imports: [
    ReactiveFormsModule,
    FormsModule,
    FloatLabelModule,
    InputTextModule,
    ButtonModule,
  ]
})
export class CrearEditarMedicamentoModalComponent implements OnInit {

  /**
   * Referencia para cerrar el modal
   */
  ref = inject(DialogRef<IMedicamento | undefined>);

  /**
   * Servicio para gestionar los formularios
   */
  formBuilder = inject(FormBuilder);

  /**
   * Formulario para crear o editar un medicamento
   */
  formGroup = this.formBuilder.group({
    nombre: ['', [Validators.required]],
    laboratorio: ['', [Validators.required]],
    fechaFabricacion: ['', [Validators.required]],
    fechaVencimiento: ['', [Validators.required]],
    valorUnitario: [1, [Validators.required, Validators.min(1)]],
    cantidad: [1, [Validators.required, Validators.min(1)]],
  });

  ngOnInit(): void {
    if (this.ref.data) {
      this.formGroup.patchValue(this.ref.data);
      this.formGroup.get('fechaFabricacion')?.setValue(this.ref.data.fechaFabricacion.split('T')[0]);
      this.formGroup.get('fechaVencimiento')?.setValue(this.ref.data.fechaVencimiento.split('T')[0]);
    }
  }

  /**
   * Fecha de vencimiento del formulario
   */
  get fechaVencimiento() {
    return this.formGroup.get('fechaVencimiento');
  }

  /**
   * Fecha de fabricación del formulario
   */
  get fechaFabricacion() {
    return this.formGroup.get('fechaFabricacion');
  }
}
