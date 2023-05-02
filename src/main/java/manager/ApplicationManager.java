package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  //  WebDriver wd;
EventFiringWebDriver wd;
    HelperUser user;
    Properties properties;
    String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

        Logger logger = LoggerFactory.getLogger(ApplicationManager.class);

    public void init() throws IOException {
        String target = System.getProperty("configuration", "config");
        logger.info("Config file is: "+ target);
     String fileName = String.format("src/test/resources/config.properties", target);
        logger.info("Config file full name: "+fileName);
        properties.load(new FileReader(new File(fileName)));

        if(browser.equals(BrowserType.CHROME)) {
            wd = new EventFiringWebDriver(new ChromeDriver());
            logger.info("Testing on Chrome Driver");
        }else if (browser.equals(BrowserType.FIREFOX)){
            wd = new EventFiringWebDriver(new FirefoxDriver());
           logger.info("Testing on Firefox");

        }
        wd.register(new MyListener());
        wd.manage().window().maximize();
      //  wd.navigate().to("http://trello.com");
        wd.navigate().to(properties.getProperty("web.baseURL"));
        logger.info("Base url is:"+properties.getProperty("web.baseURL"));
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        user = new HelperUser(wd);
    }

    public String getEmail(){
        return properties.getProperty("web.email");
 }
    public String getPassword(){
        return properties.getProperty("web.password");
    }
    public HelperUser getUser(){
        return user;
    }
    public void stop(){
        //wd.quit();
    }
}
