package jp.shiftinc.automation;

import com.codeborne.selenide.*;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.logging.Logger;

import static com.codeborne.selenide.Selenide.*;

@SuppressWarnings({"unused", "WeakerAccess"})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WebDriverTest {
    private static final java.util.logging.Logger LOGGER = Logger.getLogger(WebDriverTest.class.getName());

    // 90.0.818.66
    // 91.0.864.37

    @BeforeEach
    void setup() {
        Configuration.baseUrl = "https://www.selenium.dev/downloads";
        Configuration.timeout = 10;
        //Configuration.driverManagerEnabled=true;
        Configuration.browser = "jp.shiftinc.automation.EdgeDriverProvider";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void tearDown() {
        if(WebDriverRunner.hasWebDriverStarted()) closeWebDriver();
        SelenideLogger.removeListener("AllureSelenide");
    }

    @Test
    public void edgeTest90() {
        Configuration.browserVersion = "90.0.818.66";
        test();
    }

    @Test
    public void edgeTest91() {
        Configuration.browserVersion = "91.0.864.37";
        test();
    }

    private void test (){
        open("/");
        $("a[href*=\".150.1.zip\"]").should(Condition.visible, Duration.ofSeconds(20));
        LOGGER.info(Selenide.getUserAgent());
        open("edge://version");
        LOGGER.info($("#version").text());
    }
}
