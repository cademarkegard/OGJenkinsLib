# OpenGov Jenkins Library

## Table of contents

<!-- toc -->

- [Introduction](#introduction)
- [Boilerplate Jenkinsfile](#boilerplate-jenkinsfile)

<!-- tocstop -->

## Introduction
The pipeline library will provide developers the tools they need to construct:
 - CI Pipelines
 - Automation Pipelines

## Boilerplate Jenkinsfile
The following example shows the kind of boilerplate code that should be defined in a Jenkinsfile.
```groovy
def version = '1'
@Library("OGJenkinsLib@${version}")
```

