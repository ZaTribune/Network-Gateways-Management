package zatribune.spring.gateways.data;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import zatribune.spring.gateways.data.models.Device;
import zatribune.spring.gateways.data.models.DeviceStatus;
import zatribune.spring.gateways.data.models.Gateway;
import zatribune.spring.gateways.data.repositories.DeviceRepository;
import zatribune.spring.gateways.data.repositories.GatewayRepository;

import java.util.List;
import java.util.UUID;

@Component
public class DevBootstrap implements CommandLineRunner {

    private final GatewayRepository gatewayRepository;

    @Autowired
    public DevBootstrap(GatewayRepository gatewayRepository) {
        this.gatewayRepository = gatewayRepository;
    }

    @Override
    public void run(String... args) {
      initData();

    }

    void initData(){
        Device d1=Device.builder()
                .id(UUID.randomUUID())
                .status(DeviceStatus.ONLINE)
                .vendor("Samsung")
                .build();

        Device d2=Device.builder()
                .id(UUID.randomUUID())
                .status(DeviceStatus.ONLINE)
                .vendor("Oppo")
                .build();
        Device d3=Device.builder()
                .id(UUID.randomUUID())
                .status(DeviceStatus.ONLINE)
                .vendor("HP")
                .build();
        Device d4=Device.builder()
                .id(UUID.randomUUID())
                .status(DeviceStatus.ONLINE)
                .vendor("Dell")
                .build();
        Device d5=Device.builder()
                .id(UUID.randomUUID())
                .status(DeviceStatus.ONLINE)
                .vendor("Lenovo")
                .build();

        Gateway gateway1=Gateway.builder()
                .id(UUID.randomUUID())
                .name("Employees Gateway")
                .serial("1KV573LX0004f")
                .IPV4("192.168.1.1")
                .build();
        gateway1.setDevices(List.of(d1,d2,d3));


        Gateway gateway2=Gateway.builder()
                .id(UUID.randomUUID())
                .name("Managers Gateway")
                .serial("1KV288LX0006f")
                .IPV4("192.168.1.17")
                .build();
        gateway2.setDevices(List.of(d4,d5));

        gatewayRepository.saveAll(List.of(gateway1,gateway2));

    }
}
