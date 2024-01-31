package gr.vasilakos.soilmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SoilMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoilMicroserviceApplication.class, args);
    }

}
