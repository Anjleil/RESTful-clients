package com.projects.learn.restful_clients.repository;

import com.projects.learn.restful_clients.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer>  {}