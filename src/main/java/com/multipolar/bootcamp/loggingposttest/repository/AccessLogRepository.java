package com.multipolar.bootcamp.loggingposttest.repository;

import com.multipolar.bootcamp.loggingposttest.domain.AccessLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccessLogRepository extends MongoRepository<AccessLog, String> { }
