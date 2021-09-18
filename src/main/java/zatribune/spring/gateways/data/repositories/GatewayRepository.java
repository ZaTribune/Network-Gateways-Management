package zatribune.spring.gateways.data.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import zatribune.spring.gateways.data.models.Gateway;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface GatewayRepository extends CrudRepository<Gateway, UUID> {

    @Query("select distinct g from Gateway g left join fetch Device d on g.id=d.gateway")
    List<Gateway>findAllLeftJoined();

    @Query("select g from Gateway g left join fetch Device d on g.id=d.gateway where g.id=:gatewayId")
    Optional<Gateway> findByIdLeftJoined(UUID gatewayId);


}
