package cl.gendigital.gendeporte.users.api.endpoint;


import cl.gendigital.gendeporte.users.api.request.user.post.PostCreateUserRequest;
import cl.gendigital.gendeporte.users.api.request.user.patch.PatchVerificationRequest;
import cl.gendigital.gendeporte.users.api.request.user_info.patch.PatchEnrichRequest;
import cl.gendigital.gendeporte.users.api.responses.base.BaseResponse;
import cl.gendigital.gendeporte.users.api.responses.base.MessageResponse;
import cl.gendigital.gendeporte.users.api.responses.user.get.GetUserResponse;
import cl.gendigital.gendeporte.users.api.responses.user.post.PostCreateUserResponse;
import cl.gendigital.gendeporte.users.api.responses.user.patch.PatchVerificationResponse;
import cl.gendigital.gendeporte.users.api.responses.user_info.patch.PatchEnrichResponse;
import cl.gendigital.gendeporte.users.core.commands.user.CreateUserCmd;
import cl.gendigital.gendeporte.users.core.commands.user.GetUserCmd;
import cl.gendigital.gendeporte.users.core.commands.user.VerificationCmd;

import cl.gendigital.gendeporte.users.core.commands.user_info.EnrichCmd;
import cl.gendigital.gendeporte.users.core.entities.domain.user.User;
import cl.gendigital.gendeporte.users.core.entities.domain.user.UserInfo;
import cl.gendigital.gendeporte.users.core.port.services.UserInfoServicePort;
import cl.gendigital.gendeporte.users.core.port.services.UserServicePort;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserServicePort userService;
    private final UserInfoServicePort userInfoService;

    @PostMapping
    public ResponseEntity<BaseResponse> createUser(@RequestBody @Validated PostCreateUserRequest request){
        final Integer userId = userService.createUser(toCmd(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponseCreate(userId,"201","User created"));

    }

    @GetMapping("/by-username/{username}")
    public ResponseEntity<BaseResponse> getUser(@PathVariable String username){
        final User user = userService.getUser(new GetUserCmd(username));
        return ResponseEntity.status(HttpStatus.OK).body(toResponseGetUser(user,"200","User founded"));

    }

    @PatchMapping("/verification")
    public ResponseEntity<BaseResponse> verifyUser(@RequestBody PatchVerificationRequest request){
        final User user = userService.verifyUser(toCmd(request));
        return ResponseEntity.status(HttpStatus.OK).body(toResponseVerify(user,"200","User verified"));
    }

    @PatchMapping("/by-username/{username}/enrich")
    public ResponseEntity<BaseResponse> enrichUser (@PathVariable String username,@RequestBody PatchEnrichRequest request){
        final UserInfo userInfo = userInfoService.enrich(username,toCmd(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponseEnrich(userInfo,"200","User info accepted"));
    }

    private CreateUserCmd toCmd(PostCreateUserRequest request) {
        return new CreateUserCmd(
                request.getUsername(), request.getPassword(), request.getEmail()
        );
    }

    private VerificationCmd toCmd(PatchVerificationRequest request){
        return new VerificationCmd(
                request.getUsername(), request.getValidationCode()
        );
    }
    private EnrichCmd toCmd(PatchEnrichRequest request){
        return new EnrichCmd(
                request.getFirstName(), request.getMiddleName(), request.getLastName(), request.getSecondLastName(), request.getBirthdate(),
                request.getRut(),request.getNationality(),request.getPhone(),request.getAddress(),request.getMaritalStatus()
        );
    }


    private BaseResponse toResponseCreate(Integer userId,String code, String message) {
        return BaseResponse.builder()
                .success(new MessageResponse(code, message))
                .data(new PostCreateUserResponse(userId))
                .build();

    }

    private BaseResponse toResponseGetUser(User user, String code, String message) {
        return BaseResponse.builder()
                .success(new MessageResponse(code, message))
                .data(toResponseGetUser(user))
                .build();
    }
    private GetUserResponse toResponseGetUser(User user) {
        return GetUserResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .validationCode(user.getValidationCode())
                .createdAt(user.getCreatedAt())
                .enabledAt(user.getEnabledAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    private BaseResponse toResponseVerify(User user,String code, String message){
        return BaseResponse.builder()
                .success(new MessageResponse(code, message))
                .data(toResponseVerification(user))
                .build();
    }
    private PatchVerificationResponse toResponseVerification(User user){
        return PatchVerificationResponse
                .builder()
                .username(user.getUsername())
                .enabledAt(user.getEnabledAt())
                .build();
    }

    private BaseResponse toResponseEnrich(UserInfo userInfo,String code,String message){
        return BaseResponse.builder()
                .success(new MessageResponse(code, message))
                .data(toResponseEnrich(userInfo))
                .build();
    }

    private PatchEnrichResponse toResponseEnrich(UserInfo userInfo){
        return PatchEnrichResponse
                .builder()
                .phone(userInfo.getPhone())
                .build();
    }



}
