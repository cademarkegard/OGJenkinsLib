package com.opengov.ogjenkinslib

/**
 * Helpers for pushing to Docker registries
 */
class OGLocalRegistryImpl extends OGRegistry {
    String registryPort

    OGLocalRegistryImpl(Script script, String registryHostname, String registryPort) {
      super(script, registryHostname)
        this.registryPort = registryPort
    }

    @Override
    void pull(String image, String tag) {
        this.script.sh("docker pull ${this.registryHostname}:${this.registryPort}/${image}:${tag}")
    }

    void pullOGImage(String image, String tag) {
        this.pull("${OGConstants.DOCKERHUB_ORGANIZATION}/${image}", tag)
    }

    @Override
    void push(String image, String tag) {
        this.script.sh("docker tag ${image}:${tag} ${this.registryHostname}:${this.registryPort}/${image}:${tag}")
        this.script.sh("docker push ${this.registryHostname}:${this.registryPort}/${image}:${tag}")
    }

    void pushOGImage(String image, String tag) {
        this.push("${OGConstants.DOCKERHUB_ORGANIZATION}/${image}", tag)
    }
}
