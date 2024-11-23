package ssf.day15_ws.repositories;

import java.util.logging.Logger;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import ssf.day15_ws.models.Contact;

@Repository
public class ContactsRedis {

    @Autowired @Qualifier("redis-0")
    private RedisTemplate<String, Object> template;

    private final Logger logger = Logger.getLogger(ContactsRedis.class.getName());
    
     // keys *
     public Set<String> getIds() {
        logger.info("[Repo] Retriving ids (keys) from redis");
        return template.keys("*");
    }

    // hset contactid name contact.getName()
    public void insertContact(Contact contact) {
        logger.info("[Repo] Inserting contact into redis");
        HashOperations<String, String, Object> hashOps = template.opsForHash();
        Map<String, Object> values = new HashMap<>();
        values.put("name", contact.getName());
        values.put("email", contact.getEmail());
        values.put("phone", contact.getPhoneNum());

        hashOps.putAll(contact.getId(), values);
    }

    // hkeys contactid
    public Optional<Contact> getContactById(String id) {
        logger.info("[Repo] Retriving contact by id");
        HashOperations<String, String, Object> hashOps = template.opsForHash();
        Map<String, Object> contact = hashOps.entries(id);

        if(contact.isEmpty())
            return Optional.empty();

        Contact result = new Contact();
        result.setId(id);
        result.setName(contact.get("name").toString());
        result.setEmail(contact.get("email").toString());
        result.setPhoneNum(contact.get("phone").toString());

        return Optional.of(result);
    }
}
