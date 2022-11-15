package driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

public class AndroidDriverObject {
  private static final String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";
  public static AppiumDriver getDriver() throws Exception
  {
    URL URL = new URL(APPIUM_URL);
    return new AndroidDriver(URL,getAndroidDesiredCapabilities());

  }
  public static DesiredCapabilities getAndroidDesiredCapabilities()
  {
    File app = downloadApk();
    DesiredCapabilities capabilities = new DesiredCapabilities();

    capabilities.setCapability("platformName","Android");
    capabilities.setCapability("deviceName","otus");
    capabilities.setCapability("platformVersion","5.1");
    capabilities.setCapability("automationName","Appium"); //"Appium" "UIAutomator2
    //capabilities.setCapability("appPackage","org.wikipedia");
    //capabilities.setCapability("appActivity",".main.MainActivity");
    capabilities.setCapability("app",app.getAbsolutePath());
    capabilities.setCapability("orientation","PORTRAIT"); //LANDSCAPE, PORTRAIT

    return capabilities;
  }

  private static File downloadApk() {
    File apk = new File("build/app-java-pro-v2.apk");
    if (!apk.exists()) {
      String url = "https://github.com/nmochalova/Otushome_5/blob/main/build/app-java-pro-v2.apk?raw=true";
      try (InputStream in = new URL(url).openStream()) {
        copyInputStreamToFile(in, apk);
      }
      catch (IOException e) {
        throw new AssertionError("Failed to download apk", e);
      }
    }
    return apk;
  }
}
