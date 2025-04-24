package com.vladislaviliev.airservers.dockerBuilder

class Docker {

    private fun readDockerTemplate(): String {
        val inStream = this::class.java.classLoader.getResourceAsStream("docker_template")
            ?: throw IllegalStateException("Can't find docker_template")
        val reader = inStream.bufferedReader()
        return reader.readText().also { reader.close() }
    }

    fun buildDockerfileContents(javaVer: String, mainClass: String) = readDockerTemplate()
        .replace("$(ARG_JAVA_VER)", javaVer)
        .replace("$(ARG_MAIN_CLASS)", mainClass)
}