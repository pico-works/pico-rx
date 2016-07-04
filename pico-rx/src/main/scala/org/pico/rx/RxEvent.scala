package org.pico.rx

sealed trait RxEvent[+A, +E]

case class RxNext[A](value: A) extends RxEvent[A, Nothing]

case class RxError[E](error: E) extends RxEvent[Nothing, E]

case object RxComplete extends RxEvent[Nothing, Nothing]
