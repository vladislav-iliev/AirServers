# AirServers

## Overview
Miniature collection of servers for NewAir. TODO: Link credentials from OAuth to Resources.

## Docker
Build a docker image for a project using /DockerBuilder/dockerbuilder.java

Arguments:

* REQUIRED: --subproject=[OAuth | Resource | etc.]
* OPTIONAL: --javaLayerTag=(DEFAULT: defaultJavaLayer)
* OPTIONAL: Everything else is forwarded to 'docker build' — can sprinkle amongst the arguments '-t imagename:tag -q', etc.
