package me.maistrenko.dubbodao.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "LogEntry")
public class LogEntry {
    @Id
    private ObjectId id;
    private String name;
    private String apiHost;
    private String rpcHost;
    private String timestamp;
}
