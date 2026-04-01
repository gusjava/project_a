package a.entity.gus06.sys.scheduling1.checkcurrent.each;

import a.framework.*;
import java.util.Map;
import java.util.Date;
import java.text.SimpleDateFormat;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20180119";}

	public static final String KEY_EACH = "each";
	
	private SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
	private SimpleDateFormat yyyyMMddHH = new SimpleDateFormat("yyyyMMddHH");
	private SimpleDateFormat yyyyMMddHHmm = new SimpleDateFormat("yyyyMMddHHmm");
	private SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");

	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		Date d = (Date) o[1];
		Date d0 = (Date) o[2];
		
		if(d0==null) return false;
		
		String each = (String) map.get(KEY_EACH);
		if(each.equals("s"))
		{
			String t = yyyyMMddHHmmss.format(d);
			String t0 = yyyyMMddHHmmss.format(d0);
			return !t.equals(d0);
		}
		if(each.equals("m"))
		{
			String t = yyyyMMddHHmm.format(d);
			String t0 = yyyyMMddHHmm.format(d0);
			return !t.equals(d0);
		}
		if(each.equals("h"))
		{
			String t = yyyyMMddHH.format(d);
			String t0 = yyyyMMddHH.format(d0);
			return !t.equals(d0);
		}
		if(each.equals("d"))
		{
			String t = yyyyMMdd.format(d);
			String t0 = yyyyMMdd.format(d0);
			return !t.equals(d0);
		}
		throw new Exception("Unsupported value for each option: "+each);
	}
}
