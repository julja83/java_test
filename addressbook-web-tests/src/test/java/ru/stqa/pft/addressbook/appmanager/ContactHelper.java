package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;


import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class ContactHelper extends HelperBase {


  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void click(By locator) {
    wd.findElement(locator).click();
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getName());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomephone());
    type(By.name("mobile"), contactData.getMobilephone());
    type(By.name("email"), contactData.getEmail());

   if (creation) {
     new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
   }
    else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));}
  }
    public boolean isElementPresent(By locator){
       try {wd.findElement(locator);
            return true;}
       catch (NoSuchElementException ex){return false;}
    }


  public void gotoAddNewContact() {
    click(By.linkText("add new"));
  }

  public void selectContacts() {
    click(By.name("selected[]"));
  }

  public void deleteSelectedContacts() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void assertTrueDeletionContacts() {
       wd.switchTo().alert().accept();
  }

  public void submitContactModification() {
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void submitContactUpdate() {
    click(By.name("update"));
  }
}