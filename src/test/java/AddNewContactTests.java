import config.AppiumConfig;
import enums.ContactField;
import helpers.*;
import models.Contact;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.AddNewContactScreen;
import screens.ContactListScreen;
import screens.SplashScreen;

public class AddNewContactTests extends AppiumConfig {
    @Test
    public void addNewContactPositive(){
        new SplashScreen(driver)
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

        ContactListScreen cls = new ContactListScreen(driver).openNewContactForm()
                .fillTheForm(contact)
                .submitContact();
        Assert.assertTrue(cls.isContactAdded(contact));
    }

    @Test
    public void addNewContactNegative() {
        new SplashScreen(driver)
                .switchToAuthenticationScreen()
                .fillEmailField("loginfb42@mail.com")
                .fillPasswordField("Ytpyfrjvrf0!")
                .clickLoginButton();

        Contact contact = ContactGenerator.createIncorrectContact(ContactField.PHONE_NUMBER, "1");
        AddNewContactScreen addNewContactScreen = new ContactListScreen(driver)
                .openNewContactForm()
                .fillTheForm(contact)
                .submitContact();
        Assert.assertTrue(addNewContactScreen.isThisTheAddNewContactScreen());
    }

    //// --------------------My Tests ----------------------
    @Test
    public void addNewContactPositiveMy() {

        AddNewContactMY.addNewContact(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                EmailGenerator.generateEmail(5, 3, 2),
                PhoneNumberGenerator.generatePhoneNumber(),
                AddressGenerator.generateAddress());

    }

    @Test
    public void addNewContactNegativeMyBlankName() {
        AddNewContactMY.addNewContact("",
                NameAndLastNameGenerator.generateLastName(),
                EmailGenerator.generateEmail(5, 3, 2),
                PhoneNumberGenerator.generatePhoneNumber(),
                AddressGenerator.generateAddress());

        Assert.assertTrue(driver.getPageSource().contains("Error"));
    }
}
