package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.send.IdMessageRecipient;
import com.restfb.types.send.Message;
import com.restfb.types.send.SendResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.Map;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    private final Logger.ALogger LOG = Logger.of(this.getClass());

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
        LOG.info("chatbotJavaExample, request ::: {}", request());
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

    public Result doChatbotJavaExample() {
        LOG.debug("doChatbotJavaExample, request ::: {}", request());
        JsonNode json = request().body().asJson();
        LOG.debug("doChatbotJavaExample, body ::: {}", json.toString());
        LOG.debug("demo {}", json.get("object"));
        LOG.debug("demo {}", json.get("object").equals("page"));

        if ("page".equals(json.get("object").asText())) {

            // Iterate over each entry - there may be multiple if batched
            JsonNode entries = json.get("entry");
            LOG.debug("entries ::: {}", entries);
            entries.forEach(entry -> {
                JsonNode webhookEvent = entry.get("messaging");
                LOG.debug("webhook event ::: {}", webhookEvent);
            });
            return ok("Hello Wrong I'm here");

        } else {
            // Return a '404 Not Found' if event is not from a page subscription
            return badRequest("NOOOOOO");
        }
    }

    private void sendMessage(IdMessageRecipient recipient, Message message) {
        FacebookClient pageClient = new DefaultFacebookClient(accessToken, Version.VERSION_3_0);

        SendResponse resp = pageClient.publish("me/messages",
                SendResponse.class,
                Parameter.with("recipient", recipient),
                Parameter.with("message", message));
    }

}
