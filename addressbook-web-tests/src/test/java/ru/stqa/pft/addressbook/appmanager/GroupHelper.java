package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class GroupHelper extends HelperBase {

  public GroupHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToGroupPage() {
    click(By.linkText("group page"));
  }

  public void submitGroupCreation() {
    click(By.name("submit"));
  }

  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getName());
    type(By.name("group_header"), groupData.getHeader());
    type(By.name("group_footer"), groupData.getFooter());
  }

  public void initGroupCreation() {
    click(By.name("new"));
  }

  public void deleteSelectedGroups( ) {

    click(By.name("delete"));
  }

  public void selectGroup(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void initGroupModification() {
    click(By.name("edit"));
  }

  public void submitGroupModification() {
    click(By.name("update"));
  }

  public void create(GroupData group) {
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    returnToGroupPage();
  }
  public void modify(int index, GroupData group) {
   selectGroup(index);
   initGroupModification();
   fillGroupForm(group);
   submitGroupModification();
   returnToGroupPage();
  }
  public void delete(int index) {
    selectGroup(index);
    deleteSelectedGroups();
    returnToGroupPage();
  }

  // проверяется наличие элемента, который хотим выбрать в selectGroup
  public boolean isThereAGroup() {
  return isElementPresent(By.name("selected[]"));

  }

  public int getGroupCount() {
     return wd.findElements(By.name("selected[]")).size();
     }

  public List<GroupData> list() {
    //создаем список, который будем заполнять
   List<GroupData> groups=new ArrayList<GroupData>();//указываем конкретный класс, кот реализует интерфейс list
    //получаем список объектоа типа WebElement
    //найти все элементы с тегом span и классoм group
   List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));//это эл-т, внутри кот чек-бокс
   //element пробегает по спискус elements и из каждого элемента получаем текст имя группы
   for (WebElement element : elements){
     String name=element.getText();
     int  id=Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));//идентификатор, кот передаем в конструктор
     //создаем объект типа GroupData
     GroupData group = new GroupData(id, name,null,null);
     //добавляем созданный объект в список
     groups.add(group);
   }
   return groups;// возвращается список groups
  }
}
