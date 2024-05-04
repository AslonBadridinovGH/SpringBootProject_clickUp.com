package uz.pdp.springbootprojectprocesses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootprojectprocesses.payload.ApiResponse;
import uz.pdp.springbootprojectprocesses.payload.TaskDependencyDto;
import uz.pdp.springbootprojectprocesses.serviceInterface.TaskDependencyService;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/taskDependency")
public class TaskDependencyController {

    @Autowired
    TaskDependencyService taskDependencyService;


    @PostMapping("/addTaskDependency")
    public HttpEntity<?>addTaskDependency(@Valid @RequestBody TaskDependencyDto dependencyDto){
      ApiResponse apiResponse=taskDependencyService.addTaskDependency(dependencyDto);
      return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
   }

    @PutMapping("/{id}")
    public HttpEntity<?>editTaskDependency(@Valid @RequestBody TaskDependencyDto dependencyDto, UUID uuid){
      ApiResponse apiResponse=taskDependencyService.editTaskDependency(dependencyDto,uuid);
      return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
   }


    @DeleteMapping("/{id}")
    public HttpEntity<?>deleteTaskDependency( @PathVariable UUID id){
      ApiResponse apiResponse=taskDependencyService.deleteTaskDependency(id);
      return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
   }


}
