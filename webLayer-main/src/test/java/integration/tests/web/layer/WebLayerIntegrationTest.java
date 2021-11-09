package integration.tests.web.layer;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;
/*Essa propriedade do RANDOM_PORT faz com que o spring inicialize
 *o teste da aplicação em uma porta qualquer. Quer dizer que em cada
 *inicialização, ela será realizada em porta diferente.*/
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class WebLayerIntegrationTest 
{	@LocalServerPort
	private int port;
	
	/*É um client sincrono para fazer requisições HTTP e que
	 *embora seja utilizado no desenvolvimento também pode ser
	 *utilizado nos testes de integração*/
	private RestTemplate restTemplate;

	@BeforeEach
	public void setup() {restTemplate = new RestTemplate();}

	@Test
	public void someMethod() 
	{	String response = restTemplate.getForObject(
			"http://localhost:"+port+"/hello", String.class);
		Assertions.assertThat(response).isEqualTo("Hello");
	}
}