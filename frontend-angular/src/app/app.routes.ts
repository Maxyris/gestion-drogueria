import { Routes } from '@angular/router';
import { HttpRouterConstant } from './shared/constants/http-router.constant';

export const routes: Routes = [
  {
    path: HttpRouterConstant.MEDICAMENTOS,
    loadComponent: () => import('./pages/medicamentos/medicamentos-page.component'),
  },
  {
    path: HttpRouterConstant.VENTAS,
    loadComponent: () => import('./pages/ventas/ventas-page.component'),
  },
  {
    path: '**',
    redirectTo: HttpRouterConstant.MEDICAMENTOS
  },
];
