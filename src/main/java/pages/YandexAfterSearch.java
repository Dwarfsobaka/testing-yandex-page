package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;


public class YandexAfterSearch {
    private List<WebElement> results;
    private WebDriver chromeDriver;
    private String selectLink ="//*[@href='https://ru.wikipedia.org/wiki/%D0%A2%D0%B0%D0%B1%D0%BB%D0%B8%D1%86%D0%B0']";
    WebDriverWait wait;

    public YandexAfterSearch(WebDriver chromeDriver){
        this.chromeDriver=chromeDriver;
        wait = new WebDriverWait(chromeDriver,10);
    }

    public List<WebElement> getResults() {
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='serp-list serp-list_left_yes']")));
        results = chromeDriver.findElements(By.xpath("//*[@class='serp-item desktop-card']//h2"));
        return results;
    }
    public WebElement findLinkOnPage(){
        WebElement webElement = chromeDriver.findElement(By.xpath(selectLink));
        return webElement;
    }
}