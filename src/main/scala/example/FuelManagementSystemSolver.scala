package example

import scala.collection.mutable

class WireState {
  var state: mutable.Map[(Int, Int), (Int, Int)] = mutable.Map.empty[(Int, Int), (Int, Int)]
  var moveCount: Int = 0
  var cursor: (Int, Int) = (0,0)
}

class FuelManagementSystemSolver {
  def findIntersectionAtMinimumSteps(intersection: Array[((Int, Int), (Int, Int))]): ((Int, Int), (Int, Int)) = {
    intersection.minBy(_._2._2)
  }

  def findClosestIntersection(intersection: Array[((Int, Int), (Int, Int))]): (Int, Int) = {
    intersection.minBy({
      case (k, v) => manhattanDistance(k)
    })._1
  }

  def manhattanDistance(coord: (Int, Int)): Int = Math.abs(coord._1) + Math.abs(coord._2)

  def resetCursor(state: WireState): WireState = {
    setCursorPosition(0,0, state)
  }

  def setCursorPosition(x:Int, y: Int, wireState: WireState): WireState = {
    wireState.cursor = (x, y)
    wireState.state += (wireState.cursor -> (1, wireState.moveCount))
    wireState
  }

  def moveRight(distance: Int, wireState: WireState): WireState = {
    val startX = wireState.cursor._1
    val endX = startX + distance
    var newWireState = wireState
    for (positionX <- (startX + 1) to endX) {
      newWireState.moveCount += 1
      newWireState = setCursorPosition(positionX, newWireState.cursor._2, newWireState)
    }
    newWireState
  }

  def moveLeft(distance: Int, wireState: WireState): WireState = {
    val startX = wireState.cursor._1
    val endX = startX - distance
    var newWireState = wireState
    for (positionX <- (startX - 1) to endX by -1) {
      newWireState.moveCount += 1
      newWireState = setCursorPosition(positionX, newWireState.cursor._2, newWireState)
    }
    newWireState
  }

  def moveUp(distance: Int, wireState: WireState): WireState = {
    val startY = wireState.cursor._2
    val endY = startY + distance
    var newWireState = wireState
    for (positionY <- (startY + 1) to endY) {
      newWireState.moveCount += 1
      newWireState = setCursorPosition(newWireState.cursor._1, positionY, newWireState)
    }
    newWireState
  }

  def moveDown(distance: Int, wireState: WireState): WireState = {
    val startY = wireState.cursor._2
    val endY = startY - distance
    var newWireState = wireState
    for (positionY <- (startY - 1) to endY by -1) {
      newWireState.moveCount += 1
      newWireState = setCursorPosition(newWireState.cursor._1, positionY, newWireState)
    }
    newWireState
  }

  def getIntersection(wires: Array[String]): Array[((Int, Int), (Int, Int))] = {
    val wiresWithDirections: Array[Array[String]] = wires.map(w => w.split(","))

    var wireNumber = 0
    var state: mutable.Map[(Int, Int), (Int, Int)] = mutable.Map.empty[(Int, Int), (Int, Int)]

    for (wireDirection <- wiresWithDirections) {
      var wireState = new WireState
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
      state = state ++ wireState.state.map({
        case (k, v) => k -> (v._1 + state.getOrElse(k, (0,0))._1, v._2 + state.getOrElse(k, (0,0))._2)
      })
    }

    state.filter({
      case (k,v) => v._1 > 1 && k != (0,0)
    }).toArray
  }
}
