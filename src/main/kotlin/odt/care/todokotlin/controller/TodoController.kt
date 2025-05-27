package odt.care.todokotlin.controller

import odt.care.todokotlin.model.Todo
import odt.care.todokotlin.service.TodoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/todos")
class TodoController(private val todoService: TodoService) {

    @GetMapping
    fun getAllTodos(): ResponseEntity<List<Todo>> =
        ResponseEntity.ok(todoService.getAllTodos())

    @GetMapping("/{id}")
    fun getTodoById(@PathVariable id: Long): ResponseEntity<Todo> {
        val todo = todoService.getTodoById(id)
        return if (todo.isPresent) {
            ResponseEntity.ok(todo.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/completed")
    fun getCompletedTodos(): ResponseEntity<List<Todo>> =
        ResponseEntity.ok(todoService.getCompletedTodos())

    @GetMapping("/incomplete")
    fun getIncompleteTodos(): ResponseEntity<List<Todo>> =
        ResponseEntity.ok(todoService.getIncompleteTodos())

    @PostMapping
    fun createTodo(@RequestBody todo: Todo): ResponseEntity<Todo> =
        ResponseEntity.status(HttpStatus.CREATED).body(todoService.createTodo(todo))

    @PutMapping("/{id}")
    fun updateTodo(@PathVariable id: Long, @RequestBody todoDetails: Todo): ResponseEntity<Todo> {
        return try {
            ResponseEntity.ok(todoService.updateTodo(id, todoDetails))
        } catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        }
    }

    @PatchMapping("/{id}/toggle")
    fun toggleTodoCompleted(@PathVariable id: Long): ResponseEntity<Todo> {
        return try {
            ResponseEntity.ok(todoService.toggleTodoCompleted(id))
        } catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteTodo(@PathVariable id: Long): ResponseEntity<Void> {
        return try {
            todoService.deleteTodo(id)
            ResponseEntity.noContent().build()
        } catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        }
    }
}