package poo.project.Mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import poo.project.Dto.AppUserDTO;
import poo.project.Security.Entities.AppUser;

@Service
public class AccountMapperImp {

    public AppUser fromUserDTO(AppUserDTO userDTO) {
        AppUser appUser = new AppUser();
        BeanUtils.copyProperties(userDTO, appUser);
        return appUser;
    }

    public AppUserDTO fromUser(AppUser appUser) {
        AppUserDTO appUserDTO = new AppUserDTO();
        BeanUtils.copyProperties(appUser, appUserDTO);
        return appUserDTO;
    }

}
