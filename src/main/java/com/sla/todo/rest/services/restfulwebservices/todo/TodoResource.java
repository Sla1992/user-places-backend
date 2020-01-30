package com.sla.todo.rest.services.restfulwebservices.todo;

import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class TodoResource {

    @Autowired
    private TodoHardcodedService todoService;

    @GetMapping("/users/{username}/places")
    public List<Todo> getAllTodos(@PathVariable String username) {
        // Thread.sleep(3000);
        return todoService.findAll();
    }

    @GetMapping("/users/{username}/places/{id}")
    public Todo getTodo(@PathVariable String username, @PathVariable long id) {
        // Thread.sleep(3000);
        return todoService.findById(id);
    }


    // DELETE /users/{username}/todos/{id}
    @DeleteMapping("/users/{username}/places/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable long id) {

        Todo todo = todoService.deleteById(id);

        if (todo != null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    //Edit/Update a Todo
    //PUT /users/{user_name}/todos/{todo_id}
    @PutMapping("/users/{username}/places/{id}")
    public ResponseEntity<Todo> updateTodo(
            @PathVariable String username,
            @PathVariable long id, @RequestBody Todo todo){

        Todo todoUpdated = todoService.save(todo);

        return new ResponseEntity<Todo>(todo, HttpStatus.OK);
    }

    @PostMapping("/users/{username}/places")
    public ResponseEntity<Void> updateTodo(
            @PathVariable String username, @RequestBody Todo todo){

        Todo createdTodo = todoService.save(todo);

        //Location
        //Get current resource url
        ///{id}
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(createdTodo.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }


}