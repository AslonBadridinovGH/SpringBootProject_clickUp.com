package uz.pdp.springbootprojectprocesses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springbootprojectprocesses.entity.les10_2.ClickApps;

import java.util.UUID;

public interface ClickAppsRepository extends JpaRepository<ClickApps, UUID> {
}
