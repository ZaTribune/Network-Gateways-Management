package zatribune.spring.gateways.data.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Gateway {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    @NotEmpty
    private String serial;
    @NotEmpty
    private String name;
    @NotEmpty
    @Pattern(regexp="^(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(\\.(?!$)|$)){4}$")
    private String IPV4;

    @Valid
    @Size(max=10)
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "gateway",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE}
            ,orphanRemoval = true)
    private List<Device> devices;

    public Gateway(UUID id) {
        this.id=id;
    }


    public void setDevices(List<Device>devices){
        if (getDevices()==null){
            this.devices=new ArrayList<>(1);
        }
        devices.forEach(device -> device.setGateway(this));
        this.devices.addAll(devices);
    }
//    public Gateway addDevice(Device device){
//        device.setGateway(this);
//        this.devices.add(device);
//        return this;
//    }
}
