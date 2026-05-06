package a.entity.gus06.java.srccode.extract.entity.structure1;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140819";}


	public static final String ST_PACKAGE = "package a.";
	public static final String ST_IMPORT = "import ";
	public static final String ST_CLASSHEADER = "public class EntityImpl ";
	public static final String ST_CREATIONDATE = "public String creationDate()";
	public static final String ST_CONSTRUCTOR = "public EntityImpl()";
	
	public static final String ST_CONST = "public static final String ";
	public static final String ST_VAR_SERVICE = "private Service ";
	public static final String CO_INIT_SERVICE = "Outside.service(this,\"";
	
	public static final String ST_FEATURE_E = "public void e() throws Exception";
	public static final String ST_FEATURE_P = "public void p(Object obj) throws Exception";
	public static final String ST_FEATURE_G = "public Object g() throws Exception";
	public static final String ST_FEATURE_V = "public void v(String key, Object obj) throws Exception";
	public static final String ST_FEATURE_R = "public Object r(String key) throws Exception";
	public static final String ST_FEATURE_T = "public Object t(Object obj) throws Exception";
	public static final String ST_FEATURE_F = "public boolean f(Object obj) throws Exception";
	public static final String ST_FEATURE_H = "public double h(double value) throws Exception";
	public static final String ST_FEATURE_I = "public Object i() throws Exception";
	public static final String ST_FEATURE_X = "public void run()";
	
	
	
	private Service toArray;

	public EntityImpl() throws Exception
	{toArray = Outside.service(this,"gus.x.java.srccode.toarray");}
	
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		String[] lines = (String[]) toArray.t(obj);
		
		int _package = -1;
		int _import_first = -1;
		int _import_last = -1;
		int _classHeader = -1;
		int _creationDate = -1;
		int _constructor = -1;
		int _end = -1;
		
		int _feature_e = -1;
		int _feature_p = -1;
		int _feature_g = -1;
		int _feature_v = -1;
		int _feature_r = -1;
		int _feature_t = -1;
		int _feature_f = -1;
		int _feature_h = -1;
		int _feature_i = -1;
		int _feature_x = -1;
		
		int _const_first = -1;
		int _const_last = -1;
		
		int _varService_first = -1;
		int _varService_last = -1;
		
		int _initService_first = -1;
		int _initService_last = -1;
		
		
		for(int i=0;i<lines.length;i++)
		{
			String line = lines[i];
			
			if(_package==-1)
			{
				if(is_package(line)) _package = i;
			}
			else if(_classHeader==-1)
			{
				if(is_import(line))
				{
					if(_import_first==-1) _import_first = i;
					_import_last = i;
				}
				else if(is_classHeader(line))
				{
					_classHeader = i;
				}
			}
			else
			{
				if(is_creationDate(line))
					_creationDate = check(_creationDate,i,"Creation date method");
				else if(is_constructor(line))
					_constructor = check(_constructor,i,"Entity constructor");
					
				else if(is_feature_e(line))
					_feature_e = check(_feature_e,i,"Feature E method");
				else if(is_feature_p(line))
					_feature_p = check(_feature_p,i,"Feature P method");
				else if(is_feature_g(line))
					_feature_g = check(_feature_g,i,"Feature G method");
				else if(is_feature_v(line))
					_feature_v = check(_feature_v,i,"Feature V method");
				else if(is_feature_r(line))
					_feature_r = check(_feature_r,i,"Feature R method");
				else if(is_feature_t(line))
					_feature_t = check(_feature_t,i,"Feature T method");
				else if(is_feature_f(line))
					_feature_f = check(_feature_f,i,"Feature F method");
				else if(is_feature_h(line))
					_feature_h = check(_feature_h,i,"Feature H method");
				else if(is_feature_i(line))
					_feature_i = check(_feature_i,i,"Feature I method");
				else if(is_feature_x(line))
					_feature_x = check(_feature_x,i,"Feature Runnable method");
				
				else if(is_const(line))
				{
					if(_const_first==-1) _const_first = i;
					_const_last = i;
				}
				else if(is_var_service(line))
				{
					if(_varService_first==-1) _varService_first = i;
					_varService_last = i;
				}
				else if(is_init_service(line))
				{
					if(_initService_first==-1) _initService_first = i;
					_initService_last = i;
				}
				else if(is_end(line)) _end = i;
			}
		}
		
		
		Map map = new HashMap();
		
		put(map,"package",_package);
		put(map,"classHeader",_classHeader);
		put(map,"creationDate",_creationDate);
		put(map,"constructor",_constructor);
		put(map,"end",_end);
		
		put(map,"feature_e",_feature_e);
		put(map,"feature_p",_feature_p);
		put(map,"feature_g",_feature_g);
		put(map,"feature_v",_feature_v);
		put(map,"feature_r",_feature_r);
		put(map,"feature_t",_feature_t);
		put(map,"feature_f",_feature_f);
		put(map,"feature_h",_feature_h);
		put(map,"feature_i",_feature_i);
		put(map,"feature_x",_feature_x);
		
		put(map,"import",_import_first,_import_last);
		put(map,"const",_const_first,_const_last);
		put(map,"var_service",_varService_first,_varService_last);
		put(map,"init_service",_initService_first,_initService_last);
		
		return map;
	}
	
	
	
	
	private void put(Map map, String key, int start, int end)
	{if(start!=-1 && end!=-1) map.put(key,new int[]{start,end});}
	
	private void put(Map map, String key, int index)
	{if(index!=-1) map.put(key,new int[]{index,index});}
	
	
	

	
	private int check(int previous, int next, String element) throws Exception
	{
		if(previous!=-1) throw new Exception(element+" has been found many times");
		return next;
	}
	
	
	
	
	
	private boolean is_package(String line)
	{return line.startsWith(ST_PACKAGE);}

	private boolean is_import(String line)
	{return line.startsWith(ST_IMPORT);}

	private boolean is_classHeader(String line)
	{return line.startsWith(ST_CLASSHEADER);}

	private boolean is_creationDate(String line)
	{return line.startsWith(ST_CREATIONDATE);}

	private boolean is_constructor(String line)
	{return line.startsWith(ST_CONSTRUCTOR);}



	private boolean is_const(String line)
	{return line.startsWith(ST_CONST);}

	private boolean is_var_service(String line)
	{return line.startsWith(ST_VAR_SERVICE);}

	private boolean is_init_service(String line)
	{return line.contains(CO_INIT_SERVICE);}




	private boolean is_feature_e(String line)
	{return line.startsWith(ST_FEATURE_E);}

	private boolean is_feature_p(String line)
	{return line.startsWith(ST_FEATURE_P);}

	private boolean is_feature_g(String line)
	{return line.startsWith(ST_FEATURE_G);}

	private boolean is_feature_v(String line)
	{return line.startsWith(ST_FEATURE_V);}

	private boolean is_feature_r(String line)
	{return line.startsWith(ST_FEATURE_R);}

	private boolean is_feature_t(String line)
	{return line.startsWith(ST_FEATURE_T);}

	private boolean is_feature_f(String line)
	{return line.startsWith(ST_FEATURE_F);}

	private boolean is_feature_h(String line)
	{return line.startsWith(ST_FEATURE_H);}

	private boolean is_feature_i(String line)
	{return line.startsWith(ST_FEATURE_I);}

	private boolean is_feature_x(String line)
	{return line.startsWith(ST_FEATURE_X);}

	private boolean is_end(String line)
	{return line.trim().equals("}");}
}