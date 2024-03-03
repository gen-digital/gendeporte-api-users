package cl.gendigital.gendeporte.users.api.endpoint;

import cl.gendigital.gendeporte.users.api.request.user.post.PostEnrichRequest;
import cl.gendigital.gendeporte.users.api.request.user.post.PostCreateUserRequest;
import cl.gendigital.gendeporte.users.api.request.user.post.PostVerificationRequest;
import cl.gendigital.gendeporte.users.api.responses.base.BaseResponse;
import cl.gendigital.gendeporte.users.api.responses.base.MessageResponse;
import cl.gendigital.gendeporte.users.api.responses.user.get.GetUserResponse;
import cl.gendigital.gendeporte.users.api.responses.user.post.PostEnrichResponse;
import cl.gendigital.gendeporte.users.api.responses.user.post.PostCreateUserResponse;
import cl.gendigital.gendeporte.users.api.responses.user.post.PostVerificationResponse;
import cl.gendigital.gendeporte.users.core.commands.user.CreateUserCmd;
import cl.gendigital.gendeporte.users.core.commands.user.GetUserCmd;
import cl.gendigital.gendeporte.users.core.commands.user.EnrichCmd;
import cl.gendigital.gendeporte.users.core.commands.user.VerificationCmd;
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
public class UserControllers {

    private final UserServicePort userService;
    @PostMapping()
    public ResponseEntity<BaseResponse> createUser(@RequestBody @Validated PostCreateUserRequest request){
        final Integer userId = userService.createUser(toCmd(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponseCreate(userId,"201","User created"));
    }

    @GetMapping("/by-username/{username}")
    public ResponseEntity<BaseResponse> getUser(@PathVariable String username){
        final User user = userService.getUser(new GetUserCmd(username));
        return ResponseEntity.status(HttpStatus.OK).body(toResponse(user,"200","User founded"));

    }

    @PatchMapping("/verification")
    public ResponseEntity<BaseResponse> verifyUser(@RequestBody PostVerificationRequest request){
        final User user = userService.verifyUser(toCmd(request));
        return ResponseEntity.status(HttpStatus.OK).body(toResponseVerify(user,"200","User verified"));
    }

    @PatchMapping("/enrich")
    public ResponseEntity<BaseResponse> enrich(@RequestBody PostEnrichRequest request){
        final User user = userService.enrich(toCmd(request));
        return ResponseEntity.status(HttpStatus.OK).body(toResponseEnrich(user,"200","User updated information"));
    }

    private CreateUserCmd toCmd(PostCreateUserRequest request) {
        return new CreateUserCmd(
                request.getUsername(), request.getPassword(), request.getEmail()
        );
    }

    private VerificationCmd toCmd(PostVerificationRequest request){
        return new VerificationCmd(
                request.getUsername(), request.getValidation_code()
        );
    }

    private EnrichCmd toCmd(PostEnrichRequest request){
        return new EnrichCmd(
                request.getUsername(), request.getFirst_name(),
                request.getLast_name(),request.getPhone(), request.getAddress()
        );
    }

    private BaseResponse toResponseCreate(Integer userId,String code, String message) {
        return BaseResponse.builder()
                .success(new MessageResponse(code, message))
                .data(new PostCreateUserResponse(userId))
                .build();
    }

    private BaseResponse toResponse(User user, String code, String message) {
        return BaseResponse.builder()
                .success(new MessageResponse(code, message))
                .data(toResponseGetUser(user))
                .build();
    }
    private GetUserResponse toResponseGetUser(User user) {
        return GetUserResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .validation_code(user.getValidationCode())
                .created_at(user.getCreatedAt())
                .enabled_at(user.getEnabledAt())
                .updated_at(user.getUpdatedAt())
                .address(user.getAddress())
                .build();
    }

    private BaseResponse toResponseVerify(User user,String code, String message){
        return BaseResponse.builder()
                .success(new MessageResponse(code, message))
                .data(toResponseVerification(user))
                .build();
    }
    private PostVerificationResponse toResponseVerification(User user){
        return PostVerificationResponse
                .builder()
                .username(user.getUsername())
                .enabled_at(user.getEnabledAt())
                .build();
    }

    private BaseResponse toResponseEnrich(User user,String code, String message){
        return BaseResponse.builder()
                .success(new MessageResponse(code, message))
                .data(toResponseEnrich(user))
                .build();
    }
    private PostEnrichResponse toResponseEnrich(User user){
        return PostEnrichResponse
                .builder()
                .username(user.getUsername())
                .uptated_at(user.getUpdatedAt())
                .build();

    }

}
