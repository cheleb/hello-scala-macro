package kitamanach

import scala.quoted.*

inline def length(str: String): Int = ${ lengthImpl('str) }

def lengthImpl(exp: Expr[String])(using Quotes): Expr[Int] = '{ $exp.length }
