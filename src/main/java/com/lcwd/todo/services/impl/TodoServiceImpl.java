package com.lcwd.todo.services.impl;

import com.lcwd.todo.exceptions.ResourceNotFoundException;
import com.lcwd.todo.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements com.lcwd.todo.services.TodoService {
    //create todo method
    Logger logger = LoggerFactory.getLogger(TodoServiceImpl.class);
    List<Todo> todos = new ArrayList<>();
    public Todo createTodo(Todo todo){
        //create
        //change the logic for db if db
        todos.add(todo);
        logger.info("todos {} ",this.todos);
        return todo;
    }

    public List<Todo> getAllTodos() {
        return todos;
    }

    public Todo getTodo(int todoId) {
        Todo todo  = todos.stream().filter(t -> todoId == t.getId()).findAny().orElseThrow(()->new ResourceNotFoundException("Todo Not Found with given Id", HttpStatus.NOT_FOUND));
        logger.info("TODO {}",todo);
        return todo;
    }

    public Todo updateTodo(int todoId,Todo todo) {
        List<Todo> updatedList = todos.stream().map(t->{
            if(t.getId() == todoId){
               t.setContent(todo.getContent());
               t.setStatus(todo.getStatus());
               t.setTitle(todo.getTitle());
               return t;
            }else{
                return t;
            }
        }).collect(Collectors.toList());
        todos=updatedList;
        todo.setId(todoId);
        return todo;


    }

    public void deleteTodo(int todoId) {
        logger.info("Deleting Todo");
        List<Todo> newList = todos.stream().filter(t->todoId!=t.getId()).collect(Collectors.toList());
        todos=newList;
    }
}
