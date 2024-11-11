package me.maistrenko.dubbosvc;

import me.maistrenko.dubbointerface.DubboDemoService;
import me.maistrenko.dubbodao.entity.User;
import me.maistrenko.dubbosvc.repo.UserRepository;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;

@DubboService
public class DubboDemoServiceImpl implements DubboDemoService {
    @Autowired
    UserRepository userRepository;

    @Override
    public String sayHello(String name) {
        String hostname;
        try {
            hostname = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            hostname = "Unknown Host";
        }
        User userUpd = updateUsers(name);
        return "Hello " + name + ", current greetings count = " + userUpd.getCount() + ", from RPC: " + hostname;
    }

    private User updateUsers(String name) {
        User user = userRepository.findByName(name);
        if(user == null){
            user = new User();
            user.setName(name);
            user.setCount(1);
        }else{
            user.setCount(user.getCount()+1);
        }
        user.setLastUpdatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }
}
