package com.avanade.todo.controller;

import com.avanade.todo.model.Task;
import com.avanade.todo.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/todo")
@Api(value = "TASKs API REST")
@CrossOrigin(origins = "*")
public
class Controller {
    @Autowired
    private TaskService service;

    @GetMapping("/tasks")
    @ApiOperation("find a tasks in TODO list")
    public
    ResponseEntity<List<Task>> getAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/tasks/{id}")
    @ApiOperation("find a task by it's id in the TODO list")
    public
    ResponseEntity<Task> getById(@PathVariable(value = "id") Long taskId) {
        return new ResponseEntity<>(service.findById(taskId), HttpStatus.OK);
    }

    @PostMapping("/tasks")
    @ApiOperation("Create a new task in TODO list")
    public
    ResponseEntity<Task> create(@RequestBody Task task) {
        return new ResponseEntity<Task>(service.create(task), HttpStatus.CREATED);
    }

    @PutMapping("/tasks")
    @ApiOperation("Update a task on TODO list")
    public
    ResponseEntity<Task> update(@RequestBody Task task) {
        return new ResponseEntity<>(service.update(task), HttpStatus.OK);
    }

    @DeleteMapping("/tasks")
    @ApiOperation("Delete a task on TODO list")
    public
    ResponseEntity<HttpStatus> delete(@RequestHeader Long taskId) {
        service.delete(taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/task/random")
    @ApiOperation("Create a random task on TODO list")
    public
    ResponseEntity<Task> createRandom() {
        return new ResponseEntity<Task>(service.generateRandom(), HttpStatus.CREATED);
    }
}
