package com.dongdong.kotlinboard.domain

import com.dongdong.kotlinboard.service.PostException
import jakarta.persistence.*

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

  @OneToMany(mappedBy = "post", cascade = [(CascadeType.ALL)])
  var comments: MutableList<Comment> = mutableListOf()
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
