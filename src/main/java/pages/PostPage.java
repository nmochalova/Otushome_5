package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import org.openqa.selenium.Point;

import static com.codeborne.selenide.Selenide.$;

public class PostPage extends MainPage{
  private  int postid1, postid2, y1, y2;

  public void clickToPostsPage() {
    $("[content-desc *= 'Tab 2 of 2']").click();
    this.check();
  }

  public SelenideElement findPost(String postid) {
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

  public void scrollUntilFoundPost(int postId, int postsPixels, int deltaPix, int millsPause) {
    boolean isFound = false;
    int counter = 0;

    while ((!isFound) && (counter < 2)) {
      counter++;
      try {
        System.out.println(postId);
        SelenideElement element = this.findPost(String.valueOf(postId));
        this.scrollPage(element, postsPixels, millsPause);
        isFound = true;
      } catch (ElementNotFound e) {
        try {
          System.out.println("-50 pxl => " + (postId+1));
          SelenideElement element = this.findPost(String.valueOf(postId+1));
          this.scrollPage(element, (-deltaPix), millsPause);
        } catch (ElementNotFound ex) {
          System.out.println("+50 pxl => " + (postId-1));
          SelenideElement element = this.findPost(String.valueOf(postId-1));
          this.scrollPage(element, deltaPix, millsPause);
        }
      }
    }

    if (!isFound)
      throw new RuntimeException("Scroll is finish");
  }

}
