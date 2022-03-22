# SHOPPING LIST (CART) APPLICATION
#Run app with docker-compose:
docker-compose up --build

#Exposed Swagger UI on app container
http://localhost:8080/swagger-ui/index.html

#Run app local with dev properties
mvn spring-boot:run -Dspring-boot.run.profiles=dev

#Exposed Swagger on dev
http://localhost:8081/swagger-ui/index.html
