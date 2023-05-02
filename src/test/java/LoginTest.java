//import manager.DataProviderUser;
import manager.DataProviderUser;
import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {
   //@Test/*(groups={"first"})*/(dataProvider = "loginData", dataProviderClass = DataProviderUser.class)
   @Test(groups = {"first"})
 // public void loginPositiveTest(User user){
   public void loginPositiveTest() {
        logger.info("Login Positive Test started");
       // User user = User.builder().email("juliyabee@gmail.com").password("Pass@word1").build();
       User  user = User.builder().email(app.getEmail()).password(app.getPassword()).build();
        app.getUser().initLogin();
        app.getUser().fillLogin(user);
        app.getUser().submitLogin();
       app.getUser().pause(2000);
        app.getUser().fillPassword(user);
        app.getUser().submitPassword();
       app.getUser().pause(2000);
        Assert.assertTrue(app.getUser().isElementPresent(By.className("OUdAuicP657Tka")));
      //  app.getUser().returnToHome();
       app.getUser().logout();
       app.getUser().pause(2000);
       logger.info("Login Positive Test finished");
    }
    @Test(dataProvider = "wrongEmailData", dataProviderClass = DataProviderUser.class)

   public void loginNegativeWrongEmailTest(User user){

        logger.info("Login NegativeWrongEmail Test started");
        //User user = User.builder().email("juliyabeegmail.com").password("Pass@word1").build();
        app.getUser().initLogin();
        app.getUser().fillLogin(user);
        app.getUser().submitLogin();
        app.getUser().pause(2000);
        app.getUser().fillPassword(user);
        app.getUser().submitLogin();
        app.getUser().pause(2000);
      //  Assert.assertTrue(app.getUser().getTextFromErrorEmailMessage().contains("There isn't an account for this username"));
        Assert.assertFalse(app.getUser().isLogged());
        //<p class="error-message">There isn't an account for this username</p>
        app.getUser().returnToHome();
        logger.info("Login NegativeWrongEmail Test finished");
    }
    @Test(dataProvider = "wrongPasswordData", dataProviderClass = DataProviderUser.class)
    public void loginNegativeWrongPasswordTest(User user){
        logger.info("Login NegativeWrongPassword Test started");
       // User user = User.builder().email("juliyabee@gmail.com").password("Password1").build();
        app.getUser().initLogin();
        app.getUser().fillLogin(user);
        app.getUser().submitLogin();
        app.getUser().pause(2000);
        app.getUser().fillPassword(user);
        app.getUser().submitPassword();
        app.getUser().pause(2000);
        Assert.assertFalse(app.getUser().isLogged());
        Assert.assertTrue(app.getUser().getTextFromErrorPasswordMessage().contains("Incorrect email address and / or password.") );
        app.getUser().returnToHome();
        logger.info("Login NegativeWrongPassword Test finished");
    }
}
