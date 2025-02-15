package com.dongdong.kotlinboard.domain

import com.dongdong.kotlinboard.service.PostException
import jakarta.persistence.*

@Entity
class Comment(
  content: String,
  createdBy: String,
  post: Post,
) : BaseEntity(createdBy) {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long? = null

  @Column(columnDefinition = "TEXT")
  var content: String = content
    protected set

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id")
  var post: Post = post
    protected set

  fun update(
    content: String,
    updatedBy: String,
  ): Comment {
    this.content = content
    if (!this.createdBy.equals(updatedBy)) throw PostException.notEqualWriter()
    super.updateBaseInfo(updatedBy)
    return this
  }

  fun delete(deletedBy: String): Comment {
    if (!this.createdBy.equals(deletedBy)) throw PostException.notEqualWriter()
    return this
  }
}
