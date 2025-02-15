package com.dongdong.kotlinboard.controller.dto

import com.dongdong.kotlinboard.service.dto.CommentCreateRequestDTO

data class CommentCreateRequest(
  val content: String,
  val createdBy: String
)

fun CommentCreateRequest.toDTO() = CommentCreateRequestDTO(content, createdBy)
