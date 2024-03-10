package app.requeststudy.Request.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class UserRequest {

    private final String name;
    private final String age;

    public UserRequest(String name, String age){
        this.name=name;
        this.age=age;
    }
}
