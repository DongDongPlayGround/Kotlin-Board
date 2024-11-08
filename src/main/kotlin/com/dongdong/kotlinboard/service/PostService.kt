package com.dongdong.kotlinboard.service

import com.dongdong.kotlinboard.controller.dto.PostCreateRequest
import com.dongdong.kotlinboard.domain.Post
import com.dongdong.kotlinboard.repository.PostRepository
import com.dongdong.kotlinboard.service.dto.PostCreateRequestDTO
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class PostService(
  private val postRepository: PostRepository
) {

  fun createPost(postCreateRequestDTO: PostCreateRequestDTO): Long {
    val post = Post(
      postCreateRequestDTO.title,
      postCreateRequestDTO.content,
      postCreateRequestDTO.createdBy
    )
    return postRepository.save(post).id.also {  }
  }
}
