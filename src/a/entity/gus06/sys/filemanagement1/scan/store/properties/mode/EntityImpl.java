package a.entity.gus06.sys.filemanagement1.scan.store.properties.mode;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20201106";}
	
	public static final String CONFIG_MODE = "scan.prop.mode";
	public static final String DEFAULT_VALUE = "onnotfound";
	
	public static final String VALUE_IGNORE = "ignore";
	public static final String VALUE_REWRITE = "rewrite";
	public static final String VALUE_ONERROR = "onerror";
	public static final String VALUE_ONNOTFOUND = "onnotfound";
	public static final String VALUE_ONBEFORETODAY = "onbeforetoday";
	public static final String VALUE_ONBEFORE1WEEK = "onbefore1week";
	public static final String VALUE_ONBEFORE1MONTH = "onbefore1month";
	public static final String VALUE_SKIPFIRST = "skipfirst";


	private Service hasError;
	private Service getToday;
	private Service week1;
	private Service month1;
	private Service toDate;
	
	private boolean skipFlag = false;

	public EntityImpl() throws Exception
	{
		hasError = Outside.service(this,"gus06.map.filter.key.errortype");
		getToday = Outside.service(this,"gus06.time.today");
		week1 = Outside.service(this,"gus06.time.date.is.be.be0.week1");
		month1 = Outside.service(this,"gus06.time.date.is.be.be0.month1");
		toDate = Outside.service(this,"gus06.convert.stringtodate");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		String md5 = (String) o[1];
		
		String mode = getMode(engine);
		
		if(mode.equals(VALUE_REWRITE)) return true;
		if(mode.equals(VALUE_IGNORE)) return false;
		if(mode.equals(VALUE_ONNOTFOUND)) return onNotFound(engine,md5);
		if(mode.equals(VALUE_ONERROR)) return onError(engine,md5);
		if(mode.equals(VALUE_ONBEFORETODAY)) return onBeforeToday(engine,md5);
		if(mode.equals(VALUE_ONBEFORE1WEEK)) return onBefore1Week(engine,md5);
		if(mode.equals(VALUE_ONBEFORE1MONTH)) return onBefore1Month(engine,md5);
		if(mode.equals(VALUE_SKIPFIRST))
		{
			if(!onNotFound(engine,md5)) return false;
			if(!skipFlag) {skipFlag = true;return false;}
			return true;
		}
		
		throw new Exception("Unsupported mode: "+mode);
	}
	
	private String getMode(Object engine) throws Exception
	{
		String mode = (String) ((R)engine).r("config:"+CONFIG_MODE);
		return mode!=null ? mode : DEFAULT_VALUE;
	}
	
	
	
	
	
	private boolean onNotFound(Object engine, String md5) throws Exception
	{
		File file = (File) ((R) engine).r("propFile:"+md5);
		return file==null || !file.exists();
	}
	
	private boolean onError(Object engine, String md5) throws Exception
	{
		Map prop = (Map) ((R) engine).r("prop:"+md5);
		if(prop==null) return true;
		return hasError.f(prop);
	}
	
	private boolean onBeforeToday(Object engine, String md5) throws Exception
	{
		Map prop = (Map) ((R) engine).r("prop:"+md5);
		if(prop==null) return true;
		String time = (String) prop.get("time");
		String today = (String) getToday.g();
		return !time.startsWith(today);
	}
	
	private boolean onBefore1Week(Object engine, String md5) throws Exception
	{
		Map prop = (Map) ((R) engine).r("prop:"+md5);
		if(prop==null) return true;
		String time = (String) prop.get("time");
		return week1.f(toDate.t(time));
	}
	
	private boolean onBefore1Month(Object engine, String md5) throws Exception
	{
		Map prop = (Map) ((R) engine).r("prop:"+md5);
		if(prop==null) return true;
		String time = (String) prop.get("time");
		return month1.f(toDate.t(time));
	}
}