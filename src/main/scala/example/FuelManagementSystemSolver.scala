package example

import scala.collection.mutable
import scala.collection.mutable.Map

class FuelManagementSystemSolver {

  var cursor: (Int, Int) = (0,0)

  def findClosestIntersection(intersection: Array[(Int, Int)]): (Int, Int) = {
    intersection.minBy(manhattanDistance)
  }

  def manhattanDistance(coord: (Int, Int)): Int = Math.abs(coord._1) + Math.abs(coord._2)

  def resetCursor(state: mutable.Map[(Int, Int), Int]): mutable.Map[(Int, Int), Int] = {
    setCursorPosition(0,0, state)
  }

  def setCursorPosition(x: Int, y: Int, state: mutable.Map[(Int, Int), Int]): mutable.Map[(Int, Int), Int] = {
    cursor = (x, y)
    state += (cursor -> 1)
  }

  def moveRight(distance: Int, state: mutable.Map[(Int, Int), Int]): mutable.Map[(Int, Int), Int] = {
    val startX = cursor._1
    val endX = startX + distance
    var finalState = state
    for (positionX <- (startX + 1) to endX) {
      finalState = setCursorPosition(positionX, cursor._2, state)
    }
    finalState
  }

  def moveLeft(distance: Int, state: mutable.Map[(Int, Int), Int]): mutable.Map[(Int, Int), Int] = {
    val startX = cursor._1
    val endX = startX - distance
    var finalState = state
    for (positionX <- (startX - 1) to endX by -1) {
      finalState = setCursorPosition(positionX, cursor._2, state)
    }
    finalState
  }

  def moveUp(distance: Int, state: mutable.Map[(Int, Int), Int]): mutable.Map[(Int, Int), Int] = {
    val startY = cursor._2
    val endY = startY + distance
    var finalState = state
    for (positionY <- (startY + 1) to endY) {
      finalState = setCursorPosition(cursor._1, positionY, state)
    }
    finalState
  }

  def moveDown(distance: Int, state: mutable.Map[(Int, Int), Int]): mutable.Map[(Int, Int), Int] = {
    val startY = cursor._2
    val endY = startY - distance
    var finalState = state
    for (positionY <- (startY - 1) to endY by -1) {
      setCursorPosition(cursor._1, positionY, state)
    }
    finalState
  }

  def getIntersection(wires: Array[String]): Array[(Int, Int)] = {
    val wiresWithDirections: Array[Array[String]] = wires.map(w => w.split(","))

    var wireNumber = 0
    var state: mutable.Map[(Int, Int), Int] = mutable.Map.empty[(Int, Int), Int]

    for (wireDirection <- wiresWithDirections) {
      var wireState = mutable.Map.empty[(Int, Int), Int]
      wireState = resetCursor(wireState)
      for (movement <- wireDirection) {
        val (direction, distanceStr) = movement.splitAt(1)
        val distance = distanceStr.toInt

        wireState = direction match {
          case "R" => moveRight(distance, wireState)
          case "L" => moveLeft(distance, wireState)
          case "U" => moveUp(distance, wireState)
          case "D" => moveDown(distance, wireState)
        }
        wireNumber += 1
      }
      state = state ++ wireState.map({
        case (k, v) => k -> (v + state.getOrElse(k, 0))
      })
    }

    state.filter(x => x._2 > 1 && x._1 != (0,0)).keys.toArray
  }
}
