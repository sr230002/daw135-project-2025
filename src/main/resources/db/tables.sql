create schema sec;
create schema ema;

grant usage, create on schema sec to neondb_owner;
grant usage, create on schema ema to neondb_owner;


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

create table ema.tipo_evento(
	tipo_evento_id bigserial primary key,
	descripcion varchar(250) not null,
	estado varchar(5) not null,
	constraint ema_tipo_evento_fk_estado foreign key (estado) references sec.estado(estado)
);

create table ema.evento(
	evento_id bigserial primary key, 
	codigo varchar(50) not null,
	fecha_creacion timestamp default current_timestamp,
	titulo varchar(250) not null,
	descripcion varchar(500) not null,
	descripcion_corta varchar(250) ,
	tipo_evento_id bigint not null,
	estado varchar(5) not null,
    sede_id bigint not null,
	constraint ema_evento_fk_estado foreign key (estado) references sec.estado(estado),
    constraint ema_evento_fk_tipo_evento foreign key (tipo_evento_id) references ema.tipo_evento(tipo_evento_id),
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

