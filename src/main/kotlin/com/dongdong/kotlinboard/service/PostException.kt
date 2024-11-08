package com.dongdong.kotlinboard.service

class PostException(
  code: String,
  message: String
) : RuntimeException(message) {
  companion object {
    fun notFoundEntity(): PostException = PostException("POST-01", "존재하지 않는 게시물 입니다.")
    fun notEqualWriter(): PostException = PostException("POST-02", "작성자와 동일한 계정이 아닙니다.")

  }
}

