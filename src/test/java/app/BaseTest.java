package app;

import driver.AndroidDriverObject;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;

import java.net.URL;

public class BaseTest {
  protected AppiumDriver driver;

  @Before
  public void setUp() throws Exception {
     driver = AndroidDriverObject.getDriver();
  }

  @After
  public void tearDown()
  {
    driver.quit();
  }

}
