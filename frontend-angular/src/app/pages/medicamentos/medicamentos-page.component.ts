import { Component } from '@angular/core';
import { TableMedicamentosComponent } from './components/table-medicamentos/table-medicamentos.component';

@Component({
  selector: 'app-medicamentos-page',
  imports: [ TableMedicamentosComponent],
  templateUrl: './medicamentos-page.component.html'
})
export default class MedicamentosPageComponent { }
