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
    script.podTemplate(yaml: yaml) {
      script.node(nodeName) {
        closure()
      }
    }
  }
}
