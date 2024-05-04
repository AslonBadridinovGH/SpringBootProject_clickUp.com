package uz.pdp.springbootprojectprocesses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springbootprojectprocesses.entity.les10_2.SpaceClickApps;

import java.util.UUID;

public interface SpaceClickAppsRepo extends JpaRepository<SpaceClickApps, UUID> {
}
