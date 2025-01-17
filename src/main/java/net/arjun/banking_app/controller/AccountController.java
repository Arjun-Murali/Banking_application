package net.arjun.banking_app.controller;

import net.arjun.banking_app.entity.dto.AccountDto;
import net.arjun.banking_app.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController  {
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    //Get Account REST API
    @GetMapping("/{id}")
    public  ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
        AccountDto accountDto=accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }

    //Deposit REST API
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable  Long id, @RequestBody Map<String, Double> request){

        Double amount=request.get("amount");
        AccountDto accountDto=accountService.deposit(id,amount);

        return ResponseEntity.ok(accountDto);
    }

    //WIthdraw REST API
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable  Long id, @RequestBody Map<String, Double> request){

        Double amount=request.get("amount");
        AccountDto accountDto=accountService.withdraw(id,amount);

        return ResponseEntity.ok(accountDto);
    }

    //get all accounts REST API

    @GetMapping("/getAllAccounts")
    public ResponseEntity<List<AccountDto>> getAllAccounts(){

        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id)
    {
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account is deleted successfully");
    }




}









