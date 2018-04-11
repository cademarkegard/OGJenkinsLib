package com.opengov

import com.cloudbees.groovy.cps.NonCPS

abstract class OGRegistry {
  String registryHostname

  def script

  OGRegistry(script, registryHostname) {
    this.script = script
    this.registryHostname = registryHostname
  }

  abstract def push(image, tag)
  abstract def pull(image, tag)
}
