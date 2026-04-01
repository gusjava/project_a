package a.entity.gus06.sys.expression1.apply.op._progress_popup;

import a.framework.*;
import java.awt.Dimension;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180310";}
	
	public static final Dimension DIM = new Dimension(500,80);
	public static final String TITLE = "Progression";

	
	private Service opProgress;
	private Service show;
	
	public EntityImpl() throws Exception
	{
		opProgress = Outside.service(this,"gus06.sys.expression1.apply.op._progress");
		show = Outside.service(this,"gus06.swing.frame.show2");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		Object value = o[0];
		
		if(value==null) return null;
		
		Object progress = opProgress.t(obj);
		show.p(new Object[]{progress,DIM,TITLE});
		
		return progress;
	}
}
