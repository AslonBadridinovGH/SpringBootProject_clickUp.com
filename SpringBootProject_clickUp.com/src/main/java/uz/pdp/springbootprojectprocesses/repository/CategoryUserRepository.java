package uz.pdp.springbootprojectprocesses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springbootprojectprocesses.entity.les10_1.CategoryUser;

import java.util.UUID;

public interface CategoryUserRepository extends JpaRepository<CategoryUser, UUID> {
}
