package com.dongdong.kotlinboard.controller.dto

import java.time.LocalDateTime

data class PostDetailResponse(
  val id: Long,
  val title: String,
  val content: String,
  val createdBy: String,
  val createdAt: LocalDateTime,
  val comments: List<CommentResponse> = emptyList()
)
