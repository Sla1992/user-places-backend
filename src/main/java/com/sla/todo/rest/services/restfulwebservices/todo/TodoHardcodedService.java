package com.sla.todo.rest.services.restfulwebservices.todo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TodoHardcodedService {

    private static List<Todo> todos = new ArrayList<>();
    private static long idCounter = 0;

    static {
        todos.add(new Todo(++idCounter, "Frolian", "I visited a long time ago becaus i dont know how o write and i dont know exactly what to write but to fill in this description i need to to this 1",new Date(), true, "Visiting Grand Canyon"));
        todos.add(new Todo(++idCounter, "Frolian", "I visited a long time ago becaus i dont know how o write and i dont know exactly what to write but to fill in this description i need to to this 2",new Date(), false, "Visiting Grand Canyon 2"));
        todos.add(new Todo(++idCounter, "Frolian", "I visited a long time ago becaus i dont know how o write and i dont know exactly what to write but to fill in this description i need to to this 3",new Date(), true, "Visiting Grand Canyon 3"));
    }

    public List<Todo> findAll() {
        return todos;
    }

    public Todo save(Todo todo) {
        if(todo.getId()==-1 || todo.getId()==0) {
            todo.setId(++idCounter);
            todos.add(todo);
        } else {
            deleteById(todo.getId());
            todos.add(todo);
        }
        return todo;
    }

    public Todo deleteById(long id) {
        Todo todo = findById(id);

        if (todo == null)
            return null;

        if (todos.remove(todo)) {
            return todo;
        }

        return null;
    }

    public Todo findById(long id) {
        for (Todo todo : todos) {
            if (todo.getId() == id) {
                return todo;
            }
        }

        return null;
    }



}
