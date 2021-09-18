package zatribune.spring.gateways.data.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import zatribune.spring.gateways.data.DevBootstrap;
import zatribune.spring.gateways.data.models.Gateway;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class GatewayRepositoryIT {

    @Autowired
    private GatewayRepository repository;

    @BeforeEach
    void setUp() {

    }

    @Test
    void findAllLeftJoined() {
        List<Gateway> list=repository.findAllLeftJoined();
        System.out.println(list.size());
    }

    @Test
    void findByIdLeftJoined() {
    }
}