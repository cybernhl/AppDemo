enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        gradlePluginPortal()
        maven(url = "https://plugins.gradle.org/m2/")
        mavenCentral()
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        maven {
            url = uri("https://oss.sonatype.org/content/repositories/snapshots")
            name = "SonatypeSnapshots"
            mavenContent {
                snapshotsOnly()
            }
        }
        maven(url = "https://maven.aliyun.com/repository/gradle-plugin/")
        maven(url = "https://maven.aliyun.com/repository/spring-plugin/")
        maven(url = "https://jitpack.io")
        maven(url = "https://s3.amazonaws.com/repo.commonsware.com")
        maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven(url = "https://api.xposed.info/")
        maven(url = "https://jogamp.org/deployment/maven")
        maven(url = "https://developer.huawei.com/repo/")
        maven(url = "https://raw.githubusercontent.com/cybernhl/maven-repository/master/")
        maven(url = "https://maven.aliyun.com/repository/jcenter")
        maven(url = "https://maven.aliyun.com/repository/public/")
        maven(url = "https://maven.aliyun.com/repository/spring/")
        maven(url = "https://maven.aliyun.com/repository/google/")
        maven(url = "https://maven.aliyun.com/repository/grails-core/")
        maven(url = "https://maven.aliyun.com/repository/apache-snapshots/")
        maven(url = "https://packages.jetbrains.team/maven/p/skija/maven")
    }
    plugins {

    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

val fullVersion = System.getProperty("java.version", "8.0.0")
val versionComponents = fullVersion
    .split(".")
    .take(2)
    .filter { it.isNotBlank() }
    .map { Integer.parseInt(it) }

val currentJdk = if (versionComponents[0] == 1) versionComponents[1] else versionComponents[0]


@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositories {
        mavenCentral()
        google()
        maven {
            url = uri("https://oss.sonatype.org/content/repositories/snapshots")
            name = "SonatypeSnapshots"
            mavenContent {
                snapshotsOnly()
            }
        }
        maven(url = "https://jitpack.io")
        maven(url = "https://s3.amazonaws.com/repo.commonsware.com")
        maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven(url = "https://api.xposed.info/")
        maven(url = "https://jogamp.org/deployment/maven")
        maven(url = "https://raw.githubusercontent.com/cybernhl/maven-repository/master/")
        maven(url = "https://maven.aliyun.com/repository/jcenter")
        maven(url = "https://maven.aliyun.com/repository/public/")
        maven(url = "https://maven.aliyun.com/repository/spring/")
        maven(url = "https://maven.aliyun.com/repository/google/")
        maven(url = "https://packages.jetbrains.team/maven/p/skija/maven")
    }
}

rootProject.name = "LiveStream"

apply(from = "gradle/include_utils.gradle.kts")
val includeExternalProject = extra["includeExternalProject"] as (String, String) -> Unit
includeExternalProject(":shared", "/shared")

// Shared dependencies
includeExternalProject(":entity_common", "./Entity/general")
includeExternalProject(":entity_lang", "./Entity/lang")
includeExternalProject(":DomainManager", "./GeneralModule/DomainManager")
includeExternalProject(":DomainProbe", "./GeneralModule/DomainProbe")
includeExternalProject(":network", "./GeneralModule/network")
includeExternalProject(":resfulclient", "./GeneralModule/resfulclient")
includeExternalProject(":network_plugin", "./GeneralModule/network_plugin")
includeExternalProject(":storage", "./GeneralModule/storage")
includeExternalProject(":DI_TAGS", "./GeneralModule/DI_TAGS")
includeExternalProject(":utils", "./GeneralModule/utils")
includeExternalProject(":logic_lang", "./ProjectUtils/logic_lang")
includeExternalProject(":api_lang", "./Projectapi/lang")
includeExternalProject(":repository", "./ProjectRepository/livestream/Lang")

//include(":shared")

include(":app")
project(":app").projectDir = file("./androidApp")
include(":desktop")
project(":desktop").projectDir = file("./desktopApp")