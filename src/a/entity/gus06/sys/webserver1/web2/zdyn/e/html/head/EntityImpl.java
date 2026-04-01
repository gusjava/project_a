package a.entity.gus06.sys.webserver1.web2.zdyn.e.html.head;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140930";}
	
	public static final String TITLE = "website.title:Default title";
	public static final String CHARSET = "website.charset:UTF-8";
	public static final String FAVICO = "website.favico";
	
	public static final String KEY_OUTPUT_ENCODING = "output_encoding";
	


	private Service lib;
	private Service meta;
	private Service findProp;


	public EntityImpl() throws Exception
	{
		lib = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.head.lib");
		meta = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.head.meta");
		findProp = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.find.prop1");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		R mr = (R) obj;
		V mv = (V) obj;
		P h = (P) mr.r("data h");
		
		String title = prop(obj,TITLE);
		String charset = prop(obj,CHARSET);
		String favico = prop(obj,FAVICO);
		
		if(title!=null) h.p(title(title));
		if(charset!=null) h.p(metaCharset(charset));
		if(favico!=null) h.p(linkIco(favico));
		
		if(charset!=null)
		mv.v(KEY_OUTPUT_ENCODING,charset);
		
		meta.p(obj);
		lib.p(obj);
	}
	
	
	
	
	private String prop(Object obj, String key) throws Exception
	{return (String) findProp.t(new Object[]{obj,key});}
	
	
	
	
	private String linkIco(String s)
	{return "<link rel=\"icon\" type=\"image/ico\" href=\""+s+"\">";}
	
	private String metaCharset(String s)
	{return "<meta charset=\""+s+"\">";}
	
	private String title(String s)
	{return "<title>"+s+"</title>";}
}
