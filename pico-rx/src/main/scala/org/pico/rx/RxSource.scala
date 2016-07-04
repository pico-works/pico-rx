package org.pico.rx

import org.pico.event.Source

case class RxSource[+A, +E](unrx: Source[RxEvent[A, E]]) extends AnyVal {
  final def map[B](f: A => B): RxSource[B, E] = RxSource(unrx.map {
    case RxNext(a)  => RxNext(f(a))
    case RxError(e) => RxError(e)
    case RxComplete => RxComplete
  })

  final def filter(p: A => Boolean): RxSource[A, E] = RxSource(unrx.filter {
    case RxNext(a)  => p(a)
    case RxError(e) => true
    case RxComplete => true
  })
}
