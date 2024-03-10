package app.requeststudy.Request.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class UserResponse {

    private final String name;
    private final String age;

    public UserResponse(String name, String age){
        this.name=name;
        this.age=age;
    }
}
