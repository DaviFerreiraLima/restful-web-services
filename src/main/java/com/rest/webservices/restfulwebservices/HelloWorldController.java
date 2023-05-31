package com.rest.webservices.restfulwebservices;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class HelloWorldController {

    @GetMapping("/hello")
    public String hello(){
        return "hello world";
    }


    @GetMapping("/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello world");
    }

    @GetMapping("/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello World, %s",name));}


    @PutMapping("/users")
    public void createUser(){

    }
}
