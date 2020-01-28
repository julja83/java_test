package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactsCreationTests extends TestBase {

  @Test
  public void testContactsCreation() throws Exception {
    //проверить, что сущ группа test1, если нет,то создать её
    app.checkAndCreateGroup(new GroupData("test1", null, null));
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().createContact(new ContactData("Саша", "Иванов",
            "г. Москва ул.Строителей д.7 кв 9", "1111111111111", "22222222222",
            "1111@11.com","test1" ),true);
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after,before+1);
        }

}
