package seguranca.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import seguranca.demo.entity.Perfil;

@Repository
public interface PeriflRepository extends JpaRepository<Perfil,Long> {
    
}
