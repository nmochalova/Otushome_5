package extensions;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import providers.SelenideWebDriver;
import stubs.PingStub;

//import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;

public class AppiumExtension implements BeforeAllCallback {


  @Override
  public void beforeAll(ExtensionContext extensionContext) {
    Configuration.screenshots = false;
    Configuration.browserSize = null;
    Configuration.browser = SelenideWebDriver.class.getName();

//    configureFor(9190);
  }
}
