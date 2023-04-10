package ru.academits.nekrasovgleb.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.academits.nekrasovgleb.model.Contact;
import ru.academits.nekrasovgleb.service.ContactService;

import java.util.List;
import java.util.Random;

@Component
public class DeleteRandomContactScheduler {
    private final ContactService contactService;

    public DeleteRandomContactScheduler(ContactService contactService) {
        this.contactService = contactService;
    }

    @Scheduled(fixedRate = 10000)
    public void deleteRandomContact() {
        List<Contact> contacts = contactService.getAllContacts();

        int contactsCount = contacts.size();

        if (contactsCount == 0) {
            return;
        }

        int contactIdToDelete;

        if (contactsCount == 1) {
            contactIdToDelete = contacts.get(0).getId();
        } else {
            final Random random = new Random();
            contactIdToDelete = contacts.get(random.nextInt(contactsCount - 1)).getId();
        }

        contactService.deleteContacts(new int[]{contactIdToDelete});
    }
}