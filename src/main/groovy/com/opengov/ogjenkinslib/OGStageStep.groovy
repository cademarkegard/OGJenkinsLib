package com.opengov.ogjenkinslib

/**
 * Interface for creating steps
 */
interface OGStageStep {
    void run(Script script, String name, Closure closure)
}
