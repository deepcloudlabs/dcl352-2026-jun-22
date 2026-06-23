package com.example.cm.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.cm.document.CustomerDocument;

public interface CustomerDocumentRepository extends MongoRepository<CustomerDocument,String>{

}
