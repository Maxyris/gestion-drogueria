import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { environment } from "../../../../environments/environment";
import { HttpApiRouterConstant } from "../../../shared/constants/http-api-router.constant";
import { IHttpGlobalResponse, IPaginationResponse } from "../../../shared/interfaces/http.model";
import { IObtenerVentasResponse, IVenta } from "../../../shared/interfaces/ventas.model";

@Injectable({
  providedIn: 'root'
})
export class VentasService {
  /**
   * Servicio para manejar peticiones http
   */
  private http = inject(HttpClient);

  /**
   * Obtiene los ventas paginados
   *
   * @param pagina - Número de página
   * @param cantidadRegistros - Cantidad de registros por página
   */
  getVentas(pagina: number, cantidadRegistros: number, fechaInicio: string, fechaFin: string) {
    return this.http.get<IHttpGlobalResponse<IPaginationResponse<IObtenerVentasResponse>>>(`${environment.api_url_gateway}/${HttpApiRouterConstant.VENTAS}?pagina=${pagina}&cantidadRegistros=${cantidadRegistros}&fechaInicio=${fechaInicio}&fechaFin=${fechaFin}`);
  }
}
