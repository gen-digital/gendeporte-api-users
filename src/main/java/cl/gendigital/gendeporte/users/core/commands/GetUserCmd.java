package cl.gendigital.gendeporte.users.core.commands;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class GetUserCmd {
    private final String username;
}
