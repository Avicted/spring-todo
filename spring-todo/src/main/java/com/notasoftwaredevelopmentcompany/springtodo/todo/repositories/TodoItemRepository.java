package com.notasoftwaredevelopmentcompany.springtodo.todo.repositories;

import com.notasoftwaredevelopmentcompany.springtodo.todo.models.TodoItem;
import org.springframework.data.repository.CrudRepository;

public interface TodoItemRepository extends CrudRepository<TodoItem, Long> {

}
