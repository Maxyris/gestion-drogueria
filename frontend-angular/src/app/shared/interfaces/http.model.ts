export interface IHttpGlobalResponse<T> {
  /**
   * Mensaje de la respuesta
   */
  message: string;

  /**
   * Datos de la respuesta
   */
  data?: T;
}

export interface IPaginationResponse<T> {
  /**
   * Lista de elementos
   */
  content: T[];

  /**
   * Total de elementos
   */
  totalElements: number;
}
