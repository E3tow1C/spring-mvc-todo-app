package odt.care.todokotlin.service

import odt.care.todokotlin.model.Todo
import odt.care.todokotlin.`repositorั`.TodoRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class TodoService(private val todoRepository: TodoRepository) {

    fun getAllTodos(): List<Todo> = todoRepository.findAll()

    fun getTodoById(id: Long): Optional<Todo> = todoRepository.findById(id)

    fun getCompletedTodos(): List<Todo> = todoRepository.findByCompleted(true)

    fun getIncompleteTodos(): List<Todo> = todoRepository.findByCompleted(false)

    fun createTodo(todo: Todo): Todo = todoRepository.save(todo)

    fun updateTodo(id: Long, todoDetails: Todo): Todo {
        return todoRepository.findById(id).map { existingTodo ->
            val updatedTodo = existingTodo.copy(
                title = todoDetails.title,
                description = todoDetails.description,
                completed = todoDetails.completed,
                updatedAt = LocalDateTime.now()
            )
            todoRepository.save(updatedTodo)
        }.orElseThrow { NoSuchElementException("Todo with id $id not found") }
    }

    fun toggleTodoCompleted(id: Long): Todo {
        return todoRepository.findById(id).map { existingTodo ->
            val updatedTodo = existingTodo.copy(
                completed = !existingTodo.completed,
                updatedAt = LocalDateTime.now()
            )
            todoRepository.save(updatedTodo)
        }.orElseThrow { NoSuchElementException("Todo with id $id not found") }
    }

    fun deleteTodo(id: Long) {
        if (todoRepository.existsById(id)) {
            todoRepository.deleteById(id)
        } else {
            throw NoSuchElementException("Todo with id $id not found")
        }
    }
}