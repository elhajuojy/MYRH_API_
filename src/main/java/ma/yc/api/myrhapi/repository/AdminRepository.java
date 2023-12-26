package ma.yc.api.myrhapi.repository;

import ma.yc.api.myrhapi.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {
}
