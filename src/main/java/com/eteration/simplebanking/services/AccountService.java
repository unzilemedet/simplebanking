package com.eteration.simplebanking.services;


import com.eteration.simplebanking.dto.request.CreateAccountRequestDto;
import com.eteration.simplebanking.dto.response.FindAccountResponseDto;
import com.eteration.simplebanking.mapper.IMapper;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.repository.IAccountRepository;
import org.springframework.stereotype.Service;



// This class is a place holder you can change the complete implementation
@Service
public class AccountService {
    private final IAccountRepository accountRepository;

    public AccountService(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public FindAccountResponseDto findAccountInfo(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        return IMapper.INSTANCE.fromAccountToFindAccountResponsedto(account);

}
    public void saveAccount(Account account){
      accountRepository.save(account);
    }
    public Account createAccount(CreateAccountRequestDto dto) {
       return  accountRepository.save(IMapper.INSTANCE.fromCreateAccountRequestDtoToAccount(dto));


    }
    public Account findAccount(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);

    }

}
