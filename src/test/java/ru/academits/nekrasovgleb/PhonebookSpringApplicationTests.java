package ru.academits.nekrasovgleb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.academits.nekrasovgleb.dao.ContactDao;
import ru.academits.nekrasovgleb.model.Contact;
import ru.academits.nekrasovgleb.phonebook.PhoneBookController;
import ru.academits.nekrasovgleb.service.ContactService;

import java.util.List;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PhonebookSpringApplicationTests {
	@Test
	public void contextLoads() {
	}

	@Test
	public void phonebookControllerTest() {
		Contact contactTest1 = new Contact();
		Contact contactTest2 = new Contact();
		Contact contactTest3 = new Contact();

		contactTest1.setFirstName("Иван");
		contactTest1.setLastName("Иванов");
		contactTest1.setPhone("9230001111");
		contactTest1.setImportant(false);

		contactTest2.setFirstName("Павел");
		contactTest2.setLastName("Павлов");
		contactTest2.setPhone("9230002222");
		contactTest2.setImportant(false);

		contactTest3.setFirstName("Георгий");
		contactTest3.setLastName("Георгиев");
		contactTest3.setPhone("9230003333");
		contactTest3.setImportant(false);
		
		ContactDao contactDaoTest = new ContactDao();
		contactDaoTest.add(contactTest1);

		ContactService contactServiceTest = new ContactService(contactDaoTest);

		PhoneBookController phoneBookControllerTest = new PhoneBookController(contactServiceTest);

		List<Contact> loadedContactList1 = phoneBookControllerTest.getAllContacts();

		assertEquals(contactTest1, loadedContactList1.get(1));

		phoneBookControllerTest.addContact(contactTest2);
		phoneBookControllerTest.addContact(contactTest3);

		List<Contact> loadedContactList2 = phoneBookControllerTest.getAllContacts();

		assertEquals(4, loadedContactList2.size());
		assertEquals(contactTest2, loadedContactList2.get(2));
		assertEquals(contactTest3, loadedContactList2.get(3));

		phoneBookControllerTest.deleteContacts(new int[] {contactTest2.getId(), contactTest3.getId()});

		List<Contact> loadedContactList3 = phoneBookControllerTest.getAllContacts();

		assertEquals(2, loadedContactList3.size());
		assertEquals(contactTest1, loadedContactList3.get(1));
	}
}