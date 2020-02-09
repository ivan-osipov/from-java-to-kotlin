package fromjavatokotlin

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties

@SpringBootApplication
@EnableConfigurationProperties
class FromJavaToKotlinDemo

fun main() {
    SpringApplication.run(FromJavaToKotlinDemo::class.java)
}