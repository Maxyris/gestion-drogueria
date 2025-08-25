export interface IMedicamento {
  /**
   * Identificador del medicamento
   */
  id?: number;

  /**
   * Nombre del medicamento
   */
  nombre: string;

  /**
   * Laboratorio del medicamento
   */
  laboratorio: string;

  /**
   * Fecha de fabricación del medicamento
   */
  fechaFabricacion: Date;

  /**
   * Fecha de vencimiento del medicamento
   */
  fechaVencimiento: Date;

  /**
   * Cantidad de stock del medicamento
   */
  cantidadStock: number;

  /**
   * Valor unitario del medicamento
   */
  valorUnitario: number;

  /**
   * Fecha de creación del medicamento
   */
  fechaCreacion: Date;

  /**
   * Fecha de actualización del medicamento
   */
  fechaActualizacion: Date;
}
