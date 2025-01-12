package com.dongdong.kotlinboard.controller

import com.dongdong.kotlinboard.controller.dto.*
import com.dongdong.kotlinboard.service.PostService
import org.springdoc.core.annotations.ParameterObject
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*


@RestController
class PostController(
  private val postService: PostService,
) {

  @PostMapping("/posts")
  fun createPost(
    @RequestBody postCreateRequest: PostCreateRequest,
  ): Long {
    return postService.createPost(postCreateRequest.toDTO())
  }

  @PutMapping("/posts/{id}")
  fun updatePost(
    @PathVariable("id") id: Long,
    @RequestBody postUpdateRequest: PostUpdateRequest,
  ): Long {
    return postService.updatePost(id, postUpdateRequest.toDTO())
  }

  @DeleteMapping("/posts/{id}")
  fun deletePost(
    @PathVariable("id") id: Long,
    @RequestParam("deletedBy") deletedBy: String,
  ): Long {
    return postService.deletePost(id, deletedBy)
  }

  @GetMapping("/posts/{id}")
  fun getOnePost(
    @PathVariable("id") id: Long,
  ): PostDetailResponse {
    return postService.getById(id)
  }

  @GetMapping("/posts/search")
  fun getPost(
    @ParameterObject
    postSearchRequest: PostSearchRequest,
    @ParameterObject
    pageable: Pageable,
  ): Page<PostSummaryResponse> {
    return postService.getAllPagesBySearchFilter(postSearchRequest, pageable)
  }
}
