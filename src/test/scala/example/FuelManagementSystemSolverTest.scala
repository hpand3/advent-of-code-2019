package example

import org.scalatest.flatspec.AnyFlatSpec

class FuelManagementSystemSolverTest extends AnyFlatSpec {

  val wiresSimpleSingleIntersection: Array[String] = Array[String]("R3","D1,R3,U1")
  val wiresComplexMultipleIntersections: Array[String] = Array[String](
    "R8,U5,L5,D3",
    "U7,R6,D4,L4"
  )

  it should "find intersection in given two intersecting wires" in {
    val fuelManagementSystemSolverTest = new FuelManagementSystemSolver
    val intersection = fuelManagementSystemSolverTest.getIntersection(wiresSimpleSingleIntersection)
    assert(intersection.length == 1)
    assert(intersection contains ((3, 0)))
  }

  it should "find multiple intersections" in {
    val fuelManagementSystemSolverTest = new FuelManagementSystemSolver
    val intersection = fuelManagementSystemSolverTest.getIntersection(wiresComplexMultipleIntersections)
    assert(intersection.length == 2)
    assert(intersection contains ((3, 3)))
    assert(intersection contains ((6, 5)))
  }
}
