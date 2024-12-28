plugins {
    id("arcane_divinity-convention")

    alias(libs.plugins.loom)
}

val modId: String by project
val mcVersion = libs.versions.minecraft.asProvider().get()
val parchmentMcVersion = libs.versions.parchment.minecraft.get()
val parchmentVersion = libs.versions.parchment.asProvider().get()
val geckolibVersion = libs.versions.geckolib.asProvider().get()

version = libs.versions.arcane.divinity.get()

base {
    archivesName = "arcane_divinity-fabric-${mcVersion}"
}

repositories {
    maven {
        name = "KosmX's maven"
        url = uri("https://maven.kosmx.dev/")
    }
    maven {
        name = "Shedaniel Maven"
        url = uri("https://maven.shedaniel.me/") }
    maven {
        name = "terraformersmc Maven"
        url = uri("https://maven.terraformersmc.com/releases/")
    }
    maven {
        name = "ParchmentMC"
        url = uri("https://maven.parchmentmc.org")
        content {
            includeGroupAndSubgroups("org.parchmentmc")
        }
    }
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

    mavenCentral()
}

dependencies {
    minecraft(libs.minecraft)
    mappings(loom.layered() {
        officialMojangMappings()
        parchment("org.parchmentmc.data:parchment-${parchmentMcVersion}:${parchmentVersion}@zip")
    })
    modImplementation(libs.fabric)
    modImplementation(libs.fabric.api)

    modImplementation("software.bernie.geckolib:geckolib-fabric-${mcVersion}:${geckolibVersion}")
    modImplementation("foundry.veil:veil-fabric-${libs.versions.veil.minecraft.get()}:${libs.versions.veil.asProvider().get()}") {
        exclude("maven.modrinth")
    }

    //BetterCombat
    modApi("me.shedaniel.cloth:cloth-config-fabric:${libs.versions.clothconfig.get()}") {
        exclude("net.fabricmc.fabric-api")
    }
    modImplementation("dev.kosmx.player-anim:player-animation-lib-fabric:${libs.versions.playeranim.get()}")
    modImplementation("maven.modrinth:better-combat:${libs.versions.bettercombat.get()}-fabric")
    compileOnly(project(":common"))
}

loom {
    accessWidenerPath = file("src/main/resources/arcane_divinity.accesswidener")

    mixin.defaultRefmapName.set("${modId}.refmap.json")

    runs {
        named("client") {
            configName = "Fabric Client"

            client()
            ideConfigGenerated(true)
            runDir("runs/" + name)
            programArg("--username=Dev")
        }

        named("server") {
            configName = "Fabric Server"

            server()
            ideConfigGenerated(true)
            runDir("runs/" + name)
        }
    }
}

tasks.withType<JavaCompile>().configureEach {
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
    exclude("**/accesstransformer-nf.cfg")
}

java {
    // Loom will automatically attach sourcesJar to a RemapSourcesJar task and to the "build" task
    // if it is present.
    // If you remove this line, sources will not be generated.
    withSourcesJar()

    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

publishing {
    publications {
        create<MavenPublication>("arcane_divinity") {
            from(components["java"])
            artifactId = base.archivesName.get()
        }
    }
}