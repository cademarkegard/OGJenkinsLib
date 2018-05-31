package com.opengov.ogjenkinslib

abstract class OGRegistry {
     String registryHostname
     Script script

     OGRegistry(Script script, String registryHostname) {
       this.script = script
       this.registryHostname = registryHostname
     }

  abstract void push(String image, String tag)
  abstract void pull(String image, String tag)
}
