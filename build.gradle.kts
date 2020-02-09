plugins {
    id("org.springframework.boot") version "2.2.4.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    kotlin("jvm") version "1.3.61"
    kotlin("plugin.spring") version "1.3.61"
    kotlin("plugin.allopen") version "1.3.61"
}

group = "ru.i-osipov"
version = "0.0.1-SNAPSHOT"

configurations {
    compileOnly {
        extendsFrom(configurations.getByName("annotationProcessor"))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation(springBootStarter("data-mongodb"))
    implementation(springBootStarter("web"))
    implementation(springBootStarter("aop"))
    implementation("com.google.guava:guava:28.2-jre")
    implementation(openapi("webmvc-core", "1.2.30"))
    implementation(openapi("ui", "1.2.30"))
    implementation(openapi("security", "1.2.30"))
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation(springBootStarter("test")) {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation(junit("api", "5.5.2"))
    testRuntimeOnly(junit("engine", "5.5.2"))
    testImplementation("io.mockk:mockk:1.9.3")
}

tasks {
    test {
        useJUnitPlatform()
    }
    compileKotlin {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjvm-default=compatibility")
            jvmTarget = "1.8"
        }
    }
    compileTestKotlin {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjvm-default=compatibility")
            jvmTarget = "1.8"
        }
    }
}

fun DependencyHandler.springBootStarter(module: String, version: String? = null): String =
    "org.springframework.boot:spring-boot-starter-$module${version?.let { ":$version" } ?: ""}"

fun DependencyHandler.openapi(module: String, version: String? = null): String =
    "org.springdoc:springdoc-openapi-$module${version?.let { ":$version" } ?: ""}"

fun DependencyHandler.junit(module: String, version: String? = null): String =
    "org.junit.jupiter:junit-jupiter-$module${version?.let { ":$version" } ?: ""}"