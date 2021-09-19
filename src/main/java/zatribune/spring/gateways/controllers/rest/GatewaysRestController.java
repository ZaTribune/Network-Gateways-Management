package zatribune.spring.gateways.controllers.rest;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import zatribune.spring.gateways.data.models.Gateway;
import zatribune.spring.gateways.services.GatewayService;

import java.util.UUID;

@Slf4j
@RequestMapping("/rest/gateways")
@Controller
public class GatewaysRestController {

    private final GatewayService gatewayService;

    public GatewaysRestController(GatewayService gatewayService){
        this.gatewayService=gatewayService;
    }

    @GetMapping("/get/{id}")
    public Gateway getGateway(@PathVariable UUID id){
        return gatewayService.getById(id);
    }

    @PostMapping("/add")
    public Gateway addGateway(@ModelAttribute Gateway newGateway){
        return gatewayService.save(newGateway);
    }

    @PutMapping("/update/{id}")
    public Gateway updateGateway(@PathVariable UUID id,@ModelAttribute Gateway newGateway){
        return gatewayService.update(id,newGateway);
    }

    @PatchMapping("/patch/{id}")
    public Gateway patchGateway(@PathVariable UUID id,@ModelAttribute Gateway newGateway){

        Gateway oldGateway=gatewayService.getById(id);

        if (!oldGateway.getIPV4().equals(newGateway.getIPV4())){
            oldGateway.setIPV4((newGateway.getIPV4()));
        }

        if (!oldGateway.getName().equals(newGateway.getName())){
            oldGateway.setName(newGateway.getName());
        }

        if (!oldGateway.getSerial().equals(newGateway.getSerial())){
            oldGateway.setSerial(newGateway.getSerial());
        }
        return gatewayService.save(oldGateway);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/delete/{id}")
    public void deleteGateway(@PathVariable UUID id){
        gatewayService.deleteById(id);
    }

}
