package a.entity.gus06.file.editor.ext.vue;

import a.framework.*;

public class EntityImpl implements Entity, I, P, G, R, V {

	public String creationDate() {return "20220421";}

	private Service tab;
	private Service gui1;
	private Service gui2;
	private Service gui3;

	public EntityImpl() throws Exception
	{
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		gui1 = Outside.service(this,"*gus06.file.editor.ext.vue.tab1");
		gui2 = Outside.service(this,"*gus06.file.editor.ext.vue.tab2");
		gui3 = Outside.service(this,"*gus06.file.editor.ext.vue.tab3");
		
		tab.v("Source",gui1.i());
		tab.v("Script",gui2.i());
		tab.v("Template",gui3.i());
		
		Object comp = gui1.r("comp");
		gui2.v("comp", comp);
		gui3.v("comp", comp);
	}
	
	public Object i() throws Exception
	{return tab.i();}
	
	public Object g() throws Exception
	{return gui1.g();}
	
	public Object r(String key) throws Exception
	{return gui1.r(key);}
	
	public void v(String key, Object obj) throws Exception
	{gui1.v(key, obj);}
	
	
	public void p(Object obj) throws Exception
	{
		gui1.p(obj);
		gui2.p(obj);
		gui3.p(obj);
	}
}