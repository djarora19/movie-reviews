package adj.learn.springboot.moviecatalogue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class MovieCatalogueServiceApplication
{

	@Bean
	@LoadBalanced
	RestTemplate getRestTemplate()
	{
		HttpComponentsClientHttpRequestFactory clientRequestFactory = new HttpComponentsClientHttpRequestFactory();
		clientRequestFactory.setConnectTimeout(3000);
		return new RestTemplate(clientRequestFactory);
	}

	@Bean
	WebClient.Builder getWebClient()
	{
		return WebClient.builder();
	}

	public static void main(String[] args)
	{
		SpringApplication.run(MovieCatalogueServiceApplication.class, args);
	}

}