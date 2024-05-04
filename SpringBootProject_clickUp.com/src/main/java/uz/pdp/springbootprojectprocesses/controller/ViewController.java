package uz.pdp.springbootprojectprocesses.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootprojectprocesses.entity.Les9.User;
import uz.pdp.springbootprojectprocesses.payload.ApiResponse;
import uz.pdp.springbootprojectprocesses.payload.ViewDto;
import uz.pdp.springbootprojectprocesses.security.CurrentUser;
import uz.pdp.springbootprojectprocesses.serviceInterface.ViewService;

import java.util.UUID;

@RestController
@RequestMapping("/api/view")
public class ViewController {

    @Autowired
    ViewService viewService;


    @GetMapping
    public HttpEntity<?>getViews(){
        ApiResponse apiResponse=viewService.getViews();
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }


    @PostMapping("/addView")
    public HttpEntity<?>addView(@RequestBody ViewDto viewDto, @CurrentUser User user){
      ApiResponse apiResponse=viewService.addView(viewDto, user);
      return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
   }

    @PutMapping("/{id}")
    public HttpEntity<?>editView(@RequestBody ViewDto viewDto, @PathVariable UUID id, @CurrentUser User user){
      ApiResponse apiResponse=viewService.editView( viewDto, id, user);
      return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
   }

    @DeleteMapping("/{id}")
    public HttpEntity<?>deleteView(@PathVariable UUID id){
      ApiResponse apiResponse=viewService.deleteView(id);
      return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
   }

}
