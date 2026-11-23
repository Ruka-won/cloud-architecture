# 첫 번째 환경: 빌드용
FROM amazoncorretto:17 AS builder

# xargs 설치
RUN yum install -y findutils

WORKDIR /app

COPY . .

RUN chmod +x gradlew && ./gradlew clean bootJar --no-daemon


# 두 번째 환경: 실행용
FROM amazoncorretto:17-al2023-headless

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]