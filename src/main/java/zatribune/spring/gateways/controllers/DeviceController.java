package zatribune.spring.gateways.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import zatribune.spring.gateways.data.models.Device;
import zatribune.spring.gateways.services.DeviceService;

@Slf4j
@RequestMapping("/requests")
@Controller
public class DeviceController {

    private final DeviceService deviceService;


    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping("/add")
    public String addDevice(@ModelAttribute Device newDevice){

        return "";
    }
}
