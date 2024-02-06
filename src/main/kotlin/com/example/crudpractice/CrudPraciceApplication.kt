package com.example.crudpractice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class CrudPraciceApplication

fun main(args: Array<String>) {
    runApplication<CrudPraciceApplication>(*args)
}
