package com.dongdong.kotlinboard.controller

import com.dongdong.kotlinboard.controller.dto.*
import com.dongdong.kotlinboard.service.PostService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
class PostController(
  private val postService: PostService
) {

  @PostMapping("/posts")
  fun createPost(
    @RequestBody postCreateRequest: PostCreateRequest
  ): Long {
    return postService.createPost(postCreateRequest.toDTO())
  }

  @PutMapping("/posts/{id}")
  fun updatePost(
    @PathVariable("id") id: Long,
    @RequestBody postUpdateRequest: PostUpdateRequest
  ): Long {
    return postService.updatePost(id, postUpdateRequest.toDTO())
  }

  @DeleteMapping("/posts/{id}")
  fun deletePost(
    @PathVariable("id") id: Long,
    @RequestParam("deletedBy") deletedBy: String
  ): Long {
    return postService.deletePost(id, deletedBy)
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
