package uz.pdp.springbootprojectprocesses.entity.les10_2;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.springbootprojectprocesses.entity.template.AbsUUIDEntity;
import uz.pdp.springbootprojectprocesses.entity.enums.DependencyType;
import uz.pdp.springbootprojectprocesses.entity.les10_1.Task;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class TaskDependency extends AbsUUIDEntity {


    @ManyToOne
    private Task task;

    @OneToOne
    private Task  dependencyTask;

    private DependencyType dependencyType;

}
