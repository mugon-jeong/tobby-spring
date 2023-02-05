package com.example.tobby;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class HelloApiTest {

  @Test
  void helloApi() {
    // http localhost:8080/hello?name=Spring
    TestRestTemplate rest = new TestRestTemplate();

    ResponseEntity<String> res = rest.getForEntity("http://localhost:8080/hello?name={name}",
        String.class, "Spring");
    // status code 200
    assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
    // header(content-type_ text/plain
    assertThat(res.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE);
    // body hello Spring
    assertThat(res.getBody()).isEqualTo("Hello Spring");
  }

  @Test
  void failsHelloApi() {
    // http localhost:8080/hello?name=Spring
    TestRestTemplate rest = new TestRestTemplate();

    ResponseEntity<String> res = rest.getForEntity("http://localhost:8080/hello?name=", String.class);
    // status code 200
    assertThat(res.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
