package org.pico.rx.syntax

import org.pico.rx._
import org.pico.event.syntax.sink._

package object rxSink {
  implicit final class RxSinkOps_UN8ZzvL[A, E](val self: RxSink[A, E]) {
    def cofilter(p: A => Boolean): RxSink[A, E] = RxSink(self.unrx.cofilter {
      case RxNext(a)  => p(a)
      case RxError(e) => true
      case RxComplete => true
    })
  }
}
