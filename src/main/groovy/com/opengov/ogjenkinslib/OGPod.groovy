package com.opengov.ogjenkinslib

import org.csanchez.jenkins.plugins.kubernetes.ContainerTemplate

/**
 * Wrapper for creating Pods
 */
class OGPod {
    static def run(Script script, String templateLabel, String nodeName,
                 List<OGContainer> containers = [], List volumes = [], Closure closure) {

        Map<String, OGContainer> sidecarContainers = [:]
        List<ContainerTemplate> containerTemplates = []

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
}
