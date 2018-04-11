package com.opengov

import com.opengov.OGConstants
import com.opengov.OGContainer

class OGAgentContainerImpl extends OGContainer {

  OGAgentContainerImpl(script) {
    super(script, OGConstants.AGENT_CONTAINER_NAME, OGConstants.AGENT_CONTAINER_IMAGE, OGConstants.AGENT_CONTAINER_TAG)
    this.withCpuRequest(OGConstants.AGENT_CONTAINER_CPU_MAX)
        .withCpuLimit(OGConstants.AGENT_CONTAINER_CPU_MAX)
        .withMemoryRequest(OGConstants.AGENT_CONTAINER_MEMORY_MAX)
        .withMemoryLimit(OGConstants.AGENT_CONTAINER_MEMORY_MAX)
  }
}
