package uz.pdp.springbootprojectprocesses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootprojectprocesses.payload.ApiResponse;
import uz.pdp.springbootprojectprocesses.payload.PriorityDto;
import uz.pdp.springbootprojectprocesses.serviceInterface.PriorityService;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/priority")
public class PriorityController {

    @Autowired
    PriorityService priorityService;


    @PostMapping("/addPriority")
    public HttpEntity<?>addPriority(@Valid @RequestBody PriorityDto priorityDto){
      ApiResponse apiResponse=priorityService.addPriorityService(priorityDto);
      return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
   }

    @PutMapping("/{id}")
    public HttpEntity<?>editPriority(@Valid @RequestBody PriorityDto priorityDto, UUID uuid){
      ApiResponse apiResponse=priorityService.editPriorityService(priorityDto,uuid);
      return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
   }


    @DeleteMapping("/delete/{id}")
    public HttpEntity<?>deletePriority( @PathVariable UUID id){
      ApiResponse apiResponse=priorityService.deletePriorityService(id);
      return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
   }


}
