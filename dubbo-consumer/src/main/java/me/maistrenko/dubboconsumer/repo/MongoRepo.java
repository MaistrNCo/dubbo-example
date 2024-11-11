package me.maistrenko.dubboconsumer.repo;

import me.maistrenko.dubbodao.entity.LogEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoRepo extends MongoRepository<LogEntry, String> {

}
