package kitamanach

import scala.quoted.*

inline def isEven(i: Int): Boolean = ${ isEvenImpl('i) }

def isEvenImpl(exp: Expr[Int])(using q: Quotes): Expr[Boolean] =
  import q.reflect.*
  exp match
    case Expr(n) =>
      if n % 2 == 0 then '{ true }
      else report.errorAndAbort(s"$n is not even!")
    case _ =>
      '{ $exp % 2 == 0 }
