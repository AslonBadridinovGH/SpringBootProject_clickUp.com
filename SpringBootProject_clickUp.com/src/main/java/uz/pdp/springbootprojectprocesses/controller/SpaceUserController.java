package uz.pdp.springbootprojectprocesses.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootprojectprocesses.payload.ApiResponse;
import uz.pdp.springbootprojectprocesses.payload.SpaceUserDto;
import uz.pdp.springbootprojectprocesses.serviceInterface.SpaceUserService;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/spaceUser")
public class SpaceUserController {

    @Autowired
    SpaceUserService spaceUserService;



    @GetMapping
    public HttpEntity<?>getSpacesUsers(){
        ApiResponse apiResponse=spaceUserService.getSpaceUsers();
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }


    @PostMapping("/addSpaceUser")
    public HttpEntity<?>addSpaceUser(@Valid @RequestBody SpaceUserDto spaceDto){
      ApiResponse apiResponse=spaceUserService.addSpaceUser(spaceDto);
      return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
   }

    @PutMapping("/{id}")
    public HttpEntity<?>editSpaceUser(@RequestBody SpaceUserDto spaceDto, @PathVariable UUID id){
      ApiResponse apiResponse=spaceUserService.editSpaceUser( spaceDto, id);
      return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
   }

    @DeleteMapping("/{id}")
    public HttpEntity<?>deleteSpaceUser(@PathVariable UUID id){
      ApiResponse apiResponse=spaceUserService.deleteSpaceUser(id);
      return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
   }

}
