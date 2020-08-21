# Course-plataform
ADA ITW Bootcamp final project. This Api Rest service manage scholarships and study quotas for courses in different categories.

_This proyect is coded in java8 with frameworks: Maven, Hibernate and SpringBoot. This API uses a SQL database and provides the methods for an e-learning page.I use JUNIT for unitary tests.  _

## In Progress 🚀

_You can follow the progress of the proyect in https://trello.com/b/VJCuQfIH/tp-plataforma-de-cursos._


## Built with 🛠️

* [Maven](https://maven.apache.org/) - dependencies manager
* [Hibernate](https://hibernate.org/) - framework 
* [Spring Boot](https://spring.io/projects/spring-boot) - framework 
* [Junit](https://junit.org/junit4/) - unitary testing 
* [mysql](https://www.mysql.com/products/workbench/) - Database service
* [Spring Security](https://spring.io/projects/spring-security) - Security
* [Swagger](https://swagger.io/) - Documentation
* [Eclipse](https://www.eclipse.org/downloads/) - IDE


### Client requirements 📋

Se desea realizar un servicio Rest para gestionar becas y cupos de estudios de cursos en
diferentes categorías.
Los curso son publicados en la app por empresas que deben estar registradas en el
sistemas y aprobadas por un usuario administrador. Mientras la empresa no tenga dicha
aprobación, no podrá cargar los cursos que ofrece.
Los datos de la empresa en el sistema de ser:

● Nombre de empresa
● Cuil
● Tipo de empresa
● Dirección de la empresa
● Categoría de la empresa
● Año de fundación
● Número de contacto
● Representante:
○ Nombre y apellido
○ Tipo y Número de documento
○ Cargo en la empresa
○ Email

Los cursos que ofrecen cada empresa, sólo pueden ser cargados por los representan de
dicha empresa. Los datos de cada curso son:

● Nombre del curso
● Descripción del curso
● Modalidad del curso.
● Costo del curso
● Horas del curso
● Categoría del curso
● Número de participantes
● Número de cupos a becar.

Las solicitudes de inscripción son realizadas por los participantes inscritos en la plataforma.
Una vez que se alcanza el número de participantes por curso, no se podrá hacer más
solicitudes de inscripción.
Hay 2 tipos de solicitudes de inscripción las directas:
1) El participante puede costear el total del curso (adjudicación directa del cupo).
2) Solicita una beca de estudio.

Las solicitudes por becas no afectan el número de cupos hasta que ésta no haya sido
aprobada por el administrador, a diferencia de las solicitudes de adjudicación directa.
Para realizar un registro como participante, se debe llenar los siguientes datos:

● Nombre y Apellido
● Fecha de nacimiento
● Género
● Lugar de vivienda
● Estudio Socio-Económico:
○ Estudia?
○ Trabaja?
○ Tiene ingresos? Cantidad mensual
○ Familia a cargo? Cuantos?

Al momento que un participante haga la solicitud de inscripción a un curso, debe indicar si
es con pago al 100% ó a través de una beca. En caso de que sea por beca, debe tener los
datos Socio-Económico registrados en el sistema y no debe tener un curso en progreso con
beca adjudicado.
La aprobación de las becas está a cargo de un usuario administrador del sistema y este
podrá adjudicar un 50%, 75% o 100% de la beca o en su defecto denegar la misma.
El Administrador también tiene la responsabilidad de indicar si un participante ha finalizado
un curso.
El motor de búsqueda de cursos debe contemplar los siguientes criterios:

● Todo los curso disponibles (con cupos abiertos)
● Todos los curso por Categoría
● Todos los curso por Empresa
● Todos los curso por Participante en progreso
● Todos los curso por Participante finalizados.
● Todos los curso por Empresa y Categoría

REQUERIMIENTOS:
● El sistema debe cumplir con todas las funcionalidades descritas en el enunciado.
● El manejo de roles es a través de JWT y USER_ROLE
● Al menos una clase de cada capa con UNIT e INTEGRATIONS test

VALOR AÑADIDO:
● Api Rest Documentation (SWAGGER)
● Más UNIT e INTEGRATIONS test


