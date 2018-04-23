package com.opengov

import com.cloudbees.groovy.cps.NonCPS

import com.opengov.OGConstants
import com.opengov.OGRegistry

class OGGlobalRegistryImpl extends OGRegistry {
  OGGlobalRegistryImpl(script) {
    super(script, OGConstants.DOCKERHUB_REGISTRY_HOSTNAME)
  }

  def push(image, tag) {
    this.script.docker.withRegistry(this.registryHostname, OGConstants.DOCKERHUB_CREDENTIALS_ID) {
      this.script.sh "docker push '${image}:${tag}'"
    }
  }

  def pushOGImage(image, tag) {
    this.push("${OGConstants.DOCKERHUB_ORGANIZATION}/${image}", tag)
  }

  def pull(image, tag) {
    this.script.docker.withRegistry(this.registryHostname, OGConstants.DOCKERHUB_CREDENTIALS_ID) {
      this.script.sh "docker pull '${image}:${tag}'"
    }
  }

  def pullOGImage(image, tag) {
    this.pull("${OGConstants.DOCKERHUB_ORGANIZATION}/${image}", tag)
  }
}
