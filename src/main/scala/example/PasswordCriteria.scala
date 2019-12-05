package example

class PasswordCriteria {
  def validPassword(password: String): Boolean = {
    hasIncreasingDigits(password) && hasAdjacentDigits(password) && isSixDigits(password)
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
    for (i <- 0 until password.length - 1) {
      if (password(i) == password(i+1)) {
        return true
      }
    }
    false
  }

  def isSixDigits(password: String): Boolean = password.length == 6 && (password forall Character.isDigit)
}
