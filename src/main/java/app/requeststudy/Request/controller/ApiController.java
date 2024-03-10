package app.requeststudy.Request.controller;

import app.requeststudy.Request.dto.Req;
import app.requeststudy.Request.dto.UserRequest;
import app.requeststudy.Request.dto.UserResponse;
import app.requeststudy.Request.service.RestTemplateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client")
public class ApiController {

    private final RestTemplateService restTemplateService;

    public ApiController(RestTemplateService templateService){
        this.restTemplateService = templateService;
    }

    @GetMapping("/hello")
    public UserResponse getHello(UserRequest userRequest){
        System.out.println("/api/client/hello");
        return restTemplateService.hello(userRequest);
    }

    @GetMapping("/post")
    public UserResponse postHello(){
        System.out.println("/api/client/post");
        return restTemplateService.post();
    }

    @GetMapping("/postMain")
    public UserResponse postMain(){
        System.out.println("/api/client/postMain");
        return restTemplateService.postMain();
    }

    @GetMapping("/postExchange")
    public Req<UserResponse> postExchange(){
        System.out.println("/api/client/postMain");
        return restTemplateService.genericExchange();
    }
}
