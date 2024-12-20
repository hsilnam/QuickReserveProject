FROM openjdk:21
WORKDIR /app

## JAR 파일 복사
COPY ./build/libs/QuickReserveProject.jar app.jar

## 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]