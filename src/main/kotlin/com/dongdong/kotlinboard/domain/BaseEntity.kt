package com.dongdong.kotlinboard.domain

import jakarta.persistence.MappedSuperclass
import java.time.LocalDateTime

@MappedSuperclass
abstract class BaseEntity(
  createdBy: String
) {
  val createdBy: String = createdBy
  val createdDate: LocalDateTime = LocalDateTime.now()
  var updatedBy: String? = null
    protected set
  var updatedDate: LocalDateTime? = null
    protected set

  fun updateBaseInfo(updatedBy: String) {
    this.updatedBy = updatedBy
    this.updatedDate = LocalDateTime.now()
  }
}
