package com.opengov.ogjenkinslib

/**
 * Implements
 */
class OGTestStageStepImpl implements OGStageStep {
    void run(Script script, String name, Closure closure) {
        script.stage(name) {
            closure()
        }
    }
}
