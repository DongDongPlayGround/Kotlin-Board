package com.dongdong.kotlinboard.controller

import com.dongdong.kotlinboard.dto.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
class PostController {

  @PostMapping("/posts")
  fun createPost(
    @RequestBody postCreateRequest: PostCreateRequest
  ): Long {
    return 1L
  }

  @PutMapping("/posts/{id}")
  fun updatePost(
    @PathVariable("id") id: Long,
    @RequestBody postUpdateRequest: PostUpdateRequest
  ): Long {
    return 1L
  }

  @DeleteMapping("/posts/{id}")
  fun deletePost(
    @PathVariable("id") id: Long,
    @RequestParam("deletedBy") deletedBy: String
  ): Long {
    println("deleted by ${deletedBy}")
    return 1L
  }

  @GetMapping("/posts/{id}")
  fun getOnePost(
    @PathVariable("id") id: Long
  ): PostDetailResponse {
    return PostDetailResponse(
      id = id,
      title = "Hi. it's test TITLE",
      content = "Hi it's test CONTENT",
      createdBy = "dongdong",
      createdAt = LocalDateTime.now()
    )
  }

  @GetMapping("/posts")
  fun getPost(
    pageable: Pageable,
    postSearchRequest: PostSearchRequest
  ): Page<PostSummaryResponse> {
    return Page.empty()
  }
}
