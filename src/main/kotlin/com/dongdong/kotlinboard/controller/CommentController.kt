package com.dongdong.kotlinboard.controller

import com.dongdong.kotlinboard.controller.dto.*
import com.dongdong.kotlinboard.service.PostService
import org.springdoc.core.annotations.ParameterObject
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*


@RestController
class CommentController() {

  @PostMapping("/posts/{postId}/comments")
  fun createPost(
    @PathVariable("postId") postId: Long,
    @RequestBody commentCreateRequest: CommentCreateRequest,
  ): Long {
    println("postId : $postId")
    return 1
  }

  @PutMapping("/comments/{id}")
  fun updatePost(
    @PathVariable("id") id: Long,
    @RequestBody commentUpdateRequest: CommentUpdateRequest,
  ): Long {
    println("update id : $id")
    println("updatedBy : ${commentUpdateRequest.updatedBy}")
    return 1
  }

  @DeleteMapping("/comments/{id}")
  fun deletePost(
    @PathVariable("id") id: Long,
    @RequestParam("deletedBy") deletedBy: String,
  ): Long {
    println("delete id : $id")
    return 1;
  }

//  @GetMapping("/posts/{id}")
//  fun getOnePost(
//    @PathVariable("id") id: Long,
//  ): PostDetailResponse {
//    return postService.getById(id)
//  }
//
//  @GetMapping("/posts/search")
//  fun getPost(
//    @ParameterObject
//    postSearchRequest: PostSearchRequest,
//    @ParameterObject
//    pageable: Pageable,
//  ): Page<PostSummaryResponse> {
//    return postService.getAllPagesBySearchFilter(postSearchRequest, pageable)
//  }
}
