package app;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AppTests extends BaseTest {

  @Test
  public void firstTest() {
    String locator = "//android.widget.ScrollView/android.view.View";
    waitForElementPresent(locator,
            "Cannot find search input after clicking search init element",
            5);
  }

  public WebElement waitForElementPresent(String locator, String error_messanger, long timeoutInSecond)
  {
    WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
    wait.withMessage(error_messanger + "\n");
    return wait.until(
            ExpectedConditions.presenceOfElementLocated(By.xpath(locator))
    );
  }


}
