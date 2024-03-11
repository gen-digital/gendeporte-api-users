package cl.gendigital.gendeporte.users.core.port.services;

import cl.gendigital.gendeporte.users.core.commands.user_info.EnrichCmd;
import cl.gendigital.gendeporte.users.core.commands.user_info.GetUserInfoCmd;
import cl.gendigital.gendeporte.users.core.entities.domain.user.UserInfo;

public interface UserInfoServicePort {
    UserInfo enrich(String username,EnrichCmd cmd);
    UserInfo getUserInfo(GetUserInfoCmd cmd);
}
