package com.opengov.ogjenkinslib

import com.opengov.ogjenkinslib.OGContainer
import com.opengov.ogjenkinslib.OGAgentContainerImpl
import com.opengov.ogjenkinslib.OGDockerContainerImpl
import com.opengov.ogjenkinslib.OGConstants
import com.opengov.ogjenkinslib.OGPod


OGPod call(List<OGContainer> containers = [], List volumes = [], Closure closure) {
  List<OGContainer> allContainers = containers + [
          new OGDockerContainerImpl(this, 'docker'),
          new OGAgentContainerImpl(this)
  ]

  // we remove non-alphanumerics besides dashes to validate for k8s podname
  String jobName = env.JOB_NAME.take(36).replaceAll(/[^a-zA-Z0-9-]/, "")

  List allVolumes = volumes + [[hostPath: OGConstants.DOCKER_SOCK_PATH, mountPath: OGConstants.DOCKER_SOCK_PATH]]

  // max length of the name can only be 63 characters
  // jenkins/agent-pod-${jobName}-${uuid}
  String name = "agentPod-${jobName}-${UUID.randomUUID().toString().take(8)}".replace('/', '-')

  OGPod.run(this, 'agentPod', name, allContainers, allVolumes, closure)
}
