package org.pico.event

import org.specs2.mutable.Specification
import rx.lang.scala.{Observable, Subscription}

class ObservableSpec extends Specification {
  "xx" in {
    Observable.just(1, 2, 3).filter(_ % 2 == 0)

    val x: Subscription = Observable.just(1, 2, 3).subscribe(x => ())

    x.unsubscribe()
    ok
  }
}
