package com.dongdong.kotlinboard.controller.dto

import com.dongdong.kotlinboard.service.dto.PostCreateRequestDTO

data class PostCreateRequest(
  val title: String,
  val content: String,
  val createdBy: String
)

fun PostCreateRequest.toDTO() = PostCreateRequestDTO(title, content, createdBy)
