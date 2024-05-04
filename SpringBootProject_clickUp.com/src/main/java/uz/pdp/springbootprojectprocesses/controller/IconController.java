package uz.pdp.springbootprojectprocesses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootprojectprocesses.payload.ApiResponse;
import uz.pdp.springbootprojectprocesses.payload.IconDto;
import uz.pdp.springbootprojectprocesses.serviceInterface.IconService;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/icon")
public class IconController {

    @Autowired
    IconService iconService;

    @PostMapping("/addIcon")
    public HttpEntity<?>addIcon(@Valid @RequestBody IconDto iconDto){
      ApiResponse apiResponse=iconService.addIconService(iconDto);
      return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
   }

    @PutMapping("/{id}")
    public HttpEntity<?>editIcon(@Valid @RequestBody IconDto iconDto, UUID uuid){
      ApiResponse apiResponse=iconService.editIconService(iconDto,uuid);
      return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
   }


    @DeleteMapping("/delete/{id}")
    public HttpEntity<?>deleteIcon( @PathVariable UUID id){
      ApiResponse apiResponse=iconService.deleteIconService(id);
      return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
   }


}
