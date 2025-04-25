package com.vladislaviliev.airservers.dockerBuilder

class Args(args: Array<String>) {

    private val prefixSubproject = "--subproject="
    private val prefixJavaLayer = "--javaLayerTag="

    private val defaultJavaLayer = "eclipse-temurin:24.0.1_9-jre"

    private val map = readArgs(args)
    val subproject by map
    val javaTag by map
    val dockerBuildArgs by map

    private fun getArgOrNull(arg: String, args: Array<String>) =
        args.firstOrNull { it.startsWith(arg) }?.substringAfter("=")

    private fun getFloatingArgs(args: Array<String>) = args.filterNot {
        it.startsWith(prefixSubproject) || it.startsWith(prefixJavaLayer)
    }.joinToString(" ")

    private fun info() = arrayOf(
        "\n",
        "Arguments:",
        "REQUIRED: $prefixSubproject [OAuth | Resource | etc]",
        "OPTIONAL: $prefixJavaLayer (DEFAULT: $defaultJavaLayer)",
        "Everything else goes to 'docker build'"
    ).joinToString("\n")

    private fun readArgs(args: Array<String>): MutableMap<String, String> {
        val argSubProjectName = getArgOrNull(prefixSubproject, args) ?: throw IllegalArgumentException(info())
        val argJavaLayerTag = getArgOrNull(prefixJavaLayer, args) ?: defaultJavaLayer
        val dockerBuildArgs = getFloatingArgs(args)
        return mutableMapOf(
            ::subproject.name to argSubProjectName,
            ::javaTag.name to argJavaLayerTag,
            ::dockerBuildArgs.name to dockerBuildArgs
        )
    }
}