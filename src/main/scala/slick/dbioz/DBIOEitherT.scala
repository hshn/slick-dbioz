package slick.dbioz

import scalaz.{-\/, Applicative, EitherT, Functor, \/, \/-}
import slick.dbio.DBIO

import scala.language.higherKinds

object DBIOEitherT {
  def apply[A, B](run: DBIO[A \/ B]): DBIOEitherT[A, B] = EitherT[DBIO, A, B](run)
  def eitherT[A, B](a: DBIO[A \/ B]): DBIOEitherT[A, B] = apply(a)
  def either[A, B](d: A \/ B)(implicit applicative: Applicative[DBIO]): DBIOEitherT[A, B] = EitherT.either[DBIO, A, B](d)
  def leftT[A, B](fa: DBIO[A])(implicit functor: Functor[DBIO]): DBIOEitherT[A, B] = EitherT.leftT[DBIO, A, B](fa)
  def rightT[A, B](fb: DBIO[B])(implicit functor: Functor[DBIO]): DBIOEitherT[A, B] = EitherT.rightT[DBIO, A, B](fb)
  def left[A, B](a: A)(implicit applicative: Applicative[DBIO]): DBIOEitherT[A, B] = apply(Applicative[DBIO].point(-\/(a)))
  def pure[A, B](b: B)(implicit applicative: Applicative[DBIO]): DBIOEitherT[A, B] = apply(Applicative[DBIO].point(\/-(b)))
}
