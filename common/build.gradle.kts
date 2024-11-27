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
    
    implementation( "software.bernie.geckolib:geckolib-common-${libs.versions.minecraft.asProvider().get()}:${libs.versions.geckolib.asProvider().get()}")

    implementation("foundry.veil:veil-common-${libs.versions.veil.minecraft.get()}:${libs.versions.veil.asProvider().get()}") {
        exclude("maven.modrinth")
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