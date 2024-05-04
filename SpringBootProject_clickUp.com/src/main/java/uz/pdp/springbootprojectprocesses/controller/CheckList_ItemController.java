package uz.pdp.springbootprojectprocesses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootprojectprocesses.payload.ApiResponse;
import uz.pdp.springbootprojectprocesses.payload.CheckList_ItemDto;
import uz.pdp.springbootprojectprocesses.serviceInterface.CheckListItemService;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/checkListItem")
public class CheckList_ItemController {

    @Autowired
    CheckListItemService checkListItemService;


    @PostMapping("/addCheckListItem")
    public HttpEntity<?>addCheckListItem(@Valid @RequestBody CheckList_ItemDto checkListItemDto){
      ApiResponse apiResponse=checkListItemService.addCheckListItem( checkListItemDto);
      return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
   }


    @DeleteMapping("/{id}")
    public HttpEntity<?>deleteCheckList(@PathVariable UUID id){
      ApiResponse apiResponse=checkListItemService.deleteCheckListItem(id );
      return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
   }

}
