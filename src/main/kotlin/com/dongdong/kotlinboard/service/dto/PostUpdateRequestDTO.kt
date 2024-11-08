package com.dongdong.kotlinboard.service.dto

data class PostUpdateRequestDTO(
  val title: String,
  val content: String,
  val updatedBy: String
)
