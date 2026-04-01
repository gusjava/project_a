package a.entity.gus06.java.srccode.generate.classgenerator1;

import a.framework.*;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170303";}
	
	public static final String KEY_NAME = "name";
	public static final String KEY_PACKAGE = "package";
	public static final String KEY_IMPORTS = "imports";
	public static final String KEY_CUSTOM = "custom";
	
	public static final String KEY_ABSTRACT = "abstract";
	public static final String KEY_FINAL = "final";
	public static final String KEY_CONSTRUCTOR = "constructor";
	
	public static final String KEY_EXTENDS = "extends";
	public static final String KEY_IMPLEMENTS = "implements";
	
	public static final String KEY_FIELDS = "fields";
	
	public static final String KEY_FIELDSCOPE = "fieldscope";
	public static final String KEY_PARAM = "param";
	public static final String KEY_GETTER = "getter";
	public static final String KEY_SETTER = "setter";
	public static final String KEY_HAS = "has";
	
	

//	autoimplements: <boolean> (default=false)
//	initfield: definition|constructor|no (default=no)
//	initfield.<field>: definition|constructor|no (default=initfield)
//	defaultvalue.<field>: null|true|false|new|"<string>"|number (default=null)
//	methods: {set definitions}




	private Service nameToPackage;
	
	public EntityImpl() throws Exception
	{
		nameToPackage = Outside.service(this,"gus06.java.classname.topackage.usual");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		String name = (String) get1(map,KEY_NAME);
		String package1 = (String) get1(map,KEY_PACKAGE);
		String extends1 = (String) get(map,KEY_EXTENDS);
		String custom = (String) get(map,KEY_CUSTOM);
		
		boolean abstract1 = getBool(map,KEY_ABSTRACT,false);
		boolean final1 = getBool(map,KEY_FINAL,false);
		boolean constructor = getBool(map,KEY_CONSTRUCTOR,false);
		boolean param = getBool(map,KEY_PARAM,false);
		boolean getter = getBool(map,KEY_GETTER,true);
		boolean setter = getBool(map,KEY_SETTER,true);
		boolean has = getBool(map,KEY_HAS,false);
		
		Set imports1 = toSet(get(map,KEY_IMPORTS));
		Set implements1 = toSet(get(map,KEY_IMPLEMENTS));
		List fields1 = toList(get(map,KEY_FIELDS));
		String fieldScope = getString(map,KEY_FIELDSCOPE,"private");
		
		
		if(final1 && abstract1) throw new Exception("A class cannot be final and abstract");
		
		
		
		String[] extendsClassInfo = pathToInfos(extends1);
		String extendsPkName = extendsClassInfo[0];
		String extendsClName = extendsClassInfo[1];
		
		if(extendsPkName!=null) imports1.add(infosToPath(extendsClassInfo));
		
		
		List implList = new ArrayList();
		Iterator it = implements1.iterator();
		while(it.hasNext())
		{
			String impl = (String) it.next();
			String[] implInfo = pathToInfos(impl);
			String implPkName = implInfo[0];
			String implClName = implInfo[1];
			
			if(implPkName!=null && !implPkName.equals(package1))
				imports1.add(infosToPath(implInfo));
			
			implList.add(implClName);
		}
		
		
		List fieldList = new ArrayList();
		for(int i=0;i<fields1.size();i++)
		{
			String[] info = getFieldInfo(fields1.get(i));
			
			String fieldName = info[0];
			String fieldClass = info.length>1?info[1]:"String";
			String fieldDefault = info.length>2?info[2]:null;
			
			String[] fieldClassInfo = pathToInfos(fieldClass);
			String fieldPkName = fieldClassInfo[0];
			String fieldClName = fieldClassInfo[1];
			
			if(fieldDefault!=null && fieldDefault.equals("new"))
				fieldDefault = "new "+fieldClName+"()";
			
			if(fieldPkName!=null && !fieldPkName.equals(package1))
				imports1.add(infosToPath(fieldClassInfo));
				
			fieldList.add(new String[]{fieldClName,fieldName,fieldDefault});
		}
		
		
		
		List importList = new ArrayList(imports1);
		Collections.sort(importList);
		
		
		
		StringBuffer b = new StringBuffer();
		
		pp(b,"package "+package1+";");
		n(b);
		
		for(int i=0;i<importList.size();i++)
		{
			String import_ = (String) importList.get(i);
			if(!import_.startsWith("java.lang."))
			pp(b,"import "+import_+";");
		}
		n(b);
		
		p(b,"public ");
		if(final1) p(b,"final ");
		else if(abstract1) p(b,"abstract ");
		
		p(b,"class "+name+" ");
		if(extendsClName!=null) p(b,"extends "+extendsClName+" ");
		
		int implNb = implList.size();
		if(implNb>0)
		{
			p(b,"implements ");
			for(int i=0;i<implNb;i++)
			{
				p(b,""+implList.get(i));
				if(i<implNb-1) p(b,", ");
			}
			p(b," ");
		}
		pp(b,"{");
		n(b);
		
		
		for(int i=0;i<fieldList.size();i++)
		{
			String[] info = (String[]) fieldList.get(i);
			String clName = info[0];
			String fieldName = info[1];
			String fieldDefault = info[2];
			
			String scope = getString(map,"scope."+fieldName,fieldScope);
			checkValidScope(scope);
			
			p(b,"\t"+scope+" "+clName+" "+fieldName);
			if(fieldDefault!=null) p(b," = "+fieldDefault);
			pp(b,";");
		}
		n(b);
		
		
		if(constructor)
		{
			List fieldList1 = new ArrayList();
			for(int i=0;i<fieldList.size();i++)
			{
				String[] info = (String[]) fieldList.get(i);
				String fieldName = info[1];
				boolean param0 = getBool(map,"param_"+fieldName,param);
				if(param0) fieldList1.add(info);
			}
		
			p(b,"\tpublic "+name+"(");
			
			for(int i=0;i<fieldList1.size();i++)
			{
				String[] info = (String[]) fieldList.get(i);
				String clName = info[0];
				String fieldName = info[1];
			
				p(b,clName+" "+fieldName);
				if(i<fieldList1.size()-1) p(b,", ");
			}
			
			pp(b,") {");
			
			for(int i=0;i<fieldList1.size();i++)
			{
				String[] info = (String[]) fieldList.get(i);
				String fieldName = info[1];
				pp(b,"\t\tthis."+fieldName+" = "+fieldName+";");
			}
			
			pp(b,"\t}");
		}
		n(b);
		
		
		for(int i=0;i<fieldList.size();i++)
		{
			String[] info = (String[]) fieldList.get(i);
			String clName = info[0];
			String fieldName = info[1];
			
			boolean getter0 = getBool(map,"getter_"+fieldName,getter);
			boolean setter0 = getBool(map,"setter_"+fieldName,setter);
			boolean has0 = getBool(map,"has_"+fieldName,has);
			
			if(getter0) printGetter(b,clName,fieldName);
			if(setter0) printSetter(b,clName,fieldName);
			if(has0) printHas(b,clName,fieldName);
			
			if(getter0 || setter0 || has0) n(b);
		}
		
		n(b);
		if(custom!=null) pp(b,custom);
		
		p(b,"}");
		
		return b.toString();
	}
	
	
	private void p(StringBuffer b, String s)
	{b.append(s);}
	
	private void pp(StringBuffer b, String s)
	{b.append(s+"\n");}
	
	private void n(StringBuffer b)
	{b.append("\n");}
	
	
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
	
	private Object get1(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found: "+key);
		return map.get(key);
	}
	

	
	private Object get(Map map, String key, Object defaultValue)
	{
		if(!map.containsKey(key)) return defaultValue;
		return map.get(key);
	}
	
	private String getString(Map map, String key, String defaultValue)
	{
		if(!map.containsKey(key)) return defaultValue;
		return (String) map.get(key);
	}
	
	private boolean getBool(Map map, String key, boolean defaultValue)
	{
		if(!map.containsKey(key)) return defaultValue;
		return ((Boolean) map.get(key)).booleanValue();
	}
	
	private Set toSet(Object obj)
	{
		if(obj==null) return new HashSet();
		return new HashSet((Set) obj);
	}
	
	private List toList(Object obj)
	{
		if(obj==null) return new ArrayList();
		return new ArrayList((List) obj);
	}
	
	
	private String[] pathToInfos(String path) throws Exception
	{
		if(path==null) return new String[]{null,null};
		
		String[] nn = path.split("\\.");
		if(nn.length==1) return new String[]{findPackage(path),path};
		
		String name = nn[nn.length-1];
		String package1 = path.substring(0,path.length()-name.length()-1);
		return new String[]{package1,name};
	}
	
	private String infosToPath(String[] infos)
	{
		if(infos[0]==null || infos[1]==null) return null;
		return infos[0]+"."+infos[1];
	}
	
	
	private String[] getFieldInfo(Object field) throws Exception
	{
		if(field instanceof String[]) return (String[]) field;
		if(field instanceof String) return ((String) field).split("[\t ]+",3);
		throw new Exception("Invalid data type: "+field.getClass().getName());
	}
	
	private String findPackage(String clName) throws Exception
	{
		return (String) nameToPackage.t(clName);
	}
	
	private String titled(String name)
	{
		String first = name.substring(0,1).toUpperCase();
		if(name.length()==1) return first;
		return first+name.substring(1);
	}
	
	
	
	
	
	
	private void printSetter(StringBuffer b, String type, String name)
	{
		String titled = titled(name);
		
		pp(b,"\tpublic void set"+titled+"("+type+" "+name+") {");
		pp(b,"\t\tthis."+name+" = "+name+";");
		pp(b,"\t}");
	}
	
	private void printGetter(StringBuffer b, String type, String name)
	{
		String k = type.equals("boolean") ? "is" : "get";
		String titled = titled(name);
		
		pp(b,"\tpublic "+type+" "+k+titled+"() {");
		pp(b,"\t\treturn "+name+";");
		pp(b,"\t}");
	}
	
	private void printHas(StringBuffer b, String type, String name)
	{
		if(!Character.isUpperCase(type.charAt(0))) return;
		String titled = titled(name);
		
		pp(b,"\tpublic boolean has"+titled+"() {");
		pp(b,"\t\treturn "+name+"!=null;");
		pp(b,"\t}");
	}
	
	
	private void checkValidScope(String scope) throws Exception
	{
		if(scope.equals("private") || scope.equals("protected") || scope.equals("public")) return;
		throw new Exception("Invalid scope value: "+scope);
	}
}
