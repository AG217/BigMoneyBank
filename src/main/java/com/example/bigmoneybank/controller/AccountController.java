package com.example.bigmoneybank.controller;

import com.example.bigmoneybank.entity.Client;
import com.example.bigmoneybank.service.ClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;


@RestController
public class AccountController {

    private final ClientService clientService;

    public AccountController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("getClient")
    public List<Client> getClients() {
        return clientService.getClient();
    }

    @GetMapping("getClientCount")
    public Long getClientCount() {
        return clientService.getClientsCount();
    }

    @GetMapping("getClientById")
    public Client getClientById(@RequestParam Long id) {
        return clientService.getClientById(id).orElseThrow();
    }

    @GetMapping("takeMoney")
    public String takeMoney(@RequestParam Long id, @RequestParam BigDecimal amount) {
        return clientService.takeMoney(id, amount);
    }

    @GetMapping("getBalance")
    public BigDecimal getBalance(@RequestParam Long id) {
        return clientService.getBalance(id);
    }

    @GetMapping("putMoney")
    public String putMoney(@RequestParam Long id, @RequestParam BigDecimal amount) {
        return clientService.putMoney(id, amount);
    }
}