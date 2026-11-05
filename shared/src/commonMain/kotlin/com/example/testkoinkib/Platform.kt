package com.example.testkoinkib

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform