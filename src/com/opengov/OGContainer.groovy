package com.opengov

class OGContainer {
  String name
  String image
  String tag

  OGContainer(String name, String image, String tag) {
    this.name = name
    this.image = image
    this.tag = tag
  }

  def getName() {
    return this.name
  }

  def getImage() {
    return this.image
  }

  def getTag() {
    return this.tag
  }

  def run(closure) {
    container(this.getName(), closure)
  }
}
