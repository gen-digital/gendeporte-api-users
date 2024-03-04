package cl.gendigital.gendeporte.users.api.endpoint;

import cl.gendigital.gendeporte.users.api.request.user.post.PostCreateUserRequest;
import cl.gendigital.gendeporte.users.api.responses.base.BaseResponse;
import cl.gendigital.gendeporte.users.api.responses.base.MessageResponse;
import cl.gendigital.gendeporte.users.api.responses.user.get.GetUserResponse;
import cl.gendigital.gendeporte.users.api.responses.user.post.PostCreateUserResponse;
import cl.gendigital.gendeporte.users.core.commands.CreateUserCmd;
import cl.gendigital.gendeporte.users.core.commands.GetUserCmd;
import cl.gendigital.gendeporte.users.core.entities.domain.user.User;
import cl.gendigital.gendeporte.users.core.port.services.UserServicePort;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserServicePort userService;
    @PostMapping()
    public ResponseEntity<BaseResponse> createUser(@RequestBody @Validated PostCreateUserRequest request){
        final Integer userId = userService.createUser(toCmd(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponseCreate(userId,"200","User created"));
    }

    @GetMapping("/by-username/{username}")
    public ResponseEntity<BaseResponse> getUser(@PathVariable String username){
        final User user = userService.getUser(new GetUserCmd(username));
        return ResponseEntity.status(HttpStatus.OK).body(toResponse(user,"200","User founded"));

    }

    private CreateUserCmd toCmd(PostCreateUserRequest request) {
        return new CreateUserCmd(
                request.getUsername(), request.getPassword(), request.getEmail());
    }

    private BaseResponse toResponse(User user, String code, String message) {
        return BaseResponse.builder()
                .success(new MessageResponse(code, message))
                .data(toResponse(user))
                .build();
    }

    private GetUserResponse toResponse(User user) {
        return GetUserResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .build();
    }

    private BaseResponse toResponseCreate(Integer userId,String code, String message) {
        return BaseResponse.builder()
                .success(new MessageResponse(code, message))
                .data(new PostCreateUserResponse(userId))
                .build();
    }

}
