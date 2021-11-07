package guru.qa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.closeWindow;

public class TestBase {

    @BeforeAll
    public static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        Configuration.browserCapabilities = capabilities;

        Configuration.baseUrl = "https://demoqa.com";
        Configuration.timeout = 7500;
        Configuration.browserSize = "1960x1080";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
    }

    @AfterEach
    public void tearDown() {
        closeWindow();
    }
}
