plugins {
    kotlin("jvm")
    application
}

group = "net.cacheux.gpsc"
version = "0.1"

repositories {
    mavenCentral()
}

application {
    mainClass.set("MainKt")
    applicationName = "gpsc"
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(project(":lib"))
    implementation("org.jetbrains.kotlinx:kotlinx-cli:0.3.5")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
