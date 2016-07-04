package org.pico.rx

import org.pico.event.Sink

case class RxSink[-A, -E](unrx: Sink[RxEvent[A, E]]) extends AnyVal {
  final def comap[B](f: B => A): RxSink[B, E] = RxSink(unrx.comap {
    case RxNext(a)  => RxNext(f(a))
    case RxError(e) => RxError(e)
    case RxComplete => RxComplete
  })
}
