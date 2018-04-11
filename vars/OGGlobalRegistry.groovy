package com.opengov

import com.opengov.OGConstants
import com.opengov.OGDockerContainerImpl
import com.opengov.OGGlobalRegistryImpl

def call() {
  String ACTION_REGISTRY_PUSH = 'push'
  String ACTION_REGISTRY_PULL = 'pull'

  def registryAction = { action, image, tag ->
    milestone()
    lock("${image}-${env.JOB_NAME}-global-registry") {
      new OGDockerContainerImpl(this, 'docker').run {
        def globalRegistry = new OGGlobalRegistryImpl(this)
        switch(action) {
          case ACTION_REGISTRY_PUSH:
            globalRegistry.push(image, tag)
            break
          case ACTION_REGISTRY_PULL:
            globalRegistry.pull(image, tag)
            break
        }
      }
    }
  }

  [
    pull: { image, tag -> registryAction(ACTION_REGISTRY_PULL, image, tag) },
    push: { image, tag -> registryAction(ACTION_REGISTRY_PUSH, image, tag) }
  ]
}
