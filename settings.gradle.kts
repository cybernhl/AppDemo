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

/////**
//// * 動態引入外部專案函式
//// * 優先順序：
//// * 1. 檢查目前專案下的 Submodule 路徑 (./Entity/general)
//// * 2. 檢查同級 Workspace 路徑 (../Entity/general)
//// */
////val includeExternalProject: (String, String) -> Unit = { name, subPath ->
////    // 獲取 settings.gradle.kts 所在的目錄（根目錄）
////    val rootDir = settings.settingsDir
////    val localDir = File(rootDir, subPath.removePrefix("/"))
////    val externalDir = File(rootDir.parentFile, subPath.removePrefix("/"))
////    val finalDir = if (localDir.exists()) localDir else if (externalDir.exists()) externalDir else null
////    if (finalDir != null) {
////        include(name)
////        project(name).projectDir = finalDir
////        println("SUCCESS: Included '$name' from ${finalDir.absolutePath}")
////    } else {
////        println("WARNING: Directory for '$name' not found at ${localDir.absolutePath} or ${externalDir.absolutePath}")
////    }
////}
//
//// 引入共用工具函式 (上面的抽出變成 include_utils.gradle.kts)
apply(from = "gradle/include_utils.gradle.kts")
val includeExternalProject = extra["includeExternalProject"] as (String, String) -> Unit
includeExternalProject(":shared", "../shared")
rootProject.name = "AppDemo"

include(":androidApp")
project(":androidApp").projectDir = file("./androidApp")
include(":desktopApp")
project(":desktopApp").projectDir = file("./desktopApp")
