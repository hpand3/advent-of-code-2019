package example

import scala.io.Source

class Spaceship(componentMassesFileName: String) {
  def getFuel: Int = {
    val componentMassesFile = Source.fromResource(componentMassesFileName)
    val masses = componentMassesFile.getLines()
    masses.map(m => {
      getFuelForComponent(m.toInt)
    }).sum
  }

  private def getFuelForComponent(mass: Int): Int = {
    val calculatedFuel = (mass / 3) - 2
    if (calculatedFuel < 0) 0 else calculatedFuel + getFuelForComponent(calculatedFuel)
  }
}