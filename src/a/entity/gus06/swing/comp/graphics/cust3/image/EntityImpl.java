package a.entity.gus06.swing.comp.graphics.cust3.image;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20191120";}
	
	public static final String MODE_NORMAL = "NORMAL";
	public static final String MODE_ADJUSTED = "ADJUSTED";
	public static final String MODE_DISTORTED = "DISTORTED";


	private Service performNormal;
	private Service performAdjusted;
	private Service performDistorted;
	
	public EntityImpl() throws Exception
	{
		performNormal = Outside.service(this,"gus06.swing.comp.graphics.cust3.image.normal");
		performAdjusted = Outside.service(this,"gus06.swing.comp.graphics.cust3.image.adjusted");
		performDistorted = Outside.service(this,"gus06.swing.comp.graphics.cust3.image.distorted");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] t = (Object[]) obj;
		if(t.length!=4) throw new Exception("Wrong data number: "+t.length);
		
		Object comp = t[0];
		Object g2 = t[1];
		Object image = t[2];
		String mode = (String) t[3];
		
		if(mode==null) mode = MODE_NORMAL;
		
		if(mode.equals(MODE_NORMAL))
			performNormal.p(new Object[]{comp,g2,image});
		else if(mode.equals(MODE_ADJUSTED))
			performAdjusted.p(new Object[]{comp,g2,image});
		else if(mode.equals(MODE_DISTORTED))
			performDistorted.p(new Object[]{comp,g2,image});
			
		else throw new Exception("Unsupported mode: "+mode);
	}
}
