package com.notasoftwaredevelopmentcompany.springtodo.todo.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;
import com.notasoftwaredevelopmentcompany.springtodo.todo.models.TodoItem;
import com.notasoftwaredevelopmentcompany.springtodo.todo.services.ITodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class TodoController {
    Logger logger = LoggerFactory.getLogger(TodoController.class);

    @Autowired
    private ITodoService todoService;

    @GetMapping("/todo/{id}")
    public ResponseEntity<TodoItem> findById(@PathVariable(value = "id") Long id) {
        Optional<TodoItem> todoItem = todoService.findById(id);

        if (!todoItem.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<TodoItem>(todoItem.get(), HttpStatus.OK);
    }

    @GetMapping("/todo")
    public ResponseEntity<List<TodoItem>> findAll() {
        var todoItems = todoService.findAll();

        if (todoItems == null || todoItems.size() <= 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo items not found");
        }

        return new ResponseEntity<List<TodoItem>>(todoItems, HttpStatus.OK);
    }

    @PostMapping("/todo")
    public ResponseEntity<TodoItem> create(@RequestBody TodoItem todoItem) {
        var createdTodoItem = todoService.create(todoItem);
        return new ResponseEntity<TodoItem>(createdTodoItem, HttpStatus.CREATED);
    }

    @PutMapping("/todo/{id}")
    public ResponseEntity<TodoItem> update(@RequestBody TodoItem todoItem, @PathVariable(value = "id") Long id) {
        TodoItem updatedTodoItem = todoService.update(todoItem, id);
        return new ResponseEntity<TodoItem>(updatedTodoItem, HttpStatus.OK);
    }

    @DeleteMapping("/todo/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
        todoService.delete(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/greeting")
    public ResponseEntity<String> index() {
        String greeting = "Hello from TodoController!";
        return new ResponseEntity<String>(greeting, HttpStatus.OK);
    }
}
