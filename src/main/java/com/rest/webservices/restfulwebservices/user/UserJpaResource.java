package com.rest.webservices.restfulwebservices.user;

import com.rest.webservices.restfulwebservices.jpa.PostRepository;
import com.rest.webservices.restfulwebservices.jpa.UserRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/jpa")
public class UserJpaResource {

    private UserRepository repository;
    private PostRepository postRepository;

    public UserJpaResource(UserRepository repository, PostRepository postRepository) {
        this.repository = repository;
        this.postRepository = postRepository;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return repository.findAll();
    }


    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveOneUser(@PathVariable int id){

        Optional<User> user = repository.findById(id);

        if (user.isEmpty()){
            throw new UserNotFoundException("id:" +id);
        }
        EntityModel<User> entityModel = EntityModel.of(user.get());

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));

        return entityModel;
    }

    @PostMapping("/users")
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user){
        User savedUser = repository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return  ResponseEntity.created(location).build();
    }



    @DeleteMapping ("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        repository.deleteById(id);
    }


    @GetMapping("/users/{id}/posts")
    public List<Post> retrieveAllPostForUser(@PathVariable int id){
        Optional<User> user = repository.findById(id);

        if (user.isEmpty()){
            throw new UserNotFoundException("id:" +id);
        }
       return  user.get().getPosts().stream().toList();
    }
}
