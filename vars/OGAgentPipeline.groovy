package com.opengov

import com.opengov.OGContainer
import com.opengov.OGAgentContainerImpl
import com.opengov.OGDockerContainerImpl
import com.opengov.OGConstants

import com.opengov.OGPod

def call(List<OGContainer> containers = [], List volumes = [], closure) {
  def allContainers = containers + [
    // new OGDockerContainerImpl(this, 'docker'),
    new OGAgentContainerImpl(this)
  ]

  def allVolumes = volumes + [[hostPath: OGConstants.DOCKER_SOCK_PATH, mountPath: OGConstants.DOCKER_SOCK_PATH]]
  def name = "agentPod-${env.JOB_NAME}-${UUID.randomUUID().toString().substring(0,8)}".replace('/', '-')

  def yaml = """
  apiVersion: v1
  kind: Pod
  spec:
      containers:
        - name: docker
          image: docker:17.09
          command: ['cat']
          env:
            - name: DOCKER_HOST
              value: tcp://localhost:2375
        - name: dind-daemon
          image: docker:17.09-dind
          securityContext:
              privileged: true
          volumeMounts:
            - name: docker-graph-storage
              mountPath: /var/lib/docker
      volumes:
        - name: docker-graph-storage
          emptyDir: {}
  """
  OGPod.runYAML(this, name, name, yaml, closure)
}
