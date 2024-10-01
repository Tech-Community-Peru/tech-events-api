INSERT INTO ponente (nombre, apellido, cargo, pais_origen, especialidad) VALUES
                                                                             ('Juan', 'Pérez', 'Ingeniero de Software', 'Perú', 'Desarrollo de Backend'),
                                                                             ('María', 'García', 'Arquitecta de Sistemas', 'Chile', 'Arquitectura de Software'),
                                                                             ('Carlos', 'López', 'Analista de Datos', 'Argentina', 'Análisis de Datos');


INSERT INTO roles (nombre, rol) VALUES
                                                ('Member', 'miembro'),
                                                ('Admin', 'administrador');

INSERT INTO usuario (correo_electronico, contrasenia,roles_id) VALUES
                                                                   ('jose.ramirez@gmail.com', 'password123',1),
                                                                   ('claudia.vasquez@gmail.com', 'password456',2),
                                                                   ('luis.martinez@gmail.com', 'password789',1),
                                                                   ('maria.gomez@gmail.com', 'passwordabc',2),
                                                                   ('pedro.salazar@gmail.com', 'passworddef',2),
                                                                   ('lucia.fernandez@gmail.com', 'passwordghi',2),
                                                                   ('andres.lopez@gmail.com', 'passwordjkl',1),
                                                                   ('sofia.rojas@gmail.com', 'passwordmno',1);




INSERT INTO participante (nombre, apellido, habilidades, linkedin, informacion_adicional, ubicacion, pais_origen, edad, usuario_id) VALUES
                                                                                                                                       ('José', 'Ramírez', 'Ciberseguridad', 'linkedin.com/jose-ramirez', 'Ingeniero en seguridad informática', 'Lima, Perú', 'Perú', 30, 1),
                                                                                                                                       ('Claudia', 'Vásquez', 'Big Data', 'linkedin.com/claudia-vasquez', 'Especialista en análisis de datos', 'CDMX, México', 'México', 28, 2),
                                                                                                                                       ('Luis', 'Martínez', 'Machine Learning', 'linkedin.com/luis-martinez', 'Desarrollador de IA', 'Madrid, España', 'España', 35, 3),
                                                                                                                                       ('María', 'Gómez', 'Blockchain', 'linkedin.com/maria-gomez', 'Consultora de blockchain', 'Buenos Aires, Argentina', 'Argentina', 32, 4),
                                                                                                                                       ('Pedro', 'Salazar', 'DevOps', 'linkedin.com/pedro-salazar', 'Arquitecto de sistemas', 'Santiago, Chile', 'Chile', 40, 5),
                                                                                                                                       ('Lucía', 'Fernández', 'Inteligencia Artificial', 'linkedin.com/lucia-fernandez', 'Desarrolladora IA', 'Bogotá, Colombia', 'Colombia', 29, 6),
                                                                                                                                       ('Andrés', 'López', 'Innovación Tecnológica', 'linkedin.com/andres-lopez', 'Especialista en innovación', 'Quito, Ecuador', 'Ecuador', 38, 7),
                                                                                                                                       ('Sofía', 'Rojas', 'Cloud Computing', 'linkedin.com/sofia-rojas', 'Experta en tecnologías Cloud', 'Montevideo, Uruguay', 'Uruguay', 33, 8);


INSERT INTO comunidad (nombre, descripcion, fecha_creacion, cantidad_miembros, tematica_principal) VALUES
                                                                                                       ('Ciberseguridad Perú', 'Comunidad de expertos en ciberseguridad', '2024-01-10', 150, 'Ciberseguridad'),
                                                                                                       ('Big Data México', 'Comunidad dedicada al análisis de datos masivos', '2024-01-15', 200, 'Big Data'),
                                                                                                       ('Blockchain España', 'Comunidad sobre tecnología blockchain', '2024-01-20', 120, 'Blockchain'),
                                                                                                       ('IA Argentina', 'Comunidad de Inteligencia Artificial', '2024-01-25', 180, 'Inteligencia Artificial'),
                                                                                                       ('Cloud Chile', 'Comunidad dedicada a tecnologías Cloud', '2024-01-30', 100, 'Cloud Computing'),
                                                                                                       ('DevOps Colombia', 'Comunidad especializada en DevOps', '2024-02-05', 130, 'DevOps'),
                                                                                                       ('Innovación Ecuador', 'Comunidad enfocada en la innovación tecnológica', '2024-02-10', 90, 'Innovación'),
                                                                                                       ('Machine Learning Uruguay', 'Comunidad sobre aplicaciones de Machine Learning', '2024-02-15', 140, 'Machine Learning');


INSERT INTO ubicacion (nombre_lugar, direccion, ciudad, pais, descripcion) VALUES
                                                                              ('Auditorio Lima', 'Av. Principal 123', 'Lima', 'Perú', 'Auditorio principal del evento'),
                                                                              ('Centro de Convenciones CDMX', 'Calle 456', 'Ciudad de México', 'México', 'Centro de convenciones internacional'),
                                                                              ('Auditorio Madrid', 'Plaza Mayor 789', 'Madrid', 'España', 'Auditorio para grandes eventos'),
                                                                              ('Hotel Hilton Buenos Aires', 'Calle Corrientes 111', 'Buenos Aires', 'Argentina', 'Salón de eventos del hotel'),
                                                                              ('Centro de Innovación Santiago', 'Calle Innovación 222', 'Santiago', 'Chile', 'Centro de innovación tecnológica'),
                                                                              ('Conferencia Bogotá', 'Av. Las Américas 333', 'Bogotá', 'Colombia', 'Centro de eventos empresariales'),
                                                                              ('Centro Quito', 'Calle Libertad 444', 'Quito', 'Ecuador', 'Centro empresarial para conferencias'),
                                                                              ('Auditorio Montevideo', 'Calle Central 555', 'Montevideo', 'Uruguay', 'Auditorio para eventos corporativos');


-- Datos de prueba para la tabla evento
INSERT INTO evento (nombre, costo, descripcion, categoria_evento, tipo_evento, ponente_id, comunidad_id, ubicacion_id) VALUES
                                                                                                                           ('Evento Ciberseguridad', 100.00, 'Evento sobre seguridad informática', 'CIBERSEGURIDAD', 'PRESENCIAL', 1, 1, 1),
                                                                                                                           ('Evento Big Data', 150.00, 'Análisis de datos masivos', 'BIG_DATA', 'VIRTUAL', 2, 2, 2),
                                                                                                                           ('Evento Blockchain', 200.00, 'Tecnología blockchain aplicada', 'BLOCKCHAIN', 'PRESENCIAL', 3, 3, 3),
                                                                                                                           ('Evento IA', 120.00, 'Inteligencia artificial en el futuro', 'INTELIGENCIA_ARTIFICIAL', 'VIRTUAL', 1, 4, 4),
                                                                                                                           ('Evento Cloud', 130.00, 'Tecnologías Cloud y su impacto', 'CLOUD', 'PRESENCIAL', 1, 5, 5),
                                                                                                                           ('Evento DevOps', 110.00, 'DevOps en la práctica', 'DEVOPS', 'VIRTUAL', 2, 6, 6),
                                                                                                                           ('Evento Innovación', 180.00, 'Innovación tecnológica disruptiva', 'INNOVACION', 'PRESENCIAL', 3, 7, 7),
                                                                                                                           ('Evento Machine Learning', 140.00, 'Aplicaciones de Machine Learning', 'MACHINE_LEARNING', 'VIRTUAL', 3, 8, 8);




INSERT INTO comentario (fecha_publicacion, comentario, evento_id, usuario_id, comunidad_id) VALUES
                                                                                                ('2024-01-11 12:00:00', 'Este evento fue muy informativo.', 1, 1, 1),
                                                                                                ('2024-01-16 14:30:00', 'Me gustó la parte de análisis de datos.', 2, 2, 2),
                                                                                                ('2024-01-21 11:45:00', 'Buena organización en general.', 3, 3, 3),
                                                                                                ('2024-01-26 10:20:00', 'La charla de IA fue muy interesante.', 4, 4, 4),
                                                                                                ('2024-01-31 13:10:00', 'Gran conferencia sobre Cloud Computing.', 5, 5, 5),
                                                                                                ('2024-02-06 15:00:00', 'La sesión de DevOps fue muy útil.', 6, 6, 6),
                                                                                                ('2024-02-11 16:45:00', 'Aprendí mucho sobre Innovación Tecnológica.', 7, 7, 7),
                                                                                                ('2024-02-16 17:00:00', 'Los ejemplos prácticos fueron excelentes.', 8, 8, 8);




INSERT INTO cronograma (fecha_inicio, fecha_fin, evento_id) VALUES
                                                              ('2024-01-10 09:00:00', '2024-01-10 18:00:00', 1),
                                                              ('2024-01-15 09:00:00', '2024-01-15 18:00:00', 2),
                                                              ('2024-01-20 09:00:00', '2024-01-20 18:00:00', 3),
                                                              ('2024-01-25 09:00:00', '2024-01-25 18:00:00', 4),
                                                              ('2024-01-30 09:00:00', '2024-01-30 18:00:00', 5),
                                                              ('2024-02-05 09:00:00', '2024-02-05 18:00:00', 6),
                                                              ('2024-02-10 09:00:00', '2024-02-10 18:00:00', 7),
                                                              ('2024-02-15 09:00:00', '2024-02-15 18:00:00', 8);



INSERT INTO Usuario_Comunidad (usuario_id, comunidad_id, fecha_unido) VALUES
                                                                          (1, 1, '2024-01-11 10:30:00'),
                                                                          (2, 2, '2024-01-16 09:45:00'),
                                                                          (3, 3, '2024-01-21 14:15:00'),
                                                                          (4, 4, '2024-01-26 16:20:00'),
                                                                          (5, 5, '2024-01-31 11:50:00'),
                                                                          (6, 6, '2024-02-06 08:10:00'),
                                                                          (7, 7, '2024-02-11 12:30:00'),
                                                                          (8, 8, '2024-02-16 17:00:00');


INSERT INTO inscripcion (monto_pago, tipo_pago, estado_inscripcion, usuario_id, evento_id) VALUES
                                                                                         (100.00, 'CREDIT_CARD', 'PAID', 1, 1),
                                                                                         (150.00, 'DEBIT_CARD', 'PAID', 2, 2),
                                                                                         (200.00, 'CREDIT_CARD', 'PENDING', 3, 3),
                                                                                         (120.00, 'DEBIT_CARD', 'PAID', 4, 4),
                                                                                         (130.00, 'CREDIT_CARD', 'PAID', 5, 5),
                                                                                         (110.00, 'DEBIT_CARD', 'PAID', 6, 6),
                                                                                         (180.00, 'CREDIT_CARD', 'PAID', 7, 7),
                                                                                         (140.00, 'CREDIT_CARD', 'PENDING', 8, 8);


INSERT INTO sorteo (evento_id, descripcion, fecha_sorteo) VALUES
                                                                          (1, 'Sorteo de 1 entrada para el próximo evento de ciberseguridad', '2024-01-12'),
                                                                          (2, 'Sorteo de un curso de Big Data', '2024-01-17'),
                                                                          (3, 'Sorteo de un libro sobre Blockchain', '2024-01-22'),
                                                                          (4, 'Sorteo de una suscripción a un curso de IA', '2024-01-27'),
                                                                          (5, 'Sorteo de una entrada al evento de Cloud Computing', '2024-02-01'),
                                                                          (6, 'Sorteo de un paquete de herramientas DevOps', '2024-02-07'),
                                                                          (7, 'Sorteo de un curso de innovación tecnológica', '2024-02-12'),
                                                                          (8, 'Sorteo de un curso avanzado de Machine Learning', '2024-02-17');

INSERT INTO ganador ( sorteo_id, participante_id) VALUES
                                                              (1,1),
                                                              (2,2),
                                                              (3,3),
                                                              (4,4),
                                                              (5,5),
                                                              (6,6),
                                                              (7,7),
                                                              (8,8);
