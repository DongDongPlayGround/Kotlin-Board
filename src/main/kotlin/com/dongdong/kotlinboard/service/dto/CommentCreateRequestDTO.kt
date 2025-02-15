package com.dongdong.kotlinboard.service.dto

data class CommentCreateRequestDTO(
  val content: String,
  val createdBy: String
)
