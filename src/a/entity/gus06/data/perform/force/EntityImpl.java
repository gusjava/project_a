package a.entity.gus06.data.perform.force;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170408";}
	
	
	private Service performE;
	private Service performF;
	private Service performG;
	private Service performH;
	private Service performI;
	private Service performP;
	private Service performR;
	private Service performT;
	private Service performV;
	
	public EntityImpl() throws Exception
	{
		performE = Outside.service(this,"gus06.feature.force.e");
		performF = Outside.service(this,"gus06.feature.force.f");
		performG = Outside.service(this,"gus06.feature.force.g");
		performH = Outside.service(this,"gus06.feature.force.h");
		performI = Outside.service(this,"gus06.feature.force.i");
		performP = Outside.service(this,"gus06.feature.force.p");
		performR = Outside.service(this,"gus06.feature.force.r");
		performT = Outside.service(this,"gus06.feature.force.t");
		performV = Outside.service(this,"gus06.feature.force.v");
	}
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof E) return performE.t(obj);
		if(obj instanceof F) return performF.t(obj);
		if(obj instanceof G) return performG.t(obj);
		if(obj instanceof H) return performH.t(obj);
		if(obj instanceof I) return performI.t(obj);
		if(obj instanceof P) return performP.t(obj);
		if(obj instanceof R) return performR.t(obj);
		if(obj instanceof T) return performT.t(obj);
		if(obj instanceof V) return performV.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
