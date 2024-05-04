package uz.pdp.springbootprojectprocesses.entity.les10_1;
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
public class TaskUser extends AbsUUIDEntity {

    @ManyToOne
    private Task task;

    @OneToOne
    private User user;

}
