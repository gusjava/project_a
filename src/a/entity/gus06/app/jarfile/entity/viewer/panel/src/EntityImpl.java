package a.entity.gus06.app.jarfile.entity.viewer.panel.src;

import a.framework.*;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20140829";}

	
	private Service srcPanel;
	private Service findSrc;


	private String name;
	
	
	
	public EntityImpl() throws Exception
	{
		srcPanel = Outside.service(this,"*gus06.data.viewer.string.src.java");
		findSrc = Outside.service(this,"gus06.app.jarfile.entity.findsrc");
	}
	
	
	public Object i() throws Exception
	{return srcPanel.i();}
	
	
	
	public void p(Object obj) throws Exception
	{
		name = (String) obj;
		String src = (String) findSrc.t(name);
		srcPanel.p(src);
	}
}
