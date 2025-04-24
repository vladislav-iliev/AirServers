package com.vladislaviliev.airservers.dockerBuilder

fun main(shellArgs: Array<String>) {
    val args = Args(shellArgs)
    val files = Files(args.subproject)

    Commands().buildSubProject(files.rootProjectPath, files.subprojectPath)
    files.extractJar()
    val mainClass = files.getMainClass()
    val dockerfileContents = Docker().buildDockerfileContents(args.javaTag, mainClass)
    val buildContextPath = files.createBuildContext(dockerfileContents)
    Commands().buildDockerImage(args.dockerBuildArgs, buildContextPath)
}