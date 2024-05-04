package uz.pdp.springbootprojectprocesses.entity.Les9;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.springbootprojectprocesses.entity.template.AbsUUIDEntity;
import uz.pdp.springbootprojectprocesses.entity.enums.WorkSpaceRoleName;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"work_space_id","name"}))
public class WorkSpaceRole  extends AbsUUIDEntity {


    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private WorkSpace  workSpace;

    @Column(nullable = false)
    private String   name;

    @Enumerated(EnumType.STRING)
    private WorkSpaceRoleName  extendsRole;



}
