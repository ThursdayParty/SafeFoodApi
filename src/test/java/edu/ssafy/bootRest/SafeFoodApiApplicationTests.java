package edu.ssafy.bootRest;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Slf4j
class SafeFoodApiApplicationTests {

	@Test
	void contextLoads() {
	}

    @Test
    public void test_getForEntity() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:9090/api/foods/1", String.class, 25);
        log.info("statusCode: {}", responseEntity.getStatusCode());
        log.info("getBody: {}", responseEntity.getBody());
    }
}
