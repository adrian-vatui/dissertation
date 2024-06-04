import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver

static void main(String[] args) {
//    System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver")
//    WDS.browser = new ChromeDriver()
    System.setProperty("webdriver.firefox.driver", "/usr/local/bin/geckodriver")
    WDS.browser = new FirefoxDriver()

//    ExistingUserPostAndComment.run()
    NewUserPostAndComment.run()
}
