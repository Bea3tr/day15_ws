package ssf.day15_ws.services;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssf.day15_ws.models.Contact;
import ssf.day15_ws.repositories.ContactsRedis;

@Service
public class ContactService {

    @Autowired 
    private ContactsRedis contactRedis;

    public Set<String> getIds() {
        return contactRedis.getIds();
    }

    public String insertContact(Contact contact) {
        String id = UUID.randomUUID().toString().substring(0, 8);
        contact.setId(id);
        contactRedis.insertContact(contact);
        return id;
    }

    public Optional<Contact> getContactById(String id) {
        return contactRedis.getContactById(id);
    }
    
}
