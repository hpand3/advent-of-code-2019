package example

import org.scalatest.flatspec.AnyFlatSpec

class SpaceshipTest extends AnyFlatSpec {
  it should "return 2 fuel for the spaceship give test input" in {
    val spaceship = new Spaceship("testinputwithonemass.txt")
    val fuel = spaceship.getFuel
    assert(fuel == 2)
  }

  it should "return 966 fuel for a module of mass 1969" in {
    val spaceship = new Spaceship("testinputwithonebigmass.txt")
    val fuel = spaceship.getFuel
    assert(fuel == 966)
  }
}
