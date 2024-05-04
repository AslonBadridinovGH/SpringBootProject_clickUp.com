package uz.pdp.springbootprojectprocesses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springbootprojectprocesses.entity.les10_2.TimeTracked;

import java.util.UUID;

public interface TimeTrackedRepository extends JpaRepository<TimeTracked, UUID> {
}
