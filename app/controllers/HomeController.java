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
        Map<String, String[]> queryString = request().queryString();
        if (CollectionUtils.isEmpty(queryString)) {
            return badRequest();
        }
        return ok("Your new application is ready.");
    }

}
