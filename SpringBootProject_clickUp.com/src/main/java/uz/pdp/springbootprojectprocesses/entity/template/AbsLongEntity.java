package uz.pdp.springbootprojectprocesses.entity.template;

import lombok.Data;

import javax.persistence.*;

@Data
@MappedSuperclass
public abstract class AbsLongEntity extends AbsMainEntity{

    // UUID ning Type i
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;

}
