package back_end;

import models.Url;
import models.dao.DaoUrl;
import models.mongo.GenericDao;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Http;
import play.Logger;
import static play.Logger.of;

import javax.inject.Singleton;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Random;


@Singleton
public class UrlController extends Controller {
	private static final Logger.ALogger logger = of(UrlController.class);

	// key-url map
    private HashMap<String, String> keyMap; 
	// url-key map to quickly check
    private HashMap<String, String> valueMap; 
											
    // name domain
    private String domain; 
	// array of characters
    private String  valid; 
    // random character
    private Random myRand;
	// number characters 
    private int keyLength; 

    // Default Constructor
    UrlController() {
	keyMap = new HashMap<String, String>();
	valueMap = new HashMap<String, String>();
	myRand = new Random();
	keyLength = 8;
	valid = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	domain = "http://fkt.in";
    }

    // Constructor which enables define URL key length and base URL
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

    // sanitizeURL 
    String sanitizeURL(String url) {
	if (url.charAt(url.length() - 1) == '/')
	url = url.substring(0, url.length() - 1);
	return url;
    }

    // get key , save key into keyMap and valueMap
    private String getKey(String longURL) {
	String key;
	key = generateKey();
	keyMap.put(key, longURL);
	valueMap.put(longURL, key);
	return key;
    }
	

	// create key
    private String generateKey() {
	String key = "";
	boolean flag = true;
	while (flag) {
		key = "";
		for (int i = 0; i <= keyLength; i++) {
			key += valid.charAt(myRand.nextInt(62));
		}
		if (!keyMap.containsKey(key)) {
			flag = false;
		}
	}
	return key;
    }
	public Result shorten(Http.Request request, String long_url){
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
