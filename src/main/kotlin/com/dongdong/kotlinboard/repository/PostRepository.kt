package com.dongdong.kotlinboard.repository

import com.dongdong.kotlinboard.domain.Post
import com.dongdong.kotlinboard.domain.QPost.post
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport

interface PostRepository : JpaRepository<Post, Long>, CustomRepository {

}

interface CustomRepository {
  fun findAllPagesBySearchFilter(
    title: String?,
    createdBy: String?,
    pageRequest: Pageable,
  ): Page<Post>
}

class CustomRepositoryImpl : CustomRepository, QuerydslRepositorySupport(Post::class.java) {

  override fun findAllPagesBySearchFilter(title: String?, createdBy: String?, pageRequest: Pageable): Page<Post> {
    val postList = from(post)
      .where(
        title?.let { post.title.contains(it) },
        createdBy?.let { post.createdBy.eq(it) },
      )
      .orderBy(post.createdDate.desc())
      .offset(pageRequest.offset)
      .limit(pageRequest.pageSize.toLong())
      .fetchResults()
    return PageImpl(postList.results, pageRequest, postList.total)
  }
}
