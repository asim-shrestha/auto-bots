package com.example.autobots.autobots

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AutoBotsApplication

fun main(args: Array<String>) {
	runApplication<AutoBotsApplication>(*args)
}

data class Bot(val name: String, val health: Int, val attack: Int)