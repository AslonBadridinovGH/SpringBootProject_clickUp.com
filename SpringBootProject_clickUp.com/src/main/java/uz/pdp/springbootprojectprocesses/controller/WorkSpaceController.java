package uz.pdp.springbootprojectprocesses.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootprojectprocesses.entity.Les9.User;

import uz.pdp.springbootprojectprocesses.security.CurrentUser;
import uz.pdp.springbootprojectprocesses.serviceInterface.WorkSpaceService;
import uz.pdp.springbootprojectprocesses.payload.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/workspace")
public class WorkSpaceController {

    @Autowired
    WorkSpaceService workSpaceService;



    //OPEN  A WORKSPACE  FOR A USER WHO ENTERS THE SYSTEM
    @PostMapping("/addWorkSpace")
    public HttpEntity<?>addWorkSpace(@Valid @RequestBody WorkSpaceDto workSpaceDto, @CurrentUser User user){
      ApiResponse apiResponse=workSpaceService.addWorSpace( workSpaceDto, user);
      return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
   }


     @PostMapping("/addWorkSpaceRole")
     public HttpEntity<?>addWorkSpaceRole(@RequestParam Long workSpaceId,
                                          @RequestBody WorkSpaceRoleDto workSpaceRoleDto,
                                          @CurrentUser  User user) {
       ApiResponse apiResponse=workSpaceService.addWorSpaceRole(workSpaceId, workSpaceRoleDto, user);
       return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }


    /**
     * NAME, COLOR, AVATAR
     *
     * @param workSpaceDto
     * @return
     */
    @PutMapping
    public HttpEntity<?>editWorkSpace(@RequestBody WorkSpaceDto workSpaceDto) {
      ApiResponse apiResponse=workSpaceService.editWorSpace(workSpaceDto);
      return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
   }


    /**
     * NAME,COLOR, AVATAR
     * @param id
     * @param ownerId
     * @return
    */
    @PutMapping("/changeOwnerId/{id}")
    public HttpEntity<?>changeOwnerWorkSpace(@PathVariable Long id, @RequestParam UUID ownerId) {
      ApiResponse apiResponse=workSpaceService.changeOwnerWorkSpace(id, ownerId);
      return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
   }


    /**
     *
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public HttpEntity<?>deleteWorkSpace(@PathVariable Long id) {
      ApiResponse apiResponse=workSpaceService.deleteWorkSpace(id);
      return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
   }


    @PostMapping("/addOrEditRemove/{id}")
    public HttpEntity<?>addOrEditRemoveWorkSpace(@PathVariable Long id, @RequestBody MemberDto memberDto) {
        ApiResponse apiResponse=workSpaceService.addOrEditRemoveWorkSpace( id, memberDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }


    @PostMapping("/addOrRemovePermissionToWorkSpaceRole/{id}")
    public HttpEntity<?>addOrRemovePermissionToWorkSpaceRole(@RequestBody AddRemovePermissionDto dto) {
        ApiResponse apiResponse=workSpaceService.addOrRemovePermissionToWorkSpaceRole(dto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }


    /**
     *
     *
     * @param id
     * @return
     */
    @PutMapping("/join")
    public HttpEntity<?>joinToWorkSpace(@RequestParam Long id, @CurrentUser User user) {
        ApiResponse apiResponse=workSpaceService.joinToWorkSpace( id, user);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }


    @GetMapping("/member/{id}")
    public HttpEntity<?>getMemberAndGuestWorkSpace(@PathVariable Long id) {
        // ApiResponse apiResponse=workSpaceService.getMemberAndGuestWorkSpaces(id);
        // return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
        List<MemberDto> members=workSpaceService.getMemberAndGuestWorkSpaces(id);
        return ResponseEntity.ok(members);
    }


    @GetMapping
    public HttpEntity<?> getMyWorkSpaces(@CurrentUser User user) {
       List<WorkSpaceDto>workSpaces=workSpaceService.getMyWorkSpaces(user);
       return ResponseEntity.ok(workSpaces);
    }


}
