package a.entity.gus06.sys.treecomparator1.gui2;

import a.framework.*;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20200109";}


	private Service viewer1;
	private Service viewer2;
	private Service viewerDiff;
	private Service tab;

	public EntityImpl() throws Exception
	{
		viewer1 = Outside.service(this,"*gus06.data.viewer.object-1");
		viewer2 = Outside.service(this,"*gus06.data.viewer.object-1");
		viewerDiff = Outside.service(this,"*gus06.sys.treecomparator1.gui1");
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
	
		tab.v("Diff",viewerDiff.i());
		tab.v("Object1",viewer1.i());
		tab.v("Object2",viewer2.i());
	}
	
	
	public Object i() throws Exception
	{return tab.i();}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null) 
		{
			viewerDiff.p(null);
			viewer1.p(null);
			viewer2.p(null);
			return;
		}
		
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		viewerDiff.p(o);
		viewer1.p(o[0]);
		viewer2.p(o[1]);
	}
}
