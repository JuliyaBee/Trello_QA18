import manager.ApplicationManager;
import manager.NGListener;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import manager.NGListener;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.io.IOException;


@Listeners(NGListener.class)

public class TestBase {
  public static ApplicationManager app=new ApplicationManager
           ( System.getProperty("browser", BrowserType.CHROME));//default CHROME
    Logger logger = LoggerFactory.getLogger(TestBase.class);
    @BeforeSuite
    public void setUp() throws IOException {
        app.init();
    }
    @AfterSuite
    public void setDown(){
        app.stop();
    }


}
