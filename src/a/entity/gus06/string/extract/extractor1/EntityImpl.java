package a.entity.gus06.string.extract.extractor1;

import a.framework.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class EntityImpl implements Entity, T {


	public String creationDate() {return "20160424";}
	
	public static final String INDEX1_DEFAULT = "1";
	public static final String INDEX2_DEFAULT = "1";
	


	private Service handleMatcher;
	private Service buildRegex;

	public EntityImpl() throws Exception
	{
		handleMatcher = Outside.service(this,"gus06.string.extract.extractor1.handlematcher");
		buildRegex = Outside.service(this,"gus06.string.extract.extractor1.buildregex");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String[] o = (String[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		String data = o[0];
		String rule = o[1];
		String options = o[2];
		
		
		String index1 = findIndex1(options);
		String index2 = findIndex2(options);
		String other = findOther(options);
		
		String regex = (String) buildRegex.t(new String[]{rule,other});
		
		int flags = Pattern.DOTALL;
		if(other.contains("i")) flags = flags | Pattern.CASE_INSENSITIVE;
		
		Pattern p = Pattern.compile(regex,flags);
		Matcher m = p.matcher(data);
		
		return handleMatcher.t(new Object[]{m,index1,index2});
	}
	
	
	
	private String findIndex1(String options)
	{
		if(options.length()<1) return INDEX1_DEFAULT;
		return options.substring(0,1);
	}
	
	private String findIndex2(String options)
	{
		if(options.length()<2) return INDEX2_DEFAULT;
		return options.substring(1,2);
	}
	
	private String findOther(String options)
	{
		if(options.length()<3) return "";
		return options.substring(2);
	}
}
