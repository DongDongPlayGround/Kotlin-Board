package com.dongdong.kotlinboard.service

import com.dongdong.kotlinboard.controller.dto.PostSearchRequest
import com.dongdong.kotlinboard.domain.Post
import com.dongdong.kotlinboard.repository.PostRepository
import com.dongdong.kotlinboard.service.dto.PostCreateRequestDTO
import com.dongdong.kotlinboard.service.dto.PostUpdateRequestDTO
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.string.shouldContain
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
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
      )
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
  given("게시글 상세 조회 시") {
    val savedId = postService.createPost(
      PostCreateRequestDTO(
        title = "게시글 테스트",
        content = "kotlin board project first post!!",
        createdBy = "DongDong"
      )
    )
    When("정상 조회 시") {
      val post = postService.getById(savedId)
      then("게시글의 내용이 정상적으로 조호됨을 확인한다.") {
        post shouldNotBe null
        post.title shouldBe "게시글 테스트"
      }
    }
    When("게시글이 존재하지 않을 시") {
      then("게시글이 없다는 예외가 발생한다") {
        val exception = shouldThrow<PostException> {
          postService.getById(12345L)
        }
        exception.message shouldBe "존재하지 않는 게시물 입니다."
      }
    }
  }

  given("게시물 페이지 조회 시") {
    val pageSaveList = listOf(
      Post(title = "타이틀1", content = "내용입니다1", createdBy = "동동이"),
      Post(title = "타이틀2", content = "내용입니다2", createdBy = "동동이"),
      Post(title = "타이틀3", content = "내용입니다3", createdBy = "동동이"),
      Post(title = "타이틀4", content = "내용입니다4", createdBy = "동동이"),
      Post(title = "타이틀5", content = "내용입니다5", createdBy = "동동이"),
      Post(title = "타이틀6", content = "내용입니다6", createdBy = "동동이"),
      Post(title = "타이틀7", content = "내용입니다7", createdBy = "동동이")
    )
    postRepository.saveAll(pageSaveList)
    When("2사이즈로 타이틀이란 제목 타겟 삼아 2번째 페이지를 조회 시 ") {
      val getPageInfos = postService.getAllPagesBySearchFilter(
        postSearchRequest = PostSearchRequest("타이틀", null),
        pageRequest = PageRequest.of(1, 2)
      )
      then("타이틀5, 타이틀4 가 조회된다.") {
        // 2번째 페이지
        getPageInfos.number shouldBe 1
        // 2 사이즈
        getPageInfos.size shouldBe 2
        println(getPageInfos.number)
        getPageInfos.content.map {
          println("제목 : ${it.title}")
          it.title shouldContain "타이틀"
        }
      }
    }
  }
})


