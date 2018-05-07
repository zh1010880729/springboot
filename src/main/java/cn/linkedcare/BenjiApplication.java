package cn.linkedcare;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.linkedcare.dao")
public class BenjiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BenjiApplication.class, args);
    }
}
