package guru.qa.tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static guru.qa.pages.AutomationPracticeFormPage.*;

public class RegistrationFormWithPageObjectsTests extends TestBase {

    Faker faker = new Faker();
    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            userEmail = faker.internet().emailAddress(),
            genderValue,
            userNumber = faker.phoneNumber().subscriberNumber(10),
            fullDate,
            subjectLetter = faker.regexify("[a-eg-hl-pr-v]"),
            subjectName,
            hobbies,
            fileName = "1.jpg",
            currentAddress = faker.address().fullAddress(),
            StateAndCity;
    int genderNumber = faker.number().numberBetween(1, 3),
        stateNumber = faker.number().numberBetween(0,2),
        cityNumber = faker.number().numberBetween(0,1);
    boolean isFirstHobby = faker.bool().bool(),
            isSecondHobby = faker.bool().bool(),
            isThirdHobby = faker.bool().bool();
    Date birthDate = faker.date().birthday();

    @DisplayName("Form data should be displayed correctly in the table")
    @Test
    public void fillFormTest() {
        openPage();
        typeFirstName(firstName);
        typeLastName(lastName);
        typeUserEmail(userEmail);
        genderValue = selectGender(genderNumber);
        typeUserNumber(userNumber);
        fullDate = selectDate(birthDate);
        subjectName = inputSubject(subjectLetter);
        hobbies = selectHobbies(isFirstHobby, isSecondHobby, isThirdHobby);
        uploadPicture(fileName);
        typeCurrentAddress(currentAddress);
        StateAndCity = selectStateAndCity(stateNumber, cityNumber);
        clickSubmit();

        checkResultsValue("Student Name", firstName + " " + lastName);
        checkResultsValue("Student Email", userEmail);
        checkResultsValue("Gender", genderValue);
        checkResultsValue("Mobile", userNumber);
        checkResultsValue("Date of Birth", fullDate);
        checkResultsValue("Subjects", subjectName);
        checkResultsValue("Hobbies", hobbies);
        checkResultsValue("Picture", fileName);
        checkResultsValue("Address", currentAddress);
        checkResultsValue("State and City", StateAndCity);
    }
}
