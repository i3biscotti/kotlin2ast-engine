FROM gradle:jdk21 AS gradle
LABEL authors="i3biscotti"

WORKDIR /app

RUN cd /app
COPY . .

RUN gradle buildFatJar

FROM amazoncorretto:21
WORKDIR /app

COPY --from=gradle /app/build/libs/kotlin2ast-engine-all.jar /app/engine.jar

CMD ["java", "-jar", "/app/engine.jar"]

