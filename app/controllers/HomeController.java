package controllers;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.Map;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok("Your new application is ready.");
    }

    private static final String accessToken = "EAAGAZCKE1pF4BAM3Jalc0ZAPT6jZBDgGkC0LoIXwHiVFU9pf75dhiQPPdPDLySJ79EXDZC5HJE55irKN7FvHiLikNnLEoZBllMQ7FO6K3kSo3lZCv8fsnlSSmfbaxjZCygsPUPjAue2ZCJ9OzKYGriDwV23JgP8ZBYflnXMch04w8fgZDZD";
    private static final String verifyToken = "chatbot-java-example";

    public Result chatbotJavaExample() {
        Map<String, String[]> queryString = request().queryString();
        if (CollectionUtils.isEmpty(queryString)) {
            return badRequest();
        }

        String hubToken = StringUtils.EMPTY;
        String hubChallenge = StringUtils.EMPTY;
        if (queryString.get("hub.verify_token") != null && queryString.get("hub.verify_token").length > 0) {
            hubToken = queryString.get("hub.verify_token")[0];
        }

        if (queryString.get("hub.challenge") != null && queryString.get("hub.challenge").length > 0) {
            hubChallenge = queryString.get("hub.challenge")[0];
        }

        if (verifyToken.equals(hubToken)) {
            return ok(hubChallenge);
        } else {
            return ok("Hello Wrong");
        }
    }

}
