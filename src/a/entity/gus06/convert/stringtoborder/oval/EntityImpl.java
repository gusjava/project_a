package a.entity.gus06.convert.stringtoborder.oval;

import a.framework.*;
import javax.swing.border.Border;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140913";}


	private Service buildBorder;


	public EntityImpl() throws Exception
	{buildBorder = Outside.service(this,"gus06.swing.border.build.ovalborder");}



	public Object t(Object obj) throws Exception
	{return build((String) obj);}
	
	
	
	private Border build(String rule) throws Exception
	{
		if(rule==null) return (Border) buildBorder.g();
		String[] n = rule.split(" ");
		
		if(n.length==1) return (Border) buildBorder.t(new int[]{int_(n[0]),int_(n[0])});
		if(n.length==2) return (Border) buildBorder.t(new int[]{int_(n[0]),int_(n[1])});
		
		throw new Exception("Invalid empty border rule: "+rule);
	}
	
	private int int_(String s)
	{return Integer.parseInt(s);}
}
