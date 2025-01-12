package com.dongdong.kotlinboard.service

import com.dongdong.kotlinboard.controller.dto.PostDetailResponse
import com.dongdong.kotlinboard.controller.dto.PostSearchRequest
import com.dongdong.kotlinboard.controller.dto.PostSummaryResponse
import com.dongdong.kotlinboard.controller.dto.toPostSummaryResponse
import com.dongdong.kotlinboard.domain.Post
import com.dongdong.kotlinboard.repository.PostRepository
import com.dongdong.kotlinboard.service.dto.PostCreateRequestDTO
import com.dongdong.kotlinboard.service.dto.PostUpdateRequestDTO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class PostService(
  private val postRepository: PostRepository,
) {

  @Transactional
  fun createPost(postCreateRequestDTO: PostCreateRequestDTO): Long {
    val post = Post(
      postCreateRequestDTO.title,
      postCreateRequestDTO.content,
      postCreateRequestDTO.createdBy
    )
    return postRepository.save(post).id ?: throw PostException.notFoundEntity()
  }

  @Transactional
  fun updatePost(id: Long, postUpdateRequestDTO: PostUpdateRequestDTO): Long {
    return postRepository.findByIdOrNull(id)
      ?.update(postUpdateRequestDTO.title, postUpdateRequestDTO.content, postUpdateRequestDTO.updatedBy)?.id
      ?: throw PostException.notFoundEntity();
  }

  @Transactional
  fun deletePost(id: Long, deletedBy: String): Long {
    return postRepository.findByIdOrNull(id)
      ?.delete(deletedBy)?.id
      ?.let { postId ->
        postRepository.deleteById(postId)
        return postId
      }
      ?: throw PostException.notFoundEntity()
  }

  fun getById(id: Long): PostDetailResponse {
    return postRepository.findByIdOrNull(id)
      ?.let {
        PostDetailResponse(
          id = requireNotNull(it.id) { "id should not be null!" },
          title = it.title,
          content = it.content,
          createdBy = it.createdBy,
          createdAt = it.createdDate
        )
      } ?: throw PostException.notFoundEntity()
  }

  fun getAllPagesBySearchFilter(
    postSearchRequest: PostSearchRequest,
    pageRequest: Pageable,
  ): Page<PostSummaryResponse> {
    return postRepository.findAllPagesBySearchFilter(
      postSearchRequest.title,
      postSearchRequest.createdBy,
      pageRequest
    ).toPostSummaryResponse()
  }
}
