package example

import org.scalatest.flatspec.AnyFlatSpec

class FuelManagementSystemSolverTest extends AnyFlatSpec {

  val wiresSimpleSingleIntersection: Array[String] = Array[String]("R3","D1,R3,U1")
  val wiresComplexMultipleIntersections: Array[String] = Array[String](
    "R8,U5,L5,D3",
    "U7,R6,D4,L4"
  )
  val wiresComplexMultipleIntersectionsToComputeDistance: Array[String] = Array[String](
    "R75,D30,R83,U83,L12,D49,R71,U7,L72",
    "U62,R66,U55,R34,D71,R55,D58,R83")

  it should "find intersection in given two intersecting wires" in {
    val fuelManagementSystemSolverTest = new FuelManagementSystemSolver
    val intersection = fuelManagementSystemSolverTest.getIntersection(wiresSimpleSingleIntersection)
    assert(intersection.length == 1)
  }

  it should "find multiple intersections" in {
    val fuelManagementSystemSolverTest = new FuelManagementSystemSolver
    val intersection = fuelManagementSystemSolverTest.getIntersection(wiresComplexMultipleIntersections)
    assert(intersection.length == 2)
  }

  it should "find closest intersection" in {
    val fuelManagementSystemSolverTest = new FuelManagementSystemSolver
    val intersection = fuelManagementSystemSolverTest.getIntersection(wiresComplexMultipleIntersections)
    val closestIntersection = fuelManagementSystemSolverTest.findClosestIntersection(intersection)
    assert(closestIntersection == (3, 3))
  }

  it should "find the correct manhattan distance to the closest intersection" in {
    val fuelManagementSystemSolverTest = new FuelManagementSystemSolver
    val intersection = fuelManagementSystemSolverTest.getIntersection(wiresComplexMultipleIntersectionsToComputeDistance)
    val closestIntersection = fuelManagementSystemSolverTest.findClosestIntersection(intersection)
    val manhattanDistance = fuelManagementSystemSolverTest.manhattanDistance(closestIntersection)
    assert(manhattanDistance == 159)
  }
}
