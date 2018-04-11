package com.opengov

import com.opengov.OGConstants
import com.opengov.OGContainer

class OGDockerContainerImpl extends OGContainer {

  OGDockerContainerImpl(script, name) {
    super(script, name, OGConstants.DOCKER_CONTAINER_IMAGE, OGConstants.DOCKER_CONTAINER_TAG)
    this.withCommand(OGConstants.DOCKER_CONTAINER_COMMAND)
        .withCpuRequest(OGConstants.DOCKER_CONTAINER_CPU_MAX)
        .withCpuLimit(OGConstants.DOCKER_CONTAINER_CPU_MAX)
        .withMemoryRequest(OGConstants.DOCKER_CONTAINER_MEMORY_MAX)
        .withMemoryLimit(OGConstants.DOCKER_CONTAINER_MEMORY_MAX)
  }
}
