package uz.pdp.springbootprojectprocesses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootprojectprocesses.entity.Les9.User;
import uz.pdp.springbootprojectprocesses.payload.ApiResponse;
import uz.pdp.springbootprojectprocesses.payload.IconDto;
import uz.pdp.springbootprojectprocesses.payload.TaskTagDto;
import uz.pdp.springbootprojectprocesses.security.CurrentUser;
import uz.pdp.springbootprojectprocesses.serviceInterface.IconService;
import uz.pdp.springbootprojectprocesses.serviceInterface.TaskTagService;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/icon")
public class IconController {

    @Autowired
    IconService iconService;
/*
  Status va tasklarni qo'shish, Task statusini o'zgartirish, Task ga file biriktirish, biriktirilgan file ni o'chirish,
  Task ga comment yoki tag qo'shish uni edit qilish va o'chirish, task ga user assign qilish va assign qilingan user_ni
  olib tashlash kabi amallarni bajaruvchi method larni yozing.
*/


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
