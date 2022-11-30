package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Arrays;

import static com.codeborne.selenide.Selenide.$;
import static java.time.Duration.ofMillis;

public class MainPage {
  public MainPage open() {
    Selenide.closeWebDriver();
    Selenide.open();
    check();

    return this;
  }

  public void check(){
    $(By.xpath("//android.widget.ScrollView/android.view.View")).shouldBe(Condition.visible);
  }

  public void scrollPage(SelenideElement element, int countPixel, int millisecondsPause) {
    Point source = element.getLocation();
    System.out.println("x = " + source.x + ", y = " + source.y);

    PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
    Sequence sequence = new Sequence(finger, 1);

    //Движение указателя к позиции элемента
    sequence.addAction(finger.createPointerMove(ofMillis(0),
            PointerInput.Origin.viewport(), source.x, source.y)); //x1,y1
    //Движение указателя вниз.
    sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
    //Удержание указателя на некоторое время (в миллисекундах).
    sequence.addAction(new Pause(finger, ofMillis(millisecondsPause)));
    //Движение указателя со слайдером к конечной локации.
    sequence.addAction(finger.createPointerMove(ofMillis(millisecondsPause),
            PointerInput.Origin.viewport(), source.x, source.y - countPixel)); //x1,-y2
    //“Отрыв” указателя от слайдера.
    sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

    RemoteWebDriver driver = (RemoteWebDriver) Selenide.webdriver().object();
    driver.perform(Arrays.asList(sequence));
  }
}
