# Proyecto de Automatización: TestBioseguridad

Este proyecto contiene una serie de pruebas automatizadas desarrolladas con Java, Selenium y JUnit para la plataforma de Bioseguridad. A continuación, se detallan los pasos para ejecutar cada una de las pruebas.

## Instrucciones de Ejecución

1. **Importar el proyecto en el editor**  
   Abre tu editor de preferencia (por ejemplo, Eclipse o IntelliJ) e importa el proyecto `TestBioseguridad`.

2. **Desplegar la estructura del proyecto**  
   - Dirígete a la carpeta `src/test/java`.
   - Dentro encontrarás el paquete `testbioseguridad`.

3. **Ejecutar los archivos `.java` en el siguiente orden:**  
   Para cada uno de los archivos listados, haz clic derecho sobre el archivo en el editor y selecciona la opción:  
   `Run As > JUnit Test`

   **Orden de ejecución:**

   1. `Test1.java`  
   2. `TestCrearTresSectores.java`  
   3. `TestCrearTresTemas.java`  
   4. `TestCrearTresSubtemas.java`  
   5. `TestCrearTresPreguntas.java`  
   6. `Test3.java`

---

## Notas Importantes

- Los archivos `TestCrearTresSubtemas.java` y `TestCrearTresPreguntas.java` **no se ejecutaron con éxito** debido a que el tiempo de espera para identificar los componentes de las opciones desplegables **no es suficiente o preciso** para garantizar una ejecución estable.
  
- El archivo `Test3.java` **no fue posible ejecutarlo correctamente**, ya que **la alerta de notificación de cambio de contraseña del navegador Chrome bloquea la navegación** en la página y no se puede evitar programáticamente con la configuración actual.

---
