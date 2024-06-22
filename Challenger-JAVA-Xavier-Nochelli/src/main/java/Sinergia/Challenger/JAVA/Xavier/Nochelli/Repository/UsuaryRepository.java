package Sinergia.Challenger.JAVA.Xavier.Nochelli.Repository;

import Sinergia.Challenger.JAVA.Xavier.Nochelli.Models.Usuary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UsuaryRepository extends JpaRepository<Usuary , Long> {

     Usuary findByEmail(String email);

    boolean existsByEmail(String email);

}
