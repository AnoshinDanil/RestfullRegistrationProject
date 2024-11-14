package com.anoshindanil.restfullregistrationproject.mappers;

import com.anoshindanil.restfullregistrationproject.model.dto.UserRegistrationDto;
import com.anoshindanil.restfullregistrationproject.model.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser (UserRegistrationDto userRegistrationDto);
    UserRegistrationDto toUserRegistrationDto (User user);
}
