package uz.pdp.springbootprojectprocesses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootprojectprocesses.payload.ApiResponse;
import uz.pdp.springbootprojectprocesses.payload.TagDto;
import uz.pdp.springbootprojectprocesses.serviceInterface.TagService;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/tag")
public class TagController {

    @Autowired
    TagService tagService;


    @PostMapping("/addTag")
    public HttpEntity<?>addTag(@Valid @RequestBody TagDto tagDto){
      ApiResponse apiResponse=tagService.addTagService(tagDto);
      return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
   }

    @PutMapping("/{id}")
    public HttpEntity<?>editTag(@Valid @RequestBody TagDto tagDto, UUID uuid){
      ApiResponse apiResponse=tagService.editTagService(tagDto, uuid);
      return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
   }


    @DeleteMapping("/{id}")
    public HttpEntity<?>deleteTag( @PathVariable UUID id){
      ApiResponse apiResponse=tagService.deleteTagService(id);
      return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
   }


}
