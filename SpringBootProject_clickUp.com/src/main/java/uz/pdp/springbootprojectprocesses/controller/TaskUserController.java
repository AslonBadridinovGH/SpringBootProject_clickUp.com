package uz.pdp.springbootprojectprocesses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootprojectprocesses.payload.ApiResponse;
import uz.pdp.springbootprojectprocesses.payload.TaskUserDto;
import uz.pdp.springbootprojectprocesses.serviceInterface.TaskUserService;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/taskUser")
public class TaskUserController {

    @Autowired
    TaskUserService taskUserService;



    @PostMapping("/addTaskUser")
    public HttpEntity<?>addTaskUser(@Valid @RequestBody TaskUserDto taskUserDto ){
      ApiResponse apiResponse=taskUserService.addTaskUserService(taskUserDto);
      return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
   }

    @DeleteMapping("/{id}")
    public HttpEntity<?>deleteTaskUser(@PathVariable UUID id){
      ApiResponse apiResponse=taskUserService.deleteTaskUserService(id);
      return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
   }


}
