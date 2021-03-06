package com.azavea.franklin.database

import doobie.Fragment

import scala.annotation.implicitNotFound

/**
  * This case class is provided to allow the production of rules for transforming datatypes to doobie fragments
  */
@implicitNotFound(
  "No instance of Filterable.scala[${Model}, ${T}] in scope, check imports and make sure one is defined"
)
final case class Filterable[-Model, T](toFilters: T => List[Option[Fragment]])

object Filterable {
  // when I called this apply, I wound up with surprising errors elsewhere about argument types
  // needing to be fully known. It was confusing, so I explicitly named the summoner method `summon`
  // and have chosen to deal with a tiny bit less syntactic sugar
  def summon[Model, T](implicit ev: Filterable[Model, T]) = ev
}
