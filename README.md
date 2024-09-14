
## Introducción

**Tech Comunity** tiene como objetivo desarrollar una aplicación web que facilite la organización y participación en eventos tecnológicos y de networking. 
La plataforma permite a los usuarios crear, buscar y unirse a eventos relacionados con sus intereses tecnológicos, así como conectarse con otros profesionales y compartir conocimientos.
El proyecto está diseñado para promover el aprendizaje colaborativo y fomentar la creación de comunidades tecnológicas. Se enfoca en estudiantes y profesionales del sector tecnológico, brindando una herramienta que facilita la interacción y el crecimiento en un entorno digital.

### Colaboradores del Proyecto

| **Nombre**                | **Rol**              | **Perfil**                                                                       |    **Imagen**     |
|---------------------------|----------------------|----------------------------------------------------------------------------------|-------------------|
| Diego Sanchez Chuquimango | Líder del Proyecto   | [LinkedIn](https://www.linkedin.com/in/dsanchezchu/)                             |<img src="https://media.licdn.com/dms/image/v2/D4D35AQFUI7vCHQif-w/profile-framedphoto-shrink_400_400/profile-framedphoto-shrink_400_400/0/1723740630748?e=1726959600&v=beta&t=l8LD97EMGErB5mprBrDZq4Rb9w0nlYqYevx79MLRn_s" width="100" height="100" />| 
| Leonardo Sanchez Romero   | Miembro del Proyecto | [LinkedIn](https://www.linkedin.com/in/leonardo-sanchez-607883218/)              |<img src="https://media.licdn.com/dms/image/v2/D4E35AQHKHteEmC3OrA/profile-framedphoto-shrink_400_400/profile-framedphoto-shrink_400_400/0/1726350962776?e=1726963200&v=beta&t=izRICBiidkfqLVpzhDi0OBLg-Wq_CAV0WqgAY-v_iks" width="100" height="100" />|
| Charlie Morales Benites   | Miembro del Proyecto | [LinkedIn](https://www.linkedin.com/in/charlie-morales-benites-69553b2a6/)       |<img src="https://media.licdn.com/dms/image/v2/D4E35AQHJheob8SYbkQ/profile-framedphoto-shrink_400_400/profile-framedphoto-shrink_400_400/0/1726350402772?e=1726963200&v=beta&t=BUwICWxj1WDq3tbgGU4C5kXtQLcTCbDNbAleuKz6PRA" width="100" height="100" />|
| Rumay Yglesias Eduardo    | Miembro del Proyecto | [LinkedIn](https://www.linkedin.com/in/eduardo-daniel-rumay-yglesias-97b106329/) |<img src="https://github.com/user-attachments/assets/23d8e13f-eca0-400e-ab89-0e7c27c99627" width="100" height="100" alt="rumay-foto" />|


### Progreso del Proyecto Tech Comunity Peru

| **Columna**       | **Descripción**                                                                                                                                    |
|-------------------|----------------------------------------------------------------------------------------------------------------------------------------------------|
| **Backlog**       | Contiene todas las historias de usuario, tareas y características que deben desarrollarse. Es el listado de todo el trabajo pendiente.              |
| **En Progreso**   | Incluye las tareas que están actualmente en desarrollo. Visualiza el trabajo en curso para asegurar el flujo continuo de trabajo.                   |
| **Revisión**      | Después de completar una tarea, se mueve aquí para una revisión de código y revisión por pares (peer review). Esta fase incluye la creación de **pull requests** para asegurar que el código cumpla con los estándares de calidad antes de integrarse al proyecto principal. |
| **En Pruebas**    | Contiene las tareas que han pasado la revisión de código y necesitan pruebas exhaustivas (unitarias, de integración y de aceptación) para garantizar su calidad. |
| **Hecho**         | Las tareas completamente desarrolladas, revisadas y probadas se mueven aquí, indicando que están listas y finalizadas.                               |

Mira cómo va avanzando nuestro trabajo visitando el siguiente enlace: [Tablero de Github](https://github.com/orgs/Tech-Community-Peru/projects/4).


### Funcionalidades de la Aplicación BookHub

## Módulos Principales

### 1. Módulo de Gestión de Usuarios:
- **Registro y Perfiles de Usuarios**: Los usuarios podrán registrarse y crear perfiles detallados, incluyendo sus intereses tecnológicos, habilidades, y enlaces a sus redes profesionales como LinkedIn.
- **Gestión de Perfiles**: Funcionalidades CRUD para que los usuarios puedan editar sus perfiles, actualizar información y gestionar su presencia en la plataforma.

### 2. Módulo de Creación y Gestión de Eventos:
- **Creación de Eventos**: Los usuarios podrán crear eventos con detalles como el título, la descripción, la ubicación (física o virtual), y la fecha y hora del evento.
- **Gestión de Eventos**: Permitir a los organizadores editar o cancelar eventos creados, gestionar la lista de participantes, y enviar actualizaciones.

### 3. Módulo de Inscripción y Participación en Meetups:
- **Búsqueda de Eventos**: Los usuarios podrán buscar meetups basados en su ubicación, intereses tecnológicos, o palabras clave.
- **Inscripción a Eventos**: Facilitar la inscripción de los usuarios a eventos de su interés, con opciones para recibir notificaciones y recordatorios por correo electrónico.

### 4. Módulo de Pagos en Línea:
- **Pagos para Eventos Premium**: Integración de un sistema de pagos que permita a los usuarios pagar para unirse a meetups premium, que pueden incluir acceso a charlas exclusivas, talleres, o networking especializado.

### 5. Módulo de Reportes:
- **Estadísticas de Participación**: Los organizadores podrán generar reportes sobre la asistencia a sus eventos, interacciones, y feedback de los participantes.
- **Reportes de Actividad para Usuarios**: Los usuarios podrán ver un resumen de los eventos a los que han asistido, conexiones realizadas, y áreas de interés.

## Diagramas de la Aplicación

Para entender mejor la estructura y diseño de "Tech Community Peru", revisa los siguientes diagramas:

### Diagrama de Clases
![Tech Community Peru - Diagrama Clases](https://github.com/user-attachments/assets/20cdf93d-8894-49e4-992d-0f247aeac4fa)


### Diagrama de Base de Datos
![DIAGRAMA BASE DE DATO](https://github.com/user-attachments/assets/d4270221-6db2-4465-920e-530c7feec3f9)


Este diagrama ilustra el esquema de la base de datos utilizada por la aplicación, mostrando las tablas, columnas, y relaciones entre las entidades.

### Descripción de Capas del Proyecto

| capa        | descripción                                                                                  |
|-------------|----------------------------------------------------------------------------------------------|
| api         | Contiene los controladores REST que manejan las solicitudes HTTP y las respuestas.            |
| entity      | Define las entidades del modelo de datos que se mapean a las tablas de la base de datos.      |
| repository  | Proporciona la interfaz para las operaciones CRUD y la interacción con la base de datos.      |
| service     | Declara la lógica de negocio y las operaciones que se realizarán sobre las entidades.         |
| service impl| Implementa la lógica de negocio definida en los servicios, utilizando los repositorios necesarios. |

# Asignación de Historias de Usuario


**Sprint 1:** Funcionalidades Básicas  
*Enfocado en implementar las funcionalidades esenciales de CRUD para la gestión de categorías, libros, autores, usuarios, y el flujo inicial de compras, asegurando que el producto esté listo para un uso básico.*


| Integrante                  | Módulo                                           | Historia de Usuario                                                        | Descripción                                                                                                                                                                                                         | Tipo  |
|-----------------------------|--------------------------------------------------|----------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------|
| Diego Sanchez Chuquimango   | Módulo de Gestión de Usuarios                    | Historia de Usuario 12: Registro e integración rápida.                     | Coincide con la necesidad de registrarse en la plataforma.                                                                                                                                                          | CRUD  |
|                             |                                                  | Historia de Usuario 13: Inicio de sesión.                                  | Relacionado con la autenticación del usuario.                                                                                                                                                                       | CRUD  |
|                             |                                                  | Historia de Usuario 14: Olvidé mi contraseña                               | Como Parte del sistema de gestión de credenciales y recuperación de cuentas.                                                                                                                                        | CRUD  |
| Leonardo Sanchez Romero     | Módulo de Creación y Gestión de Eventos          | Historia de Usuario 05: Cancelación de asistencia a un evento.             | Permite la gestión de la asistencia a eventos.                                                                                                                                                                      | CRUD  |
|                             |                                                  | Historia de Usuario 15: Valoración y Comentarios de Eventos.               | Relacionado con la retroalimentación de eventos.                                                                                                                                                                    | CRUD  |
| Rumay Yglesias Eduardo      | Módulo de Inscripción y Participación en Meetups | Historia de Usuario 01: Búsqueda de eventos por categoría tecnológica.     | Coincide con la necesidad de filtrar eventos según intereses.                                                                                                                                                       | CRUD  |
|                             |                                                  | Historia de Usuario 06: Notificación por gustos.                           | Se alinea con la personalización de la información recibida.                                                                                                                                                        | CRUD  |
|                             |                                                  | Historia de Usuario 05: Cancelación de asistencia a un evento.             | Involucra la funcionalidad de inscribirse y cancelar asistencia.                                                                                                                                                    | CRUD  |
| Charlie Morales Benites     | Modulo de pagos en linea                         | Historia de usuario 2: Transparencia de costos en eventos                  | Como estudiante, quiero ver de antemano todos los costos asociados a un evento, incluidos impuestos y cargos adicionales, para decidir si puedo permitirme asistir sin sorpresas en el proceso de pago.             | Core  |
|                             |                                                  | Historia de usuario 4: Compra de eventos premium                           | Como usuario interesado en un evento premium, quiero poder comprar mi entrada de manera rápida y segura, para asegurar mi participación en eventos exclusivos que requieren pago.                                   | Core  |
|                             |                                                  | Historia de Usuario 7: Sistema de recompensas basado en pagos realizados   | Como usuario que ha asistido a varios eventos, quiero acumular recompensas o puntos por cada pago que realice, para poder canjearlos en eventos futuros, obtener descuentos adicionales o participar de un sorteo.  | Core  |
|                             |                                                  | Historia de Usuario 8: Gestión de metodos de pagos preferidos              | Como usuario frecuente de la plataforma, quiero guardar mis métodos de pago preferidos (tarjeta de crédito, PayPal, transferencia bancaria) para facilitar futuros pagos y mejorar mi experiencia de usuario.       | CRUD  |
|                             |                                                  | Historia de Usuario 9: Selección de Método de Pago en el Proceso de Compra | Como usuario que está completando una compra, quiero poder seleccionar entre mis métodos de pago guardados o ingresar uno nuevo, para asegurarse de utilizar el método de pago más conveniente en cada transacción. | CRUD  |
|                             |                                                  | Historia de Usuario 10: Verificación de Seguridad en Métodos de Pago       | Como usuario, quiero que todos los métodos de pago que utilice en la plataforma pasen por una verificación de seguridad para asegurar que mis transacciones sean seguras y estén protegidas contra fraudes.         | CRUD  |
|                             |                                                  | Historia de Usuario 11: Notificación de estado de pago                     | Como usuario que está realizando un pago, quiero recibir una notificación que me asegure que mi pago ha sido concretado exitosamente.                                                                               | CRUD  |
| Leonardo Sanchez Romero     | Módulo de Reportes                               | Historia de Usuario 3: Generar reportes de asistencia para eventos         | Como usuario que ha asistido a varios eventos, quiero acumular recompensas o puntos por cada pago que realice, para poder canjearlos en eventos futuros, obtener descuentos adicionales o participar de un sorteo.  | CRUD  |

## Recomendaciones

1. **Comunicación Constante:** Mantener una comunicación abierta y constante entre todos los miembros del equipo para resolver dudas y compartir avances. Utilizar herramientas colaborativas como Slack o Microsoft Teams para facilitar la interacción.

2. **Revisión de Código:** Implementar prácticas de revisión de código entre pares para asegurar la calidad del software y fomentar el aprendizaje mutuo. Esto ayudará a detectar errores a tiempo y a mejorar la calidad del código entregado.

3. **Gestión del Tiempo:** Planificar y gestionar bien el tiempo para cada historia de usuario, priorizando aquellas que son críticas para el funcionamiento básico del sistema. Utilizar técnicas como el método Pomodoro para mantener la concentración y eficiencia.

4. **Pruebas Continuas:** Realizar pruebas continuas de las funcionalidades desarrolladas para identificar y corregir errores tempranamente. Asegurar que cada historia de usuario esté completamente probada antes de considerarla terminada.

5. **Documentación Clara:** Mantener una documentación clara y actualizada del código y de las decisiones tomadas durante el desarrollo. Esto facilitará el mantenimiento y futuras mejoras del producto.

6. **Retroalimentación Regular:** Programar sesiones regulares de retroalimentación al final de cada sprint para evaluar lo que funcionó bien y qué se puede mejorar. Utilizar estas sesiones para ajustar las estrategias y métodos de trabajo según sea necesario.

7. **Enfoque en el Usuario:** Mantener siempre al usuario final en mente durante el desarrollo. Asegurar que todas las funcionalidades implementadas aporten valor al usuario y mejoren su experiencia con la plataforma.

8. **Gestión de Riesgos:** Identificar posibles riesgos que puedan afectar el desarrollo del proyecto y planificar estrategias para mitigarlos. Esto incluye problemas técnicos, falta de recursos o cambios en los requisitos.

9. **Optimización del Rendimiento:** Asegurarse de que las funcionalidades añadidas no comprometan el rendimiento del sistema. Realizar pruebas de carga y optimización cuando sea necesario para garantizar una experiencia de usuario fluida.

10. **Preparación para el Lanzamiento:** A medida que se acerque el final del segundo sprint, prepararse para el lanzamiento del producto con un plan de despliegue claro, asegurando que todas las funcionalidades estén completamente probadas y documentadas.
