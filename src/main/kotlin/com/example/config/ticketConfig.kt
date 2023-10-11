package com.example.config

import java.sql.Connection
import java.sql.DriverManager

class ticketConfig {
    fun connect(): Connection {
        val url = "jdbc:postgresql://localhost:5432/ticketingsamp"

        val username = "postgres"

        val password = "root"

        return DriverManager.getConnection(url,username,password)
    }
}