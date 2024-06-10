import config.AppiumConfig;
import helpers.*;
import models.Contact;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.ContactListScreen;
import screens.SplashScreen;

public class RemoveContactTest extends AppiumConfig {
    @Test
    public void removeContactPositive() {
        ContactListScreen contactListScreen = new SplashScreen(driver)
                .switchToAuthenticationScreen()
                .fillEmailField(EmailGenerator.generateEmail(5,3,2))
                .fillPasswordField(PasswordStringGenerator.generateRandomPassword())
                .clickByRegistrationButton();

        Contact contact = new Contact();
        String firstName = NameAndLastNameGenerator.generateName();
        String lastName = NameAndLastNameGenerator.generateLastName();
        String phone = PhoneNumberGenerator.generatePhoneNumber();
        contact.setName(firstName);
        contact.setLastName(lastName);
        contact.setEmail(EmailGenerator.generateEmail(5,3,2));
        contact.setPhone(phone);
        contact.setAddress(AddressGenerator.generateAddress());
        contact.setDescription("test");

        contactListScreen.openNewContactForm().fillTheForm(contact).submitContact();

        contactListScreen.removeAContact();

        Assert.assertFalse(contactListScreen.isContactPresent(firstName, lastName, phone));
    }

    /*@Test
    public void removeContactPositiveBase() {
        ContactListScreen contactListScreen = new SplashScreen(driver)
                .switchToAuthenticationScreen()
                .fillEmailField("loginfb42@mail.com")
                .fillPasswordField("Ytpyfrjvrf0!")
                .clickLoginButton();

        Contact contact = new Contact();
        contact.setName(NameAndLastNameGenerator.generateName());
        contact.setLastName(NameAndLastNameGenerator.generateLastName());
        contact.setEmail(EmailGenerator.generateEmail(5,3,2));
        contact.setPhone(PhoneNumberGenerator.generatePhoneNumber());
        contact.setAddress(AddressGenerator.generateAddress());
        contact.setDescription("test");

        contactListScreen.openNewContactForm().fillTheForm(contact).submitContact();

        contactListScreen.removeAContact();
    }*/
}
