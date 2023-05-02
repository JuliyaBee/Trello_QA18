package manager;

import com.google.common.io.Files;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;

public class  HelperBase {
    WebDriver wd;
    public HelperBase(){}
    public HelperBase(WebDriver wd){
        this.wd=wd;
    }
    public boolean isElementPresent(By locator){
        return(wd.findElements(locator).size()>0);
    }
    public void click(By locator){
        wd.findElement(locator).click();
    }
    public void type(By locator, String text) {
        if (text != null &&!text.isEmpty()) {
            WebElement element = wd.findElement(locator);
            element.click();
            element.clear();
            element.sendKeys(text);
        }
    }
    public void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public  void  takeScreenShot(String link) {
       File tmp = ((TakesScreenshot)wd).getScreenshotAs(OutputType.FILE);
        File screenshot = new File(link);
        try {
            Files. copy(tmp, screenshot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getTextByLocator(By locator){

        return wd.findElement(locator).getText();
    }
    public boolean isHomePage(){
        String  current_url = wd.getCurrentUrl();


        System.out.println(current_url);
        return current_url.equals("https://trello.com/")||current_url.equals("https://trello.com/home");
    }
    public void returnToHome(){
        wd.navigate().to("https://trello.com");
    }


    }



