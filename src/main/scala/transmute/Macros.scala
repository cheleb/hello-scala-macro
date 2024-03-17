package transmute

import scala.quoted.*

object internal:
  opaque type Into[From, To] = From

import internal.Into

extension [From](self: From)
  def into[To]: Into[From, To] = throw new Error("This should never be called")

extension [From, To](inline self: Into[From, To])
  // 1. We need to be able to call the macro from the extension method
  inline def transform: To = ${ transformImpl[From, To]('self) }

  // 2. We need to be able to call the macro from the extension method
  // using quote is provided as given from top level splice ${ ... }
def transformImpl[From: Type, To: Type](expr: Expr[Into[From, To]])(using
    Quotes
): Expr[To] =
  import quotes.reflect.*
  // EXPR: transmute.into[transmute.Person](transmute.TestApp.p)[transmute.User]bloop
  expr match
    case '{ transmute.into[From]($fromExpr)[To] } =>
      report.errorAndAbort(s"EXPR: ${fromExpr.show}")
    case _ =>
      report.errorAndAbort(s"EXPR: ${expr.show}")
