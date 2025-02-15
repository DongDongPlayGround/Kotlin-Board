package com.dongdong.kotlinboard.controller.dto

data class CommentResponse(
  val id: Long,
  var content: String,
  val createdBy: String,
  val createdAt: String
)
