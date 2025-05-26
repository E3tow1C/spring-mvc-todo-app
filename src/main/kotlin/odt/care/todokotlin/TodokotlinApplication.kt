package odt.care.todokotlin

import io.github.cdimascio.dotenv.dotenv
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TodokotlinApplication

fun main(args: Array<String>) {
    // Set as system properties so Spring can use them
    val dotenv = dotenv()

    System.setProperty("DB_NAME", dotenv["DB_NAME"])
    System.setProperty("DB_USER", dotenv["DB_USER"])
    System.setProperty("DB_PASSWORD", dotenv["DB_PASSWORD"])

    runApplication<TodokotlinApplication>(*args)
}
