package zatribune.spring.gateways.controllers.rest;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import zatribune.spring.gateways.data.models.Device;
import zatribune.spring.gateways.services.DeviceService;

import java.util.UUID;

@Slf4j
@RequestMapping("/rest/devices")
@RestController
public class DevicesRestController {

    private final DeviceService deviceService;

    @Autowired
    public DevicesRestController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping("/get/{id}")
    public Device getDevice(@PathVariable UUID id){
         return deviceService.getById(id);
    }

    @PostMapping("/add")
    public Device addDevice(@ModelAttribute Device newDevice){
        return deviceService.save(newDevice);
    }

    @PutMapping("/update/{id}")
    public Device updateDevice(@PathVariable UUID id,@ModelAttribute Device newDevice){
        return deviceService.update(id,newDevice);
    }

    @PatchMapping("/patch/{id}")
    public Device patchDevice(@PathVariable UUID id,@ModelAttribute Device newDevice){

        Device oldDevice=deviceService.getById(id);

        if (!oldDevice.getVendor().equals(newDevice.getVendor())){
            oldDevice.setVendor(newDevice.getVendor());
        }

        if (!oldDevice.getGateway().equals(newDevice.getGateway())){
            oldDevice.setGateway(newDevice.getGateway());
        }

        if (!oldDevice.getStatus().equals(newDevice.getStatus())){
            oldDevice.setStatus(newDevice.getStatus());
        }

        return deviceService.save(oldDevice);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/delete/{id}")
    public void deleteDevice(@PathVariable UUID id){
        deviceService.deleteById(id);
    }

}
