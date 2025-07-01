package com.hashpow.spring_backend.controller;

import com.hashpow.spring_backend.entity.CryptoTask;
import com.hashpow.spring_backend.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("")
    public ResponseEntity<String> addTask(@RequestBody String inputText, HttpServletRequest httpRequest) {
        String userId = (String) httpRequest.getAttribute("userId");
        CryptoTask task = taskService.addTask(inputText, userId);
        if(task != null) return ResponseEntity.ok("Task Added Successfully");
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input");
    }

    @GetMapping("")
    public ResponseEntity<List<CryptoTask>> getTasks(HttpServletRequest httpRequest) {
        String userId = (String) httpRequest.getAttribute("userId");
        List<CryptoTask> tasks = taskService.getAllTasks(userId);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable("id") Long id, HttpServletRequest httpRequest) {
        String userId = (String) httpRequest.getAttribute("userId");
        CryptoTask task = taskService.getTask(id);
        if(task == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
        if(!task.getUserId().equals(userId)) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Task Id");
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTaskById(@PathVariable("id") Long id, HttpServletRequest httpRequest) {
        String userId = (String) httpRequest.getAttribute("userId");

        CryptoTask task = taskService.getTask(id);
        if(task == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task Not Found");
        if(!task.getUserId().equals(userId)) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid input");
        task = taskService.deleteTask(task);
        return ResponseEntity.ok(task);
    }


}
