package uz.pdp.springbootprojectprocesses.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootprojectprocesses.payload.ApiResponse;
import uz.pdp.springbootprojectprocesses.payload.SpaceViewDto;
import uz.pdp.springbootprojectprocesses.serviceInterface.SpaceViewService;

import java.util.UUID;

@RestController
@RequestMapping("/api/spaceView")
public class SpaceViewController {

    @Autowired
    SpaceViewService spaceViewService;



    @GetMapping("/{id}")
    public HttpEntity<?>getSpaceViewsBySpaceId(@PathVariable UUID id){
        ApiResponse apiResponse=spaceViewService.getSpaceViewsBySpaceId(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }


    @PostMapping("/addSpaceView")
    public HttpEntity<?>addSpaceView(@RequestBody SpaceViewDto spaceDto){
      ApiResponse apiResponse=spaceViewService.addSpaceView(spaceDto);
      return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
   }

    @PutMapping("/{id}")
    public HttpEntity<?>editSpaceView(@RequestBody SpaceViewDto spaceDto, @PathVariable UUID id){
      ApiResponse apiResponse=spaceViewService.editSpaceView( spaceDto, id);
      return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
   }


    @DeleteMapping("/{id}")
    public HttpEntity<?>deleteSpaceView(@PathVariable UUID id){
      ApiResponse apiResponse=spaceViewService.deleteSpaceView(id);
      return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
   }

}
