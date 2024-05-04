package uz.pdp.springbootprojectprocesses.entity.Les9;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.springbootprojectprocesses.entity.template.AbsUUIDEntity;
import uz.pdp.springbootprojectprocesses.entity.enums.WorkSpacePermissionName;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class WorkSpacePermission extends AbsUUIDEntity {


    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private WorkSpaceRole workSpaceRole;


    @Enumerated(EnumType.STRING)
    private WorkSpacePermissionName  permissionName;  //add member, remover member

}
