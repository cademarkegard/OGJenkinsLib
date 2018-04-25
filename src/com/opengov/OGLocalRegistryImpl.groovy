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

  def push(image, tag) {
    this.script.sh("docker tag ${image}:${tag} ${this.registryHostname}:${this.registryPort}/${image}:${tag}")
    this.script.sh("docker push ${this.registryHostname}:${this.registryPort}/${image}:${tag}")
  }

  def pushOGImage(image, tag) {
    this.push("${OGConstants.DOCKERHUB_ORGANIZATION}/${image}", tag)
  }

  def pull(image, tag) {
    this.script.sh("docker pull ${this.registryHostname}:${this.registryPort}/${image}:${tag}")
  }

  def pullOGImage(image, tag) {
    this.pull("${OGConstants.DOCKERHUB_ORGANIZATION}/${image}", tag)
  }
}
