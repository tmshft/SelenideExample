package jp.shiftinc.automation;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

@SuppressWarnings("unused")
public class EdgeDriverProvider implements WebDriverProvider {

    @SuppressWarnings("NullableProblems")
    @Override
    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {
        WebDriverManager
                .edgedriver()
                .clearDriverCache()
                .driverVersion(Configuration.browserVersion)
                .setup();
        EdgeOptions options = new EdgeOptions();
        options.merge(desiredCapabilities);
        return new EdgeDriver(options);
    }
}
