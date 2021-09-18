package zatribune.spring.gateways.services;

import zatribune.spring.gateways.data.models.Gateway;

import java.util.List;
import java.util.UUID;

public interface GatewayService extends CrudService<Gateway>{
    Gateway getByIdWithDevices(UUID uuid);
    List<Gateway> getAllWithDevices();
}
