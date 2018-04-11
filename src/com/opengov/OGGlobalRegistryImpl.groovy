package com.opengov

import com.cloudbees.groovy.cps.NonCPS

import com.opengov.OGConstants
import com.opengov.OGRegistry

class OGGlobalRegistryImpl extends OGRegistry {
  OGGlobalRegistryImpl(script) {
    super(script, OGConstants.DOCKERHUB_REGISTRY_HOSTNAME)
  }

  @NonCPS
  def push(image, tag) {
    this.script.docker.withRegistry(this.registryHostname, OGConstants.DOCKERHUB_CREDENTIALS_ID) {
      this.script.sh "docker push '${OGConstants.DOCKERHUB_ORGANIZATION}/${image}:${tag}'"
    }
  }

  @NonCPS
  def pull(image, tag) {
    this.script.docker.withRegistry(this.registryHostname, OGConstants.DOCKERHUB_CREDENTIALS_ID) {
      this.script.sh "docker pull '${OGConstants.DOCKERHUB_ORGANIZATION}/${image}:${tag}'"
    }
  }
}
