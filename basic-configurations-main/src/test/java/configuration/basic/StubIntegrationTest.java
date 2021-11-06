package configuration.basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import configuration.basic.stubs.WorldServiceImplStub;
import configuration.basic.world.HelloService;

@SpringBootTest(classes = {WorldServiceImplStub.class})
@ContextConfiguration(classes = HelloService.class)
@ExtendWith(SpringExtension.class)
class StubIntegrationTest 
{	@Autowired
	private HelloService helloService;

	@Test
	public void otherTest() 
	{	String result = helloService.getHelloWorld();
		Assertions.assertTrue(result.equals("Hello Stub"));
	}
}