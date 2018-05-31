package com.opengov.ogjenkinslib

import com.cloudbees.groovy.cps.NonCPS
import org.csanchez.jenkins.plugins.kubernetes.ContainerTemplate

/**
 * Creates a container that can be used in a pod
 */
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

    Script script

    OGContainer(Script script, String name, String image, String tag) {
        this.script = script
        this.image = image
        this.name = name
        this.tag = tag
    }

    ContainerTemplate getContainerTemplate() {
        this.script.containerTemplate(
              name:this.name,
              image:"${this.image}:${this.tag}",
              ttyEnabled:this.ttyEnabled,
              command:this.command,
              resourceRequestCpu:this.cpuRequest,
              resourceLimitCpu:this.cpuLimit,
              resourceRequestMemory:this.memoryRequest,
              resourceLimitMemory:this.memoryLimit
        )
    }

    void run(Closure closure) {
        this.script.container(this.name) {
            closure()
        }
    }

    // Builder
    @NonCPS
    OGContainer withCommand(String command) {
        this.command = command
        this
    }

    @NonCPS
    OGContainer withTtyEnabled(Boolean ttyEnabled) {
        this.ttyEnabled = ttyEnabled
        this
    }

    @NonCPS
    OGContainer withCpuRequest(String cpuRequest) {
        this.cpuRequest = cpuRequest
        this
    }

    @NonCPS
    OGContainer withCpuLimit(String cpuLimit) {
        this.cpuLimit = cpuLimit
        this
    }

    @NonCPS
    OGContainer withMemoryRequest(String memoryRequest) {
        this.memoryRequest = memoryRequest
        this
    }

    @NonCPS
    OGContainer withMemoryLimit(String memoryLimit) {
        this.memoryLimit = memoryLimit
        this
    }
}
