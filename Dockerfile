#Imagen basada en Java 17
FROM openjdk:17-slim

#Directorio de trabajo
WORKDIR /app

#Copiar el archivo jar del proyecto al directorio /app en el contenedor
COPY target/FoodForLess-0.0.1-SNAPSHOT.jar /app/FoodForLess.jar

#Exponer el puerto de la aplicacion
EXPOSE 8080

#Comando para ejecutar la aplicacion
CMD ["java", "-jar", "/app/FoodForLess.jar"]
