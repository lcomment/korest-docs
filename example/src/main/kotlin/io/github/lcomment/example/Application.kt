package io.github.lcomment.example

import java.util.*
import org.springframework.boot.runApplication

@org.springframework.boot.autoconfigure.SpringBootApplication
class Application

@jakarta.annotation.PostConstruct
fun started() {
    TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"))
}

fun main(args: Array<String>) {
    started()
    runApplication<Application>(*args)
}
