package com.projects.learn.restful_clients.repository;

import com.projects.learn.restful_clients.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>  {}