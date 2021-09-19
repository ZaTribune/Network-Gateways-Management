package zatribune.spring.gateways.controllers.rest;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import zatribune.spring.gateways.data.models.DeviceStatus;
import zatribune.spring.gateways.data.models.Gateway;
import zatribune.spring.gateways.services.GatewayService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RequestMapping("/rest/gateways")
@Controller
public class GatewaysRestController {

    private final GatewayService gatewayService;

    public GatewaysRestController(GatewayService gatewayService){
        this.gatewayService=gatewayService;
    }


    @PostMapping("/add")
    public String addNewGateway(@Valid @ModelAttribute(name = "newGateway") Gateway gateway, BindingResult bindingResult
            ,HttpServletResponse response, Model model) throws IOException {
        log.info("adding a new gateway");
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> log.error(objectError.toString()));
            model.addAttribute("statuses",DeviceStatus.values());
            //to force being recognized as error on ajax
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return "fragments/addGateway";
        }
        gatewayService.save(gateway);
        model.addAttribute("gateways",gatewayService.getAllWithDevices());
        return "home/gateways";
    }

    @Transactional
    @RequestMapping("/showGateway/{id}")
    public String showGateway(@PathVariable UUID id, Model model){
        Gateway gateway = gatewayService.getByIdWithDevices(id);
        model.addAttribute("gateway", gateway);
        return "/fragments/showGateway";
    }

    @Transactional
    @RequestMapping("/updateGateway/{id}")
    public String updateGateway(@PathVariable UUID id, Model model) {
        model.addAttribute("gateway", gatewayService.getByIdWithDevices(id));
        model.addAttribute("statuses", DeviceStatus.values());
        return "fragments/modifyGateway";
    }

    @PostMapping("/updateOrSaveGateway")
    public String updateOrSaveGateway(@Valid @ModelAttribute Gateway gateway,BindingResult bindingResult,Model model){
        log.info(" for gateway {}", gateway.getId());
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> log.error(objectError.toString()));
            model.addAttribute("statuses",DeviceStatus.values());
            return "fragments/modifyGateway";
        }
        Gateway updated=gatewayService.save(gateway);
        model.addAttribute("gateway",updated);//return updated info
        return "fragments/showGateway";
    }

}
