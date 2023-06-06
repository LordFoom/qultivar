// build.gradle.kts
tasks.register("clean") {
    doLast {
        val appDir = project.projectDir.resolve("src")
        println("Cleaning Node application in: $appDir")

        project.exec {
            workingDir = appDir
            commandLine("rm", "-rf", "build/")
        }
    }
}

tasks.register("build") {
    doLast {
        val appDir = project.projectDir.resolve("src")
        println("Building Node application in: $appDir")

        project.exec {
            workingDir = appDir
            commandLine("npm", "run", "build")
        }
    }
}

tasks.register("run") {
    doLast {
        val appDir = project.projectDir.resolve("src")
        println("Building Node application in: $appDir")

        project.exec {
            workingDir = appDir
            commandLine("npm", "run", "start")
        }
    }
}

tasks.getByName("run").dependsOn("build")
