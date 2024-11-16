plugins {
    id("arcane_divinity-convention")

    alias(libs.plugins.moddevgradle)
}

version = libs.versions.arcanedivinity.get()

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

    implementation( "software.bernie.geckolib:geckolib-common-${libs.versions.minecraft.asProvider().get()}:${libs.versions.geckolib.asProvider().get()}") //TODO: Make Updatable
    //compileOnly(libs.iris)
}

repositories {
    maven {
        name = "GeckoLib"
        url = uri("https://dl.cloudsmith.io/public/geckolib3/geckolib/maven/")
        content {
            includeGroup("software.bernie.geckolib")
        }
    }
    mavenLocal()
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