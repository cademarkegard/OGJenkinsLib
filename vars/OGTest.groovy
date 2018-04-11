package com.opengov

import com.opengov.OGTestStageStepImpl
import com.opengov.OGConstants

def call(serviceName, stageName, closure) {
  milestone()
  lock(resource: env.JOB_NAME, inversePrecedence: true) {
    OGTestStageStepImpl.run(this, stageName, closure)
  }
}
