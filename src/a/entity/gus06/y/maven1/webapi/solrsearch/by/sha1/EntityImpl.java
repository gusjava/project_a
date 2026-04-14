package a.entity.gus06.y.maven1.webapi.solrsearch.by.sha1;

import a.framework.*;
import java.net.URLEncoder;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251228";}
	
	public static final int ROW_MAX = 20;


	private Service parseJson;
	private Service buildSha1;
	private Service get;

	public EntityImpl() throws Exception
	{
		parseJson = Outside.service(this,"gus.x.json.parse1");
		buildSha1 = Outside.service(this,"gus06.crypto.hash.sha1.hexa");
		get = Outside.service(this,"gus06.y.maven1.webapi.get");
	}

	public Object t(Object obj) throws Exception
	{
		String sha1 = toSha1(obj);

		String queryUrl = "https://search.maven.org/solrsearch/select?q=1:" +
			sha1 +
			"&rows=" + ROW_MAX +
			"&wt=json";

		String json = download(queryUrl);
		Map result = parseJson(json);

		List docs = (List) ((Map)result.get("response")).get("docs");
		if(docs.isEmpty()) return null;
		Map doc = (Map) docs.get(0);
		
		Map m = new HashMap();
		m.put("groupId", doc.get("g"));
		m.put("artifactId", doc.get("a"));
		m.put("version", doc.get("v"));
		return m;
	}

	private String download(String urlStr) throws Exception
	{return (String) get.t(urlStr);}

	private Map parseJson(String json) throws Exception
	{return (Map) parseJson.t(json);}
	
	private String toSha1(Object obj) throws Exception
	{
		if(obj instanceof String) return (String) obj;
		if(obj instanceof File) return (String) buildSha1.t(obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
