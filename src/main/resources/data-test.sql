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
INSERT INTO evento (nombre, costo, descripcion, calificacion, comentario, ponente_id) VALUES
                                                                                          ('Conferencia IA 2024', 150.00, 'Una conferencia sobre el futuro de la inteligencia artificial.', 5, 'Muy interesante.', 1),
                                                                                          ('Taller de UX/UI', 100.00, 'Aprende los fundamentos del diseño UX/UI.', 4, 'Muy útil para diseñadores.', 2),
                                                                                          ('Hackathon de Ciberseguridad', 200.00, 'Evento competitivo enfocado en ciberseguridad.', 5, 'Experiencia retadora.', 3);

-- Datos de prueba para la tabla ubicacion
INSERT INTO ubicacion (nombre_lugar, direccion, ciudad, pais, descripcion, evento_id) VALUES
                                                                                          ('Auditorio Principal', 'Av. Principal 123, Edificio A', 'Lima', 'Perú', 'Auditorio amplio con capacidad para 500 personas.', 1),
                                                                                          ('Sala de Conferencias Norte', 'Calle Los Robles 456, Piso 2', 'Bogotá', 'Colombia', 'Sala equipada con proyector y sonido envolvente.', 2),
                                                                                          ('Centro de Convenciones', 'Carretera Panamericana Norte km 50', 'Santiago', 'Chile', 'Espacio ideal para conferencias y exposiciones de gran tamaño.', 3);


-- Datos de prueba para la tabla calificacion
INSERT INTO calificacion (puntuacion, evento_id) VALUES
                                                     (5, 1),
                                                     (4, 2),
                                                     (3, 3);

-- Datos de prueba para la tabla comentario
INSERT INTO comentario (comentario, evento_id) VALUES
                                                   ('¡Excelente evento, aprendí mucho!', 1),
                                                   ('El ponente fue muy claro y directo.', 2),
                                                   ('Me gustaría que se extendiera más el tiempo.', 3);

-- Datos de prueba para la tabla evento_categoria
INSERT INTO evento_categoria (nombre, descripcion, evento_id) VALUES
                                                                  ('Inteligencia Artificial', 'Eventos sobre IA', 1),
                                                                  ('Diseño', 'Eventos sobre UX/UI', 2),
                                                                  ('Ciberseguridad', 'Competencias de ciberseguridad', 3);

-- Datos de prueba para la tabla fecha
INSERT INTO fecha (fecha_inicio, fecha_fin, evento_id) VALUES
                                                           ('2024-09-15 08:00:00', '2024-09-15 10:00:00', 1),
                                                           ('2024-09-16 09:00:00', '2024-09-16 11:00:00', 2),
                                                           ('2024-09-17 14:00:00', '2024-09-17 16:00:00', 3);
-- Datos de prueba para la tabla pago
INSERT INTO pago (monto, fecha_pago, estado, usuario_id) VALUES
                                                                                (50.00, '2024-09-15', 'PENDING', 1),
                                                                                (75.00, '2024-09-16', 'PENDING', 2),
                                                                                (100.00, '2024-09-17', 'PAID', 3);


-- Datos de prueba para la tabla suscripcion
INSERT INTO suscripcion (tipo_suscripcion, fecha_inicio, fecha_fin, estado, usuario_id, pago_id) VALUES
                                                                                                     ('Mensual', '2024-09-01 08:00:00', '2024-09-30 23:59:59', 'ACTIVE', 1, 1),
                                                                                                     ('Anual', '2024-09-01 08:00:00', '2025-08-31 23:59:59', 'ACTIVE', 2, 2),
                                                                                                     ('Mensual', '2024-09-15 08:00:00', '2024-10-14 23:59:59', 'INACTIVE', 3, 3);


-- Datos de prueba para la tabla acceso_premium
INSERT INTO acceso_premium (estado, usuario_id, suscripcion_id, evento_id) VALUES
                                                                               ('ACTIVE', 1, 1, 1),
                                                                               ('INACTIVE', 2, 2, 2),
                                                                               ('ACTIVE', 3, 3, 3);

-- Datos de prueba para la tabla evento_comunidad
INSERT INTO evento_comunidad (evento_id, comunidad_id) VALUES
                                                           (1, 1),
                                                           (2, 1),
                                                           (3, 2);

-- Datos de prueba para la tabla usuario_comunidad
INSERT INTO usuario_comunidad (usuario_id, comunidad_id, fecha_unido, rol) VALUES
                                                                               (1, 1, '2024-09-15 08:00:00', 'miembro'),
                                                                               (2, 1, '2024-09-16 09:00:00', 'aministrador'),
                                                                               (3, 2, '2024-09-17 14:00:00', 'aministrador');

-- Datos de prueba para la tabla usuario_evento
INSERT INTO usuario_evento (estado_inscripcion, usuario_id, evento_id) VALUES
                                                                           ('Confirmado', 1, 1),
                                                                           ('Pendiente', 2, 1),
                                                                           ('Cancelado', 3, 2);