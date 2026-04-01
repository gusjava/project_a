package a.entity.gus06.sys.webserver1.web2.zdyn.e.format.findop0;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T, G {

	public String creationDate() {return "20141002";}


	private Service op_now;
	private Service op_upper;
	private Service op_lower;
	private Service op_titled;
	private Service op_glued;
	private Service op_html;
	private Service op_eval;
	private Service op_check;
	private Service op_int;
	private Service op_length;
	private Service op_outside;
	private Service op_urlhttp;
	private Service op_randint;
	private Service op_date_fr1;
	private Service op_date_fr2;
	private Service op_date_fr3;
	private Service op_date_fr4;
	private Service op_datasize_fr;
	private Service op_datasize_en;
	
	private Map map;



	public EntityImpl() throws Exception
	{
		op_now = Outside.service(this,"gus06.time.now.pattern");
		op_upper = Outside.service(this,"gus06.string.transform.str.upper");
		op_lower = Outside.service(this,"gus06.string.transform.str.lower");
		op_titled = Outside.service(this,"gus06.string.transform.str.titled");
		op_glued = Outside.service(this,"gus06.string.transform.normalize.diacritics.lower.glued");
		op_html = Outside.service(this,"gus06.string.transform.format.html.encode");
		op_eval = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.bool.eval1");
		op_check = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.bool.eval2");
		op_int = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.bool.eval1.t");
		op_length = Outside.service(this,"gus06.string.transform.str.length");
		op_outside = Outside.service(this,"gus06.outside.call");
		op_urlhttp = Outside.service(this,"gus06.string.transform.url.http.complete");
		op_randint = Outside.service(this,"gus06.data.generate.string.random.number10");
		op_date_fr1 = Outside.service(this,"gus06.string.transform.format.timestamp.fr1");
		op_date_fr2 = Outside.service(this,"gus06.string.transform.format.timestamp.fr2");
		op_date_fr3 = Outside.service(this,"gus06.string.transform.format.timestamp.fr3");
		op_date_fr4 = Outside.service(this,"gus06.string.transform.format.timestamp.fr4");
		op_datasize_fr = Outside.service(this,"gus06.string.transform.format.datasize.fr");
		op_datasize_en = Outside.service(this,"gus06.string.transform.format.datasize.en");
		
		map = new HashMap();
		
		put("now",op_now);
		put("upper",op_upper);
		put("lower",op_lower);
		put("titled",op_titled);
		put("glued",op_glued);
		put("html",op_html);
		put("eval",op_eval);
		put("check",op_check);
		put("int",op_int);
		put("length",op_length);
		put("outside",op_outside);
		put("http",op_urlhttp);
		put("randint",op_randint);
		put("date_fr1",op_date_fr1);
		put("date_fr2",op_date_fr2);
		put("date_fr3",op_date_fr3);
		put("date_fr4",op_date_fr4);
		put("datasize_fr",op_datasize_fr);
		put("datasize_en",op_datasize_en);
	}
	
	
	private void put(String key, Object value)
	{map.put(key,value);}
	
	
	public Object t(Object obj) throws Exception
	{return findOp((String) obj);}
	
	
	public Object g() throws Exception
	{return map;}
	
	
	private T findOp(String op) throws Exception
	{
		if(!map.containsKey(op)) return null;
		return (T) map.get(op);
	}
}
