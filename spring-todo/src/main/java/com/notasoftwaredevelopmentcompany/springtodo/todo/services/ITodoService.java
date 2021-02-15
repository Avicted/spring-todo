package com.notasoftwaredevelopmentcompany.springtodo.todo.services;

import java.util.List;
import java.util.Optional;
import com.notasoftwaredevelopmentcompany.springtodo.todo.models.TodoItem;

public interface ITodoService {
    List<TodoItem> findAll();

    Optional<TodoItem> findById(long id);

    TodoItem create(TodoItem todoItem);

    void delete(long id);

    TodoItem update(TodoItem todoItem, Long id);
}
