package com.dongdong.kotlinboard.service

import com.dongdong.kotlinboard.controller.dto.PostCreateRequest
import com.dongdong.kotlinboard.repository.PostRepository
import io.kotest.core.spec.style.BehaviorSpec
import org.springframework.boot.test.context.SpringBootTest

/**
 * BDD 와 같은 테스트 형식
 * given/when/then 구조
 */
@SpringBootTest
class PostServiceTest(
  private val postService: PostService,
  private val postRepository: PostRepository
) : BehaviorSpec({
  given("게시글 생성 시") {
    When("게시글 생성") {
      val postId = postService.createPost(PostCreateRequestDTO(
        title = "게시글 테스트",
        content = "kotlin board project first post!!",
        createdBy = "DongDong"
      ));
      then("게시글이 정상 생성됨을 확인한다.") {
        postId shouldNotBe null
        val post = postRepository.findByIdOrNull(postId);
        post shouldNotBe null
      }
    }
  }
})
