package cn.linkedcare;

import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.*;
import com.pingplusplus.model.Charge;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BenjiApplication.class)
public class BenjiApplicationTests {


    @Test
    public void doTest() {
        Pingpp.apiKey = "sk_live_1qPSe5KWr5iD4irL4SiPaj1O";
        Pingpp.privateKeyPath = this.getClass().getClassLoader().getResource("pingPlusPlusPrivateKey.pem").getPath();
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("order_no", "benjiTest123456");
        chargeParams.put("amount", 100);
        Map<String, String> app = new HashMap<String, String>();
        app.put("id", "app_9K484Sj9GW1K4enn");
        chargeParams.put("app", app);
        chargeParams.put("channel", "wx_wap");
        chargeParams.put("currency", "cny");
        chargeParams.put("client_ip", "127.0.0.1");
        chargeParams.put("subject", "test_1");
        chargeParams.put("body", "test");
        Map<String, String> extraMap = new HashMap<>(16);
        extraMap.put("result_url", "https://www.baidu.com");
        chargeParams.put("extra", extraMap);
        long timeExpire = LocalDateTime.now().plusHours(1).toEpochSecond(ZoneOffset.of("+8"));
        chargeParams.put("time_expire", timeExpire);
        try {
            Charge charge = Charge.create(chargeParams);
            System.out.println(timeExpire + "----------------" + charge.getTimeExpire());
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        } catch (ChannelException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (RateLimitException e) {
            e.printStackTrace();
        }
    }

}
