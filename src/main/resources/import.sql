
INSERT INTO profesional(apellido,email,nombre,telefono) VALUES ("Booth, Uma G.","lectus@mauriselit.edu","Allen","1-883-183-551");
INSERT INTO profesional(apellido,email,nombre,telefono) VALUES ("Wall, Nathan C.","egestas@utodiovel.co.uk","Stone","1-542-994-189");
INSERT INTO profesional(apellido,email,nombre,telefono) VALUES ("Dalton, Deanna U.","vitae@tincidunt.net","Luke","1-358-484-158");
INSERT INTO profesional(apellido,email,nombre,telefono) VALUES ("Matthews, Cade A.","rutrultrices@ac.net","Keefe","1-186-661-529");
INSERT INTO profesional(apellido,email,nombre,telefono) VALUES ("Conway, Farrah W.","et.lacinia@etla.co.uk","Wynter","1-487-309-592");
INSERT INTO profesional(apellido,email,nombre,telefono) VALUES ("Gilliam, Kirby O.","facilorci@vitaeali.org","Kirby","1-720-484-375");
INSERT INTO profesional(apellido,email,nombre,telefono) VALUES ("Holt, Kane Y.","urna@magna","Roth","1-668-825-872");
INSERT INTO profesional(apellido,email,nombre,telefono) VALUES ("Guzman, Adria M.","porta.eli@augueport.edu","Jerry","1-779-591-256");
INSERT INTO profesional(apellido,email,nombre,telefono) VALUES ("Harvey, Thor B.","enim@aultricies.ca","Bevis","1-819-606-998");
INSERT INTO profesional(apellido,email,nombre,telefono) VALUES ("Cantrell, Baker O.","Vivamus@Crasint.ca","Debra","1-521-178-719");

INSERT INTO `estado_bloque`(`descripcion`, `nombre`) VALUES ("el estado se encuentro disponible para su uso","habilitado");
INSERT INTO `estado_bloque`(`descripcion`, `nombre`) VALUES ("el estado se encuentro suspendido para su uso","desabilitado");

INSERT INTO `estado_cliente`(`descripcion`, `nombre`) VALUES ("el estado se encuentro disponible para su uso","habilitado");
INSERT INTO `estado_cliente`(`descripcion`, `nombre`) VALUES ("el estado se encuentro suspendido para su uso","desabilitado");

INSERT INTO `estado_horario_profesional`(`descripcion`, `nombre`) VALUES ("el estado se encuentro disponible para su uso","habilitado");
INSERT INTO `estado_horario_profesional`(`descripcion`, `nombre`) VALUES ("el estado se encuentro suspendido para su uso","desabilitado");

INSERT INTO `estado_movimiento`(`descripcion`, `nombre`) VALUES ("el estado se encuentro disponible para su uso","habilitado");
INSERT INTO `estado_movimiento`(`descripcion`, `nombre`) VALUES ("el estado se encuentro suspendido para su uso","desabilitado");

INSERT INTO `estado_profesional`(`descripcion`, `nombre`) VALUES ("el estado se encuentro disponible para su uso","habilitado");
INSERT INTO `estado_profesional`(`descripcion`, `nombre`) VALUES ("el estado se encuentro suspendido para su uso","desabilitado");

INSERT INTO `estado_reserva`(`descripcion`, `nombre`) VALUES ("el estado se encuentro disponible para su uso","habilitado");
INSERT INTO `estado_reserva`(`descripcion`, `nombre`) VALUES ("el estado se encuentro suspendido para su uso","desabilitado");

INSERT INTO `estado_servicio`(`descripcion`, `nombre`) VALUES ("el estado se encuentro disponible para su uso","habilitado");
INSERT INTO `estado_servicio`(`descripcion`, `nombre`) VALUES ("el estado se encuentro suspendido para su uso","desabilitado");

INSERT INTO `estado_servicio_ofrecido`(`descripcion`, `nombre`) VALUES ("el estado se encuentro disponible para su uso","habilitado");
INSERT INTO `estado_servicio_ofrecido`(`descripcion`, `nombre`) VALUES ("el estado se encuentro suspendido para su uso","desabilitado");

INSERT INTO `bloque_horario`( `dia_semana`, `hora_fin`, `hora_inicio`, `id_estado_bloque`) VALUES ("1","8:10","8:00",1)
INSERT INTO `bloque_horario`( `dia_semana`, `hora_fin`, `hora_inicio`, `id_estado_bloque`) VALUES ("1","8:20","8:10",1)
INSERT INTO `bloque_horario`( `dia_semana`, `hora_fin`, `hora_inicio`, `id_estado_bloque`) VALUES ("1","8:30","8:20",1)
INSERT INTO `bloque_horario`( `dia_semana`, `hora_fin`, `hora_inicio`, `id_estado_bloque`) VALUES ("1","8:40","8:30",1)
INSERT INTO `bloque_horario`( `dia_semana`, `hora_fin`, `hora_inicio`, `id_estado_bloque`) VALUES ("1","8:50","8:40",1)
INSERT INTO `bloque_horario`( `dia_semana`, `hora_fin`, `hora_inicio`, `id_estado_bloque`) VALUES ("1","9:00","8:50",1)
INSERT INTO `bloque_horario`( `dia_semana`, `hora_fin`, `hora_inicio`, `id_estado_bloque`) VALUES ("1","9:10","9:00",1)
INSERT INTO `bloque_horario`( `dia_semana`, `hora_fin`, `hora_inicio`, `id_estado_bloque`) VALUES ("1","9:20","9:10",1)
INSERT INTO `bloque_horario`( `dia_semana`, `hora_fin`, `hora_inicio`, `id_estado_bloque`) VALUES ("1","9:30","9:20",1)
INSERT INTO `bloque_horario`( `dia_semana`, `hora_fin`, `hora_inicio`, `id_estado_bloque`) VALUES ("1","9:40","9:30",1)
INSERT INTO `bloque_horario`( `dia_semana`, `hora_fin`, `hora_inicio`, `id_estado_bloque`) VALUES ("1","9:50","9:40",1)
INSERT INTO `bloque_horario`( `dia_semana`, `hora_fin`, `hora_inicio`, `id_estado_bloque`) VALUES ("1","10:00","9:50",1)
INSERT INTO `bloque_horario`( `dia_semana`, `hora_fin`, `hora_inicio`, `id_estado_bloque`) VALUES ("1","10:10","10:00",1)
INSERT INTO `bloque_horario`( `dia_semana`, `hora_fin`, `hora_inicio`, `id_estado_bloque`) VALUES ("1","10:20","10:10",1)
INSERT INTO `bloque_horario`( `dia_semana`, `hora_fin`, `hora_inicio`, `id_estado_bloque`) VALUES ("1","10:30","10:20",1)
INSERT INTO `bloque_horario`( `dia_semana`, `hora_fin`, `hora_inicio`, `id_estado_bloque`) VALUES ("1","10:40","10:30",1)
INSERT INTO `bloque_horario`( `dia_semana`, `hora_fin`, `hora_inicio`, `id_estado_bloque`) VALUES ("1","10:50","10:40",1)
INSERT INTO `bloque_horario`( `dia_semana`, `hora_fin`, `hora_inicio`, `id_estado_bloque`) VALUES ("1","11:00","10:50",1)
INSERT INTO `bloque_horario`( `dia_semana`, `hora_fin`, `hora_inicio`, `id_estado_bloque`) VALUES ("1","11:10","11:00",1)
INSERT INTO `bloque_horario`( `dia_semana`, `hora_fin`, `hora_inicio`, `id_estado_bloque`) VALUES ("1","11:20","11:10",1)
INSERT INTO `bloque_horario`( `dia_semana`, `hora_fin`, `hora_inicio`, `id_estado_bloque`) VALUES ("1","11:30","11:20",1)
INSERT INTO `bloque_horario`( `dia_semana`, `hora_fin`, `hora_inicio`, `id_estado_bloque`) VALUES ("1","11:40","11:30",1)
INSERT INTO `bloque_horario`( `dia_semana`, `hora_fin`, `hora_inicio`, `id_estado_bloque`) VALUES ("1","11:50","11:40",1)
INSERT INTO `bloque_horario`( `dia_semana`, `hora_fin`, `hora_inicio`, `id_estado_bloque`) VALUES ("1","12:00","11:50",1)


