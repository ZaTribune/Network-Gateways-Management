package zatribune.spring.gateways.controllers.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import zatribune.spring.gateways.data.models.Device;
import zatribune.spring.gateways.data.models.DeviceStatus;
import zatribune.spring.gateways.data.models.Gateway;
import zatribune.spring.gateways.services.DeviceService;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DevicesRestController.class)
class DevicesRestControllerTest {

    MockMvc mockMvc;

    @MockBean
    private DeviceService deviceService;

    @BeforeEach
    void setUp() {
        DevicesRestController controller = new DevicesRestController(deviceService);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    }

    @Test
    void getDevice() throws Exception {
        UUID id = UUID.randomUUID();

        Device d1 = Device.builder()
                .id(id)
                .status(DeviceStatus.ONLINE)
                .vendor("Samsung")
                .build();

        when(deviceService.getById(id)).thenReturn(d1);

        mockMvc.perform(
                get("/rest/devices/get/" + id).accept(MediaType.APPLICATION_JSON))
                //.andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void addDevice() throws Exception {
        UUID id = UUID.randomUUID();

        Device d1 = Device.builder()
                .id(id)
                .status(DeviceStatus.ONLINE)
                .vendor("Samsung")
                .build();

        ObjectMapper mapper = new ObjectMapper();

        when(deviceService.save(any(Device.class))).thenReturn(d1);

        mockMvc.perform(
                post("/rest/devices/add")
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(mapper.writeValueAsString(d1))
                        .requestAttr("device", new Device()))
                //.andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updateDevice() throws Exception {
        UUID id = UUID.randomUUID();

        Device d1 = Device.builder()
                .id(id)
                .status(DeviceStatus.ONLINE)
                .vendor("Samsung")
                .build();

        ObjectMapper mapper = new ObjectMapper();

        when(deviceService.update(any(), any(Device.class))).thenReturn(d1);

        mockMvc.perform(
                put("/rest/devices/update/" + id)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(mapper.writeValueAsString(d1))
                        .requestAttr("device", new Device()))
                //.andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void patchDevice() throws Exception {
        UUID id = UUID.randomUUID();

        Gateway gateway1 = Gateway.builder()
                .id(UUID.randomUUID())
                .name("Employees Gateway")
                .serial("1KV573LX0004f")
                .IPV4("192.168.1.1")
                .build();

        Device d1 = Device.builder()
                .id(id)
                .status(DeviceStatus.ONLINE)
                .gateway(gateway1)
                .vendor("Samsung")
                .build();

        Device d2 = Device.builder()
                .id(id)
                .status(DeviceStatus.OFFLINE)
                .gateway(gateway1)
                .vendor("IPhone")
                .build();


        ObjectMapper mapper = new ObjectMapper();

        when(deviceService.getById(id)).thenReturn(d1);
        when(deviceService.save(any())).thenReturn(d2);
        when(deviceService.update(id, d1)).thenReturn(d2);

        mockMvc.perform(
                patch("/rest/devices/patch/" + id)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(mapper.writeValueAsString(d1))
                        .contentType(MediaType.APPLICATION_JSON))
                //.andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteDevice() throws Exception {

        UUID id = UUID.randomUUID();

        doNothing().when(deviceService).deleteById(id);

        mockMvc.perform(
                delete("/rest/devices/delete/" + id)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .requestAttr("device", new Device()))
                .andExpect(status().isAccepted());
        verify(deviceService, times(1)).deleteById(id);
    }
}