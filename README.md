# Sistema de Gestión de Inventario Farmacéutico

## Descripción

Sistema de inventario y venta de medicamentos para droguerías. Este proyecto reemplaza el proceso manual de inventario con una solución digital completa que permite gestionar medicamentos, realizar ventas y mantener un control preciso del stock.

## Funcionalidades

### Gestión de Medicamentos
- **Crear** medicamentos con información completa
- **Consultar** medicamentos con filtros y paginación
- **Actualizar** información de medicamentos
- **Eliminar** medicamentos del inventario

### Sistema de Ventas
- **Venta de medicamentos** con modal de confirmación
- **Cálculo automático** del valor total
- **Actualización automática** del inventario
- **Registro completo** de transacciones

### Consultas y Filtros
- **Filtrado de medicamentos** con tabla paginada
- **Filtrado de ventas** por rango de fechas
- **Búsqueda avanzada** en inventario

### Configuración de Base de Datos
1. Ejecutar el script `Oracle Script.sql` en Oracle Database
2. Configurar las credenciales de conexión en los archivos `application.yml`

```sql
-- Ejecutar el script Oracle Script.sql en Oracle Database
@Oracle Script.sql
```

### Acceder a la Aplicación
- **Frontend**: http://localhost:4200
- **API Gateway**: http://localhost:8080

## Uso del Sistema

### Gestión de Medicamentos
1. **Crear Medicamento**: Hacer clic en "Nuevo Medicamento" y completar el formulario
2. **Editar Medicamento**: Hacer clic en el botón "Editar" en la fila correspondiente
3. **Eliminar Medicamento**: Hacer clic en el botón "Eliminar" y confirmar
4. **Filtrar Medicamentos**: Usar los filtros disponibles en la tabla

### Realizar Ventas
1. **Seleccionar Medicamento**: En la tabla de medicamentos, hacer clic en "Vender"
2. **Especificar Cantidad**: En el modal, ingresar la cantidad a vender
3. **Confirmar Venta**: Revisar el total y confirmar la transacción
4. **Verificar Inventario**: El stock se actualiza automáticamente

### Consultar Ventas
1. **Acceder a Ventas**: Navegar a la sección de ventas
2. **Filtrar por Fechas**: Seleccionar rango de fechas para filtrar
3. **Ver Detalles**: Revisar información completa de cada venta

## Tecnologías Utilizadas

### Backend
- **Spring Boot 3.5.5**: Framework principal
- **Spring Data JPA**: Persistencia de datos
- **Spring Cloud Gateway**: API Gateway
- **Lombok**: Reducción de código boilerplate
- **Oracle JDBC**: Conexión a base de datos
- **Maven**: Gestión de dependencias

### Frontend
- **Angular 20.1.0**: Framework de frontend
- **PrimeNG 20.0.1**: Componentes de UI
- **PrimeIcons**: Iconografía
- **RxJS**: Programación reactiva
- **TypeScript**: Lenguaje de programación
- **Angular Forms**: Gestión de formularios

### Base de Datos
- **Oracle Database**: Sistema de gestión de base de datos