package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WikiPage {
    protected WebDriver chromeDriver;
    private String findTable = "//*[@class='wikitable']";

    public WikiPage(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
    }

    public List<String> findElement(String keysFind) {
        List<WebElement> results = chromeDriver.findElements(By.xpath(findTable));
        WebElement table = results.stream().filter(x -> x.getText().contains(keysFind)).findAny().get();

        List<String> tableOfStrings = new ArrayList<>();
        List<WebElement> listTr = table.findElements(By.tagName("tr"));
        Iterator<WebElement> iterator = listTr.iterator();
        while (iterator.hasNext()) {
            tableOfStrings.add(iterator.next().getText());
        }
        return tableOfStrings;
    }

    public boolean isFirst(List<String> table, String initials) {
        for (int i = 0; i < table.size(); i++) {
            if (table.get(i).contains(initials) && table.indexOf(table.get(i)) == 1) {
                return true;
            }
        }
        return false;
    }

    public boolean isLast(List<String> table, String initials) {
        for (int i = 0; i < table.size(); i++) {
            if (table.get(i).contains(initials) && table.indexOf(table.get(i)) == table.size()-1) {
                return true;
            }
        }
        return false;
    }

}
