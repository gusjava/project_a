package a.entity.gus06.sys.xhtml1.search.gui.panel;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, I, P, E {

	public String creationDate() {return "20221015";}

	
	private Service dataToFileList;
	private Service searchGui;
	
	private Map data;
	
	public EntityImpl() throws Exception
	{
		dataToFileList = Outside.service(this,"gus06.sys.xhtml1.tool.datamap.to.filelist");
		searchGui = Outside.service(this,"*gus06.sys.dirsearch1.gui.maingui1");
	}
	
	
	public Object i() throws Exception
	{return searchGui.i();}
	
	
	
	public Object g() throws Exception
	{return null;}
	
	
	public void e() throws Exception
	{}
	
	
	public void p(Object obj) throws Exception
	{
		data = (Map) obj;
		if(data==null) return;
		
		List files = (List) dataToFileList.t(data);
		searchGui.v("roots", files);
	}
}