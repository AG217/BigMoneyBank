package com.example.bigmoneybank.service;

import com.example.bigmoneybank.dao.ClientRepository;
import com.example.bigmoneybank.dao.OperationRepository;
import com.example.bigmoneybank.entity.Client;
import com.example.bigmoneybank.entity.Operation;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Data
public class ClientService {

    private final OperationRepository operationRepository;
    private final ClientRepository clientRepository;

    public List<Client> getClient() {
        return StreamSupport.stream(clientRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public Long getClientsCount() {
        return clientRepository.count();
    }

    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    public String putMoney(Long id, BigDecimal amount) {
        Client client = clientRepository.findById(id).orElseThrow();
        try {
            client.setBalance(client.getBalance().add(amount));
            clientRepository.save(client);
            Operation operation = new Operation();
            operation.setClientId(client);
            operation.setOperationType(2);
            operation.setOperationSum(amount);
            operation.setOperationDate(Date.valueOf(java.time.LocalDate.now()));
            operationRepository.save(operation);
            return String.valueOf(1);
        } catch (Throwable t) {
            return "Ошибка при выполнении операции";
        }
    }

    public String takeMoney(Long id, BigDecimal amount) {
        Client client = clientRepository.findById(id).orElseThrow();
        if (client.getBalance().compareTo(amount) == -1) {
            return "Недостаточно средств";
        }
        client.setBalance(client.getBalance().subtract(amount));
        clientRepository.save(client);
        Operation operation = new Operation();
        operation.setClientId(client);
        operation.setOperationType(1);
        operation.setOperationSum(amount);
        operation.setOperationDate(Date.valueOf(java.time.LocalDate.now()));
        operationRepository.save(operation);
        return String.valueOf(1);
    }

    public BigDecimal getBalance(Long id) {
        Client client = clientRepository.findById(id).orElseThrow();
        return client.getBalance();
    }

    public List<Operation> getOperationList(Long id, Date startDate, Date endDate) {
        Client client = clientRepository.findById(id).orElseThrow();
        return operationRepository.findByClientIdAndOperationDateBetween(client, startDate, endDate);
    }
}