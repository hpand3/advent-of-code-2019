package example

class PasswordCriteria {
  def getPossibleCombinations(start: Int, end: Int): Array[Int] = {
    (start to end).filter(s => validPassword(s.toString)).toArray
  }

  def validPassword(password: String): Boolean = {
    isSixDigits(password) && hasIncreasingDigits(password) && hasAdjacentDigits(password)
  }

  def hasIncreasingDigits(password: String): Boolean = {
    for (i <- 0 until password.length - 1) {
      if (password(i) > password(i+1)) {
        return false
      }
    }
    true
  }


  def hasAdjacentDigits(password: String): Boolean = {
    password
      .groupBy(identity)
      .mapValues(_.length)
      .filter(_._2 == 2)
      .keys.sum >= 1

  }

  def isSixDigits(password: String): Boolean = password.length == 6 && (password forall Character.isDigit)
}
