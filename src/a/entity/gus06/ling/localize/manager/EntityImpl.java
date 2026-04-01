package a.entity.gus06.ling.localize.manager;

import a.framework.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Iterator;


public class EntityImpl implements Entity, V, G, ActionListener {

	public String creationDate() {return "20140808";}


	private Service builder;
	private Service langManager;
	
	private HashSet set;

	
	public EntityImpl() throws Exception
	{
		builder = Outside.service(this,"gus06.ling.localize.builder");
		langManager = Outside.service(this,"gus06.ling.language.manager");
		
		set = new HashSet();
		langManager.addActionListener(this);
	}
	
	
	public Object g() throws Exception
	{return set;}
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		Object holder = builder.t(obj);
		((P) holder).p(key);
		((E) holder).e();
		
		set.add(holder);
	}
	
	
	
	public void actionPerformed(ActionEvent e)
	{languageChanged();}
	
	
	
	private void languageChanged()
	{
		try
		{
			Iterator it = set.iterator();
			while(it.hasNext()) execute((E) it.next());
		}
		catch(Exception e)
		{Outside.err(this,"languageChanged()",e);}
	}
	
	
	private void execute(E e)
	{
		try{e.e();}
		catch(Exception ex)
		{Outside.err(this,"execute(E)",ex);}
	}
}
