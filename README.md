# biobank-services

## Run

Just run DataserviceApplication to get the application up and running.

## Example requests

```bash
curl http://localhost:8080/doctor/42
```

## Swagger

Just open http://localhost:8080/swagger-ui/. The API is available under http://localhost:8080/v2/api-docs.

## Docker

### Build

```bash
build.bat
# or
docker build -t biobank-services .
```

### Run

```bash
docker run -p 8080:8080 --name biobank-services biobank-services
docker stop biobank-services
```
