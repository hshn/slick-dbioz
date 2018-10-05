package slick.dbioz

import org.scalatest.FlatSpec
import scalaz.syntax.either._
import slick.dbio.DBIO

import scala.concurrent.ExecutionContext.Implicits.global

object DBIOEitherTTest extends FlatSpec {

  DBIOEitherT(DBIO.successful(1.right))
  DBIOEitherT(DBIO.successful(1.left))
  DBIOEitherT(DBIO.failed(new Exception("")))

  DBIOEitherT.eitherT(DBIO.successful(1.right))
  DBIOEitherT.eitherT(DBIO.successful(1.left))
  DBIOEitherT.eitherT(DBIO.failed(new Exception()))

  DBIOEitherT.either(1.right)
  DBIOEitherT.either(1.left)

  DBIOEitherT.leftT(DBIO.successful(1))
  DBIOEitherT.leftT(DBIO.failed(new Exception()))

  DBIOEitherT.rightT(DBIO.successful(1))
  DBIOEitherT.rightT(DBIO.failed(new Exception()))

  DBIOEitherT.left(1)
  DBIOEitherT.pure(1)
}
