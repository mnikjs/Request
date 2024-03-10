package app.requeststudy.Request.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserRequest {

    private final String name = "김종순";
    private final String age = "20";
}
