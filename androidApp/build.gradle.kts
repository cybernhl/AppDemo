import com.android.build.api.artifact.SingleArtifact
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.biqbila.bilapay.app"
    compileSdk = 37
    buildFeatures {
        buildConfig = true
        compose = true
    }
    defaultConfig {
        applicationId = "com.biqbila.bilapay.app"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "src/main/keepRules/rules.keep","proguard-rules.pro"
            )
        }
    }

    flavorDimensions += "env"
    productFlavors {
        create("dev") {
            dimension = "env"
            buildConfigField("String", "API_URL", "\"https://app-api.131re.xyz/\"")
            buildConfigField("String[]", "API_URLS", "new String[]{\"https://app-api.131re.xyz/\"}")
        }

        create("sit") {
            dimension = "env"
            buildConfigField("String", "API_URL", "\"https://app-api.131re.xyz/\"")
            buildConfigField("String[]", "API_URLS", "new String[]{\"https://app-api.131re.xyz/\"}")
        }

        create("prod") {
            dimension = "env"
            buildConfigField("String", "API_URL", "\"https://app-api.131re.xyz/\"")
            buildConfigField("String[]", "API_URLS", "new String[]{\"https://app-api.131re.xyz/\"}")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/*"
            excludes += "META-INF/INDEX.LIST"
            excludes += "META-INF/*.kotlin_module"
            excludes += "META-INF/LICENSE"
            excludes += "META-INF/LICENSE.txt"
            excludes += "META-INF/NOTICE"
            excludes += "META-INF/io.netty.versions.properties"
            excludes += "META-INF/AL2.0"
            excludes += "META-INF/LGPL2.1"
        }
    }
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_11
    }
}

androidComponents {
    onVariants(selector().all()) { variant ->
//        val artifactName = "BiqbilaPay-${variant.name}-${appVersionName}_${appVersionCode}"
        variant.outputs.forEach { output ->
            // 若日後啟用 ABI／密度分包，保留篩選條件以避免檔名衝突。
            val filters = output.filters.joinToString("") { "-${it.identifier}" }
//            output.outputFileName.set("$artifactName$filters.apk")
        }

        val renameBundle = tasks.register<RenameBundleTask>(
            "rename${variant.name.replaceFirstChar { it.uppercaseChar() }}Bundle"
        )
//        variant.artifacts.use(renameBundle)
//            .wiredWithFiles(RenameBundleTask::inputBundle, RenameBundleTask::outputBundle)
//            .withName("$artifactName.aab")
//            .toTransform(SingleArtifact.BUNDLE)
    }
}

/** 透過公開 Artifact API 複製最終 AAB，以保留簽章並套用版本檔名。 */
@DisableCachingByDefault(because = "僅複製單一 AAB，無需額外建置快取")
abstract class RenameBundleTask : DefaultTask() {
    @get:InputFile
    @get:PathSensitive(PathSensitivity.NONE)
    abstract val inputBundle: RegularFileProperty

    @get:OutputFile
    abstract val outputBundle: RegularFileProperty

    /** 逐位元複製已完成的 Bundle，不改寫壓縮內容或簽章。 */
    @TaskAction
    fun renameBundle() {
        inputBundle.get().asFile.copyTo(outputBundle.get().asFile, overwrite = true)
    }
}

dependencies {
    implementation(libs.androidx.activity.compose)

    implementation(libs.androidx.lifecycle.process)
    implementation(libs.androidx.datastore.preferences )

    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.koin.compose)
    implementation(libs.koin.android)
    implementation(libs.koin.compose.viewmodel)
    implementation(libs.koin.compose.viewmodel.navigation)

    implementation(libs.de.ktorfit)
    implementation(libs.de.ktorfit.converters.call)
    implementation(libs.de.ktorfit.converters.flow)
    implementation(libs.ktor.client.json)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.client.serialization)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.json)
    implementation(libs.ktor.client.cio)

    implementation(project(":shared"))
    implementation(libs.material)//FIXME use material3 maybe remove !! but "themes.xml" use !!
    implementation(libs.jetbrains.compose.ui.tooling.preview)
    debugImplementation(libs.jetbrains.compose.ui.tooling)
}