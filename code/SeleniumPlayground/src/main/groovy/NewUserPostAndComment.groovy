import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

import java.time.Duration
import java.time.Instant
import java.time.temporal.ChronoUnit

class NewUserPostAndComment {
    static void run() {
        WebDriverWait wait = new WebDriverWait(WDS.browser, Duration.of(10, ChronoUnit.SECONDS))
        Actions actions = new Actions(WDS.browser)
        JavascriptExecutor executor = (JavascriptExecutor) WDS.browser;

        WDS.browser.get('http://localhost:4200/login')
        WDS.browser.get('http://localhost:4200/register')

        String username = Common.register(WDS.browser, wait)

        Common.login(WDS.browser, wait, username);

        List<WebElement> posts = WDS.browser.findElements(By.className("post-card"))
        posts.shuffle()
        posts.subList(0, 2).forEach(post -> {
            Common.comment(actions, wait, executor, post, username)
        })

        Common.postNoImages(WDS.browser, username)
    }
}
