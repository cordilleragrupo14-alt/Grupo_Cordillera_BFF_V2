# BFF Grupo Cordillera — Cómo instalar este código

## 1. Copiar los archivos a tu repo

Copia el contenido de `src/` sobre tu proyecto `Grupo_Cordillera_BFF_V2` real,
reemplazando los archivos existentes con el mismo nombre. Estructura:

```
src/main/java/cl/grupocordillera/bff/
├── BffApplication.java        ⚠️ Ver nota abajo
├── client/
│   ├── VentaClient.java
│   ├── LogisticaClient.java
│   ├── AnaliticaClient.java
│   ├── GestionClient.java
│   └── NotificacionClient.java
├── config/
│   └── CorsConfig.java
├── controller/
│   ├── DashboardController.java
│   ├── LogisticaController.java
│   ├── VentaController.java
│   └── NotificacionController.java
├── dto/
│   └── (todos los DTOs)
├── service/
│   ├── DashboardService.java
│   ├── LogisticaService.java
│   ├── VentaService.java
│   └── NotificacionService.java
└── util/
    └── MaskUtil.java

src/main/resources/
└── application.yml
```

⚠️ **`BffApplication.java`**: probablemente tu proyecto YA tiene una clase
principal con `@SpringBootApplication` (puede tener otro nombre, ej.
`BffV2Application`). NO agregues una segunda — Spring Boot falla si hay dos.
Solo asegúrate de que la tuya tenga la anotación `@EnableFeignClients`
además de `@SpringBootApplication`.

## 2. Borrar controllers/services antiguos

Estos archivos quedan REEMPLAZADOS por los nuevos (bórralos si tenían
las rutas viejas sin `/v1`):
- El `DashboardController.java` viejo (el que exponía `/api/dashboard/resumen`)
- El `LogisticaController.java` viejo (el que exponía `/api/logistica` sin subrutas)
- El `VentaController.java` viejo (el que exponía `/api/ventas` sin `/clientes`)
- `AnaliticaController.java` si no lo usas en otro lado (ya no se llama
  directamente, el Dashboard lo consume vía Feign)

## 3. Variables de entorno en Render

Ve a Render → `Grupo_Cordillera_BFF_V2` → **Environment**, agrega:

| Key | Value |
|---|---|
| `VENTAS_URL` | `https://grupo-cordillera-ms-ventas-1.onrender.com` |
| `LOGISTICA_URL` | `https://grupo-cordillera-ms-logistica.onrender.com` |
| `ANALITICA_URL` | `https://grupo-cordillera-ms-analitica-kpi-1.onrender.com` |
| `GESTION_URL` | `https://grupo-cordillera-ms-gestion.onrender.com` |
| `NOTIFICACIONES_URL` | `https://grupo-cordillera-ms-notificaciones.onrender.com` |
| `CORS_ALLOWED_ORIGINS` | `https://grupo-cordillera-f-v2.vercel.app` |

(`application.yml` ya trae estos mismos valores como default, así que
funcionará igual aunque no configures las variables — pero es buena
práctica tenerlas explícitas en Render.)

## 4. ⚠️ Pendiente de tu parte (para que compile y funcione 100%)

Los Feign Clients (`VentaClient`, `LogisticaClient`, `AnaliticaClient`,
`GestionClient`, `NotificacionClient`) asumen rutas razonables en tus
microservicios (ej. `GET /api/ventas/clientes`, `GET /api/analitica/kpis`).
**Si tus microservicios reales usan otras rutas, ajusta solo esos 5
archivos** — el resto del BFF no cambia.

También en `VentaService.java`, el método `registrar()` tiene un `TODO`:
`VentaDTO` (id, rutCliente, monto, estado) no tiene campos para
`productos` ni `canal`, que sí espera el frontend en el formulario de
"Nueva venta". Hay que decidir si:
- Amplías `VentaDTO` con esos campos, o
- Creas un DTO específico para el request que se traduce a lo que
  espera tu `ms-ventas` real.

## 5. Desplegar

```bash
git add .
git commit -m "Implementa contrato completo de API para el frontend"
git push
```

Render debería redesplegar automáticamente. Verifica el log de build/deploy
en Render, y luego prueba el frontend en
https://grupo-cordillera-f-v2.vercel.app — los errores de CORS y 404
deberían desaparecer.

## 6. Seguridad — IMPORTANTE

Si en algún momento compartiste la contraseña de la base de datos
PostgreSQL en un chat, correo, o cualquier canal no seguro, **rótala
cuanto antes** desde Render (Base de datos → regenerar credenciales) y
actualiza la variable de entorno correspondiente en los servicios que
la usen.
