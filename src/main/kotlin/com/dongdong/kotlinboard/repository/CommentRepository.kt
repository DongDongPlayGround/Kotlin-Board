package com.dongdong.kotlinboard.repository

import com.dongdong.kotlinboard.domain.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository: JpaRepository<Comment, Long> {
}
