import { Component } from '@angular/core';
import { TableVentasComponent } from './components/ventas-medicamentos/table-ventas.component';

@Component({
  selector: 'app-ventas-page',
  imports: [TableVentasComponent],
  templateUrl: './ventas-page.component.html'
})
export default class VentasPageComponent { }
