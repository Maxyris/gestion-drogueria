import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { environment } from "../../../../environments/environment";
import { HttpApiRouterConstant } from "../../../shared/constants/http-api-router.constant";
import { IHttpGlobalResponse, IPaginationResponse } from "../../../shared/interfaces/http.model";
import { IMedicamento } from "../../../shared/interfaces/medicamento.model";
import { IVenta } from "../../../shared/interfaces/ventas.model";

@Injectable({
  providedIn: 'root'
})
export class MedicamentosService {
  /**
   * Servicio para manejar peticiones http
   */
  private http = inject(HttpClient);

  /**
   * Obtiene los medicamentos paginados
   *
   * @param pagina - Número de página
   * @param cantidadRegistros - Cantidad de registros por página
   */
  getMedicamentos(pagina: number, cantidadRegistros: number) {
    return this.http.get<IHttpGlobalResponse<IPaginationResponse<IMedicamento>>>(`${environment.api_url_gateway}/${HttpApiRouterConstant.MEDICAMENTOS}?pagina=${pagina}&cantidadRegistros=${cantidadRegistros}`);
  }

  /**
   * Crea un medicamento
   *
   * @param medicamento - Medicamento a crear
   */
  crearMedicamento(medicamento: IMedicamento) {
    return this.http.post<IHttpGlobalResponse<void>>(`${environment.api_url_gateway}/${HttpApiRouterConstant.MEDICAMENTOS}`, medicamento);
  }

  /**
   * Edita un medicamento
   *
   * @param medicamento - Medicamento a editar
   */
  editarMedicamento(medicamento: IMedicamento) {
    return this.http.put<IHttpGlobalResponse<void>>(`${environment.api_url_gateway}/${HttpApiRouterConstant.MEDICAMENTOS}`, medicamento);
  }

  /**
   * Elimina un medicamento
   *
   * @param id - ID del medicamento a eliminar
   */
  eliminarMedicamento(id: number) {
    return this.http.delete<IHttpGlobalResponse<void>>(`${environment.api_url_gateway}/${HttpApiRouterConstant.MEDICAMENTOS}?idMedicamento=${id}`);
  }

  /**
   * Vende un medicamento
   *
   * @param venta - Venta a realizar
   */
  venderMedicamento(venta: IVenta) {
    return this.http.post<IHttpGlobalResponse<void>>(`${environment.api_url_gateway}/${HttpApiRouterConstant.VENTAS}`, venta);
  }
}
