package com.vladislaviliev.airservers.dockerBuilder

import java.nio.file.Files
import java.nio.file.Path
import java.util.jar.JarFile
import kotlin.io.path.pathString
import kotlin.io.path.useLines
import kotlin.jvm.optionals.getOrNull

class Files(subprojectName: String) {

    val rootProjectPath = findRootPath()
    val subprojectPath: Path = rootProjectPath.resolve(subprojectName)
    private val buildPath: Path = subprojectPath.resolve("target")
    private val extractedJarPath: Path = Files.createTempDirectory("docker-builder-jar-")

    private fun findRootPath(): Path {
        val jarPathStr = this::class.java.protectionDomain.codeSource.location.path
        val projName = "AirServers"
        if (!jarPathStr.contains(projName)) throw IllegalStateException("Must be within $projName")
        return Path.of(jarPathStr.substringBefore(projName) + projName)
    }

    private fun getJarPath(): Path = Files.walk(buildPath, 1)
        .filter { it.toString().endsWith(".jar") }
        .findFirst()
        .getOrNull()
        ?: throw IllegalStateException("Can't find jar in ${buildPath.pathString}")

    fun extractJar() {
        val jarFile = JarFile(getJarPath().toFile())
        jarFile.use { jar ->
            jar.entries().asSequence().filter { !it.isDirectory }.forEach { entry ->
                jar.getInputStream(entry).use { entryInStream ->
                    val filePath = extractedJarPath.resolve(entry.name)
                    Files.createDirectories(filePath.parent)
                    Files.copy(entryInStream, filePath)
                }
            }
        }
    }

    fun createBuildContext(dockerfileContents: String): Path {
        Files.createFile(extractedJarPath.resolve("Dockerfile")).toFile().writeText(dockerfileContents)
        return extractedJarPath
    }

    fun getMainClass() = extractedJarPath.resolve("META-INF").resolve("MANIFEST.MF")
        .useLines { seq -> seq.firstOrNull { line -> line.startsWith("Start-Class:") } }
        ?.substringAfter("Start-Class:")
        ?.trim()
        ?: throw IllegalStateException("Can't find Start-Class in META-INF/MANIFEST.MF")
}