package com.driver.ServiceLayer;

import com.driver.Group;
import com.driver.Message;
import com.driver.User;
import com.driver.WhatsappRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service

public class WhatsappService {
    WhatsappRepository whatsappRepository=new WhatsappRepository();
    public boolean createUser(String Name, String Mobile){
        if(whatsappRepository.checkifNoexits(Mobile)){
            return true;
        }
        User user=new User();
        user.setName(Name);
        user.setMobile(Mobile);
        return false;
    }
    public Group createGroup(List<User>users){
Group group=new Group();
group.setNumberOfParticipants(users.size());
if(group.getNumberOfParticipants()==2){
    group.setName(users.get(1).getName());
}
else {
  int count= whatsappRepository.numberofgroups()+1;
  group.setName("Group"+ Integer.toString(count));
  whatsappRepository.addgroup(group,users);
}
return group;
    }
    public int createMessage(String content){
       return whatsappRepository.getMessegenumer(content);
    }
public int sendMessage (Message message, User sender, Group group){
       return whatsappRepository.sendMessage(message,sender,group);
}
public int changeAdmin (User approver, User user, Group group){
      return whatsappRepository.ChangeAdmin(approver,user,group);
}
    public int removeUser(User user){
       return whatsappRepository.removeUser(user);
    }
    public String findMessage(Date start, Date end, int K){
        return whatsappRepository.findMessage(start,end,K);
    }
}
