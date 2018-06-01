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

  // we remove non-alphanumerics besides dashes to validate for k8s podname
  def jobName = env.JOB_NAME.take(36).replaceAll(/[^a-zA-Z0-9-]/, "")

  def allVolumes = volumes + [[hostPath: OGConstants.DOCKER_SOCK_PATH, mountPath: OGConstants.DOCKER_SOCK_PATH]]

  // max length of the name can only be 63 characters
  // jenkins/agent-pod-${jobName}-${uuid}
  def name = "agentPod-${jobName}-${UUID.randomUUID().toString().take(8)}".replace('/', '-')


  OGPod.run(this, 'agentPod', name, name, allContainers, allVolumes, closure)
}
