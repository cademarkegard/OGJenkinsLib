package com.opengov.ogjenkinslib

class OGGlobalRegistryImpl extends OGRegistry {
    OGGlobalRegistryImpl(Script script) {
      super(script, OGConstants.DOCKERHUB_REGISTRY_HOSTNAME)
    }

    void push(String image, String tag) {
        this.script.docker.withRegistry(this.registryHostname, OGConstants.DOCKERHUB_CREDENTIALS_ID) {
            this.script.sh "docker push '${image}:${tag}'"
         }
    }

    void pushOGImage(String image, String tag) {
        this.push("${OGConstants.DOCKERHUB_ORGANIZATION}/${image}", tag)
    }

    void pull(String image, String tag) {
        this.script.docker.withRegistry(this.registryHostname, OGConstants.DOCKERHUB_CREDENTIALS_ID) {
            this.script.sh "docker pull '${image}:${tag}'"
        }
    }

    void pullOGImage(String image, String tag) {
        this.pull("${OGConstants.DOCKERHUB_ORGANIZATION}/${image}", tag)
     }
}
