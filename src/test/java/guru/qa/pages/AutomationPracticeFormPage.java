package guru.qa.pages;

import com.codeborne.selenide.*;
import guru.qa.pages.components.CalendarComponent;

import java.util.Date;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AutomationPracticeFormPage {
    private static final String FORM_TITLE = "Student Registration Form",
                                TABLE_TITLE = "Thanks for submitting the form";
    private static String genderLocator = ("label[for=\"gender-radio-%d\"]"),
                          hobbyLocator = ("label[for=\"hobbies-checkbox-%d\"]"),
                          stateLocator = "#react-select-3-option-%d",
                          cityLocator = "#react-select-4-option-%d";
    private static SelenideElement
            formTitle = $(".practice-form-wrapper"),
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            userNumberInput = $("#userNumber"),
            subjectInput = $("#subjectsInput"),
            subjectTab = $("#react-select-2-option-0"),
            uploadFileInput = $("#uploadPicture"),
            currentAddressInput = $("#currentAddress"),
            stateInput = $("#state"),
            cityInput = $("#city"),
            submitButton = $("#submit"),
            tableHeader = $("#example-modal-sizes-title-lg");
    private static CalendarComponent calendar = new CalendarComponent();

    public static void openPage() {
        open("/automation-practice-form");
        formTitle.shouldHave(text(FORM_TITLE));
    }

    public static void typeFirstName(String value) {
        firstNameInput.setValue(value);
    }

    public static void typeLastName(String value) {
        lastNameInput.setValue(value);
    }

    public static void typeUserEmail(String value) {
        userEmailInput.setValue(value);
    }

    public static String selectGender(int option) {
        $(String.format(genderLocator, option)).click();

        return $(String.format(genderLocator, option)).text();
    }

    public static void typeUserNumber(String value) {
        userNumberInput.setValue(value);
    }

    public static String selectDate (Date birthDate) {
        String fullDate;
        calendar.setDate(String.format("%td", birthDate), String.format("%tB", birthDate), String.format("%tY", birthDate));
        fullDate = String.format("%td %<tB,%<tY", birthDate);

        return fullDate;
    }

    public static String inputSubject(String value) {
        String subject;
        subjectInput.setValue(value);
        subject = subjectTab.text();
        subjectTab.click();
        return subject;
    }

    public static String selectHobbies(boolean firstHobby, boolean secondHobby, boolean thirdHobby) {
        String hobbies = "";
        if (firstHobby) {
            $(String.format(hobbyLocator, 1)).click();
            hobbies += $(String.format(hobbyLocator, 1)).text() + ", ";
        }
        if (secondHobby) {
            $(String.format(hobbyLocator, 2)).click();
            hobbies += $(String.format(hobbyLocator, 2)).text() + ", ";
        }
        if (thirdHobby) {
            $(String.format(hobbyLocator, 3)).click();
            hobbies += " " +  $(String.format(hobbyLocator, 3)).text();
        }
        if (hobbies.endsWith(", ")) {
            hobbies = hobbies.substring(0, hobbies.length()-2);
        }
        return hobbies;
    }

    public static void uploadPicture(String fileName) {
        uploadFileInput.uploadFromClasspath("img/" + fileName);
    }

    public static void typeCurrentAddress(String value) {
        currentAddressInput.shouldBe(enabled).setValue(value);
    }

    public static String selectStateAndCity(int stateNumber, int cityNumber) {
        String StateAndCity;
        stateInput.click();
        StateAndCity = $(String.format(stateLocator, stateNumber)).text() + " ";
        $(String.format(stateLocator, stateNumber)).scrollIntoView(false).click();
        cityInput.click();
        StateAndCity += $(String.format(cityLocator, cityNumber)).text();
        $(String.format(cityLocator, cityNumber)).click();
        return StateAndCity;
    }

    public static void clickSubmit() {
        submitButton.click();
        tableHeader.shouldBe(visible).shouldHave(text(TABLE_TITLE));
    }

    public static void checkResultsValue(String key, String value) {
        if (value.equals("")) {
            $(".table-responsive").$(byText(key)).sibling(0).shouldBe(empty);
        } else {
            $(".table-responsive").$(byText(key)).sibling(0).shouldHave(exactText(value));
        }
    }

}
