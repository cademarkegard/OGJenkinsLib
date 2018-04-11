package com.opengov

import com.cloudbees.groovy.cps.NonCPS

import java.net.InetAddress

import com.opengov.OGRegistry

class OGLocalRegistryImpl extends OGRegistry {
  String registryPort

  OGLocalRegistryImpl(script, registryHostname, registryPort) {
    super(script, registryHostname)
    this.registryPort = registryPort
  }

  @NonCPS
  def push(image, tag) {
    this.script.sh("docker push ${this.registryHostname}:${this.registryPort}/${image}:${tag}")
  }

  @NonCPS
  def pull(image, tag) {
    this.script.sh("docker pull ${this.registryHostname}:${this.registryPort}/${image}:${tag}")
  }
}
