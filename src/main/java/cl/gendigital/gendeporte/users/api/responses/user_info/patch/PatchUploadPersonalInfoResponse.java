package cl.gendigital.gendeporte.users.api.responses.user_info.patch;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class PatchUploadPersonalInfoResponse {
    private final String phone;
}
