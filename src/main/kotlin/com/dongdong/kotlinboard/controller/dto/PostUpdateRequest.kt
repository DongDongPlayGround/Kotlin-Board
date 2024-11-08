package com.dongdong.kotlinboard.controller.dto

import com.dongdong.kotlinboard.service.dto.PostUpdateRequestDTO

data class PostUpdateRequest(
  val title: String,
  val content: String,
  val updatedBy: String
)

fun PostUpdateRequest.toDTO() = PostUpdateRequestDTO(title, content, updatedBy)
