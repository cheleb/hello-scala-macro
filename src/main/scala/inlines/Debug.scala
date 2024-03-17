package inlines

object Config:
  inline val debug = true

object Logger:
  private var indent = 0

  inline def log[T](msg: String, indentMarging: => Int)(op: => T): T =
    if Config.debug then
      indent += indentMarging
      println(s"${" " * indent}+ starting $msg")
      val res = op
      println(s"${" " * indent}- ended $msg = $res")
      indent -= indentMarging
      op
    else op

@main
def debug2: Unit =
  Logger.log("coucou", 1) {
    1 + 1
  }
  factorial(4)

def factorial(n: BigInt): BigInt =
  Logger.log(s"factorial($n)", 2) {
    if n == 0 then 1
    else n * factorial(n - 1)
  }
