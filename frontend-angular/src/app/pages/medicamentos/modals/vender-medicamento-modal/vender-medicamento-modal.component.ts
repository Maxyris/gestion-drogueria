import { Component, inject } from "@angular/core";
import { DialogRef } from "@ngneat/dialog";
import { ButtonModule } from "primeng/button";
import { FloatLabelModule } from "primeng/floatlabel";
import { InputTextModule } from "primeng/inputtext";
import { IMedicamento } from "../../../../shared/interfaces/medicamento.model";
import { FormControl, FormsModule, ReactiveFormsModule, Validators } from "@angular/forms";
import { CurrencyPipe } from "@angular/common";

@Component({
  selector: 'app-vender-medicamento-modal',
  templateUrl: './vender-medicamento-modal.component.html',
  styleUrls: ['./vender-medicamento-modal.component.scss'],
  standalone: true,
  imports: [
    InputTextModule,
    ButtonModule,
    FloatLabelModule,
    ReactiveFormsModule,
    FormsModule,
    CurrencyPipe
  ],
})
export class VenderMedicamentoModalComponent {

  /**
   * Referencia para cerrar el modal
   */
  ref = inject(DialogRef<IMedicamento>);

  /**
   * Formulario para la cantidad de medicamentos a vender
   */
  inputCantidad = new FormControl<number>(1, [Validators.required, Validators.min(1),
     Validators.pattern(/^\d+$/)]);
}
