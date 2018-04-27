package com.opengov

class OGConstants {
  static final String AGENT_POD_TEMPLATE_NAME_DEFAULT = 'default'
  static final String AGENT_POD_TEMPLATE_LABEL_DEFAULT = 'jenkins-jenkins-slave'
  static final String AGENT_CONTAINER_NAME = 'jnlp'
  static final String AGENT_CONTAINER_IMAGE = 'jenkins/jnlp-slave'
  static final String AGENT_CONTAINER_TAG = '3.10-1-alpine'
  static final String AGENT_CONTAINER_CPU_MAX = '450m'
  static final String AGENT_CONTAINER_MEMORY_MAX = '1G'
  static final String DOCKER_CONTAINER_IMAGE = 'docker'
  static final String DOCKER_CONTAINER_TAG = '17.09'
  static final String DOCKER_CONTAINER_COMMAND = 'cat'
  static final String DOCKER_CONTAINER_CPU_MAX = '450m'
  static final String DOCKER_CONTAINER_MEMORY_MAX = '3G'
  static final String DOCKER_SOCK_PATH = '/var/run/docker.sock'
  static final String DOCKERHUB_CREDENTIALS_ID = 'dockerhub-push'
  static final String DOCKERHUB_REGISTRY_HOSTNAME = 'https://index.docker.io/v1/'
  static final String DOCKERHUB_ORGANIZATION = 'opengovorg'
}
