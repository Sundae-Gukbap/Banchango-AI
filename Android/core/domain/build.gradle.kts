plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

kotlin.jvmToolchain(17)

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "17"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "17"
    }
    test {
        useJUnitPlatform()
    }
}
