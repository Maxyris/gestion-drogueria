export interface IVenta {
  /**
   * Identificador de la venta
   */
  id?: number;

  /**
   * Fecha y hora de la venta
   */
  fechaHora: Date;

  /**
   * Identificador del medicamento
   */
  idMedicamento: number;

  /**
   * Cantidad de medicamentos vendidos
   */
  cantidadVenta: number;

  /**
   * Valor total de la venta
   */
  valorTotal: number;

  /**
   * Valor unitario del medicamento
   */
  valorUnitario: number;
}

export interface IObtenerVentasResponse extends IVenta {
  /**
   * Nombre del medicamento
   */
  nombreMedicamento: string;
}
