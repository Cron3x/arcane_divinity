import net.neoforged.moddevgradle.tasks.JarJar

plugins {
    id ("java-library")
    id("arcane_divinity-convention")

    alias(libs.plugins.moddevgradle)
}

val modId: String by project
val mcVersion = libs.versions.minecraft.asProvider().get()
val parchmentMcVersion = libs.versions.parchment.minecraft.get()
val parchmentVersion = libs.versions.parchment.asProvider().get()
val neoforgeVersion = libs.versions.neoforge.asProvider().get()

version = libs.versions.arcane.divinity.get()

base {
    archivesName = "arcane_divinity-neoforge-${mcVersion}"
}

configurations {
    val shade by creating
    implementation {
        extendsFrom(shade)
    }
    additionalRuntimeClasspath {
        extendsFrom(shade)
    }
}

neoForge {
    version = neoforgeVersion

    accessTransformers.files.setFrom(project(":common").file("src/main/resources/META-INF/accesstransformer-nf.cfg"))
    parchment.minecraftVersion.set(parchmentMcVersion)
    parchment.mappingsVersion.set(parchmentVersion)

    runs {
        configureEach {
            logLevel = org.slf4j.event.Level.DEBUG
        }

        mods.create(modId).sourceSet(project.sourceSets.getByName("main"))

        create("client") {
            client()
        }

        create("server") {
            server()
            programArgument("--nogui")
        }
    }
}

repositories {
    mavenLocal()
    maven {
        name = "GeckoLib"
        url = uri("https://dl.cloudsmith.io/public/geckolib3/geckolib/maven/")
        content {
            includeGroup("software.bernie.geckolib")
        }
    }
    maven {
        name = "BlameJared Maven (CrT / Bookshelf)"
        url = uri("https://maven.blamejared.com")
    }
    exclusiveContent {
        forRepository {
            maven {
                name = "Modrinth"
                url = uri("https://api.modrinth.com/maven")
            }
        }
        filter {
            includeGroup( "maven.modrinth")
        }
    }
}

dependencies {
    implementation("foundry.veil:veil-neoforge-${libs.versions.veil.minecraft.get()}:${libs.versions.veil.asProvider().get()}") {
        exclude("maven.modrinth")
    }
    jarJar("foundry.veil:veil-neoforge-${libs.versions.veil.minecraft.get()}:${libs.versions.veil.asProvider().get()}")
    implementation( "software.bernie.geckolib:geckolib-neoforge-${libs.versions.minecraft.asProvider().get()}:${libs.versions.geckolib.asProvider().get()}"){
    }
    compileOnly(project(":common"))
}

tasks.withType<Test>().configureEach {
    enabled = false;
}

tasks.withType<JarJar>().configureEach{
    enabled = true;
}

tasks.named<JavaCompile>("compileJava").configure {
    source(project(":common").sourceSets.getByName("main").allSource)
}

tasks.named<Jar>("sourcesJar").configure {
    from(project(":common").sourceSets.getByName("main").allSource)
}

tasks.withType<Javadoc>().configureEach {
    source(project(":common").sourceSets.getByName("main").allJava)
}

tasks.withType<ProcessResources>().configureEach {
    from(project(":common").sourceSets.getByName("main").resources)
}

publishing {
    publishing {
        publications {
            create<MavenPublication>("arcane_divinity") {
                from(components["java"])
                artifactId = base.archivesName.get()
            }
        }
    }
}