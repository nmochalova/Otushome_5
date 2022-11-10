package app;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import extensions.AppiumExtension;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Coordinates;
import pages.MainPage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.partialText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@ExtendWith(AppiumExtension.class)
public class Posts_Test {
  private MainPage mainPage = new MainPage();

  @Test
  public void firstTest() {
    mainPage.open();

    $(By.className("android.widget.ScrollView")).shouldBe(Condition.visible);
    $("[content-desc *= 'Tab 2 of 2']").click();

    $("android.widget.ScrollView .android.view.View").should(Condition.visible);
//    String str = $("[content-desc *= 'post id: 1']").getAttribute("content-desc");
//    System.out.println(str);


    for (int i = 0; i < 10; i++) {
      Selenide.actions().scrollByAmount(0,100);
      ElementsCollection posts = $$("[content-desc *= 'post id:']");
      System.out.println(posts.size());
      System.out.println(posts.first().getAttribute("content-desc"));
    }


 //   $("[content-desc *= 'post id: 1']").click();
  }

}
