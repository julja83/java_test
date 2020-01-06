package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
//import sun.plugin2.util.BrowserType;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  public WebDriver wd ;
  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private  ContactHelper contactHelper;
  private GroupHelper groupHelper;
  private String browser;

  public ApplicationManager(String browser) {
    this.browser = browser;
  }

  public void init() {
    //String browser= BrowserType.FIREFOX;
    if(browser.equals(BrowserType.FIREFOX))
    { wd = new FirefoxDriver();
    }
    else if (browser.equals(BrowserType.CHROME)){
      wd = new ChromeDriver();
    }
    else if (browser.equals(BrowserType.IEXPLORE)){
      wd=new InternetExplorerDriver();
    }

  wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
  wd.get("http://localhost/addressbook/");
    groupHelper = new GroupHelper(wd);
    contactHelper=new ContactHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper=new SessionHelper(wd);
    sessionHelper.login("admin", "secret");
  }
  public void logout() {
   wd.findElement(By.linkText("Logout")).click();
  }

  public void stop() {
    logout();
    wd.quit();
  }
  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public ContactHelper getContactHelper() {
    return contactHelper;
  }
  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }
}
