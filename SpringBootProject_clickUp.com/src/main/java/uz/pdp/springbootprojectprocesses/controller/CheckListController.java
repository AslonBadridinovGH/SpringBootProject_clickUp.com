package uz.pdp.springbootprojectprocesses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootprojectprocesses.payload.ApiResponse;
import uz.pdp.springbootprojectprocesses.payload.CheckListDto;
import uz.pdp.springbootprojectprocesses.serviceInterface.CheckListService;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/checkList")
public class CheckListController {

    @Autowired
    CheckListService checkListService;


    @PostMapping("/addCheckList")
    public HttpEntity<?>addCheckList(@Valid @RequestBody CheckListDto checkListDto){
      ApiResponse apiResponse=checkListService.addCheckList( checkListDto);
      return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
   }

    @PutMapping("/editCheckList/{id}")
    public HttpEntity<?>editCheckList(@Valid @RequestBody CheckListDto checkListDto,@PathVariable UUID id){
      ApiResponse apiResponse=checkListService.editCheckList( checkListDto, id);
      return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
   }


    @DeleteMapping("/{id}")
    public HttpEntity<?>deleteCheckList(@PathVariable UUID id){
      ApiResponse apiResponse=checkListService.deleteCheckList(id);
      return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
   }


}
