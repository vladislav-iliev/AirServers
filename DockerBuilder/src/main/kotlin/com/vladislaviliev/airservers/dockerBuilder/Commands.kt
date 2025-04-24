package com.vladislaviliev.airservers.dockerBuilder

import java.nio.file.Path
import kotlin.io.path.pathString

class Commands {

    private fun runCmd(cmd: List<String>) {
        val resultCode = ProcessBuilder(cmd)
            .redirectOutput(ProcessBuilder.Redirect.INHERIT)
            .redirectError(ProcessBuilder.Redirect.INHERIT)
            .start()
            .waitFor()
        if (resultCode != 0) throw RuntimeException("Command '$cmd' failed with code $resultCode")
    }

    fun buildSubProject(rootProjectPath: Path, subprojectPath: Path) {
        val cmd = listOf(
            rootProjectPath.resolve("mvnw").pathString,
            "-f",
            subprojectPath.resolve("pom.xml").pathString,
            "package",
            "-Dmaven.test.skip"
        )
        runCmd(cmd)
    }

    fun buildDockerImage(buildArgs: String, buildContextPath: Path) {
        val cmd = mutableListOf<String>()
        cmd.add("docker")
        cmd.add("build")
        if (buildArgs.isNotEmpty()) cmd.addAll(buildArgs.split(" "))
        cmd.add(buildContextPath.pathString)
        runCmd(cmd)
    }
}