import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class VictoriaLemaTest {
    public String url = "https://openweathermap.org/";

    @Test
    //    TC_1_1  - Тест кейс:
    //    //1. Открыть страницу https://openweathermap.org/
    //    //2. Набрать в строке поиска город Paris
    //    //3. Нажать пункт меню Search
    //    //4. Из выпадающего списка выбрать Paris, FR
    //    //5. Подтвердить, что заголовок изменился на "Paris, FR"
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        driver.get(url);
        Thread.sleep(6000);
        WebElement searchCityField = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder='Search city']")
        );

        searchCityField.click();
        searchCityField.sendKeys(cityName);
        WebElement searchButton = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//button[@type='submit']")
        );
        searchButton.click();
        Thread.sleep(5000);
        WebElement parisFRChoiceDropDownMenu = driver.findElement(
                By.xpath("//ul[@class='search-dropdown-menu']/li/span[text()='Paris, FR ']")
        );
        parisFRChoiceDropDownMenu.click();
        WebElement h2CityCountryHeader = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );
        Thread.sleep(2000);
        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);
        driver.quit();

    }

    @Test
//    TC_11_01
//1.  Открыть базовую ссылку
//2.  Нажать на пункт меню Guide
//3.  Подтвердить, что вы перешли на страницу со ссылкой https://openweathermap.org/guide и
// что title этой страницы OpenWeatherMap API guide - OpenWeatherMap
    public void testGuideTitleAndGuideEndPoint_WhenGoingToPageTitle() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String expectedResultEndPoint = "https://openweathermap.org/guide";
        String expectedResultPageTitle = "OpenWeatherMap API guide - OpenWeatherMap";

        driver.get(url);
        Thread.sleep(4000);
        WebElement guideButton = driver.findElement(By.xpath("//body[@class='body-orange']/"
                + "nav[@id='nav-website']/ul[@id='first-level-nav']/div[@id='desktop-menu']/ul/li/a[@href='/guide']"));
        Thread.sleep(4000);
        guideButton.click();
        Thread.sleep(4000);
        String actualResultEndPoint = driver.getCurrentUrl();
        String actualResultPageTitle = driver.getTitle();

        Assert.assertEquals(actualResultPageTitle, expectedResultPageTitle);
        Assert.assertEquals(actualResultEndPoint, expectedResultEndPoint);

        driver.quit();

    }
    //   TC_11_02
//1.  Открыть базовую ссылку
//2.  Нажать на единицы измерения Imperial: °F, mph

    //3.  Подтвердить, что температура для города показана в Фарингейтах
    @Test
    public void testTemperatureHeadingInFarengeitOnMainPage_WhenClickToImperialFarenheit() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //     boolean expectedResult = true;
        String temp = "F";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(4000);
        WebElement imperialFarenheitButton = driver.findElement(
                By.xpath("//div[@class='controls']/div[@class='switch-container']"
                        + "/div[text()='Imperial: °F, mph']")
        );

        imperialFarenheitButton.click();
        Thread.sleep(5000);
        WebElement temperature = driver.findElement(By.xpath("//div[@class='current-temp']/span"));

//        boolean actualResult;
//        if (temperature.getText().toUpperCase().contains(temp)) {
//            actualResult = true;
//        } else {
//            actualResult = false;
//        }
        Assert.assertTrue(temperature.getText().toUpperCase().contains(temp));

        //      Assert.assertEquals(actualResult, expectedResult);
        driver.quit();
    }

    @Test
    // 1.  Открыть базовую ссылку
    //  2. Подтвердить, что внизу страницы есть панель(то есть то,что есть панель) с текстом “We use cookies which are essential
    //  for the site to work. We also use non-essential cookies to help us improve our services.
    //  Any data collected is anonymised. You can allow all cookies or manage them individually.”
    //  3. Подтвердить, что на панели внизу страницы есть 2 кнопки “Allow all” и “Manage cookies” - не текст, а
    //  что там две кнопки
    //(сделано просто по поиску текста)
    public void testCookiesOnTheFooterWhenOpeningAPage() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String expectedResult1 = "We use cookies which are essential for the site to work. "
                + "We also use non-essential cookies to help us improve our services. "
                + "Any data collected is anonymised. You can allow all cookies or manage them individually.";
        String expectedResult2 = "Allow all";
        String expectedResult3 = "Manage cookies";

        driver.get(url);
        Thread.sleep(5000);
        WebElement cookiesWarning = driver.findElement(
                By.xpath("//div[@id='stick-footer-panel']//"
                        + "div[@class='stick-footer-panel__container']/p")
        );
        //можно By.className "stick-footer-panel"
        WebElement allowCookies = driver.findElement(
                By.xpath("//div[@id='stick-footer-panel']//"
                        + "div[@class='stick-footer-panel__btn-container']/"
                        + "button[@class='stick-footer-panel__link']")
        );
        WebElement manageCookies = driver.findElement(
                By.xpath("//div[@id='stick-footer-panel']//"
                        + "div[@class='stick-footer-panel__btn-container']/a[@href='/cookies-settings']")
        );

        String actualResult1 = cookiesWarning.getText();
        String actualResult2 = allowCookies.getText();
        String actualResult3 = manageCookies.getText();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
        Assert.assertEquals(actualResult3, expectedResult3);
        driver.quit();
    }

    @Test
    /**TC_11_04
     1.  Открыть базовую ссылку
     2.  Подтвердить, что в меню Support есть 3 подменю с названиями “FAQ”, “How to start” и “Ask a question”
     */
    public void testSubmenusOfMenuSupport_WhenClickingOnSupport() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String expectedResult1 = "FAQ";
        String expectedResult2 = "How to start";
        String expectedResult3 = "Ask a question";

        driver.get(url);
        Thread.sleep(3000);
        driver.manage().window().maximize();
        WebElement support = driver.findElement(
                By.xpath("//div[@id='support-dropdown']"));
        Thread.sleep(3000);
        support.click();

        WebElement faq = driver.findElement(
                By.xpath("//ul[@id='support-dropdown-menu']/li/a[@href='/faq']")
        );
        WebElement start = driver.findElement(
                By.xpath("//ul[@id='support-dropdown-menu']/li/a[@href='/appid']")
        );
        WebElement question = driver.findElement(
                By.xpath("//ul[@id='support-dropdown-menu']/li/"
                        + "a[@href='https://home.openweathermap.org/questions']")
        );

        String actualResult1 = faq.getText();
        String actualResult2 = start.getText();
        String actualResult3 = question.getText();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
        Assert.assertEquals(actualResult3, expectedResult3);
        Assert.assertEquals(driver.findElements(
                By.xpath("//ul[@id='support-dropdown-menu']/li")).size(), 3);
        //последним подтвердили, чо именно 3
        driver.quit();
    }

    @Test
    /**
     * TC_11_05
     * //1. Открыть базовую ссылку
     * //2. Нажать пункт меню Support → Ask a question
     * //3. Заполнить поля Email, Subject, Message
     * //4. Не подтвердив CAPTCHA, нажать кнопку Submit
     * 5. Подтвердить, что пользователю будет показана ошибка “reCAPTCHA verification failed, please try again.”
     */
    public void testEmptyCaptchaOfMenuSupport_WhenAskingAQuestion() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String email = "tester@test.com";
        String message = "Test";
        String expectedResult = "reCAPTCHA verification failed, please try again.";

        driver.get(url);
        Thread.sleep(3000);
        driver.manage().window().maximize();
        WebElement support = driver.findElement(
                By.xpath("//div[@id='support-dropdown']"));
        Thread.sleep(3000);
        support.click();
        Thread.sleep(3000);
        WebElement question = driver.findElement(
                By.xpath("//ul[@id='support-dropdown-menu']/li/"
                        + "a[@href='https://home.openweathermap.org/questions']")
        );
        question.click();
        ArrayList<String> page = new ArrayList<String>(driver.getWindowHandles()); //переходит на новую страницу
        driver.switchTo().window(page.get(1));
        Thread.sleep(5000);
        WebElement emailField = driver.findElement(
                By.xpath("//div[@class='form-group email required question_form_email']/div[@class='col-sm-8']/input[@id='question_form_email']")
        );
        emailField.click();
        emailField.sendKeys(email);
        WebElement subjectField = driver.findElement(
                By.xpath("//select[@id='question_form_subject']")
        );
        subjectField.click();
        WebElement selectFieldChoice = driver.findElement(By.xpath("//option[1]"));
        selectFieldChoice.click();
        WebElement messageField = driver.findElement(
                By.xpath("//textarea[@id='question_form_message']")
        );
        messageField.click();
        messageField.sendKeys(message);
        WebElement submitButton = driver.findElement(
                By.xpath("//div[@class='col-sm-8']/input[@type='submit']")
        );
        submitButton.click();
        WebElement reCapthaText = driver.findElement(By.xpath("//div[@class='help-block']"));
        String actualResult = reCapthaText.getText();

        Assert.assertEquals(actualResult, expectedResult);
        driver.quit();
    }


    @Test
    /**
     * TC_11_07
     * 1.  Открыть базовую ссылку
     * 2.  Нажать на единицы измерения Imperial: °F, mph
     * 3.  Нажать на единицы измерения Metric: °C, m/s
     * 4.  Подтвердить, что в результате этих действий, единицы измерения температуры изменились с F на С
     *
     */

    public void testChangeToFarenheitAndBackToCelsiusOnMainPage_WhenClickToFarenheitThenToCelsius() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        char expectedResult = 'C';

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(2000);
        WebElement farenheitButton = driver.findElement(
                By.xpath("//div[@class='switch-container']/div[3][@class='option']")
        );
        Thread.sleep(1000);
        farenheitButton.click();
        WebElement celsiusButton = driver.findElement(
                By.xpath("//div[@class='switch-container']/div[2][@class='option']")
        );
        Thread.sleep(1000);
        celsiusButton.click();
        WebElement tempCels = driver.findElement(
                By.xpath("//div[@class='current-temp']/span[@class='heading']")
        );
        String tempText = tempCels.getText();
        char actualResult = tempText.toUpperCase().charAt(tempText.length() - 1);

        Assert.assertEquals(actualResult, expectedResult);
        driver.quit();
    }

    @Test
    /**
     * TC_11_08
     * 1.  Открыть базовую ссылку
     * 2.  Нажать на лого компании
     * 3.  Дождаться, когда произойдет перезагрузка сайта, и подтвердить, что текущая ссылка не изменилась
     */
    public void testURLOnTheMainPage_WhenClickingOnCompanyLogo() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String expectedResult = "https://openweathermap.org/";

        driver.get(url);
        Thread.sleep(7000);
        WebElement companyLogoImage = driver.findElement(
                By.xpath("//ul[@id='first-level-nav']/li[@class='logo']")
        );
        Thread.sleep(2000);
        companyLogoImage.click();
        Thread.sleep(2000);
        String actualResult = driver.getCurrentUrl();

        Assert.assertEquals(actualResult, expectedResult);
        driver.quit();

    }

    @Test
    /**
     * TC_11_09
     * 1.  Открыть базовую ссылку
     * 2.  В строке поиска в навигационной панели набрать “Rome”
     * 3.  Нажать клавишу Enter
     * 4.  Подтвердить, что вы перешли на страницу в ссылке которой содержатся слова “find” и “Rome”
     * 5. Подтвердить, что в строке поиска на новой странице вписано слово “Rome”
     */
    public void testCityNameInURL_WhenSearchingItInNavigationBar() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String cityNameForSearch = "Rome";
        String verbInURL= "find";
        String expectedResult = "Rome";//надо ли буквы аппер кейс
        boolean expectedResultFind = true;

        driver.get(url);
        Thread.sleep(7000);
        WebElement searchWeatherField = driver.findElement(
                By.xpath("//div[@id='desktop-menu']/form[@role='search']"
                        + "/input[@placeholder='Weather in your city']")
        );
        searchWeatherField.sendKeys(cityNameForSearch);
        searchWeatherField.sendKeys(Keys.ENTER);

        Thread.sleep(3000);
        WebElement cityNameInSearchField = driver.findElement(By.id("search_str"));
        String actualResult = cityNameInSearchField.getAttribute("value");
        driver.getCurrentUrl();
        boolean actualResultFind;
        if (driver.getCurrentUrl().contains(cityNameForSearch) && driver.getCurrentUrl().contains(verbInURL)) {
            actualResultFind = true;
        } else {
            actualResultFind = false;
        }


        Assert.assertEquals(actualResultFind, expectedResultFind);
        Assert.assertEquals(actualResult, expectedResult);
        driver.quit();

    }

    @Test
    public void testDeskTopMenuClickAPIFind30Buttons1() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        //Arrange
        String url = "https://openweathermap.org/";
        driver.get(url);
        driver.manage().window().maximize();
        int expectedResult = 30;
        Thread.sleep(7000);

        WebElement element = driver.findElement(
                By.xpath("//div[@id = 'desktop-menu']//a[text()= 'API']"));
        element.click();

        int countButton = driver.findElements(
                By.xpath("//a[contains(@class, 'btn_block orange round') " +
                        "or contains(@class, 'ow-btn round btn-orange') ]")).size();

        int actualResult = countButton;

        Assert.assertEquals(actualResult, expectedResult);
        driver.quit();

    }


}




