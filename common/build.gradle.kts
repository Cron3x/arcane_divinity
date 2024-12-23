plugins {
    id("arcane_divinity-convention")

    alias(libs.plugins.moddevgradle)
}

version = libs.versions.arcane.divinity.get()

base {
    archivesName = "arcane_divinity-common-${libs.versions.minecraft.asProvider().get()}"
}

neoForge {
    neoFormVersion = libs.versions.neoform.get()
    validateAccessTransformers = true
    accessTransformers.files.setFrom("src/main/resources/META-INF/accesstransformer-nf.cfg")

    parchment.minecraftVersion.set(libs.versions.parchment.minecraft.get())
    parchment.mappingsVersion.set(libs.versions.parchment.asProvider().get())
}

dependencies {
    compileOnly(libs.mixin)
    compileOnly(libs.mixinextras.common)

    //Geckolib
    implementation( "software.bernie.geckolib:geckolib-common-${libs.versions.minecraft.asProvider().get()}:${libs.versions.geckolib.asProvider().get()}")

    //Veil
    implementation("foundry.veil:veil-common-${libs.versions.veil.minecraft.get()}:${libs.versions.veil.asProvider().get()}") {
        exclude("maven.modrinth")
    }

    //Accessories
    //compileOnly("io.wispforest:accessories-common:${accessories_version}")

    //Better Combat
    compileOnly("me.shedaniel.cloth:cloth-config:${libs.versions.clothconfig.get()}") {
        exclude("net.fabricmc")
    }
    implementation("dev.kosmx.player-anim:player-animation-lib:${libs.versions.playeranim.get()}"){
        exclude("net.fabricmc")
    }
    //implementation("maven.modrinth:better-combat:${libs.versions.bettercombat.get()}")
}

repositories {
    mavenLocal()
    maven {
        name = "KosmX's maven"
        url = uri("https://maven.kosmx.dev/")
    }
    maven {
        name = "Shedaniel Maven"
        url = uri("https://maven.shedaniel.me/")
    }
    maven {
        name = "terraformersmc Maven"
        url = uri("https://maven.terraformersmc.com/releases/")
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