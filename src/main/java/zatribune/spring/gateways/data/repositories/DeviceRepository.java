package zatribune.spring.gateways.data.repositories;

import org.springframework.data.repository.CrudRepository;
import zatribune.spring.gateways.data.models.Device;

import java.util.UUID;

public interface DeviceRepository extends CrudRepository<Device, UUID> {
}
