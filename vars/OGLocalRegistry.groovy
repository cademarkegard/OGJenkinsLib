package com.opengov

import com.opengov.OGConstants
import com.opengov.OGDockerContainerImpl
import com.opengov.OGLocalRegistryImpl
import com.opengov.OGGlobalRegistryImpl

def call() {
  String ACTION_REGISTRY_PUSH = 'push'
  String ACTION_REGISTRY_PULL = 'pull'

  def registryAction = { action, image, tag ->
    milestone()
    lock("${image}-${env.JOB_NAME}-local-registry") {
      new OGDockerContainerImpl(this, 'docker').run {
        def registryIp = retry(10) {
          InetAddress.getByName(env.LOCAL_DOCKER_REGISTRY_HOST)
        }

        def registryHostname = registryIp.getHostAddress()
        def localRegistry = new OGLocalRegistryImpl(this, registryHostname, env.LOCAL_DOCKER_REGISTRY_PORT)
        switch(action) {
          case ACTION_REGISTRY_PUSH:
            localRegistry.push(image, tag)
            break
          case ACTION_REGISTRY_PULL:
            localRegistry.pull(image, tag)
            break
        }
      }
    }
  }

  def localRegistry = new OGGlobalRegistryImpl(this)
  [
    pull: { image, tag -> registryAction(ACTION_REGISTRY_PULL, image, tag) },
    push: { image, tag -> registryAction(ACTION_REGISTRY_PUSH, image, tag) }
  ]
}
