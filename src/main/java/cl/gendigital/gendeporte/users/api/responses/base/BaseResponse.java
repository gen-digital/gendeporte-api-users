package cl.gendigital.gendeporte.users.api.responses.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse {
    @Builder.Default
    private final LocalDateTime date = LocalDateTime.now();
    private MessageResponse success;
    private MessageResponse failure;
    private Object data;
    @Setter
    private MessageResponse debug;
    @Setter
    private String throwable;
}
