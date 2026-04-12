package a.entity.gus06.framework.sources;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, G, R {
	public String creationDate() {return "20251129";}
	
	public static final String D = "\n___________________________\n";
	
	public static final String IMPL_E = "package gus06.framework;\n\npublic interface E {\n\tpublic void e() throws Exception;\n}";
	public static final String IMPL_G = "package gus06.framework;\n\npublic interface G {\n\tpublic Object g() throws Exception;\n}";
	public static final String IMPL_P = "package gus06.framework;\n\npublic interface P {\n\tpublic void p(Object obj) throws Exception;\n}";
	public static final String IMPL_T = "package gus06.framework;\n\npublic interface T {\n\tpublic Object t(Object obj) throws Exception;\n}";
	public static final String IMPL_F = "package gus06.framework;\n\npublic interface F {\n\tpublic boolean f(Object obj) throws Exception;\n}";
	public static final String IMPL_R = "package gus06.framework;\n\npublic interface R {\n\tpublic Object r(String key) throws Exception;\n}";
	public static final String IMPL_V = "package gus06.framework;\n\npublic interface V {\n\tpublic void v(String key, Object obj) throws Exception;\n}";
	public static final String IMPL_S = "package gus06.framework;\n\nimport java.awt.event.ActionListener;\nimport java.util.List;\n\npublic interface S {\n\tpublic void addActionListener(ActionListener listener);\n\tpublic void removeActionListener(ActionListener listener);\n\tpublic List listeners();\n}";
	public static final String IMPL_H = "package gus06.framework;\n\npublic interface H {\n\tpublic double h(double value) throws Exception;\n}";
	public static final String IMPL_I = "package gus06.framework;\n\npublic interface I {\n\tpublic Object i() throws Exception;\n}";
	
	public static final String IMPL_SERVICE = "package gus06.framework;\n\npublic interface Service extends Runnable, E, F, G, H, I, P, R, S, T, V {}";
	public static final String IMPL_ENTITY = "package gus06.framework;\n\npublic interface Entity {\n\tpublic String creationDate();\n}";
	public static final String IMPL_MANAGER = "package gus06.framework;\n\npublic interface Manager {\n\tpublic Service callService(Entity entity, String id) throws Exception; \n\tpublic Object callResource(Entity entity, String id) throws Exception;\n\tpublic void sendError(Entity entity, String id, Exception e);\n}";
	public static final String IMPL_OUTSIDE = "package gus06.framework;\n\npublic final class Outside {\n\n\tprivate static Manager manager;\n\t\n\tpublic static void setManager(Manager manager0)\n\t{if(manager==null) manager = manager0;}\n\t \n\tpublic static Service service(Entity entity, String id) throws Exception\n\t{return manager.callService(entity,id);}\n\t\n\tpublic static Object resource(Entity entity, String id) throws Exception\n\t{return manager.callResource(entity,id);}\n\t\n\tpublic static void err(Entity entity, String id, Exception e)\n\t{manager.sendError(entity,id,e);}\n}";
	public static final String IMPL_S1 = "package gus06.framework;\n\nimport java.util.*;\nimport java.awt.event.*;\n\npublic class S1 implements S {\n\n\tpublic final static int EVENTID = 1000;\n\t\n\tprivate Vector listeners = new Vector();\n\tprivate boolean activated = true;\n\t\n\tpublic void addActionListener(ActionListener listener)\n\t{listeners.add(listener);}\n\t\n\tpublic void removeActionListener(ActionListener listener)\n\t{listeners.remove(listener);}\n\t\n\tpublic List listeners() {return listeners;}\n\t\n\tprivate void fireActionEvent(Object source, String id)\n\t{ \n\t\tif(source==null) source = this;\n\t\tActionEvent evt = new ActionEvent(source,EVENTID,id);\n\t\tfor(int i=0;i<listeners.size();i++)\n\t\t((ActionListener) listeners.get(i)).actionPerformed(evt);\n\t}\n\t\n\tpublic void setActivated(boolean value)\n\t{activated = value;}\n\t\n\tpublic boolean isActivated()\n\t{return activated;}\n\n\tpublic void send(Object source, String id)\n\t{if(activated) fireActionEvent(source,id);}\n}";
	
	private Map map;
	
	public EntityImpl() throws Exception
	{
		map = new HashMap();
		map.put("E",IMPL_E);
		map.put("G",IMPL_G);
		map.put("P",IMPL_P);
		map.put("T",IMPL_T);
		map.put("F",IMPL_F);
		map.put("R",IMPL_R);
		map.put("V",IMPL_V);
		map.put("S",IMPL_S);
		map.put("H",IMPL_H);
		map.put("I",IMPL_I);
		
		map.put("SERVICE",IMPL_SERVICE);
		map.put("ENTITY",IMPL_ENTITY);
		map.put("MANAGER",IMPL_MANAGER);
		map.put("OUTSIDE",IMPL_OUTSIDE);
		map.put("S1",IMPL_S1);
	}

	
	
	public Object g() throws Exception
	{return map;}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("*")) return all();
	
		if(key.equals("E")) return IMPL_E;
		if(key.equals("G")) return IMPL_G;
		if(key.equals("P")) return IMPL_P;
		if(key.equals("T")) return IMPL_T;
		if(key.equals("F")) return IMPL_F;
		if(key.equals("R")) return IMPL_R;
		if(key.equals("V")) return IMPL_V;
		if(key.equals("S")) return IMPL_S;
		if(key.equals("H")) return IMPL_H;
		if(key.equals("I")) return IMPL_I;
		
		if(key.equals("SERVICE")) return IMPL_SERVICE;
		if(key.equals("ENTITY")) return IMPL_ENTITY;
		if(key.equals("MANAGER")) return IMPL_MANAGER;
		if(key.equals("OUTSIDE")) return IMPL_OUTSIDE;
		if(key.equals("S1")) return IMPL_S1;
		
		if(key.equals("keys")) return new String[]{
			"E", "G", "P", "T", "F", "R", "V", "S", "H", "I", 
			"SERVICE", "ENTITY", "MANAGER", "OUTSIDE", "S1"
		};
		throw new Exception("Unknown key: "+key);
	}
	
	private String all()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append(IMPL_E+D);
		sb.append(IMPL_G+D);
		sb.append(IMPL_P+D);
		sb.append(IMPL_T+D);
		sb.append(IMPL_F+D);
		sb.append(IMPL_R+D);
		sb.append(IMPL_V+D);
		sb.append(IMPL_S+D);
		sb.append(IMPL_H+D);
		sb.append(IMPL_I+D);
		
		sb.append(IMPL_SERVICE+D);
		sb.append(IMPL_ENTITY+D);
		sb.append(IMPL_MANAGER+D);
		sb.append(IMPL_OUTSIDE+D);
		sb.append(IMPL_S1+D);
		
		return sb.toString();
	}
}
