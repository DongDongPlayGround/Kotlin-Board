package com.dongdong.kotlinboard.controller.dto

import com.dongdong.kotlinboard.domain.Post
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import java.time.LocalDateTime

data class PostSummaryResponse(
  val id: Long,
  val title: String,
  val createdBy: String? = "",
  val createdAt: LocalDateTime
)

fun Page<Post>.toPostSummaryResponse(): Page<PostSummaryResponse> = PageImpl(content.map { it.toPostSummaryResponse() }, pageable,  totalElements)

fun Post.toPostSummaryResponse(): PostSummaryResponse = PostSummaryResponse(
  id = requireNotNull(id) {"id should not be null"},
  title = title,
  createdBy = createdBy,
  createdAt = createdDate
)
