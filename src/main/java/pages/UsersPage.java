package pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class UsersPage extends BasePage<UsersPage>{
  public void checkLoadPage(){
    $(By.className("android.widget.ScrollView")).shouldBe(Condition.visible);
  }
}
