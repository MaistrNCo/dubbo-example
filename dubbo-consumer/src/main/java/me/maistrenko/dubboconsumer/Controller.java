package me.maistrenko.dubboconsumer;

import me.maistrenko.dubboconsumer.repo.MongoRepo;
import me.maistrenko.dubbodao.entity.LogEntry;
import me.maistrenko.dubbointerface.DubboDemoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class Controller {
    @DubboReference
    DubboDemoService dubboDemoService;
    @Autowired
    MongoRepo mongoRepo;
    @GetMapping
    public String hello(@RequestParam String name) {
        String respponse = dubboDemoService.sayHello(name);
        String hostname;
        try {
            hostname = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            hostname = "Unknown Host";
        }
        LogEntry logEntry = createLogEntry(name, hostname, respponse);
        mongoRepo.save(logEntry);
        return respponse + ", API host: " + hostname;
    }

    private LogEntry createLogEntry(String name, String hostname, String response) {
        LogEntry logEntry = new LogEntry();
        logEntry.setName(name);
        logEntry.setApiHost(hostname);
        logEntry.setRpcHost(response.substring(response.lastIndexOf(":") + 2));
        logEntry.setTimestamp(getTimestamp());
        return logEntry;
    }

    private String getTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }
}
