import { Component, inject, input } from "@angular/core";
import { DialogRef } from "@ngneat/dialog";
import { ButtonModule } from "primeng/button";
import { DialogModule } from "primeng/dialog";

@Component({
  selector: 'app-confirmacion-modal',
  templateUrl: './confirmacion-modal.component.html',
  styleUrls: ['./confirmation-modal.component.scss'],
  imports: [DialogModule, ButtonModule],
})
export class ConfirmationModalComponent {
  /**
   * Referencia para cerrar el modal
   */
  ref: DialogRef<{titulo: string, mensaje: string}> = inject(DialogRef);
}
