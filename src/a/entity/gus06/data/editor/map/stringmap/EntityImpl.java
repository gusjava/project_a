package a.entity.gus06.data.editor.map.stringmap;

import a.framework.*;

import java.util.Map;
import java.util.HashMap;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl extends S1 implements Entity, I, P, G {

	public String creationDate() {return "20150329";}

	
	private Service editor;
	
	private Map map;
	private boolean loading;
	

	public EntityImpl() throws Exception
	{
		editor = Outside.service(this,"*gus06.map.string.editor1");
		
		map = (Map) Outside.resource(this,"supportmap");
		((S) map).addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{if(!loading) edited();}
		});
		loading = false;
		editor.p(map);
	}
	
	
	private void edited()
	{send(this,"edited()");}
	
	
	
	public Object i() throws Exception
	{return editor.i();}
	
	
	
	public void p(Object obj) throws Exception
	{
		Map m = (Map) obj;
		loading = true;
		map.clear();
		if(m!=null) map.putAll(m);
		loading = false;
	}
	
	
	public Object g() throws Exception
	{return new HashMap(map);}
}
