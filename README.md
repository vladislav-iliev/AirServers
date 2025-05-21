# AirServers

## Overview
Miniature collection of servers for NewAir - OAuth for authentication, Resource for air data.

## Docker
Build a docker image for a project using DockerBuilder/dockerbuilder.java

Arguments:

* REQUIRED: --subproject=[OAuth | Resource | etc.]
* OPTIONAL: --javaLayerTag=(DEFAULT: eclipse-temurin:24.0.1_9-jre)
* OPTIONAL: Everything else is forwarded to 'docker build' — can sprinkle amongst the arguments '-t imagename:tag -q', etc.
