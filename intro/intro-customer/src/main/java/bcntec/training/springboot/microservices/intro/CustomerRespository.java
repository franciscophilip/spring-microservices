package bcntec.training.springboot.microservices.intro;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

//repository does not support Reactive. Ideally this should use reactive repository
@RepositoryRestResource
@Lazy
interface CustomerRespository extends JpaRepository<Customer,Long> {
	Optional<Customer> findByName(@Param("name") String name);
}
