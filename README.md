# AirServers

## Overview
Miniature collection of servers for NewAir. TODO: Link credentials from OAuth to Resources.

## Docker
Build a project using /DockerBuilder/dockerbuilder.java

Arguments:

* REQUIRED: --subproject=[OAuth | Resource | etc]
* OPTIONAL: --javaLayerTag=(DEFAULT: openjdk:21)
* Everything else goes to 'docker build' - can add '-t imagename:tag', etc.