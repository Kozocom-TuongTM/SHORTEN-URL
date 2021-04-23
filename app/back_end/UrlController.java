package back_end;

import models.Url;
import models.dao.DaoUrl;
import models.mongo.GenericDao;
import models.request.Link;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Http;
import play.Logger;
import static play.Logger.of;
// import play.data.FormFactory;
// import play.data.Form;

import javax.inject.Singleton;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Random;


@Singleton
public class UrlController extends Controller {
	private static final Logger.ALogger logger = of(UrlController.class);

	// @Inject
	// FormFactory formFactory;

    private HashMap<String, String> keyMap; // key-url map
    private HashMap<String, String> valueMap; // url-key map to quickly check
											
    // already entered in our system
    private String domain; 
						
    private char myChars[]; 
    private Random myRand; 
    private int keyLength; 

    // Default Constructor
    UrlController() {
	keyMap = new HashMap<String, String>();
	valueMap = new HashMap<String, String>();
	myRand = new Random();
	keyLength = 8;
	myChars = new char[62];
	for (int i = 0; i < 62; i++) {
		int j = 0;
		if (i < 10) {
			j = i + 48;
		} else if (i > 9 && i <= 35) {
			j = i + 55;
		} else {
			j = i + 61;
		}
		myChars[i] = (char) j;
	}
	domain = "http://fkt.in";
    }

    // Constructor which enables you to define tiny URL key length and base URL
    // name
    UrlController(int length, String newDomain) {
	this();
	this.keyLength = length;
	if (!newDomain.isEmpty()) {
		newDomain = sanitizeURL(newDomain);
		domain = newDomain;
	}
    }

    // shortenURL
    // the public method which can be called to shorten a given URL
    public String shortenURL(String longURL) {
	String shortURL = "";
	if (validateURL(longURL)) {
		longURL = sanitizeURL(longURL);
		if (valueMap.containsKey(longURL)) {
			shortURL = domain + "/" + valueMap.get(longURL);
		} else {
			shortURL = domain + "/" + getKey(longURL);
		}
	}
	return shortURL;
    }

    // expandURL
    // public method which returns back the original URL given the shortened url
    public String expandURL(String shortURL) {
	String longURL = "";
	String key = shortURL.substring(domain.length() + 1);
	longURL = keyMap.get(key);
	return longURL;
    }

    // Validate URL
    boolean validateURL(String url) {
	return true;
    }

    String sanitizeURL(String url) {
	if (url.charAt(url.length() - 1) == '/')
	url = url.substring(0, url.length() - 1);
	return url;
    }

    private String getKey(String longURL) {
	String key;
	key = generateKey();
	keyMap.put(key, longURL);
	valueMap.put(longURL, key);
	return key;
    }
    private String generateKey() {
	String key = "";
	boolean flag = true;
	while (flag) {
		key = "";
		for (int i = 0; i <= keyLength; i++) {
			key += myChars[myRand.nextInt(62)];
		}
		// System.out.println("Iteration: "+ counter + "Key: "+ key);
		if (!keyMap.containsKey(key)) {
			flag = false;
		}
	}
	return key;
    }
	public Result shorten(Http.Request request, String long_url){
	//	Form<Link> formInput = this.formFactory.form(Link.class).bindFromRequest(request);
	//	Link query = formInput.get();
       // query.getLong_url.class;
		
       //logger.debug(query.getLong_url());
		UrlController u = new  UrlController(10, "https://papa/");
	    DaoUrl daoUrl = DaoUrl.getInstance();
		daoUrl.createUrl(long_url,u.shortenURL(long_url));
		return ok(Json.toJson(u.shortenURL(long_url)));
	}
	
	public Result error(Http.Request request){
		String error = "Unable to shorten that link. It is not a valid url.";
		return ok(Json.toJson(error));
	} 
}
