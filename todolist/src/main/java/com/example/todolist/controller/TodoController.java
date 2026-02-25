package com.example.todolist.controller;

import com.example.todolist.todo.Todo;
import com.example.todolist.todo.TodoRepository;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @PostMapping("/add")
    public Todo addTodo(@RequestParam String task){
        Todo todo = new Todo();
        todo.setTask(task);
        todo.setCompleted(false);
        todoRepository.save(todo);
        return todo;
    }

    @GetMapping("/todos")
    public List<Todo> getTodos(){
        return todoRepository.findAll();
    }

    @PutMapping("/update/{id}")
    public Todo updateTodo(@PathVariable Long id, @RequestParam(required = false) Boolean completed) {
        Todo todo = todoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("잘못된 id")
        );
        if (completed != null) {
            todo.setCompleted(completed);
        }
        todoRepository.save(todo);
        return todo;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTodo(@PathVariable Long id){
        todoRepository.deleteById(id);
    }
//    private final TodoRepository todoRepository;
//
//    public TodoController(TodoRepository todoRepository) {
//        this.todoRepository = todoRepository;
//    }
//
//    @GetMapping("/todos")
//    public List<Todo> getTodos(){
//        return todoRepository.findAll();
//    }
//
//    @GetMapping("/todos/{id}")
//    public Todo getTodo(@PathVariable Long id){
//        return todoRepository.findById(id).orElseThrow(() ->
//                new RuntimeException("Todo not found: "+id));
//    }
//
//    @PostMapping("/todos")
//    public Todo createTodo(@RequestParam String title){
//        Todo todo = new Todo(title);
//        return todoRepository.save(todo);
//    }
//
//    @PatchMapping("/todos/{id}")
//    public Todo updateTitle(@PathVariable Long id, @RequestParam String title){
//        Todo todo = todoRepository.findById(id)
//                .orElseThrow(()-> new RuntimeException("Todo not found: "+id));
//        todo.setTitle(title);
//        return todoRepository.save(todo);
//    }
//
//    @DeleteMapping("/todos/{id}")
//    public void deleteTodo(@PathVariable Long id){
//        todoRepository.deleteById(id);
//    }
}
