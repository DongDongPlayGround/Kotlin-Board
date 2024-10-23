package com.dongdong.kotlinboard

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class SampleTest : FunSpec({

  test("sample test!") {
    1 shouldBe 1
  }

  test("sample test1") {
    val name: String = "seungho"
    name shouldBe "seungho"
  }
})
