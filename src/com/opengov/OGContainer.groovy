package com.opengov

import com.cloudbees.groovy.cps.NonCPS

class OGContainer {
  String name
  String image
  String tag
  String command = null
  String cpuRequest = '250m'
  String cpuLimit = '250m'
  String memoryRequest = '1G'
  String memoryLimit = '1G'

  Boolean ttyEnabled = true

  def script

  OGContainer(script, name, image, tag) {
    this.script = script
    this.image = image
    this.name = name
    this.tag = tag
  }

  def getContainerTemplate() {
    this.script.containerTemplate(name: this.name,
                                  image: "${this.image}:${this.tag}",
                                  ttyEnabled: this.ttyEnabled,
                                  command: this.command,
                                  resourceRequestCpu: this.cpuRequest,
                                  resourceLimitCpu: this.cpuLimit,
                                  resourceRequestMemory: this.memoryRequest,
                                  resourceLimitMemory: this.memoryLimit)
  }

  void run(closure) {
    this.script.container(this.name) {
      closure()
    }
  }

  // Builder
  @NonCPS
  OGContainer withCommand(String command) {
    this.command = command
    return this
  }

  @NonCPS
  OGContainer withTtyEnabled(Boolean ttyEnabled) {
    this.ttyEnabled = ttyEnabled
    return this
  }

  @NonCPS
  OGContainer withCpuRequest(String cpuRequest) {
    this.cpuRequest = cpuRequest
    return this
  }

  @NonCPS
  OGContainer withCpuLimit(String cpuLimit) {
    this.cpuLimit = cpuLimit
    return this
  }

  @NonCPS
  OGContainer withMemoryRequest(String memoryRequest) {
    this.memoryRequest = memoryRequest
    return this
  }

  @NonCPS
  OGContainer withMemoryLimit(String memoryLimit) {
    this.memoryLimit = memoryLimit
    return this
  }
}
