package uz.pdp.springbootprojectprocesses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootprojectprocesses.payload.ApiResponse;
import uz.pdp.springbootprojectprocesses.payload.TaskHistoryDto;
import uz.pdp.springbootprojectprocesses.serviceInterface.TaskHistoryService;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/taskHistory")
public class TaskHistoryController {

    @Autowired
    TaskHistoryService taskHistoryService;


    @PostMapping("/addTaskHistory")
    public HttpEntity<?>addTaskHistory(@Valid @RequestBody TaskHistoryDto taskHistoryDto){
      ApiResponse apiResponse=taskHistoryService.addTaskHistory(taskHistoryDto);
      return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
   }

    @PutMapping("/{id}")
    public HttpEntity<?>editTaskHistory(@Valid @RequestBody TaskHistoryDto taskHistoryDto, UUID uuid){
      ApiResponse apiResponse=taskHistoryService.editTaskHistory(taskHistoryDto,uuid);
      return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
   }


    @DeleteMapping("/{id}")
    public HttpEntity<?>deleteTaskHistory( @PathVariable UUID id){
      ApiResponse apiResponse=taskHistoryService.deleteTaskHistory(id);
      return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
   }


}
