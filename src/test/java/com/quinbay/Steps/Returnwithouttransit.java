package com.quinbay.Steps;
import com.quinbay.Pages.DetailpageXpath;
import com.quinbay.Pages.Filling_page;
import com.quinbay.Pages.HomepageXpath;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.hu.De;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.Assert.*;
import org.openqa.selenium.support.ui.Select;
import java.util.LinkedHashMap;
public class Return {
    static WebDriver driver;
    JavascriptExecutor js;
    LinkedHashMap<String, String> map = new LinkedHashMap<>();
    HomepageXpath xpath;

    @Given("The user should go to the home page of the travel website")
    public void theUserShouldGoToTheHomePageOfTheBlibliTravelWebsite() {
        System.out.println("Navigate to the blibli travel website");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        System.setProperty("webdriver.chrome.driver", "path/to/driver/exe");
        System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.blibli.com/travel");
        System.out.println("Navigated to the blibli travel website");

    }
    @Then("Enter the details to book the flight")
    public void enterTheDetailsToBookAFlight() throws InterruptedException {
        System.out.println("User enters the details");
        Thread.sleep(2000);
        WebElement place = driver.findElement(By.xpath(xpath.Place));
        place.sendKeys("Surabaya");
        place.sendKeys(Keys.ENTER);
        driver.findElement(By.xpath(xpath.calenderbutton)).click();
        driver.findElement(By.xpath(xpath.date)).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//small[contains(text(),'Pulang Pergi')]")).click();
        Thread.sleep(3000);
        System.out.println("The details are entered");
    }
    @And("Click book a plane")
    public void clickBookAFlight() throws InterruptedException {
        System.out.println("User clicks book flight button");
        driver.findElement(By.xpath(xpath.serach_flight)).click();
        Thread.sleep(3000);
        System.out.println("Book button was clicked by user");
    }
    @And("Scroll the page until the planes are visible")
    public void scrollThePageUntilTheFlightsAreVisible() throws InterruptedException {
        js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1500)");
        Thread.sleep(3000);
    }
    @And("click on the details link and store details in map")
    public void clickOnTheDetailLink() throws InterruptedException {
        System.out.println("Click on the detail section and store details in a map");
        Thread.sleep(5000);
        driver.findElement(By.xpath(DetailpageXpath.details)).click();
        map.put("source1", driver.findElement(By.xpath(DetailpageXpath.source)).getText());
        map.put("destination1", driver.findElement(By.xpath(DetailpageXpath.destination)).getText());
        map.put("Airline-code1", driver.findElement(By.xpath(DetailpageXpath.Flight)).getText());
        map.put("start-time1", driver.findElement(By.xpath(DetailpageXpath.sourceTime)).getText());
        map.put("end-time1", driver.findElement(By.xpath(DetailpageXpath.destinationTime)).getText());
        System.out.println("Map" + map);
        System.out.println("The details are stored in a map");
    }
    @And("Click on the go button")
    public void clickOnTheSelectGoButton() {
        driver.findElement(By.xpath(DetailpageXpath.search)).click();
    }
    @And("Click on the details section for return and verify")
    public void clickOnTheDetailSectionForReturnAndVerifyTheValues() throws InterruptedException {
        System.out.println("Click on details section for return and verify the details");
        Thread.sleep(3000);
        Thread.sleep(3000);
        driver.findElement(By.xpath(DetailpageXpath.Details)).click();
        map.put("source2", driver.findElements(By.xpath(DetailpageXpath.source)).get(1).getText());
        map.put("destination2", driver.findElements(By.xpath(DetailpageXpath.destination)).get(1).getText());
        map.put("Airline-code2", driver.findElements(By.xpath(DetailpageXpath.Flight)).get(1).getText());
        map.put("start-time2", driver.findElements(By.xpath(DetailpageXpath.sourceTime)).get(1).getText());
        map.put("end-time2", driver.findElements(By.xpath(DetailpageXpath.destinationTime)).get(1).getText());
        System.out.println("The details are verified");
    }
    @And("Click on the select go button for return")
    public void click_on_the_select_go_button_for_return() throws InterruptedException {
        System.out.println("Click on select go button");
        driver.findElement(By.xpath("//b[contains(text(),'×')]")).click();
        driver.findElement(By.xpath(DetailpageXpath.search)).click();
        //driver.findElement(By.xpath(DetailpageXpath.orderTicket)).click();
        Thread.sleep(5000);
    }
    @And("Enter the details to book a plane")
    public void enterTheDetailsToProceed() throws InterruptedException {
        System.out.println("User enters the details");
        Thread.sleep(3000);
        Select surname = new Select(driver.findElement(By.name("title")));
        surname.selectByVisibleText("Nona");
        driver.findElement(By.name("fullName")).sendKeys("Samyuktha");
        driver.findElement(By.name("phoneNumber")).sendKeys("08594389534");
        driver.findElement(By.name("email")).sendKeys("samyuktha@gmail.com");
        js.executeScript("window.scrollBy(0,700)");
        driver.findElement(By.xpath(Filling_page.checkbox)).click();
        Thread.sleep(10000);
        System.out.println("The details are entered");
    }
    @And("Click on the details section and verify detail")
    public void clickOnTheDetailSectionAndVerify() throws InterruptedException {
        System.out.println("Click on detail section and verify details");
        //detail= prop.getProperty("detail1");
        driver.findElement(By.xpath(DetailpageXpath.Details)).click();
        //driver.findElement(By.xpath(detail)).click();
        Thread.sleep(3000);
        assertEquals(map.get("source1"), driver.findElement(By.xpath(DetailpageXpath.source)).getText());
        assertEquals(map.get("destination1"), driver.findElement(By.xpath(DetailpageXpath.destination)).getText());
        assertEquals(map.get("Airline-code1"), driver.findElement(By.xpath(DetailpageXpath.Flight)).getText());
        assertEquals(map.get("start-time1"), driver.findElement(By.xpath(DetailpageXpath.sourceTime)).getText());
        assertEquals(map.get("end-time1"), driver.findElement(By.xpath(DetailpageXpath.destinationTime)).getText());
        Thread.sleep(3000);
        driver.findElement(By.xpath("//b[contains(text(),'×')]")).click();
        System.out.println("The details are verified");
    }
}