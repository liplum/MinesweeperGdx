plugins {
    `java-library`
}
val appName: String by project
val ktxVersion = "1.11.0-rc3"
java {
    sourceCompatibility = JavaVersion.VERSION_1_7
}
dependencies {
    val gdxVersion: String by project
    val kotlinVersion: String by project
    api("com.badlogicgames.gdx:gdx:$gdxVersion")
    api("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
    libKtx {
        +"ktx-app"
        +"ktx-collections"
        +"ktx-preferences"
        +"ktx-math"
        +"ktx-graphics"
    }
}
tasks.named<JavaCompile>("compileJava") {
    options.encoding = "UTF-8"
}
tasks.named<JavaCompile>("compileTestJava") {
    options.encoding = "UTF-8"
}
sourceSets {
    main {
        java.srcDirs("src")
    }
}
eclipse {
    project.name = "$appName-core"
}
inline fun DependencyHandler.libKtx(config: ApiGroupSpec.() -> Unit) {
    apiGroup(group = "io.github.libktx", version = ktxVersion, config)
}

inline fun DependencyHandler.apiGroup(group: String, version: String, config: ApiGroupSpec.() -> Unit) {
    ApiGroupSpec(group = group, version = version, handler = this).config()
}

class ApiGroupSpec(
    private val group: String,
    private val version: String,
    private val handler: DependencyHandler,
) {
    operator fun String.unaryPlus() {
        handler.api(group = group, name = this, version = version)
    }
}