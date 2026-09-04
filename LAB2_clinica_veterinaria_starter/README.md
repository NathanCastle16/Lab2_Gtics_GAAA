# Clínica veterinaria - plantilla de estudio

Esta carpeta está basada en el esquema MySQL proporcionado y en el enfoque de clase: Entity -> Repository -> Controller -> Vista.

## Requisitos
- Java 17+
- Maven
- MySQL

## Cómo ejecutar
1. Ejecuta `database.sql` en MySQL.
2. Abre `src/main/resources/application.properties`.
3. Cambia `CAMBIA_AQUI_TU_PASSWORD` por tu contraseña de MySQL. Si no tienes contraseña, deja el valor vacío.
4. Desde la raíz del proyecto ejecuta:

```bash
mvn spring-boot:run
```

5. Abre:

```text
http://localhost:8080/mascotas
```

## Qué ya está preparado
- Entidad `Mascota` mapeada a la tabla `mascota`.
- `JpaRepository`.
- Listado con `findAll()`.
- Registro con DataBinding + `save()`.
- Eliminación con `deleteById()`.
- Búsqueda por query methods: nombre, especie y estado.
- Formulario de edición cargando datos mediante `findById()`.

## Partes que debes completar
### Pregunta 5
El laboratorio exige que la actualización se realice mediante una consulta personalizada. Completa:
- `MascotaRepository`: `@Query` + `@Modifying`.
- El endpoint POST `/mascotas/actualizar`.

### Pregunta 6
Completa las consultas personalizadas para:
- `MAX(edad)`
- `MIN(edad)`
- `AVG(edad)`
- `COUNT(...)`

y envía los resultados a `reporte.html`.

## Ramas sugeridas
- `feature/listado-mascotas`
- `feature/busqueda-mascotas`
- `feature/registro-mascota`
- `feature/edicion-mascota`
- `feature/reporte-mascotas`
