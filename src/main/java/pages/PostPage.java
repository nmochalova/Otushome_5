package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;

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
    String locatorUsername = String.format("[content-desc *= 'post id: %s']",postid);
    SelenideElement el_2 = $(locatorUsername).should(Condition.visible);
    return el_2.getAttribute("content-desc");
  }

  public void scrollUntilFoundPost(int postId, int postsPixels, int deltaPix, int millsPause) {
    boolean isFound = false; //признак того, что пост не найден
    int counter = 0;        //счетчик, по которому отслеживаем конец экрана
    String str = deltaPix+" pxl => ";

    while ((!isFound) && (counter < 2)) {
      counter++;
      try {
        scroll("",postId,postsPixels,millsPause); //ищем на экарне пост по id и скролим его
        isFound = true; //если нашли, то переходим к следующему посту
      } catch (ElementNotFound e) { //если не нашли
        try {
          scroll("-"+str,postId+1, (-deltaPix), millsPause); //то ищем пост id+1 подскролирваем его вниз
        } catch (ElementNotFound ex) { //если и его не нашли
          scroll("+"+str,postId-1, deltaPix, millsPause); //то ищем пост с id-1 и подскролироваем его вверх
        }
      }
    }
    if (!isFound) //если дважды подскролили один и тот же пост, то считаем, что это конец экрана
      throw new RuntimeException("Scroll is finish");
  }

  private void scroll(String text, int postId, int pixelToY, int millsPause) {
    System.out.println(text+postId);
    SelenideElement element = this.findPost(String.valueOf(postId));
    this.scrollPage(element, pixelToY, millsPause);
  }

}
