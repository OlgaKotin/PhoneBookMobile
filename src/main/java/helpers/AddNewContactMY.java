package helpers;

import models.Contact;
import screens.ContactListScreen;
import screens.SplashScreen;

import static config.AppiumConfig.driver;

public class AddNewContactMY {

    public static void addNewContact(String name, String lastName, String email,
                                     String phone, String address){
        new SplashScreen(driver)
                .switchToAuthenticationScreen()
                .fillEmailField("loginfb42@mail.com")
                .fillPasswordField("Ytpyfrjvrf0!")
                .clickLoginButton();

        Contact contact = new Contact();
        contact.setName(name);
        contact.setLastName(lastName);
        contact.setEmail(email);
        contact.setPhone(phone);
        contact.setAddress(address);
        contact.setDescription("desc");

        new ContactListScreen(driver).openNewContactForm()
                .fillTheForm(contact)
                .submitContact();
    }
}
