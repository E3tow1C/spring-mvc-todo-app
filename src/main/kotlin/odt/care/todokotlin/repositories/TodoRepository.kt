package odt.care.todokotlin.repositories

import odt.care.todokotlin.models.Todo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TodoRepository : JpaRepository<Todo, Long> {
    fun findByCompleted(completed: Boolean): List<Todo>
}