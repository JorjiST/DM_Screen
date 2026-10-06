plugins {
    id("java-library")
    application
    id("org.openjfx.javafxplugin") version "0.1.0"
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

group = "com.jorji"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

javafx {
    version = "25"
    modules = listOf("javafx.controls", "javafx.fxml", "javafx.web")
}

application {
    mainClass.set("com.jorji.Main")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation(project(":Domain"))
    implementation(project(":Persistence"))
    implementation(project(":Content"))

    compileOnly("org.projectlombok:lombok:1.18.42")
    annotationProcessor("org.projectlombok:lombok:1.18.42")
    testCompileOnly("org.projectlombok:lombok:1.18.42")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.42")

    implementation("log4j:log4j:1.2.17")
}

tasks.test {
    useJUnitPlatform()
}