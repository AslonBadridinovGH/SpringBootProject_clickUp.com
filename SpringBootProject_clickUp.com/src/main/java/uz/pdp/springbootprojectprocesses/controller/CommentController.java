package uz.pdp.springbootprojectprocesses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootprojectprocesses.payload.ApiResponse;
import uz.pdp.springbootprojectprocesses.payload.CommentDto;
import uz.pdp.springbootprojectprocesses.serviceInterface.CommentService;

import java.util.UUID;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    CommentService commentService;


    @PostMapping("/addComment")
    public HttpEntity<?>addComment(@RequestBody CommentDto commentDto){
      ApiResponse apiResponse=commentService.addComment( commentDto);
      return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
   }

   @PutMapping("/{id}")
    public HttpEntity<?>addComment(@RequestBody CommentDto commentDto, @PathVariable UUID id){
      ApiResponse apiResponse=commentService.editComment( commentDto, id);
      return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
   }

   @DeleteMapping("/delete/{id}")
    public HttpEntity<?>deleteComment(@PathVariable UUID id){
      ApiResponse apiResponse=commentService.deleteComment(id);
      return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
   }

}
