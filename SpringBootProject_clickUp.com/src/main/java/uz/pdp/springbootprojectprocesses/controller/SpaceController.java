package uz.pdp.springbootprojectprocesses.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootprojectprocesses.entity.Les9.User;
import uz.pdp.springbootprojectprocesses.payload.ApiResponse;
import uz.pdp.springbootprojectprocesses.payload.SpaceDto;
import uz.pdp.springbootprojectprocesses.security.CurrentUser;
import uz.pdp.springbootprojectprocesses.serviceInterface.SpaceService;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/space")
public class SpaceController {

    @Autowired
    SpaceService spaceService;


    @GetMapping
    public HttpEntity<?>getSpaces(){
        ApiResponse apiResponse=spaceService.getSpaces();
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }


    @PostMapping("/addSpace")
    public HttpEntity<?>addSpace(@Valid @RequestBody SpaceDto spaceDto, @CurrentUser User user){
      ApiResponse apiResponse=spaceService.addSpace( spaceDto, user);
      return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
   }


    /**
     * NAME, COLOR, AVATAR O'ZGARAISHI MUMKIN
     *
     * @param spaceDto
     * @return
     */
    @PutMapping("/{id}")
    public HttpEntity<?>editSpace(@RequestBody SpaceDto spaceDto, @PathVariable UUID id, @CurrentUser User user){
      ApiResponse apiResponse=spaceService.editWorSpace( spaceDto, id, user);
      return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
   }


    /**
     * Space delete
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public HttpEntity<?>deleteSpace(@PathVariable UUID id){
      ApiResponse apiResponse=spaceService.deleteSpace(id);
      return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
   }
}
