import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.buildConfig)
}

group = "com.biqbila.bilapay.app"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17
    }
}

buildConfig {
    useKotlinOutput()
    packageName("com.biqbila.bilapay.app")

    // 讀取 Gradle 參數，例如: ./gradlew :desktop:run -Penv=sit
    val env = project.findProperty("env")?.toString() ?: "dev"
    println("Desktop Build Environment: $env")
    buildConfigField("String", "FLAVOR_ENV", "\"$env\"")
    val isPackageTask = gradle.startParameter.taskNames.any { it.contains("package", ignoreCase = true) }
    val buildType = if (isPackageTask) "release" else "debug"

    buildConfigField("String", "BUILD_TYPE", "\"$buildType\"")
    buildConfigField("String", "VERSION_NAME", "\"$version\"")
    when (env) {
        "sit" -> {
            buildConfigField("String", "BASE_URL", "\"https://app-api.131re.xyz/\"")
            buildConfigField("List<String>", "API_URLS", "listOf(\"https://app-api.131re.xyz/\")")
        }
        "prod" -> {
            buildConfigField("String", "BASE_URL", "\"https://app-api.131re.xyz/\"")
            buildConfigField("List<String>", "API_URLS", "listOf(\"https://app-api.131re.xyz/\")")
        }
        else -> { // dev
            buildConfigField("String", "BASE_URL", "\"https://app-api.131re.xyz/\"")
            buildConfigField("List<String>", "API_URLS", "listOf(\"https://app-api.131re.xyz/\")")
        }
    }
}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation(libs.kotlinx.coroutines.swing)

    implementation(libs.de.ktorfit)
    implementation(libs.de.ktorfit.converters.call)
    implementation(libs.de.ktorfit.converters.flow)
    implementation(libs.ktor.client.json)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.client.serialization)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.json)
    implementation(libs.ktor.client.cio)

    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.koin.compose)
    implementation(libs.koin.compose.viewmodel)

    implementation(project(":shared"))

    implementation(libs.jetbrains.compose.ui.tooling.preview)
}

compose.desktop {
    application {
        mainClass = "com.biqbila.bilapay.app.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.biqbila.bilapay"
            packageVersion = "1.0.0"
        }
    }
}