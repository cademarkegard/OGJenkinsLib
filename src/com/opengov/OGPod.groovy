package com.opengov

import com.opengov.OGContainer

class OGPod {
  String name
  String label
  List<OGContainer> ogContainers
  List volumes

  OGPod(String name, String label, List<OGContainer> ogContainers = [], List volumes = []) {
    this.name = name
    this.label = label
    this.ogContainers = ogContainers
    this.volumes = volumes
  }

  def run(closure) {
    def containerTemplates = this.ogContainers.collect { ogContainer ->
      containerTemplate(ogContainer.getName(), "${ogContainer.getImage()}:${ogContainer.getTag()}", ttyEnabled: true, command: 'cat')
    }

    def volumeTemplates = [
      hostPathVolume(hostPath: '/var/run/docker.sock', mountPath: '/var/run/docker.sock')
    ] + this.volumes

    podTemplate(label: this.label, containers: containers, volumes: volumes) {
      node(this.label, closure)
    }
  }
}
