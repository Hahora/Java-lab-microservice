FROM eclipse-temurin:21-jdk-alpine AS builder

# Информация об авторе
LABEL maintainer="Богдан <ваша@почта.ру>"
LABEL version="1.0"

# Рабочая директория
WORKDIR /app

# Копируем JAR файл (уже собранный локально)
ARG JAR_FILE=target/developer-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# Распаковываем JAR для оптимизации
RUN mkdir -p target/dependency && \
    (cd target/dependency && jar -xf /app/app.jar)

FROM eclipse-temurin:21-jre-alpine

# Информация о здоровье приложения
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
    CMD wget --no-verbose --tries=1 --spider http://localhost:8080/actuator/health || exit 1

# Временная папка для Spring Boot
VOLUME /tmp

# Копируем только необходимые файлы
ARG DEPENDENCY=/app/target/dependency
COPY --from=builder ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=builder ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=builder ${DEPENDENCY}/BOOT-INF/classes /app

# Открываем порт
EXPOSE 8080

# Команда запуска
ENTRYPOINT ["java", \
    "-Xmx512m", \
    "-Djava.security.egd=file:/dev/./urandom", \
    "-cp", "app:app/lib/*", \
    "com.example.developerservice.DeveloperServiceApplication"]