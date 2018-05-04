package com.opengov

import com.opengov.OGContainer
import com.opengov.OGConstants

class OGPod {
  static def run(script, String templateName, String templateLabel, String nodeName, List<OGContainer> containers = [], List volumes = [], closure) {
    def sidecarContainers = [:]
    def containerTemplates = []
    containers.each {
      containerTemplates << it.getContainerTemplate()
      sidecarContainers[it.getName()] = it
    }

    def volumeTemplates = volumes.collect {
      script.hostPathVolume(hostPath: it.hostPath, mountPath: it.mountPath)
    }

    script.podTemplate(label: templateLabel, containers: containerTemplates, volumes: volumeTemplates) {
      script.node(nodeName) {
        closure(sidecarContainers)
      }
    }
  }

  static def runYAML(script, String templateLabel, String nodeName, String yaml, closure) {
  def containers = [
    script.containerTemplate(name: 'docker', image: 'docker:17.09', command: 'cat', ttyEnabled: true, envVars: [script.envVar(key: 'DOCKER_HOST', value: 'tcp://localhost:2375')]),
    script.containerTemplate(name: 'dind-daemon', image: 'docker:17.09-dind', ttyEnabled: true, privileged: true)
  ]

  script.podTemplate(label: templateLabel, containers: containers, volumes: [script.emptyDirVolume(mountPath: '/var/lib/docker', memory: false)]) {
      script.node(nodeName) {
        closure()
      }
    }
  }
}
