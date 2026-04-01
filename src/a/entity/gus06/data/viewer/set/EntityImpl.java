package a.entity.gus06.data.viewer.set;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;


public class EntityImpl implements Entity, I, P, G {

	public String creationDate() {return "20140731";}


	private Service listViewer;
	private Service tryAndSort;
	
	private Set data;
	
	public EntityImpl() throws Exception
	{
		listViewer = Outside.service(this,"*gus06.data.viewer.list");
		tryAndSort = Outside.service(this,"gus06.data.list.tryandsort");
	}
	
	
	public Object g() throws Exception
	{return data;}
	
	
	public Object i() throws Exception
	{return listViewer.i();}
	
	
	public void p(Object obj) throws Exception
	{
		data = (Set) obj;
		if(data==null) listViewer.p(null);
		else
		{
			List l = new ArrayList(data);
			tryAndSort.p(l);
			listViewer.p(l);
		}
	}
}
