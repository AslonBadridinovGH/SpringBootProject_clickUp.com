package uz.pdp.springbootprojectprocesses.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import uz.pdp.springbootprojectprocesses.entity.enums.AddType;
import uz.pdp.springbootprojectprocesses.entity.enums.WorkSpacePermissionName;
import uz.pdp.springbootprojectprocesses.entity.enums.WorkSpaceRoleName;

import uz.pdp.springbootprojectprocesses.repository.*;
import uz.pdp.springbootprojectprocesses.serviceInterface.WorkSpaceService;
import uz.pdp.springbootprojectprocesses.entity.Les9.*;
import uz.pdp.springbootprojectprocesses.payload.*;


import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class WorkSpaceServiceImpl implements WorkSpaceService {

     @Autowired
     WorkSpaceRepository workSpaceRepository;
     @Autowired
     AttachmentRepository attachmentRepository;
     @Autowired
     WorkspaceUserRepository workspaceUserRepository;
     @Autowired
     WorkSpaceRoleRepository workSpaceRoleRepository;
     @Autowired
     WorkSpacePermissionRepository workSpacePermissionRepository;
     @Autowired
     UserRepository userRepository;


      @Override
      public ApiResponse addWorSpace(WorkSpaceDto workSpaceDto, User ownerUser)  {


          if (workSpaceRepository.existsByOwnerIdAndName(ownerUser.getId(), workSpaceDto.getName()))
          return new ApiResponse("you have already such workspace",false);

           WorkSpace workSpace=new WorkSpace( workSpaceDto.getName(), workSpaceDto.getColor(), ownerUser,
          workSpaceDto.getAvatarId()==null?null:attachmentRepository.findById(workSpaceDto.getAvatarId()).
          orElseThrow( () -> new ResourceNotFoundException("Attachment")) );
          workSpaceRepository.save(workSpace);

          WorkSpaceRole ownerRole = workSpaceRoleRepository.save(new WorkSpaceRole(workSpace, WorkSpaceRoleName.ROLE_OWNER.name(),  null));
          WorkSpaceRole adminRole = workSpaceRoleRepository.save(new WorkSpaceRole(workSpace, WorkSpaceRoleName.ROLE_ADMIN.name(),  null));
          WorkSpaceRole memberRole= workSpaceRoleRepository.save(new WorkSpaceRole(workSpace, WorkSpaceRoleName.ROLE_MEMBER.name(), null));
          WorkSpaceRole guestRole = workSpaceRoleRepository.save(new WorkSpaceRole(workSpace, WorkSpaceRoleName.ROLE_GUEST.name(),  null));


          WorkSpacePermissionName[] workSpacePermissionNames = WorkSpacePermissionName.values();

          List<WorkSpacePermission>workSpacePermissionList=new ArrayList<>();

          for (WorkSpacePermissionName workSpacePermissionName : workSpacePermissionNames)
          {

               WorkSpacePermission workSpacePermission=new WorkSpacePermission(ownerRole, workSpacePermissionName);
              workSpacePermissionList.add(workSpacePermission);

               if (workSpacePermissionName.getWorkSpaceRoleNames().contains(WorkSpaceRoleName.ROLE_ADMIN)){
                   workSpacePermissionList.add(new WorkSpacePermission(adminRole, workSpacePermissionName));
               }
               if (workSpacePermissionName.getWorkSpaceRoleNames().contains(WorkSpaceRoleName.ROLE_MEMBER)){
                   workSpacePermissionList.add(new WorkSpacePermission(memberRole, workSpacePermissionName));
               }
               if (workSpacePermissionName.getWorkSpaceRoleNames().contains(WorkSpaceRoleName.ROLE_GUEST)){
                   workSpacePermissionList.add(new WorkSpacePermission(guestRole, workSpacePermissionName));
               }
          }
          // workSpacePermissionRepository  ga  4 ta WorkSpacePermission  larni  birdan saqladik
         workSpacePermissionRepository.saveAll(workSpacePermissionList);

          // WORKSPACE USER
        workspaceUserRepository.save(new WorkSpaceUser(workSpace, ownerUser, ownerRole,
        new Timestamp(System.currentTimeMillis()), new Timestamp( System.currentTimeMillis()) ));

        return new ApiResponse("WorkSpace was saved",true);

     }



      @Override
      public ApiResponse addWorSpaceRole(Long workSpaceId, WorkSpaceRoleDto workSpaceRoleDto, User ownerUser) {

      Optional<WorkSpace> optionalWorkSpace = workSpaceRepository.findByOwnerId(ownerUser.getId());
      if (!optionalWorkSpace.isPresent()) {
          return new ApiResponse("you have already such workspace",false);
      }


      if (workSpaceRoleRepository.existsByWorkSpaceIdAndName(workSpaceId, workSpaceRoleDto.getName())){;
      return new ApiResponse("Error",false);}
      else {
      WorkSpaceRole workSpaceRole = workSpaceRoleRepository.save(
                 new WorkSpaceRole(optionalWorkSpace.get(), workSpaceRoleDto.getName(), workSpaceRoleDto.getExtendsRole()) );


      List<WorkSpacePermission> workSpacePermissionList = workSpacePermissionRepository.findAllByWorkSpaceRole_NameAndWorkSpaceRole_WorkSpaceId(
                                                          workSpaceRoleDto.getExtendsRole().name(), workSpaceId);
      List<WorkSpacePermission> newWorkSpacePermissions=new ArrayList<>();

      for (WorkSpacePermission workSpacePermission : workSpacePermissionList) {
          WorkSpacePermission newWorkSpacePermission=new WorkSpacePermission(workSpaceRole, workSpacePermission.getPermissionName());
          newWorkSpacePermissions.add(newWorkSpacePermission);
      }
      workSpacePermissionRepository.saveAll(newWorkSpacePermissions);
      return new ApiResponse("Accepted",true);
      }
  }


     @Override
     public ApiResponse addOrRemovePermissionToWorkSpaceRole(AddRemovePermissionDto dto) {

          Optional<WorkSpaceRole> optionalRole= workSpaceRoleRepository.findById(dto.getRoleId());
         if(!optionalRole.isPresent())
          return new ApiResponse("you have not  such workspace role",false);

         Optional<WorkSpacePermission> optionalWorkSpacePermission =
          workSpacePermissionRepository.findByWorkSpaceRoleIdAndPermissionName(dto.getRoleId(), dto.getPermissionName());

         if (dto.getAddType().equals(AddType.ADD)) {
             if (optionalWorkSpacePermission.isPresent()) return new ApiResponse("already exist",false);

            WorkSpacePermission workSpacePermission=new WorkSpacePermission(optionalRole.get(), dto.getPermissionName());
          workSpacePermissionRepository.save(workSpacePermission);
          return new ApiResponse("WorkSpacePermission was successfully added",true);
         }
         else if (dto.getAddType().equals(AddType.REMOVE)) {
             if (optionalWorkSpacePermission.isPresent()){
                 workSpacePermissionRepository.delete(optionalWorkSpacePermission.get());
                 return new ApiResponse("successfully deleted",true);
             }
               return new ApiResponse("such WorkSpacePermission is not exist ",false);
         }
         return new ApiResponse("such permission is not exist",false);
     }

      // WorkSpace ni  Add, Edit,  Remove
     @Override
     public ApiResponse addOrEditRemoveWorkSpace(Long id, MemberDto memberDto) {

              if (memberDto.getAddType().equals(AddType.ADD)) {
             WorkSpaceUser workSpaceUser=new WorkSpaceUser(
            workSpaceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id "+id)),
            userRepository.findById(memberDto.getId()).orElseThrow(() -> new ResourceNotFoundException("id "+ memberDto.getId())),
            workSpaceRoleRepository.findById(memberDto.getRoleId()).orElseThrow(() -> new ResourceNotFoundException("id "+memberDto.getRoleId())),
            new Timestamp(System.currentTimeMillis()),  null);
            workspaceUserRepository.save(workSpaceUser);

        }
        else if (memberDto.getAddType().equals(AddType.EDIT))  {

            WorkSpaceUser workSpaceUser=
                    workspaceUserRepository.findByWorkSpaceIdAndUserId(id, memberDto.getId()).orElseGet(WorkSpaceUser::new);
            workSpaceUser.setWorkSpaceRole( workSpaceRoleRepository.findById(memberDto.getRoleId()).orElseThrow(() -> new ResourceNotFoundException("id")));
            workspaceUserRepository.save(workSpaceUser);
        }
        else if (memberDto.getAddType().equals(AddType.REMOVE)) {
            workspaceUserRepository.deleteByWorkSpaceIdAndUserId(id, memberDto.getId());
        }

        return new ApiResponse("successfully done",true);
    }

     @Override
     public ApiResponse editWorSpace(WorkSpaceDto workSpaceDto) {

         WorkSpace workSpace = workSpaceRepository.findById(workSpaceDto.getId()).orElseThrow(
                                                            () -> new ResourceNotFoundException("not found"));
         workSpace.setName(workSpaceDto.getName());
         workSpace.setColor(workSpaceDto.getColor());
         workSpace.setAvatar(workSpaceDto.getAvatarId()==null?null:attachmentRepository.findById(workSpaceDto.getAvatarId()).
                                                  orElseThrow( () -> new ResourceNotFoundException("Attachment")));
         workSpaceRepository.save(workSpace);
         return new ApiResponse("WorkSpace was edited",true);

     }

     @Override
     public ApiResponse changeOwnerWorkSpace(Long id, UUID ownerId) {

         Optional<WorkSpace> workSpaceOptional = workSpaceRepository.findById(id);
         if (!workSpaceOptional.isPresent())
             return new ApiResponse("you have not workspace with such id ",false);

         Optional<WorkSpaceUser> optionalUser = workspaceUserRepository.findById(ownerId);
         if (!optionalUser.isPresent()) return new ApiResponse("User is not exist",false);

          WorkSpace workSpace = workSpaceOptional.get();
         workSpace.setOwner(optionalUser.get().getUser());
         workSpaceRepository.save(workSpace);
         return new ApiResponse("WorkSpace was saved",true);
     }

     @Override
     public ApiResponse deleteWorkSpace(Long id) {

          try {
          workSpaceRepository.deleteById(id);
          return new ApiResponse("deleted",true);
     }catch (Exception e){
               return new ApiResponse("not deleted error",false);
          }
     }

     // User was added to WorkSpace
     @Override
     public ApiResponse joinToWorkSpace(Long id, User user) {
         Optional<WorkSpaceUser> optionalWorkSpaceUser = workspaceUserRepository.findByWorkSpaceIdAndUserId(id, user.getId());
         if (optionalWorkSpaceUser.isPresent()){
             WorkSpaceUser workSpaceUser = optionalWorkSpaceUser.get();
             workSpaceUser.setDateJoined(new Timestamp(System.currentTimeMillis()));
             workspaceUserRepository.save(workSpaceUser);
             return new ApiResponse("Success",true);
         }
        return new ApiResponse("Error", false);
     }


     @Override
     public List<MemberDto> getMemberAndGuestWorkSpaces(Long id) {
     /*
         List<WorkSpaceRoleName> names=new ArrayList<>(Arrays.asList(WorkSpaceRoleName.ROLE_GUEST,WorkSpaceRoleName.ROLE_MEMBER));
         List<WorkSpaceUser> list = workspaceUserRepository.findAllByWorkSpaceIdAndWorkSpaceRoleRoleNameIn(id,names);
         return new ApiResponse("List",true, Collections.singletonList(list));
     */

     List<WorkSpaceUser> workSpaceUsers = workspaceUserRepository.findAllByWorkSpaceId(id);

       /* List<MemberDto>members=new ArrayList<>();
           for (WorkSpaceUser workSpaceUser : workSpaceUsers) {
             MemberDto memberDto = mapWorkSpaceUserToMemberDto(workSpaceUser);
             members.add(memberDto);
         }*/

     // second way:
     List<MemberDto> memberDtoList = workSpaceUsers.stream().map(workSpaceUser -> mapWorkSpaceUserToMemberDto(workSpaceUser))
                                     .collect(Collectors.toList());

     return workSpaceUsers.stream().map(this::mapWorkSpaceUserToMemberDto).collect(Collectors.toList());
   }

     @Override
     public List<WorkSpaceDto> getMyWorkSpaces(User user) {

        List<WorkSpaceUser> workSpaceUsers = workspaceUserRepository.findAllByUserId(user.getId());
        return workSpaceUsers.stream().map(workSpaceUser ->
                mapWorkSpaceUserToWorkSpaceDto(workSpaceUser.getWorkSpace())).collect(Collectors.toList());
    }

     public WorkSpaceDto mapWorkSpaceUserToWorkSpaceDto(WorkSpace workSpace){

     WorkSpaceDto workSpaceDto=new WorkSpaceDto();
     workSpaceDto.setId(workSpace.getId());
     workSpaceDto.setInitialLetter(workSpace.getInitialLetter());
     workSpaceDto.setName(workSpace.getName());

     workSpaceDto.setAvatarId(workSpace.getAvatar()==null?null:workSpace.getAvatar().getId());
     workSpaceDto.setColor(workSpace.getColor());
     return workSpaceDto;
     }

     public MemberDto mapWorkSpaceUserToMemberDto(WorkSpaceUser workSpaceUser){
          MemberDto memberDto=new MemberDto();
        memberDto.setId(workSpaceUser.getUser().getId());
        memberDto.setFullName(workSpaceUser.getUser().getFullName());
        memberDto.setEmail(workSpaceUser.getUser().getEmail());
        memberDto.setRoleName(workSpaceUser.getWorkSpaceRole().getName());
        memberDto.setLastActive(workSpaceUser.getUser().getLastActiveTime());
        return memberDto;
    }

}
