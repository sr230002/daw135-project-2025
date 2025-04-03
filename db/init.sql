create schema sec;
create schema ema;

--grant usage, create on schema sec to neondb_owner;
--grant usage, create on schema ema to neondb_owner;

create table sec.estado(
    estado varchar(5)  primary key,
    descripcion varchar(200) not null  
);

create table sec.rol(
    rol_id bigserial primary key,
    codigo varchar(50) not null,
    descripcion varchar(200) not null,
    estado varchar(5) not null,
    constraint sec_rol_fk_estado foreign key (estado) references sec.estado(estado)
);

create table sec.institucion(
	institucion_id bigserial primary key,
    codigo varchar(50) not null,
    nombre varchar(100)
);

create table sec.sede(
	sede_id bigserial primary key,
    codigo varchar(50) not null,
    nombre varchar(100),
    direccion varchar(100),
    telefono varchar(100),
    email varchar(100),
    institucion_id bigint not null,
    constraint sec_sede_fk_institucion foreign key (institucion_id) references sec.institucion(institucion_id)
);

create table sec.usuario(
	usuario_id bigserial primary key,
    nombre varchar(100),
    correo varchar(100) unique,
    clave varchar(500),
    fecha_creacion timestamp default current_timestamp,
    estado varchar(5),
    rol_id bigint not null,
    sede_id bigint not null,
    constraint sec_user_fk_rol foreign key (rol_id) references sec.rol(rol_id),
    constraint sec_user_fk_estado foreign key (estado) references sec.estado(estado),
    constraint sec_user_fk_sede foreign key (sede_id) references sec.sede(sede_id)
);

create table ema.evento_tipo(
	evento_tipo_id bigserial primary key,
	descripcion varchar(250) not null
);

create table ema.evento(
	evento_id bigserial primary key, 
	codigo varchar(50) not null,
	fecha_creacion timestamp default current_timestamp,
	titulo varchar(250) not null,
	descripcion varchar(500) not null,
	descripcion_corta varchar(250) ,
	evento_tipo_id bigint not null,
	estado varchar(5) not null,
    sede_id bigint not null,
	constraint ema_evento_fk_estado foreign key (estado) references sec.estado(estado),
    constraint ema_evento_fk_evento_tipo foreign key (evento_tipo_id) references ema.evento_tipo(evento_tipo_id),
    constraint ema_evento_fk_sede foreign key (sede_id) references sec.sede(sede_id)
);

create table ema.evento_programacion(
	evento_programacion_id bigserial primary key,
    fecha_creacion timestamp default current_timestamp,
    fecha_programacion date not null,
    hora_inicio time not null,
    hora_fin time not null,
    virtual boolean default true,
    cupos int null,
    lugar varchar(250) null,
    enlace varchar(250) null,
    evento_id bigint not null,
    ponente_id bigint not null,
    constraint ema_evento_programacion_fk_evento foreign key (evento_id) references ema.evento(evento_id),
    constraint ema_evento_programacion_fk_usuario foreign key (ponente_id) references sec.usuario(usuario_id)
);
 

create table ema.evento_registro(
	evento_registro_id bigserial primary key,
    fecha_creacion timestamp default current_timestamp,
    evento_programacion_id bigint not null,
    participante_id bigint not null,
    estado varchar(5) not null,
    constraint ema_evento_registro_fk_evento_programacion foreign key (evento_programacion_id) references ema.evento_programacion(evento_programacion_id),
    constraint ema_evento_registro_fk_usuario foreign key (participante_id) references sec.usuario(usuario_id),
    constraint ema_evento_registro_fk_estado foreign key (estado) references sec.estado(estado)
);


create table ema.encuesta(
	encuesta_id bigserial primary key,
    fecha_creacion timestamp default current_timestamp,
    titulo varchar(250) not null,
	descripcion varchar(500) not null,
	estado varchar(5) not null,
    evento_programacion_id bigint not null,
    constraint ema_encuesta_fk_estado foreign key (estado) references sec.estado(estado),
    constraint ema_encuesta_fk_evento_programacion foreign key (evento_programacion_id) references ema.evento_programacion(evento_programacion_id)
);


/******************************************************************************
Cargando Datos Inciales
******************************************************************************/

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
/*
INSERT INTO ema.evento (codigo, titulo, descripcion, descripcion_corta, evento_tipo_id, estado, sede_id) VALUES
('EVT001', 'Innovación Tecnológica', 'Evento sobre las últimas tendencias en tecnología.', 'Tendencias Tech', 1, 'ACT', 1),
('EVT002', 'Gestión de Proyectos', 'Seminario sobre metodologías ágiles y tradicionales.', 'Proyectos Ágiles', 2, 'ACT', 2),
('EVT003', 'Desarrollo Web Moderno', 'Taller práctico sobre frameworks de desarrollo.', 'Frameworks Web', 3, 'INA', 3);
*/

-- Insertando en la tabla sec.rol
insert into sec.rol(codigo , descripcion , estado ) values
('ADM', 'Admin', 'ACT'),
('PNT', 'Ponente', 'ACT'),
('PTP', 'Participante', 'ACT')
;

-- Insertando en la tabla sec.usuario
INSERT INTO sec.usuario (nombre, correo, clave, estado, rol_id, sede_id) VALUES
('Juan Pérez', 'juan.perez@email.com', 'clave_encriptada_1', 'ACT', 3, 1),
('María López', 'maria.lopez@email.com', 'clave_encriptada_2', 'ACT', 3, 1),
('Carlos Gómez', 'carlos.gomez@email.com', 'clave_encriptada_3', 'ACT', 3, 1),
('Ana Ramírez', 'ana.ramirez@email.com', 'clave_encriptada_4', 'ACT', 3, 1),
('Pedro Castillo', 'pedro.castillo@email.com', 'clave_encriptada_5', 'ACT', 3, 1),
('Laura Méndez', 'laura.mendez@email.com', 'clave_encriptada_6', 'ACT', 3, 1),
('Luis Fernández', 'luis.fernandez@email.com', 'clave_encriptada_7', 'ACT', 3, 1);

-- Insertando en la tabla ema.evento_programacion
/*
INSERT INTO ema.evento_programacion (fecha_programacion, hora_inicio, hora_fin, virtual, cupos, lugar, enlace, evento_id, ponente_id) VALUES
('2024-04-10', '10:00:00', '12:00:00', TRUE, 50, NULL, 'https://zoom.us/evento1', 3, 1),
('2024-04-11', '14:00:00', '16:00:00', FALSE, 30, 'Sala 101', NULL, 3, 1),
('2024-04-12', '09:00:00', '11:00:00', TRUE, 100, NULL, 'https://meet.google.com/evento2', 3, 1),
('2024-04-13', '13:00:00', '15:00:00', FALSE, 40, 'Auditorio Principal', NULL, 3, 1),
('2024-04-14', '08:00:00', '10:00:00', TRUE, 80, NULL, 'https://zoom.us/evento3', 3, 1),
('2024-04-15', '17:00:00', '19:00:00', FALSE, 25, 'Sala 202', NULL, 3, 1),
('2024-04-16', '11:00:00', '13:00:00', TRUE, 70, NULL, 'https://meet.google.com/evento4', 3, 1),
('2024-04-17', '15:00:00', '17:00:00', FALSE, 35, 'Sala de Conferencias', NULL, 3, 1),
('2024-04-18', '09:30:00', '11:30:00', TRUE, 90, NULL, 'https://zoom.us/evento5', 3, 1),
('2024-04-19', '14:30:00', '16:30:00', FALSE, 20, 'Biblioteca', NULL, 3, 1);
*/

