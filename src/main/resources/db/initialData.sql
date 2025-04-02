-- Insertando en la tabla sec.estado
INSERT INTO sec.estado (estado, descripcion) VALUES
('ACT', 'Activo'),
('FIN', 'Finalizado'),
('INA', 'Inactivo'),
('SUS', 'Suspendido'),
('CNC', 'Cancelado');

-- Insertando en la tabla sec.institucion
INSERT INTO sec.institucion (codigo, nombre) VALUES
('SVINST001', 'Universidad Nacional De El Salvador'),
('SVINST002', 'Universidad Tecnológica De El Salvador');

-- Insertando en la tabla sec.sede
INSERT INTO sec.sede (codigo, nombre, direccion, telefono, email, institucion_id) VALUES
('SVSD001', 'Sede Central', 'Av. Principal #123', '2222-3333', 'central@uni.edu', 1),
('SVSD002', 'Sede Norte', 'Calle Secundaria #456', '2333-4444', 'norte@uni.edu', 1),
('SVSD003', 'Sede Este', 'Blvd. Tecnológico #789', '2444-5555', 'este@tec.edu', 2);

-- Insertando en la tabla ema.evento_tipo
INSERT INTO ema.evento_tipo (descripcion) VALUES
('Conferencia'),
('Seminario'),
('Taller');

-- Insertando en la tabla ema.evento
INSERT INTO ema.evento (codigo, titulo, descripcion, descripcion_corta, evento_tipo_id, estado, sede_id) VALUES
('EVT001', 'Innovación Tecnológica', 'Evento sobre las últimas tendencias en tecnología.', 'Tendencias Tech', 1, 'ACT', 1),
('EVT002', 'Gestión de Proyectos', 'Seminario sobre metodologías ágiles y tradicionales.', 'Proyectos Ágiles', 2, 'ACT', 2),
('EVT003', 'Desarrollo Web Moderno', 'Taller práctico sobre frameworks de desarrollo.', 'Frameworks Web', 3, 'INA', 3);
