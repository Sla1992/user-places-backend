package com.sla.todo.rest.services.restfulwebservices.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class TodoResource {

    @Autowired
    private TodoHardcodedService todoService;

    @GetMapping("/users/{username}/todos")
    public List<Todo> getAllTodos(String username) throws InterruptedException {
        return todoService.findAll();
    }

    //DELETE /users/{username}/todos/{id}
    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(
            @PathVariable String username, @PathVariable long id){

        Todo todo = todoService.deleteById(id);
        if(todo!=null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}