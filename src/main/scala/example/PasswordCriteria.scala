package example

class PasswordCriteria {
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
