package com.dongdong.kotlinboard.dto

data class PostUpdateRequest(
  val title: String,
  val content: String,
  val updatedBy: String
)
