package example

import scala.concurrent.Future

class FuelManagementSystemSolver {
  var cursor: (Int, Int) = (0,0)
  var state: Map[(Int, Int), Int] = Map()

  def resetCursor(): Unit = {
    setCursorPosition(0,0)
  }

  def setCursorPosition(x: Int, y: Int): Unit = {
    cursor = (x, y)
    state.get(cursor) match {
      case Some(traversed)  => state += (cursor -> (traversed + 1))
      case None             => state += (cursor -> 1)
    }
  }

  def moveRight(distance: Int): Unit = {
    val startX = cursor._1 + 1
    val endX = cursor._1 + distance
    for (positionX <- startX to endX) {
      println(s"Move horizontal $positionX")
      setCursorPosition(positionX, cursor._2)
    }
  }

  def moveLeft(distance: Int): Unit = {
    val startX = cursor._1 - 1
    val endX = cursor._1 - distance
    for (positionX <- startX to endX by -1) {
      println(s"Move horizontal $positionX")
      setCursorPosition(positionX, cursor._2)
    }
  }

  def moveUp(distance: Int): Unit = {
    val startY = cursor._2 + 1
    val endY = cursor._2 + distance
    for (positionY <- startY to endY) {
      println(s"Move vertical $positionY")
      setCursorPosition(cursor._1, positionY)
    }
  }

  def moveDown(distance: Int): Unit = {
    val startY = cursor._2 - 1
    val endY = cursor._2 - distance
    for (positionY <- startY to endY by -1) {
      println(s"Move vertical $positionY")
      setCursorPosition(cursor._1, positionY)
    }
  }

  def getIntersection(wires: Array[String]): Array[(Int, Int)] = {
    val wiresWithDirections: Array[Array[String]] = wires.map(w => w.split(","))

    for (wireDirection <- wiresWithDirections) {
      resetCursor()
      for (movement <- wireDirection) {
        val (direction, distanceStr) = movement.splitAt(1)
        val distance = distanceStr.toInt

        direction match {
          case "R" => moveRight(distance)
          case "L" => moveLeft(distance)
          case "U" => moveUp(distance)
          case "D" => moveDown(distance)
        }
      }
    }
    state.filter(x => x._2 > 1 && x._1 != (0,0)).keys.toArray
  }
}
