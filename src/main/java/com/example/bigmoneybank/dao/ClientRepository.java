package com.example.bigmoneybank.dao;

import com.example.bigmoneybank.entity.Client;
import org.springframework.data.repository.CrudRepository;


public interface ClientRepository extends CrudRepository<Client, Long> {

}
