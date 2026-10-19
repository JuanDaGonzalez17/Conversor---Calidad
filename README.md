# Conversor - Calidad

API REST (Spring Boot 4, Java 21) para convertir temperatura y peso, con integración continua en GitHub Actions, análisis estático en SonarCloud y pruebas de aceptación con Karate.

## Endpoints

| Método | Ruta | Parámetros | Ejemplo |
|---|---|---|---|
| GET | `/api/temperatura` | `origen`, `destino` (`celsius`, `fahrenheit`, `kelvin`), `valor` | `/api/temperatura?origen=celsius&destino=fahrenheit&valor=25` |
| GET | `/api/peso` | `origen`, `destino` (`gramos`, `libras`), `valor` | `/api/peso?origen=gramos&destino=libras&valor=453.59237` |

Respuesta exitosa (200):

```json
{ "origen": "celsius", "destino": "fahrenheit", "valor": 25.0, "resultado": 77.0 }
```

Valor fuera de dominio (bajo el cero absoluto, peso negativo, NaN, unidad desconocida) → **400**:

```json
{ "error": "El valor en kelvin (-1.0) esta por debajo del minimo permitido (0.0 kelvin)." }
```

## Comandos

```bash
./gradlew build              # compila, unit tests + cobertura JaCoCo, genera el jar
./gradlew performanceTest    # pruebas de rendimiento (@Tag("performance"))
./gradlew bootRun            # levanta la API en http://localhost:8080
./gradlew acceptanceTest     # Karate contra la API levantada (-Dkarate.baseUrl=...)
```

## Pipeline (cada push a `main`)

1. Build + unit tests + cobertura
2. Pruebas de rendimiento
3. Análisis en SonarCloud
4. Levantar la API y correr las pruebas de aceptación de Karate
5. Publicar el artefacto (`app-jar`) y los reportes (`reportes`)
