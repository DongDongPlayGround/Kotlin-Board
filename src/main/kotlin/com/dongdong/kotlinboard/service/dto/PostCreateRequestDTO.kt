package com.dongdong.kotlinboard.service.dto

data class PostCreateRequestDTO(
  val title: String,
  val content: String,
  val createdBy: String
)
