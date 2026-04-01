package a.entity.gus06.appli.webdownloader.engine.download;

import a.framework.*;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class EntityImpl implements Entity, R, E, P {

	public String creationDate() {return "20150408";}
	
	public static final String KEY_URL = "url";
	public static final String TAG = "[]";
	public static final Pattern P_URL = Pattern.compile("\\[([0-9]+)-([0-9]+)\\]");
	
	
	private Service optionManager;
	private Service handleUrl;

	private int size;
	private int index;
	private String line;
	
	private String rule;
	private int start;
	private int end;
	
	

	public EntityImpl() throws Exception
	{
		optionManager = Outside.service(this,"gus06.sys.option.manager");
		handleUrl = Outside.service(this,"gus06.appli.webdownloader.engine.download.handleurl");
	}
	
	
	public void e() throws Exception
	{next();}
	
	
	public void p(Object obj) throws Exception
	{
		String rule = (String) obj;
		if(rule.equals("init")) {init();return;}
		throw new Exception("Unknown rule: "+rule);
	}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("size")) return ""+size;
		if(key.equals("line")) return line;
		
		if(key.equals("keys")) return new String[]{"size","line"};
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	
	
	private void init() throws Exception
	{
		String s = option(KEY_URL);
		
		if(s==null || s.equals(""))
		{
			size = -1;
			line = "error:no url defined";
			return;
		}
		
		Matcher m = P_URL.matcher(s);
		if(!m.find())
		{
			size = 1;
			rule = s;
			return;
		}
		
		start = Integer.parseInt(m.group(1));
		end = Integer.parseInt(m.group(2));
		
		StringBuffer b = new StringBuffer();
		m.appendReplacement(b,TAG);
		m.appendTail(b);
		
		rule = b.toString();
		index = start;
		size = end-start;
	}
	



	private void next() throws Exception
	{
		if(rule==null) throw new Exception("Rule not initialized yet");
		String url = rule.replace(TAG,""+index);
		index++;
		line = (String) handleUrl.t(new URL(url));
	}
	
	
	
	private String option(String key) throws Exception
	{return (String) optionManager.r(key);}
}
