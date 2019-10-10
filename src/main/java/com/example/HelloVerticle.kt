package com.example

import io.vertx.core.AbstractVerticle
import io.vertx.core.Vertx
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class HelloVerticle : AbstractVerticle() {
    private val logger = LoggerFactory.getLogger(HelloVerticle::class.java)
    private var counter: Long = 1

    override fun start() {
        vertx.setPeriodic(5000) { _ -> logger.info("tick") }

        vertx.createHttpServer()
            .requestHandler { req ->
                logger.info("Request #{} from {}", counter++, req.remoteAddress().host())
                req.response().end("Hello!")
            }
            .listen(8080)
        logger.info("Open http://localhost:8080/")
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            val vertx = Vertx.vertx()
            vertx.deployVerticle(HelloVerticle())
        }
    }
}
