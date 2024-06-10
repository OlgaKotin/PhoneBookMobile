import config.AppiumConfig;
import org.testng.Assert;
import org.testng.annotations.Test;
import screenactions.ScreenshotUtil;
import screens.ContactListScreen;
import screens.SplashScreen;

public class LoginTest extends AppiumConfig {

    @Test
    public void loginPositiveTest() {
        ContactListScreen contactListScreen = new SplashScreen(driver)
                .switchToAuthenticationScreen()
                .fillEmailField("loginfb42@mail.com")
                .fillPasswordField("Ytpyfrjvrf0!")
                .clickLoginButton();
        ScreenshotUtil screenshotUtil = new ScreenshotUtil(driver);
        screenshotUtil.takeScreenShot("TestMethod");

        Assert.assertTrue(contactListScreen.isContactListActivityPresent());
    }
}
