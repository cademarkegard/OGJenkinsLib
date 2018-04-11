package com.opengov

import com.opengov.OGStageStep

class OGTestStageStepImpl implements OGStageStep {
  static def run(script, name, closure) {

    script.stage(name) {
      closure()
    }
  }
}
