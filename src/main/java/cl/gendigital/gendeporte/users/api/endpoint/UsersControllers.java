package cl.gendigital.gendeporte.users.api.endpoint;

import cl.gendigital.gendeporte.users.api.request.user.post.PostMoreInfoUserRequest;
import cl.gendigital.gendeporte.users.api.request.user.post.PostNewUserRequest;
import cl.gendigital.gendeporte.users.api.request.user.post.PostVerifyUserRequest;
import cl.gendigital.gendeporte.users.api.responses.base.BaseResponse;
import cl.gendigital.gendeporte.users.api.responses.base.MessageResponse;
import cl.gendigital.gendeporte.users.api.responses.user.get.GetUserResponse;
import cl.gendigital.gendeporte.users.api.responses.user.post.PostMoreInfoUserResponse;
import cl.gendigital.gendeporte.users.api.responses.user.post.PostNewUserResponse;
import cl.gendigital.gendeporte.users.api.responses.user.post.PostVerifyUserResponse;
import cl.gendigital.gendeporte.users.core.commands.CreateUserCmd;
import cl.gendigital.gendeporte.users.core.commands.GetUserCmd;
import cl.gendigital.gendeporte.users.core.commands.MoreInformationUserCmd;
import cl.gendigital.gendeporte.users.core.commands.VerifyUserCmd;
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
        return ResponseEntity.status(HttpStatus.OK).body(toResponse(user,"200","User founded"));

    }

    @PostMapping("/verify-users")
    public ResponseEntity<BaseResponse> verifyUser(@RequestBody PostVerifyUserRequest request){
        User user = userService.verifyUser(toCmd(request));
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(toResponseVerify(user,"200","User verified"));
    }

    @PostMapping("/moreInfo-users")
    public ResponseEntity<BaseResponse> moreInformation(@RequestBody PostMoreInfoUserRequest request){
        User user = userService.moreInfo(toCmd(request));
        return ResponseEntity.status(HttpStatus.OK).body(toResponseInfo(user,"200","User updated information"));
    }

    private CreateUserCmd toCmd(PostNewUserRequest request) {
        return new CreateUserCmd(
                request.getUsername(), request.getPassword(), request.getEmail()
        );
    }

    private VerifyUserCmd toCmd(PostVerifyUserRequest request){
        return new VerifyUserCmd(
                request.getUsername(), request.getValidationCode()
        );
    }

    private MoreInformationUserCmd toCmd(PostMoreInfoUserRequest request){
        return new MoreInformationUserCmd(
                request.getUsername(), request.getFirstName(), request.getFirstName(),request.getPhone(), request.getAddress()
        );
    }

    private BaseResponse toResponse(User user, String code, String message) {
        return BaseResponse.builder()
                .success(new MessageResponse(code, message))
                .data(toResponse(user))
                .build();
    }
    private BaseResponse toResponse(Integer userId) {
        return BaseResponse.builder()
                .success(new MessageResponse("C00", "Success user creation"))
                .data(new PostNewUserResponse(userId))
                .build();
    }
    private BaseResponse toResponseVerify(User user,String code, String message){
        return BaseResponse.builder()
                .success(new MessageResponse(code, message))
                .data(toResponser(user))
                .build();
    }
    private BaseResponse toResponseInfo(User user,String code, String message){
        return BaseResponse.builder()
                .success(new MessageResponse(code, message))
                .data(toResponseMore(user))
                .build();
    }


    private PostVerifyUserResponse toResponser(User user){
        return PostVerifyUserResponse
                .builder()
                .username(user.getUsername())
                .enabledAt(user.getEnabledAt())
                .build();
    }

    private GetUserResponse toResponse(User user) {
        return GetUserResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .validationCode(user.getValidationCode())
                .createdAt(user.getCreatedAt())
                .enabledAt(user.getEnabledAt())
                .updatedAt(user.getUpdatedAt())
                .address(user.getAddress())
                .build();
    }



    private PostMoreInfoUserResponse toResponseMore(User user){
        return PostMoreInfoUserResponse
                .builder()
                .username(user.getUsername())
                .uptatedAt(user.getUpdatedAt())
                .build();

    }

}
