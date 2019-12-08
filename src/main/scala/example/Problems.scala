package example

import scala.io.Source
import scala.util.control.Breaks

object Problem1 {
  def main(args: Array[String]) {
    val spaceship = new Spaceship("componentmasses.txt")
    println(spaceship.getFuel)
  }
}

object Problem2 {
  def main(args: Array[String]) {
    val spaceshipComputer = new SpaceshipComputer

    val opcodeFile = Source.fromResource("opcodes.csv")
    val opcodeStr = opcodeFile.getLines().toArray
    val storage = opcodeStr(0).split(",").map(_.toInt)

    println(spaceshipComputer.processOpcode(storage)(0))
  }
}

object Problem2_2 {

  def main(args: Array[String]): Unit = {
    val spaceshipComputer = new SpaceshipComputer

    val opcodeFile = Source.fromResource("opcodes.csv")
    val opcodeStr = opcodeFile.getLines().toArray
    val storage = opcodeStr(0).split(",").map(_.toInt)

    val loop = new Breaks
    var noun = 0
    var verb = 0

    loop.breakable({
      for (n <- 0 until 100) {
        for (v <- 0 until 100) {
          noun = n
          verb = v
          storage(1) = noun
          storage(2) = verb
          val output = spaceshipComputer.processOpcode(storage.clone())
          if (output(0) == 19690720) {
            loop.break()
          }
        }
      }
    })
    println(s"noun: $noun, verb: $verb")
    val answer = 100 * noun + verb
    println(s"Answer: $answer")
  }
}

object Problem3 {
  def main(args: Array[String]) {
    problemPart1()
    problemPart2()
  }

  def problemPart1(): Unit = {
    val fuelManagementSystemFile = Source.fromResource("fuelmanagementsystem.txt")
    val fuelManagementSystemWires = fuelManagementSystemFile.getLines().toArray

    val fuelManagementSystemSolverTest = new FuelManagementSystemSolver
    val intersection = fuelManagementSystemSolverTest.getIntersection(fuelManagementSystemWires)
    val closestIntersection = fuelManagementSystemSolverTest.findClosestIntersection(intersection)

    println(Math.abs(closestIntersection._1) + Math.abs(closestIntersection._2))
  }

  def problemPart2(): Unit = {
    val fuelManagementSystemFile = Source.fromResource("fuelmanagementsystem.txt")
    val fuelManagementSystemWires = fuelManagementSystemFile.getLines().toArray

    val fuelManagementSystemSolver = new FuelManagementSystemSolver
    val intersection = fuelManagementSystemSolver.getIntersection(fuelManagementSystemWires)
    val intersectionWithMinimumSteps = fuelManagementSystemSolver.findIntersectionAtMinimumSteps(intersection)

    println(intersectionWithMinimumSteps._2._2)
  }
}

object Problem4 {
  def main(args: Array[String]) {
    val start = 193651
    val end = 649729

    val passwordCriteria = new PasswordCriteria
    val validPasswordsLength = passwordCriteria.getPossibleCombinations(start, end).length
    println(s"Part 1: Number of valid passwords are $validPasswordsLength")
  }
}

object Problem5 {
  def main(args: Array[String]) {
    val spaceshipComputer = new SpaceshipComputer

    val opcodeFile = Source.fromResource("opcodes_problem5.csv")
    val opcodeStr = opcodeFile.getLines().toArray
    val storage = opcodeStr(0).split(",").map(_.toInt)

    spaceshipComputer.processOpcode(storage)
  }
}
