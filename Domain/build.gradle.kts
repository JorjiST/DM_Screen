plugins {
    id("java-library")
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

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.mockito:mockito-core:5.20.0")
    testImplementation("org.mockito:mockito-junit-jupiter:5.20.0")
    compileOnly("org.projectlombok:lombok:1.18.42")
    annotationProcessor("org.projectlombok:lombok:1.18.42")
    implementation("log4j:log4j:1.2.17")
    api(project(":Content"))
}

tasks.test {
    useJUnitPlatform()
}