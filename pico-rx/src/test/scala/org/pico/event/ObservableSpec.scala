package org.pico.event

import org.specs2.mutable.Specification
import rx.lang.scala.{Observable, Subscription}
import scala.concurrent.duration._

class ObservableSpec extends Specification {
  "xx" in {
    Observable.just(1, 2, 3).filter(_ % 2 == 0)

    Observable.just(1, 2, 3).debounce(1.second)

    val x: Subscription = Observable.just(1, 2, 3).subscribe(x => ())

    x.unsubscribe()
    ok
  }
}
