package br.com.jotavesecurity.mscondominios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsCondominiosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCondominiosApplication.class, args);
	}

}
