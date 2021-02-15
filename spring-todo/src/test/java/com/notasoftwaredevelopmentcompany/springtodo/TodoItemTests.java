package com.notasoftwaredevelopmentcompany.springtodo;

import com.notasoftwaredevelopmentcompany.springtodo.todo.models.TodoItem;
import com.notasoftwaredevelopmentcompany.springtodo.todo.services.ITodoService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TodoItemTests {
    @Autowired
    private ITodoService todoService;

    private final String title = "This is the title";
    private final String description = "This is the description";
    private static TodoItem todoItem;
    private static Long todoItemId;

    @Test
    @Order(1)
    void contextLoads() {
    }

    @Test
    @Order(2)
    @Rollback(false)
    public void createTodoItem() {
        todoItem = todoService.create(new TodoItem(title, description));
        todoItemId = todoItem.getId();
        assertThat(todoItem.getId()).isGreaterThan(0);
        assertThat(todoItem.getTitle()).isEqualTo(title);
        assertThat(todoItem.getDescription()).isEqualTo(description);
    }

    @Test
    @Order(3)
    @Rollback(false)
    public void readTodoItem() {
        Optional<TodoItem> readTodoItem = todoService.findById(todoItemId);

        if (readTodoItem.isPresent()) {
            TodoItemTests.todoItem = readTodoItem.get();
            assertThat(TodoItemTests.todoItem.getTitle()).isEqualTo(title);
            assertThat(TodoItemTests.todoItem.getDescription()).isEqualTo(description);
            assertThat(TodoItemTests.todoItem.getId()).isEqualTo(TodoItemTests.todoItemId);
        }
    }

    @Test
    @Order(4)
    public void readAllTodoItems() {
        List<TodoItem> allTodoItems = todoService.findAll();
        assertNotNull(allTodoItems);
    }

    @Test
    @Order(5)
    @Rollback(false)
    public void updateTodoItem() {
        String updatedTitle = "This is the updated title";
        String updatedDescription = "This is the updated description";
        TodoItem updatedTodoItem = new TodoItem(updatedTitle, updatedDescription);
        TodoItem result = todoService.update(updatedTodoItem, TodoItemTests.todoItemId);
        assertThat(result.getTitle()).isEqualTo(updatedTitle);
    }

    @Test
    @Order(6)
    @Rollback(false)
    void deleteTodoItem() {
        todoService.delete(TodoItemTests.todoItemId);
        Optional<TodoItem> deletedTodoItem = todoService.findById(todoItemId);
        assertThat(deletedTodoItem.isEmpty());
    }
}
