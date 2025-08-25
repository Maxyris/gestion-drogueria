import { Component, signal, WritableSignal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { MenuItem } from 'primeng/api';
import { MenubarModule } from 'primeng/menubar';
import { HttpRouterConstant } from './shared/constants/http-router.constant';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, MenubarModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class App {

  items: WritableSignal<MenuItem[]> = signal<MenuItem[]>(
    [
      {
        label: 'Medicamentos',
        icon: 'pi pi-shopping-bag',
        routerLink: `/${HttpRouterConstant.MEDICAMENTOS}`
      },
      {
        label: 'Ventas',
        icon: 'pi pi-shopping-cart',
        routerLink: `/${HttpRouterConstant.VENTAS}`
      }
    ]
  )
}
