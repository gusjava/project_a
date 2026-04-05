package a.entity.gus.y.autocomplete1.find;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260405";}

	private Service ser;
	private Service pgrid;
	private Service pborder;
	private Service list;
	private Service map;
	private Service set;
	private Service table;
	private Service millis;
	private Service th;
	private Service th1;
	private Service th2;
	private Service al;
	private Service color;
	private Service try_;
	private Service fea;
	private Service thr;
	private Service cons;
	private Service sysout;
	private Service o;
	private Service icon;
	private Service entity;

	public EntityImpl() throws Exception
	{
		ser = Outside.service(this,"gus06.swing.textcomp.autocomplete.entity.addservice");
		pgrid = Outside.service(this,"gus06.swing.textcomp.autocomplete.entity.addpanel.grid");
		pborder = Outside.service(this,"gus06.swing.textcomp.autocomplete.entity.addpanel.border");
		list = Outside.service(this,"gus06.swing.textcomp.autocomplete.entity.addlist");
		map = Outside.service(this,"gus06.swing.textcomp.autocomplete.entity.addmap");
		set = Outside.service(this,"gus06.swing.textcomp.autocomplete.entity.addset");
		table = Outside.service(this,"gus06.swing.textcomp.autocomplete.entity.addtable");
		millis = Outside.service(this,"gus06.swing.textcomp.autocomplete.entity.addmillis");
		th = Outside.service(this,"gus06.swing.textcomp.autocomplete.entity.addthrow");
		th1 = Outside.service(this,"gus06.swing.textcomp.autocomplete.entity.addthrow.invaliddatatype");
		th2 = Outside.service(this,"gus06.swing.textcomp.autocomplete.entity.addthrow.unknownkey");
		al = Outside.service(this,"gus06.swing.textcomp.autocomplete.entity.actionlistener");
		color = Outside.service(this,"gus06.sys.autocomplete1.editor1.color");
		try_ = Outside.service(this,"gus06.swing.textcomp.autocomplete.entity.addtrycatch");
		fea = Outside.service(this,"gus06.swing.textcomp.autocomplete.entity.addfeature");
		thr = Outside.service(this,"gus06.swing.textcomp.autocomplete.entity.addthrows");
		cons = Outside.service(this,"gus06.swing.textcomp.autocomplete.entity.addcons");
		sysout = Outside.service(this,"gus06.swing.textcomp.autocomplete.entity.addsysout");
		o = Outside.service(this,"gus06.swing.textcomp.autocomplete.entity.adddataarray");
		icon = Outside.service(this,"gus06.sys.autocomplete1.editor1.icon");
		entity = Outside.service(this,"gus06.sys.autocomplete1.editor1.entity");
	}
	
	public Object t(Object obj) throws Exception
	{
		String name = (String) obj;
		if(name.equals("ser")) return ser;
		if(name.equals("table")) return table;
		if(name.equals("millis")) return millis;
		if(name.equals("th")) return th;
		if(name.equals("th1")) return th1;
		if(name.equals("th2")) return th2;
		if(name.equals("al")) return al;
		if(name.equals("color")) return color;
		if(name.equals("try")) return try_;
		if(name.equals("fea")) return fea;
		if(name.equals("thr")) return thr;
		if(name.equals("cons")) return cons;
		if(name.equals("sysout")) return sysout;
		if(name.equals("o")) return o;
		if(name.equals("icon")) return icon;
		if(name.equals("entity")) return entity;
		
		if(name.equals("+pgrid")) return pgrid;
		if(name.equals("+pborder")) return pborder;
		if(name.equals("+panel")) return pborder;
		if(name.equals("+list")) return list;
		if(name.equals("+map")) return map;
		if(name.equals("+set")) return set;
		
		throw new Exception("Unknown autocomplete action: "+name);
	}
}
