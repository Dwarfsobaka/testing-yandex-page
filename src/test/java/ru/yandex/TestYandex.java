package ru.yandex;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebElement;
import pages.WikiPage;
import pages.YandexAfterSearch;
import pages.YandexBeforeSearch;

import java.util.ArrayList;


public class TestYandex extends BaseTest{

    @Feature("Проверка результатов поиска")
    @DisplayName("Проверка результатов поиска слова Таблица")
    @ParameterizedTest(name = "{displayName} ")
    @CsvSource({"Таблица, Таблица — Википедия, Преподаватели кафедры программирования, Сергей Владимирович, Сергей Адамович"})
    public void testPO(String keyWord, String header, String tableName, String firstOrder, String lastOrder) {
        chromeDriver.get("https://yandex.ru/");
        YandexBeforeSearch yaBeforeSearch = new YandexBeforeSearch(chromeDriver);
        yaBeforeSearch.find(keyWord);
        YandexAfterSearch yaAfterSearch = new YandexAfterSearch(chromeDriver);

        Assertions.assertTrue(yaAfterSearch.getResults().stream().anyMatch(x->x.getText().contains(header)),
                "Заголовок содержит текст \"Таблица — Википедия\"");

        WebElement link = yaAfterSearch.findLinkOnPage();
        link.click();
        ArrayList<String> tabs = new ArrayList<>(chromeDriver.getWindowHandles());
        chromeDriver.switchTo().window(tabs.get(1));

        WikiPage wikiPage = new WikiPage(chromeDriver);

        Assertions.assertTrue(wikiPage.isLast(wikiPage.findElement(tableName), lastOrder)
             && wikiPage.isFirst(wikiPage.findElement(tableName), firstOrder),
                firstOrder + " идёт первым в списке, а " + lastOrder+  " последним");
    }
}
