package uz.pdp.springbootprojectprocesses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootprojectprocesses.payload.ApiResponse;
import uz.pdp.springbootprojectprocesses.payload.ClickAppsDto;
import uz.pdp.springbootprojectprocesses.serviceInterface.ClickAppsService;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/clickApps")
public class ClickAppsController {

    @Autowired
    ClickAppsService clickAppsService;


    @PostMapping("/addClickApps")
    public HttpEntity<?>addClickApps(@Valid @RequestBody ClickAppsDto clickAppsDto){
      ApiResponse apiResponse=clickAppsService.addClickApps( clickAppsDto);
      return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
   }

    @PutMapping("/editClickApps/{id}")
    public HttpEntity<?>editClickApps(@Valid @RequestBody ClickAppsDto clickAppsDto,@PathVariable UUID id){
      ApiResponse apiResponse=clickAppsService.editClickApps( clickAppsDto, id);
      return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
   }


    @DeleteMapping("/{id}")
    public HttpEntity<?>deleteClickApps(@PathVariable UUID id){
      ApiResponse apiResponse=clickAppsService.deleteClickApps(id);
      return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
   }


}
