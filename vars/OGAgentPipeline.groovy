package com.opengov

import com.opengov.OGContainer
import com.opengov.OGAgentContainerImpl
import com.opengov.OGDockerContainerImpl
import com.opengov.OGConstants

import com.opengov.OGPod

def call(List<OGContainer> containers = [], List volumes = [], closure) {
  def allContainers = containers + [
    new OGDockerContainerImpl(this, 'docker'),
    new OGAgentContainerImpl(this)
  ]

  def allVolumes = volumes + [[hostPath: OGConstants.DOCKER_SOCK_PATH, mountPath: OGConstants.DOCKER_SOCK_PATH]]
  def name = "agentPod-${env.JOB_NAME}-${UUID.randomUUID().toString().substring(0,8)}"

  OGPod.run(this, 'agentPod', name, name, allContainers, allVolumes, closure)
}
