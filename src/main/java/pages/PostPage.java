package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class PostPage extends MainPage{

  public void clickToPostsPage() {
    $("[content-desc *= 'Tab 2 of 2']").click();
    this.check();
  }

  public SelenideElement findUser(String postid) {
    String locator = String.format("[content-desc *= 'post id: %s']",postid);
    SelenideElement element = $(locator).should(Condition.visible);
    return element;
  }

  public void clickPost(SelenideElement postElement) {
    postElement.click();
  }

  public void checkPost(String title) {
    String locatorName = String.format("[content-desc ^= '%s']",title);
    $(locatorName).shouldBe(Condition.visible);
  }

  public String getPostInfo(String postid) {
    String locatorUsername = String.format("[content-desc *= 'user id: %s']",postid);
    SelenideElement el_2 = $(locatorUsername).should(Condition.visible);
    return el_2.getAttribute("content-desc");
  }
}
