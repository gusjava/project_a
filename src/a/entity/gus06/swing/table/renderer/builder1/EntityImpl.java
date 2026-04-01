package a.entity.gus06.swing.table.renderer.builder1;

import a.framework.*;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191113";}


	private Service fromMap;
	private Service fromList;
	private Service fromComp;
	private Service fromString;
	private Service fromT;
	private Service fromG;
	
	
	public EntityImpl() throws Exception
	{
		fromMap = Outside.service(this,"gus06.swing.table.renderer.builder1.map");
		fromList = Outside.service(this,"gus06.swing.table.renderer.builder1.list");
		fromComp = Outside.service(this,"gus06.swing.table.renderer.builder1.comp");
		fromString = Outside.service(this,"gus06.swing.table.renderer.builder1.string");
		fromT = Outside.service(this,"gus06.swing.table.renderer.builder1.t");
		fromG = Outside.service(this,"gus06.swing.table.renderer.builder1.g");
	}


	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof TableCellRenderer) return obj;
		if(obj instanceof Map) return fromMap.t(obj);
		if(obj instanceof List) return fromList.t(obj);
		if(obj instanceof Component) return fromComp.t(obj);
		if(obj instanceof String) return fromString.t(obj);
		if(obj instanceof Number) return fromString.t(obj);
		if(obj instanceof Boolean) return fromString.t(obj);
		if(obj instanceof T) return fromT.t(obj);
		if(obj instanceof G) return fromG.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
