package com.sla.todo.rest.services.restfulwebservices.todo;

import com.sla.todo.rest.services.restfulwebservices.todo.Todo;
import com.sla.todo.rest.services.restfulwebservices.todo.TodoHardcodedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class TodoJpaResource {

    @Autowired
    private TodoHardcodedService todoService;

    @Autowired
    private TodoJpaRepository todoJpaRepository;

    @GetMapping("/jpa/users/{username}/places")
    public List<Todo> getAllTodos(@PathVariable String username) {
        // Thread.sleep(3000);
        return todoJpaRepository.findByUsername(username);
        //return todoService.findAll();
    }

    @GetMapping("/jpa/users/{username}/places/{id}")
    public Todo getTodo(@PathVariable String username, @PathVariable long id) {
        // Thread.sleep(3000);
        return todoJpaRepository.findById(id).get();
        //return todoService.findById(id);
    }


    // DELETE /users/{username}/todos/{id}
    @DeleteMapping("/jpa/users/{username}/places/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable long id) {

        todoJpaRepository.deleteById(id);

        return ResponseEntity.notFound().build();
    }

    //Edit/Update a Todo
    //PUT /users/{user_name}/todos/{todo_id}
    @PutMapping("/jpa/users/{username}/places/{id}")
    public ResponseEntity<Todo> updateTodo(
            @PathVariable String username,
            @PathVariable long id, @RequestBody Todo todo){

        todo.setUsername(username);

        Todo todoUpdated = todoJpaRepository.save(todo);

        return new ResponseEntity<Todo>(todo, HttpStatus.OK);
    }

    @PostMapping("/jpa/users/{username}/places")
    public ResponseEntity<Void> createTodo(
            @PathVariable String username,
            @RequestBody Todo todo){

        todo.setUsername(username);

        Todo createdTodo = todoJpaRepository.save(todo);

        //Location
        //Get current resource url
        ///{id}
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(createdTodo.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }


}