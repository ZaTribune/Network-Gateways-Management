package zatribune.spring.gateways.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zatribune.spring.gateways.data.models.Device;
import zatribune.spring.gateways.data.models.DeviceStatus;
import zatribune.spring.gateways.data.models.Gateway;
import zatribune.spring.gateways.services.GatewayService;

import java.util.List;

@Slf4j
@Controller
public class MainController {

    private final GatewayService gatewayService;


    public MainController(GatewayService gatewayService) {
        this.gatewayService = gatewayService;
    }

    @RequestMapping({"/","/index",""})
    public String landingPage(Model model){

        List<Gateway> list=gatewayService.getAll();
        //list.forEach(item->System.out.println(item.getDevices().size()));
        model.addAttribute("gateways",list);
        model.addAttribute("newDevice",new Device());
        model.addAttribute("newGateway",new Gateway());
        model.addAttribute("statuses", DeviceStatus.values());

        return "index";
    }

    @RequestMapping("/modal/{type}")
    public String getModal
            (@PathVariable ModalType type, Model model, @RequestParam(required = false) List<ObjectError> errors) {
        log.info("Now, I'm opening a modal of type {}", type);
        //todo: use Fragmentation =>e.g.  return "fragments/modal :: " + modalType;
        switch (type) {
            case CREATE:
                model.addAttribute("newGateway",new Gateway());
                return "fragments/addGateway";
            case DELETE:
                model.addAttribute("title", "Confirm Deleting a Gateway !");
                model.addAttribute("question", "Are you sure you want to delete this Gateway?");
                model.addAttribute("info", "....some errors");
                break;
            case ERROR:
                model.addAttribute("title", null);
                model.addAttribute("errors", errors);
                model.addAttribute("info", "....some errors");
                break;
        }

        return "fragments/modal";
    }
}
