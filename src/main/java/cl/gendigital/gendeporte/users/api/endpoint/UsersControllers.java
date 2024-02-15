package cl.gendigital.gendeporte.users.api.endpoint;

import cl.gendigital.gendeporte.users.api.request.user.PostNewUserRequest;
import cl.gendigital.gendeporte.users.core.commands.CreateUserCmd;
import cl.gendigital.gendeporte.users.core.commands.GetUserCmd;
import cl.gendigital.gendeporte.users.core.entities.domain.user.User;
import cl.gendigital.gendeporte.users.core.port.services.UserServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/User")
@RestController
@RequiredArgsConstructor
public class UsersControllers {

    private final UserServicePort userService;
    @PostMapping
    public Integer createUser(@RequestBody @Validated PostNewUserRequest request){
        Integer userId = userService.createUser(toCmd(request));
        return userId;
    }

    @GetMapping("/searchUser")
    public String getUser(@RequestParam String username){
        User user = userService.getUser(new GetUserCmd(username));
        String email = user.getEmail();
        String userName = user.getEmail();
        return email + userName;

    }

    private CreateUserCmd toCmd(PostNewUserRequest request) {
        return new CreateUserCmd(
                request.getUsername(), request.getPassword(), request.getEmail());
    }
}
