package com.dongdong.kotlinboard.domain

import com.dongdong.kotlinboard.service.PostException
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Post(
  title: String,
  content: String,
  createdBy: String,
) : BaseEntity(createdBy) {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long? = null

  var title: String = title
    protected set

  @Column(columnDefinition = "TEXT")
  var content: String = content
    protected set

  fun update(
    title: String,
    content: String,
    updatedBy: String,
  ): Post {
    if(!this.createdBy.equals(updatedBy))  throw PostException.notEqualWriter()
    this.title = title
    this.content = content
    super.updateBaseInfo(updatedBy)
    return this
  }

  fun delete(deletedBy: String): Post {
    if(!this.createdBy.equals(deletedBy)) throw PostException.notEqualWriter()
    return this
  }
}
