package cl.gendigital.gendeporte.users.api.endpoint;

import cl.gendigital.gendeporte.users.api.request.user.post.PostNewUserRequest;
import cl.gendigital.gendeporte.users.api.responses.base.BaseResponse;
import cl.gendigital.gendeporte.users.api.responses.base.MessageResponse;
import cl.gendigital.gendeporte.users.api.responses.user.get.GetUserResponse;
import cl.gendigital.gendeporte.users.api.responses.user.post.PostNewUserResponse;
import cl.gendigital.gendeporte.users.core.commands.CreateUserCmd;
import cl.gendigital.gendeporte.users.core.commands.GetUserCmd;
import cl.gendigital.gendeporte.users.core.entities.domain.user.User;
import cl.gendigital.gendeporte.users.core.port.services.UserServicePort;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UsersControllers {

    private final UserServicePort userService;
    @PostMapping("/create-users")
    public ResponseEntity<BaseResponse> createUser(@RequestBody @Validated PostNewUserRequest request){
        Integer userId = userService.createUser(toCmd(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(userId));
    }

    @GetMapping("/search-users")
    public ResponseEntity<BaseResponse> getUser(@RequestParam String username){
        User user = userService.getUser(new GetUserCmd(username));
        return ResponseEntity.ok(toResponse(user,"201","Usuario Encontrado"));

    }

    private CreateUserCmd toCmd(PostNewUserRequest request) {
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
                .validationCode(user.getValidationCode())
                .build();
    }

    private BaseResponse toResponse(Integer userId) {
        return BaseResponse.builder()
                .success(new MessageResponse("C00", "Success user creation"))
                .data(new PostNewUserResponse(userId))
                .build();
    }

}
