package com.dongdong.kotlinboard.repository

import com.dongdong.kotlinboard.domain.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository: JpaRepository<Post, Long> {
}
