package uz.pdp.springbootprojectprocesses.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootprojectprocesses.payload.ApiResponse;
import uz.pdp.springbootprojectprocesses.payload.TaskDto;
import uz.pdp.springbootprojectprocesses.serviceInterface.TaskService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/task")
public class TaskController {

  @Autowired
  TaskService taskService;

    @PostMapping("/addTask")
    public HttpEntity<?>addTask(@Valid @RequestBody TaskDto taskDto){
      ApiResponse apiResponse=taskService.addTask( taskDto);
      return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
   }




}
