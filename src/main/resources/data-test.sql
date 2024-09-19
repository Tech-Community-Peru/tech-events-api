-- Datos de prueba para la tabla ponente
INSERT INTO ponente (nombre, apellido, cargo, pais_origen, especialidad) VALUES
                                                                             ('Juan', 'Pérez', 'Ingeniero de Software', 'Perú', 'Desarrollo de Backend'),
                                                                             ('María', 'García', 'Arquitecta de Sistemas', 'Chile', 'Arquitectura de Software'),
                                                                             ('Carlos', 'López', 'Analista de Datos', 'Argentina', 'Análisis de Datos');

-- Datos de prueba para la tabla usuario
INSERT INTO usuario (nombre, apellido, correo_electronico, contrasenia, habilidades, linkedin, informacion_adicional, ubicacion, pais_origen, edad) VALUES
                                                                                                                                                        ('Juan', 'Pérez', 'juan.perez@example.com', 'password123', 'Java, Spring Boot', 'https://linkedin.com/in/juanperez', 'Desarrollador Backend', 'Lima', 'Perú', 30),
                                                                                                                                                        ('María', 'García', 'maria.garcia@example.com', 'password456', 'Python, Django', 'https://linkedin.com/in/mariagarcia', 'Arquitecta de Software', 'Santiago', 'Chile', 28),
                                                                                                                                                        ('Carlos', 'López', 'carlos.lopez@example.com', 'password789', 'SQL, Data Analysis', 'https://linkedin.com/in/carloslopez', 'Analista de Datos', 'Buenos Aires', 'Argentina', 35);

-- Datos de prueba para la tabla comunidad
INSERT INTO comunidad (nombre, descripcion, fecha_creacion, cantidad_miembros, tematica_principal) VALUES
                                                                                                       ('Comunidad de Desarrollo Web', 'Un espacio para compartir conocimientos sobre desarrollo web.', '2023-01-10 10:30:00', 150, 'Desarrollo Web'),
                                                                                                       ('Comunidad de Inteligencia Artificial', 'Discusiones y proyectos sobre IA y Machine Learning.', '2023-03-20 15:45:00', 230, 'Inteligencia Artificial'),
                                                                                                       ('Comunidad de Seguridad Informática', 'Compartimos tips y buenas prácticas de ciberseguridad.', '2022-11-05 09:00:00', 180, 'Ciberseguridad');


-- Datos de prueba para la tabla evento
INSERT INTO evento (nombre, costo, descripcion, categoria_evento,tipo_evento,ponente_id,comunidad_id) VALUES
                                                                                                          ('Conferencia IA 2024', 150.00, 'Una conferencia sobre el futuro de la inteligencia artificial.','GESTIONTI', 'VIRTUAL', 1,1),
                                                                                                          ('Taller de UX/UI', 100.00, 'Aprende los fundamentos del diseño UX/UI.','GESTIONTI','VIRTUAL', 2,2),
                                                                                                          ('Hackathon de Ciberseguridad', 200.00, 'Evento competitivo enfocado en ciberseguridad.','GESTIONTI', 'PRESENCIAL',3,3);

-- Datos de prueba para la tabla ubicacion
INSERT INTO ubicacion (nombre_lugar, direccion, ciudad, pais, descripcion, evento_id) VALUES
                                                                                          ('Auditorio Principal', 'Av. Principal 123, Edificio A', 'Lima', 'Perú', 'Auditorio amplio con capacidad para 500 personas.', 1),
                                                                                          ('Sala de Conferencias Norte', 'Calle Los Robles 456, Piso 2', 'Bogotá', 'Colombia', 'Sala equipada con proyector y sonido envolvente.', 2),
                                                                                          ('Centro de Convenciones', 'Carretera Panamericana Norte km 50', 'Santiago', 'Chile', 'Espacio ideal para conferencias y exposiciones de gran tamaño.', 3);


-- Datos de prueba para la tabla comentario
INSERT INTO comentario (fecha_publicacion, comentario, evento_id, usuario_id) VALUES
                                                                                  ('2024-09-15', '¡Excelente evento, aprendí mucho!', 1, 1),
                                                                                  ('2024-09-15', 'El ponente fue muy claro y directo.', 2, 2),
                                                                                  ('2024-09-15', 'Me gustaría que se extendiera más el tiempo.', 3, 3);



-- Datos de prueba para la tabla fecha
INSERT INTO cronograma (fecha_inicio, fecha_fin, evento_id) VALUES
                                                                ('2024-09-15 08:00:00', '2024-09-15 10:00:00', 1),
                                                                ('2024-09-16 09:00:00', '2024-09-16 11:00:00', 2),
                                                                ('2024-09-17 14:00:00', '2024-09-17 16:00:00', 3);



-- Datos de prueba para la tabla usuario_comunidad
INSERT INTO usuario_comunidad (usuario_id, comunidad_id, fecha_unido, rol) VALUES
                                                                               (1, 1, '2024-09-15 08:00:00', 'miembro'),
                                                                               (2, 1, '2024-09-16 09:00:00', 'aministrador'),
                                                                               (3, 2, '2024-09-17 14:00:00', 'aministrador');

-- Datos de prueba para la tabla usuario_evento
INSERT INTO inscripción(tipo_pago,monto_pago,estado_inscripcion, usuario_id, evento_id) VALUES
                                                                                            ('CREDIT_CARD',120.50,'PENDING', 1, 1),
                                                                                            ('CREDIT_CARD',180,'PAID', 2, 2),
                                                                                            ('DEBIT_CARD',200,'PENDING', 3, 3);