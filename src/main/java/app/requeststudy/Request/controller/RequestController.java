package app.requeststudy.Request.controller;

import app.requeststudy.Request.service.RestBasicService;
import app.requeststudy.Request.vo.SimpleVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class RequestController {

    private final RestBasicService restBasicService;

    public RequestController(RestBasicService restBasicService) {
        this.restBasicService = restBasicService;
    }


    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/getText")
    public String getText(){
        return restBasicService.getText();
    }

    @GetMapping("/getObject2")
    public Map<String, Object> getObject2(){
        return restBasicService.getObject2();
    }

    @GetMapping("/getObject3")
    public List<SimpleVO> getObject3(){
        return restBasicService.getObject3();
    }
}
