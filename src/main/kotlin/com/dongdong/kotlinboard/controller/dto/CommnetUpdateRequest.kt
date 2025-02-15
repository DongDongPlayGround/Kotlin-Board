package com.dongdong.kotlinboard.controller.dto

import com.dongdong.kotlinboard.service.dto.CommentUpdateRequestDTO


data class CommentUpdateRequest(
  val content: String,
  val updatedBy: String
)

fun CommentUpdateRequest.toDTO() = CommentUpdateRequestDTO(content, updatedBy)
