package com.dongdong.kotlinboard.domain

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
}
