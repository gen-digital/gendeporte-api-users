package cl.gendigital.gendeporte.users.core.port.services;

import cl.gendigital.gendeporte.users.core.commands.user_info.UploadPersonalInfoCmd;
import cl.gendigital.gendeporte.users.core.commands.user_info.GetUserInfoCmd;
import cl.gendigital.gendeporte.users.core.entities.domain.user.UserInfo;

public interface UserInfoServicePort {
    UserInfo UploadPersonalInfo(String username, UploadPersonalInfoCmd cmd);
    UserInfo getUserInfo(GetUserInfoCmd cmd);
}
