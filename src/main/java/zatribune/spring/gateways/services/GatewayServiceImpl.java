package zatribune.spring.gateways.services;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zatribune.spring.gateways.data.models.Gateway;
import zatribune.spring.gateways.data.repositories.GatewayRepository;
import zatribune.spring.gateways.exceptions.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class GatewayServiceImpl implements GatewayService {

    private final GatewayRepository repository;

    public GatewayServiceImpl(GatewayRepository repository){
        this.repository=repository;

    }


    @Override
    public Gateway getById(UUID uuid) {
        return repository.findById(uuid).orElseThrow(()->new EntityNotFoundException("Gateway not found for id "+uuid));
    }

    @Transactional
    @Override
    public Gateway getByIdWithDevices(UUID uuid) {
        Gateway gateway=repository.findByIdLeftJoined(uuid)
                .orElseThrow(()->new EntityNotFoundException("Gateway not found for id "+uuid));
        Hibernate.initialize(gateway.getDevices());

        return gateway;
    }

    @Override
    public Gateway save(Gateway input) {
        if(input.getDevices()!=null&&input.getDevices().size()>0){
            input.getDevices().forEach(device -> device.setGateway(input));
        }else {
            input.setDevices(new ArrayList<>());
        }
        return repository.save(input);
    }

    @Override
    public Gateway update(UUID id,Gateway input) {
        repository.findById(id).orElseThrow(()->new EntityNotFoundException("Device not found by id "+id));
        return repository.save(input);
    }

    @Override
    public void delete(Gateway input) {
        //todo:check for successful deletion using a custom query
        repository.delete(input);
    }

    @Override
    public void deleteById(UUID id) {
        Gateway gateway=repository.findById(id).orElseThrow(()->new EntityNotFoundException("Gateway not found by id "+id));
        repository.delete(gateway);
    }

    @Override
    public List<Gateway> getAll() {
        return StreamSupport.stream(repository.findAll().spliterator(),false)
                .collect(Collectors.toList());
    }

    @Override
    public List<Gateway> getAllWithDevices() {
        return repository.findAllLeftJoined();
    }

}
