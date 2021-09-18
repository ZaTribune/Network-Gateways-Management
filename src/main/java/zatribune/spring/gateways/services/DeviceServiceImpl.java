package zatribune.spring.gateways.services;

import org.springframework.stereotype.Service;
import zatribune.spring.gateways.data.models.Device;
import zatribune.spring.gateways.data.repositories.DeviceRepository;
import zatribune.spring.gateways.exceptions.EntityNotFoundException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DeviceServiceImpl implements DeviceService{

    private final DeviceRepository repository;

    public DeviceServiceImpl(DeviceRepository repository){
        this.repository=repository;

    }


    @Override
    public Device getById(UUID uuid) {
        return repository.findById(uuid).orElseThrow(()->new EntityNotFoundException("Device not found by id "+uuid));
    }

    @Override
    public Device save(Device input) {
        return repository.save(input);
    }

    @Override
    public Device update(Device input) {
        return repository.save(input);
    }

    @Override
    public void delete(Device input) {
        //todo:check for successful deletion using a custom query
        repository.delete(input);
    }

    @Override
    public List<Device> getAll() {
        return StreamSupport.stream(repository.findAll().spliterator(),false)
                .collect(Collectors.toList());
    }

}
