-- Crear la base de datos
DROP DATABASE IF EXISTS jhocasport;
CREATE DATABASE jhocasport CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE jhocasport;

-- Tabla: categorias
CREATE TABLE categorias (
  ID_Categoria INT NOT NULL AUTO_INCREMENT,
  Nombre_Categoria VARCHAR(50) NOT NULL,
  PRIMARY KEY (ID_Categoria),
  UNIQUE KEY (Nombre_Categoria)
);

INSERT INTO categorias VALUES 
(1, 'Zapatillas deportivas'),
(2, 'Tennis casuales'),
(3, 'Sandalias'),
(4, 'Botas');

-- Tabla: productos
CREATE TABLE productos (
  ID_Producto INT NOT NULL AUTO_INCREMENT,
  Nombre VARCHAR(100) NOT NULL,
  Descripción TEXT,
  Precio DECIMAL(10,2) NOT NULL,
  Stock INT NOT NULL,
  ID_Categoria INT DEFAULT NULL,
  PRIMARY KEY (ID_Producto),
  FOREIGN KEY (ID_Categoria) REFERENCES categorias(ID_Categoria)
);

INSERT INTO productos VALUES 
(1, 'Nike Air Zoom', 'Zapatillas para running', 450000.00, 50, 1),
(2, 'Adidas Superstar', 'Tennis clásicos de cuero', 320000.00, 80, 2),
(3, 'Crocs Classic', 'Sandalias de goma cómodas', 150000.00, 100, 3),
(4, 'Dr. Martens 1460', 'Botas clásicas de cuero', 600000.00, 30, 4);

-- Tabla: clientes
CREATE TABLE clientes (
  ID_Cliente INT NOT NULL AUTO_INCREMENT,
  Nombre VARCHAR(50) NOT NULL,
  Apellido VARCHAR(50) NOT NULL,
  Dirección VARCHAR(150),
  Teléfono VARCHAR(20),
  Email VARCHAR(100),
  PRIMARY KEY (ID_Cliente)
);

INSERT INTO clientes VALUES 
(1, 'Carlos', 'Pérez', 'Calle 123 #45-67', '3111111111', 'carlos@gmail.com'),
(2, 'Ana', 'Gómez', 'Cra 10 #20-30', '3222222222', 'ana@gmail.com'),
(3, 'Luis', 'Rodríguez', 'Av. Siempre Viva #742', '3000000000', 'luis@gmail.com');

-- Tabla: usuarios
CREATE TABLE usuarios (
  ID_Usuario INT NOT NULL AUTO_INCREMENT,
  Nombre_Usuario VARCHAR(50) NOT NULL,
  Contraseña VARCHAR(255) NOT NULL,
  Rol ENUM('admin','vendedor','facturador') NOT NULL,
  PRIMARY KEY (ID_Usuario),
  UNIQUE KEY (Nombre_Usuario)
);

INSERT INTO usuarios VALUES 
(1, 'admin1', 'admin123', 'admin'),
(2, 'vendedor1', 'vendedor123', 'vendedor'),
(3, 'facturador1', 'facturador123', 'facturador');

-- Tabla: ventas
CREATE TABLE ventas (
  ID_Venta INT NOT NULL AUTO_INCREMENT,
  Fecha_Venta DATETIME DEFAULT CURRENT_TIMESTAMP,
  ID_Cliente INT,
  ID_Usuario INT,
  Total DECIMAL(10,2),
  PRIMARY KEY (ID_Venta),
  FOREIGN KEY (ID_Cliente) REFERENCES clientes(ID_Cliente),
  FOREIGN KEY (ID_Usuario) REFERENCES usuarios(ID_Usuario)
);

INSERT INTO ventas VALUES 
(1, '2025-06-20 14:30:00', 1, 2, 560000.00),
(2, '2025-06-21 11:45:00', 2, 2, 960000.00),
(3, '2025-06-22 17:15:00', 3, 2, 750000.00);

-- Tabla: detalles_venta
CREATE TABLE detalles_venta (
  ID_Detalle INT NOT NULL AUTO_INCREMENT,
  ID_Venta INT,
  ID_Producto INT,
  Cantidad INT NOT NULL,
  Precio_Subtotal DECIMAL(10,2) NOT NULL,
  Descuento DECIMAL(10,2) DEFAULT 0.00,
  PRIMARY KEY (ID_Detalle),
  FOREIGN KEY (ID_Venta) REFERENCES ventas(ID_Venta),
  FOREIGN KEY (ID_Producto) REFERENCES productos(ID_Producto)
);

INSERT INTO detalles_venta VALUES 
(1, 1, 1, 1, 450000.00, 0.00),
(2, 1, 4, 1, 600000.00, 50000.00),
(3, 2, 2, 3, 960000.00, 0.00),
(4, 3, 3, 2, 300000.00, 0.00);

-- Tabla: facturas
CREATE TABLE facturas (
  ID_Factura INT NOT NULL AUTO_INCREMENT,
  ID_Venta INT,
  ID_Usuario INT,
  Fecha_Factura DATETIME DEFAULT CURRENT_TIMESTAMP,
  Monto_Total DECIMAL(10,2) NOT NULL,
  Tipo_Pago ENUM('efectivo','tarjeta','transferencia') NOT NULL,
  Estado ENUM('emitida','pagada','anulada') DEFAULT 'emitida',
  PRIMARY KEY (ID_Factura),
  UNIQUE KEY (ID_Venta),
  FOREIGN KEY (ID_Venta) REFERENCES ventas(ID_Venta),
  FOREIGN KEY (ID_Usuario) REFERENCES usuarios(ID_Usuario)
);

INSERT INTO facturas VALUES 
(1, 1, 3, '2025-06-20 14:45:00', 1050000.00, 'efectivo', 'pagada'),
(2, 2, 3, '2025-06-21 12:00:00', 960000.00, 'tarjeta', 'emitida'),
(3, 3, 3, '2025-06-22 17:30:00', 750000.00, 'transferencia', 'pagada');

-- Tabla: movimientos_inventario
CREATE TABLE movimientos_inventario (
  ID_Movimiento INT NOT NULL AUTO_INCREMENT,
  ID_Producto INT,
  Fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
  Cambio INT,
  Motivo VARCHAR(100),
  PRIMARY KEY (ID_Movimiento),
  FOREIGN KEY (ID_Producto) REFERENCES productos(ID_Producto)
);

INSERT INTO movimientos_inventario VALUES 
(1, 1, '2025-06-19 10:00:00', 100, 'Ingreso de lote'),
(2, 1, '2025-06-20 15:00:00', -1, 'Venta'),
(3, 4, '2025-06-20 15:00:00', -1, 'Venta'),
(4, 2, '2025-06-21 12:10:00', -3, 'Venta'),
(5, 3, '2025-06-22 17:45:00', -2, 'Venta');
