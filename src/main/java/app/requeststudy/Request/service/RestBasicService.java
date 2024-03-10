package app.requeststudy.Request.service;

import app.requeststudy.Request.dto.UserResponse;
import app.requeststudy.Request.vo.SimpleVO;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

@Service
public class RestBasicService {


    public String getText(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080")
                .path("/getText")
                .encode(Charset.defaultCharset())
                .build()
                .toUri();
        System.out.println("uri.toString() = " + uri.toString());

        RestTemplate restTemplate = new RestTemplate();

        //String result = restTemplate.getForObject(uri, String.class);
        //getForEntity는 응답을 ResponseEntity로 받을 수 있도록 해준다 .
        //파라미터 첫번째는 요청 URI 이며 , 2번째는 받을 타입
        //요청에 대한 여러가지 메서드가 있다 Get방식의 요청을 하기 위해선 getForEntity(ResponseEntity 반환)
        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

        System.out.println("result.getStatusCode() = " + result.getStatusCode());
        System.out.println("result.getBody() = " + result.getBody());

        return result.getBody();
    }

    public Map<String, Object> getObject2() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080")
                .path("/getObject2")
                .encode(Charset.defaultCharset())
                .build()
                .toUri();
        System.out.println("uri.toString() = " + uri.toString());

        RestTemplate restTemplate = new RestTemplate();

        //String result = restTemplate.getForObject(uri, String.class);
        //getForEntity는 응답을 ResponseEntity로 받을 수 있도록 해준다 .
        //파라미터 첫번째는 요청 URI 이며 , 2번째는 받을 타입
        //요청에 대한 여러가지 메서드가 있다 Get방식의 요청을 하기 위해선 getForEntity(ResponseEntity 반환)
        ResponseEntity<Map> result = restTemplate.getForEntity(uri, Map.class);

        System.out.println("result.getStatusCode() = " + result.getStatusCode());
        System.out.println("result.getBody() = " + result.getBody());

        return result.getBody();
    }

    public List<SimpleVO> getObject3() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080")
                .path("/getObject3")
                .encode(Charset.defaultCharset())
                .build()
                .toUri();
        System.out.println("uri.toString() = " + uri.toString());

        RestTemplate restTemplate = new RestTemplate();

        //String result = restTemplate.getForObject(uri, String.class);
        //getForEntity는 응답을 ResponseEntity로 받을 수 있도록 해준다 .
        //파라미터 첫번째는 요청 URI 이며 , 2번째는 받을 타입
        //요청에 대한 여러가지 메서드가 있다 Get방식의 요청을 하기 위해선 getForEntity(ResponseEntity 반환)
        ResponseEntity<List> result = restTemplate.getForEntity(uri, List.class);

        System.out.println("result.getStatusCode() = " + result.getStatusCode());
        System.out.println("result.getBody() = " + result.getBody());

        return result.getBody();
    }
}
