package app.requeststudy.Request.service;

import app.requeststudy.Request.dto.Req;
import app.requeststudy.Request.dto.UserRequest;
import app.requeststudy.Request.dto.UserResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
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


    public UserResponse post() {

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080")
                .path("/api/server/user-test/{userId}/name/{username}")
                .encode(Charset.defaultCharset())
                .build()
                //pathVariable사용을 위한 메소드 순서대로 들어간다.
                .expand("100","ugo")
                .toUri();
        System.out.println("uri = " + uri);

        //아래 순서로 변환
        //http body - object - object mapper -> json - > http body json

        UserRequest req = new UserRequest("김종순", "30");

        RestTemplate restTemplate = new RestTemplate();
        //post 의 경우 PostForEntity를 사용한다. 파라미터 1 요청 주소 , 2 요청 바디 , 3 응답 바디
        ResponseEntity<UserResponse> result = restTemplate.postForEntity(uri, req, UserResponse.class);

        System.out.println("result.getStatusCode() = " + result.getStatusCode());
        System.out.println("result.getStatusCode() = " + result.getHeaders());
        System.out.println("result.getBody() = " + result.getBody());

        return result.getBody();
    }

    public UserResponse postMain() {

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080")
                .path("/api/server/user/{userId}/name/{username}")
                .encode()
                .build()
                //pathVariable사용을 위한 메소드 순서대로 들어간다.
                .expand("100","ugo")
                .toUri();
        System.out.println("uri = " + uri);

        //아래 순서로 변환
        //http body - object - object mapper -> json - > http body json

        UserRequest req = new UserRequest("김태성", "50");

        //RequestEntity 생성
        RequestEntity<UserRequest> requestEntity = RequestEntity
                //요청 방식 정한다
                .post(uri)
                //타입
                .contentType(MediaType.APPLICATION_JSON)
                //헤더 키 값과 벨류
                .header("x-authorization","auth")
                .header("custom-header","custom header")
                //요청 바디(요청 데이터)
                .body(req);

        RestTemplate restTemplate = new RestTemplate();
        //헤더를 함께 보낼때는 exchange메서드를 사용한다 파라미터 1 요청정보들이 들어있는 entity, 2 응답받을 타입
        ResponseEntity<UserResponse> response = restTemplate.exchange(requestEntity, UserResponse.class);

        System.out.println("result.getStatusCode() = " + response.getStatusCode());
        System.out.println("result.getStatusCode() = " + response.getHeaders());
        System.out.println("result.getBody() = " + response.getBody());

        return response.getBody();
    }

    public Req<UserResponse> genericExchange(){
        URI uri =UriComponentsBuilder
                .fromUriString("http://localhost:8080")
                .path("/api/server/user2/{userId}/name/{username}")
                .encode()
                .build()
                //pathVariable사용을 위한 메소드 순서대로 들어간다.
                .expand("100","ugo")
                .toUri();
        System.out.println(uri);

        //아래 순서로 변환
        //http body - object - object mapper -> json - > http body json

        UserRequest body = new UserRequest("김태성", "50");

        Req<UserRequest> req= new Req<>();
        req.setHeader(new Req.Header());
        req.setResBody(body);


        //RequestEntity 생성
        RequestEntity<Req<UserRequest>> requestEntity = RequestEntity
                //요청 방식 정한다
                .post(uri)
                //타입
                .contentType(MediaType.APPLICATION_JSON)
                //헤더 키 값과 벨류
                .header("x-authorization","auth")
                .header("custom-header","custom header")
                //요청 바디(요청 데이터)
                .body(req);

        RestTemplate restTemplate = new RestTemplate();
        //제네릭엔 클래스를 붙힐 수없다
        //아래처럼 ParameterizedTypeReference로 한번 감싸 타입을 갖게 한다.
        ResponseEntity<Req<UserResponse>> response
                = restTemplate.exchange(requestEntity,new ParameterizedTypeReference<>(){});

        //getBody() 는 reponseEntity의 응답 body이며 , getrBody는 그 응답바디 안의 필드명이다.
        return response.getBody();
    }

}
