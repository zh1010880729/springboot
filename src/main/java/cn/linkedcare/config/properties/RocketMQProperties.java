package cn.linkedcare.config.properties;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Created by Benji on 2018/9/25.
 */
@Data
@Component("rocketMQProperties")
public class RocketMQProperties {

    private String namesrvAddr;

}
