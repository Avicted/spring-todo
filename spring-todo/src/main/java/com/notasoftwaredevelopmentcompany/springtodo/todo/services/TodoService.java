package com.notasoftwaredevelopmentcompany.springtodo.todo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.notasoftwaredevelopmentcompany.springtodo.todo.models.TodoItem;
import com.notasoftwaredevelopmentcompany.springtodo.todo.repositories.TodoItemRepository;

@Service
public class TodoService implements ITodoService {
    @Autowired
    private TodoItemRepository repository;

    @Override
    public Optional<TodoItem> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<TodoItem> findAll() {
        List<TodoItem> todoList = new ArrayList<>();
        repository.findAll().forEach(todoList::add);
        return todoList;
    }

    @Override
    public TodoItem create(TodoItem todoItem) {
        var createdTodoItem = repository.save(todoItem);
        return createdTodoItem;
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public TodoItem update(TodoItem todoItem, Long id) {
        Optional<TodoItem> todoItemFromDB = repository.findById(id);

        if (todoItemFromDB.isPresent()) {
            TodoItem originalTodoItem = todoItemFromDB.get();
            originalTodoItem.setTitle(todoItem.getTitle());
            originalTodoItem.setDescription(todoItem.getDescription());
            return repository.save(originalTodoItem);
        } else {
            return null;
        }
    }
}
