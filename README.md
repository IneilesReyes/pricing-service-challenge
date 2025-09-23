# Pricing Service

## Tech Stack

- Java 21
- Spring Boot
- H2 (in-memory database)
- Lombok
- MapStruct

## Testing Stack

- JUnit 5
- Mockito
- Easy Random

## Running the Application

### Clone the repository

```bash
git clone https://github.com/IneilesReyes/pricing-service-challenge
cd pricing-service-challenge
```

### Build the project

Using Maven Wrapper:

```bash
./mvnw clean install
```

### Run the application
```bash
./mvnw spring-boot:run
```

## API Usage

You can query the API using the following cURL command:

```bash
curl --location --request GET 'http://localhost:8080/product-service/v1/prices/applied?productId=35455&brandId=1&applicationDate=2020-06-14T10:00:00'
```

## Swagger UI

To explore and test the API endpoints, visit the Swagger UI at:

[http://localhost:8080/pricing-service/swagger-ui/index.html](http://localhost:8080/pricing-service/swagger-ui/index.html)
