package app.requeststudy.Request.service;

import app.requeststudy.Request.dto.UserRequest;
import app.requeststudy.Request.dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.Charset;

@Service
public class RestTemplateService {

    //Server로 요청을 보내는 서비스이다 .
    //http://localhost/api/server/hello 로 요청을 할 것이다.

    public UserResponse hello(UserRequest userRequest){

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080")
                .path("/api/server/hello")
                .queryParam("name",userRequest.getName())
                .queryParam("age",userRequest.getAge())
                .encode(Charset.defaultCharset())
                .build()
                .toUri();
        System.out.println("uri.toString() = " + uri.toString());

        RestTemplate restTemplate = new RestTemplate();

        //String result = restTemplate.getForObject(uri, String.class);
        //getForEntity는 응답을 ResponseEntity로 받을 수 있도록 해준다 .
        //파라미터 첫번째는 요청 URI 이며 , 2번째는 받을 타입
        ResponseEntity<UserResponse> result = restTemplate.getForEntity(uri, UserResponse.class);

        System.out.println("result.getStatusCode() = " + result.getStatusCode());
        System.out.println("result.getBody() = " + result.getBody());

        return result.getBody();

    }

}
