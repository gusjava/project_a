package a.entity.gus06.appli.gusexplorer.data.manager;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class EntityImpl extends S1 implements Entity, P, G, V, R {

	public String creationDate() {return "20141208";}
	

	private Service listPersister;
	private Service op_add;
	private Service op_clear;
	private Service op_exchange;
	private Service op_init;
	private Service op_keep;
	private Service op_modify;
	private Service op_permute;
	private Service op_reload;
	private Service op_remove;
	
	private Map lastModification;
	private List list;
	


	public EntityImpl() throws Exception
	{
		listPersister = Outside.service(this,"gus06.app.persister1.data.filelist");
		op_add = Outside.service(this,"gus06.appli.gusexplorer.data.manager.op.add");
		op_clear = Outside.service(this,"gus06.appli.gusexplorer.data.manager.op.clear");
		op_exchange = Outside.service(this,"gus06.appli.gusexplorer.data.manager.op.exchange");
		op_init = Outside.service(this,"gus06.appli.gusexplorer.data.manager.op.init");
		op_keep = Outside.service(this,"gus06.appli.gusexplorer.data.manager.op.keep");
		op_modify = Outside.service(this,"gus06.appli.gusexplorer.data.manager.op.modify");
		op_permute = Outside.service(this,"gus06.appli.gusexplorer.data.manager.op.permute");
		op_reload = Outside.service(this,"gus06.appli.gusexplorer.data.manager.op.reload");
		op_remove = Outside.service(this,"gus06.appli.gusexplorer.data.manager.op.remove");
		
		list = (List) listPersister.r(getClass().getName());
	}
	
	
	
	public Object g() throws Exception
	{return new ArrayList(list());}
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("add"))		{add(obj);return;}
		if(key.equals("clear"))		{clear();return;}
		if(key.equals("exchange"))	{exchange((File[]) obj);return;}
		if(key.equals("init"))		{init((List) obj);return;}
		if(key.equals("keep"))		{keep(obj);return;}
		if(key.equals("modify"))	{modify((File[]) obj);return;}
		if(key.equals("permute"))	{permute((File[]) obj);return;}
		if(key.equals("reload"))	{reload((File) obj);return;}
		if(key.equals("remove"))	{remove(obj);return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("lastModification")) return lastModification;
		if(key.equals("keys")) return new String[]{"lastModification"};
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	public void p(Object obj) throws Exception
	{add(obj);}
	
	
	
	
	
	private void add(Object obj) throws Exception
	{perform(op_add,obj);}
	
	private void clear() throws Exception
	{perform(op_clear,null);}
	
	private void keep(Object obj) throws Exception
	{perform(op_keep,obj);}
	
	private void init(List l0) throws Exception
	{perform(op_init,l0);}
	
	private void exchange(File[] f) throws Exception
	{perform(op_exchange,f);}
	
	private void permute(File[] f) throws Exception
	{perform(op_permute,f);}
	
	private void modify(File[] f) throws Exception
	{perform(op_modify,f);}
	
	private void reload(File f) throws Exception
	{perform(op_reload,f);}
	
	private void remove(Object obj) throws Exception
	{perform(op_remove,obj);}
	
	
	
	
	private void perform(T op, Object data) throws Exception
	{
		lastModification = (Map) op.t(new Object[]{list(),data});
		if(lastModification==null) return;
		
		save();
		modified();
	}
	
	
	private void save() throws Exception
	{listPersister.v(getClass().getName(),list);}
	
	
	private List list() throws Exception
	{return list;}
	
	
	private void modified()
	{send(this,"modified()");}
}
