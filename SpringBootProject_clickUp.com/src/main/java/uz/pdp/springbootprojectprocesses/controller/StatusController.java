package uz.pdp.springbootprojectprocesses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootprojectprocesses.payload.ApiResponse;
import uz.pdp.springbootprojectprocesses.payload.StatusDto;
import uz.pdp.springbootprojectprocesses.serviceInterface.StatusService;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/status")
public class StatusController {

    @Autowired
    StatusService statusService;


    @PostMapping("/addStatus")
    public HttpEntity<?>addStatus(@Valid @RequestBody StatusDto statusDto){
      ApiResponse apiResponse=statusService.addStatus( statusDto);
      return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
   }


    @PutMapping("/{id}")
    public HttpEntity<?>editStatus(@Valid @RequestBody StatusDto statusDto, @PathVariable UUID id){
      ApiResponse apiResponse=statusService.editStatus( statusDto, id);
      return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
   }




}
