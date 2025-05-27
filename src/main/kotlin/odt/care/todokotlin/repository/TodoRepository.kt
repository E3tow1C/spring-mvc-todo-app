package odt.care.todokotlin.repository

import odt.care.todokotlin.model.Todo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TodoRepository : JpaRepository<Todo, Long> {
    fun findByCompleted(completed: Boolean): List<Todo>
}