import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

import java.time.Instant

class Common {
    static void login(WebDriver driver, WebDriverWait wait, String username) {

        driver.findElement(By.id("input-username")).sendKeys(username)
        driver.findElement(By.id("input-password")).sendKeys("password")
        driver.findElement(By.id("login-button")).click()

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("post-card")))
        assert !driver.getCurrentUrl().endsWith("login")
    }

    static void comment(Actions actions, WebDriverWait wait, JavascriptExecutor executor, WebElement post, String username) {
        actions.moveToElement(post)

        // load comments
        // post.findElement(By.className("comment-button")).click()
        executor.executeScript("arguments[0].click()", post.findElement(By.className("comment-button")))

        // write and submit comment
        wait.until(ExpectedConditions.visibilityOf(post.findElement(By.className("input-comment"))))
                .sendKeys("Comment left at ${Instant.now()} by ${username}")
        post.findElement(By.className("submit-comment-button")).click()
    }

    static void postNoImages(WebDriver driver, String username) {
        driver.findElement(By.id("post-text"))
                .sendKeys("Post made at ${Instant.now()} by ${username}")
        driver.findElement(By.id("post-button")).click()
    }

    static String register(WebDriver driver, WebDriverWait wait)  {
        String username = "user_" + UUID.randomUUID().toString()

        driver.findElement(By.id("input-username")).sendKeys(username)
        driver.findElement(By.id("input-email")).sendKeys("${username}@example.com")
        driver.findElement(By.id("input-password")).sendKeys("password")
        driver.findElement(By.id("register-button")).click()

        wait.until((it) -> it.getCurrentUrl().endsWith("login"))

        return username
    }
}
