package com.marcrobito.amtest.network


class NetworkManager {
    val environment = NetworkEnvironment.Production
}

enum class NetworkEnvironment{
    Staging(),
    QA(),
    Production()
}