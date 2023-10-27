package com.eteration.simplebanking.mapper;

import com.eteration.simplebanking.dto.request.CreateAccountRequestDto;
import com.eteration.simplebanking.dto.response.FindAccountResponseDto;
import com.eteration.simplebanking.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IMapper {
    IMapper INSTANCE = Mappers.getMapper(IMapper.class);


    Account fromCreateAccountRequestDtoToAccount(final CreateAccountRequestDto dto);
    FindAccountResponseDto fromAccountToFindAccountResponsedto(final Account account);
}
