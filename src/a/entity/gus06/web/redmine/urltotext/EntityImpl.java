package a.entity.gus06.web.redmine.urltotext;

import a.framework.*;
import java.util.Map;
import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.URL;
import java.net.URLEncoder;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250626";}
	
	public static final String KEY_URL = "url";
	public static final String KEY_LOGIN = "login";
	public static final String KEY_PWD = "pwd";


	private Service getBase;

	public EntityImpl() throws Exception
	{
		getBase = Outside.service(this,"gus.x.url.getbase");
	}
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		String url_ = get(map,KEY_URL);
		String login = get(map,KEY_LOGIN);
		String pwd = get(map,KEY_PWD);
		
		URI uri = URI.create(url_);
		URI uriBase = new URI(uri.getScheme(), null, uri.getHost(), uri.getPort(), null, null, null);
		URI uriLogin = uriBase.resolve("/login");
		
		CookieManager cookieManager = new CookieManager();
		cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);

		HttpClient client = HttpClient.newBuilder().cookieHandler(cookieManager).build();
		HttpRequest getLoginPage = HttpRequest.newBuilder().uri(uriLogin).GET().build();
		HttpResponse<String> loginPageResponse = client.send(getLoginPage, HttpResponse.BodyHandlers.ofString());
		String html = loginPageResponse.body();

		Pattern pattern = Pattern.compile("name=\"authenticity_token\" value=\"([^\"]+)\"");
		Matcher matcher = pattern.matcher(html);
		if (!matcher.find()) throw new Exception("Token not found");
		String token = matcher.group(1);
		
		String loginForm = "username="+login+"&password="+pwd
		+"&authenticity_token="+URLEncoder.encode(token, "UTF-8");

		HttpRequest requestLogin = HttpRequest.newBuilder().uri(uriLogin)
		.header("Content-Type", "application/x-www-form-urlencoded")
		.POST(HttpRequest.BodyPublishers.ofString(loginForm))
		.build();

		HttpResponse<String> responseLogin = client.send(requestLogin, HttpResponse.BodyHandlers.ofString());
		if (responseLogin.statusCode() != 302 && responseLogin.statusCode() != 200)
			throw new Exception("Page returned error code: "+responseLogin.statusCode());

		HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();
		HttpResponse<String> reponse = client.send(request, HttpResponse.BodyHandlers.ofString());
		return reponse.body();
	}
	
	private String get(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Unknown key: "+key);
		return (String) map.get(key);
	}
}
