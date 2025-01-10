package com.dongdong.kotlinboard.service

import com.dongdong.kotlinboard.repository.PostRepository
import com.dongdong.kotlinboard.service.dto.PostCreateRequestDTO
import com.dongdong.kotlinboard.service.dto.PostUpdateRequestDTO
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull

/**
 * BDD 와 같은 테스트 형식
 * given/when/then 구조
 */
@SpringBootTest
class PostServiceTest(
  private val postService: PostService,
  private val postRepository: PostRepository,
) : BehaviorSpec({
  given("게시글 생성 시") {
    When("게시글 생성") {
      val postId = postService.createPost(
        PostCreateRequestDTO(
          title = "게시글 테스트",
          content = "kotlin board project first post!!",
          createdBy = "DongDong"
        )
      );
      then("게시글이 정상 생성됨을 확인한다.") {
        postId shouldNotBe null
        println("postId : $postId")
        val post = postRepository.findByIdOrNull(postId);
        post shouldNotBe null
      }
    }
  }

  given("게시글 수정 시") {
    val savedId = postService.createPost(
      PostCreateRequestDTO(
        title = "제목",
        content = "kotlin board project first post!!",
        createdBy = "dongdong"
      )
    );
    When("정상 수정 시") {
      then("정상 수정됨을 확인한다") {
        val originPost = postRepository.findByIdOrNull(savedId)
        originPost?.id shouldNotBe null
        val updatePost = postService.updatePost(
          originPost?.id!!, PostUpdateRequestDTO(
            title = "update 제목",
            content = "update content!!",
            updatedBy = "dongdong"
          )
        )
        val updatedPost = postRepository.findByIdOrNull(updatePost);
        updatedPost?.createdBy shouldBe updatedPost?.createdBy
        updatedPost?.title shouldBe "update 제목"
        updatedPost?.content shouldBe "update content!!"
      }
    }
    When("아이디의 게시물이 없으면") {
      then("존재하지 않는다는 에러가 발생한다.") {
        val exception = shouldThrow<PostException> {
          postService.updatePost(
            113, PostUpdateRequestDTO(
              title = "update 제목",
              content = "update content!!",
              updatedBy = "dongdong"
            )
          )
        }
        exception.message shouldBe "존재하지 않는 게시물 입니다."
      }
    }
    When("작성자가 동일하지 않으면") {
      then("작성자와 동일한 계정이 아닙니다 에러가 발생한다.") {
        val exception = shouldThrow<PostException> {
          postService.updatePost(
            savedId, PostUpdateRequestDTO(
              title = "update 제목",
              content = "update content!!",
              updatedBy = "dongdong!!!!!!"
            )
          )
        }
        exception.message shouldBe "작성자와 동일한 계정이 아닙니다."
      }
    }
  }

  given("게시글 삭제 시") {
    When("정상 삭제 시") {
      val savedId = postService.createPost(
        PostCreateRequestDTO(
          title = "제목",
          content = "kotlin board project first post!!",
          createdBy = "dongdong"
        )
      );
      then("정상 삭제됨을 확인한다.") {
        val deletedPostId = postService.deletePost(
          savedId, "dongdong"
        )
        postRepository.findByIdOrNull(deletedPostId) shouldBe null
      }
    }
    When("삭제자가 작성자와 동일하지 않으면") {
      val savedId = postService.createPost(
        PostCreateRequestDTO(
          title = "제목",
          content = "kotlin board project first post!!",
          createdBy = "dongdong"
        )
      );
      then("작성자와 동일한 계정이 아닙니다 에러가 발생한다.") {
        val exception = shouldThrow<PostException> {
          postService.deletePost(
            savedId, "dongdong!!!"
          )
        }
        exception.message shouldBe "작성자와 동일한 계정이 아닙니다."
      }
    }
  }
})


