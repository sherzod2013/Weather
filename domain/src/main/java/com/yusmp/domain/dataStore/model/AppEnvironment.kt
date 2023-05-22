package com.yusmp.domain.dataStore.model

enum class AppEnvironment(val url: String) {
    DEV("http://api.weatherapi.com/v1/"),
    UAT("http://api.weatherapi.com/v1/"),
}