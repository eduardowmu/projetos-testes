package configuration.basic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import configuration.basic.config.Config;
import configuration.basic.config.EnvironmentConfig;
/*Referencias:
 *https://www.concretepage.com/spring-5/activeprofiles-example-spring-test
 **/
@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test2")
@ContextConfiguration(classes = {Config.class})
class ProfilesIntegrationTest 
{	@Autowired
	private EnvironmentConfig environmentConfig;

	@Test
	void test() {environmentConfig.someMethod();}
}