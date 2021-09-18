package zatribune.spring.gateways.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import zatribune.spring.gateways.data.models.Gateway;
import zatribune.spring.gateways.services.GatewayService;
import zatribune.spring.gateways.services.GatewayServiceImpl;

import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GatewaysController.class)
class GatewaysControllerTest {


    private MockMvc mvc;

    @MockBean
    private GatewayServiceImpl gatewayService;

    private GatewaysController gatewaysController;


    @BeforeEach
    void setup(){
        gatewaysController=new GatewaysController(gatewayService);
        mvc= MockMvcBuilders.standaloneSetup(gatewaysController).build();
    }

    @Test
    void addNewGateway() {

    }

    @Test
    void showGateway() throws Exception {
        UUID id=UUID.randomUUID();
        String name="Employees Gateway";
        Gateway gateway1=Gateway.builder()
                .id(id)
                .name(name)
                .serial("1KV573LX0004f")
                .IPV4("192.168.1.1")
                .build();

        when(gatewayService.getById(id)).thenReturn(gateway1);

        mvc.perform(
                get("/gateways/showGateway/"+ id)
                        .accept(MediaType.TEXT_HTML))
                .andDo(print())
                .andExpect(view().name("/fragments/showGateway"))
                .andExpect(status().isOk());
    }
}