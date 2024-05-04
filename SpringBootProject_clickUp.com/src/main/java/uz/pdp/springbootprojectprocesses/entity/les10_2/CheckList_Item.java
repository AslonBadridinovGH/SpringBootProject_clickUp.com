package uz.pdp.springbootprojectprocesses.entity.les10_2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.springbootprojectprocesses.entity.template.AbsUUIDEntity;
import uz.pdp.springbootprojectprocesses.entity.Les9.User;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class CheckList_Item extends AbsUUIDEntity {

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private CheckList checkList;

    @OneToOne
    private  User  assignedUser;

    private boolean  resolved;
}