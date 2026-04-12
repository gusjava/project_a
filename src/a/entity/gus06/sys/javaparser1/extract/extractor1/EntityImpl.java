package a.entity.gus06.sys.javaparser1.extract.extractor1;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import com.github.javaparser.ast.*;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.type.*;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.javadoc.Javadoc;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180227";}
	
	public static final String METHODS = "methods";
	public static final String FIELDS = "fields";
	public static final String MODIFIERS = "modifiers";
	public static final String PACKAGE = "package";
	public static final String IMPORTS = "imports";
	public static final String STATIC_IMPORTS = "static_imports";
	public static final String NAME = "name";
	public static final String TYPE = "type";
	public static final String ANNOTATIONS = "annotations";
	public static final String JAVADOC = "javadoc";
	public static final String IMPLEMENTS = "implements";
	public static final String EXTENDS = "extends";
	public static final String PROPS = "props";


	private Service buildCU;
	private Service buildMethod;
	private Service buildField;
	private Service buildAnno;
	private Service generateProps;

	public EntityImpl() throws Exception
	{
		buildCU = Outside.service(this,"gus06.sys.javaparser1.tool.build.compilationunit");
		buildMethod = Outside.service(this,"gus06.sys.javaparser1.tool.buildinfo.method");
		buildField = Outside.service(this,"gus06.sys.javaparser1.tool.buildinfo.field");
		buildAnno = Outside.service(this,"gus06.sys.javaparser1.tool.buildinfo.annotations");
		generateProps = Outside.service(this,"gus06.sys.javaparser1.tool.generate.props");
	}

	
	public Object t(Object obj) throws Exception
	{
		CompilationUnit cu = (CompilationUnit) buildCU.t(obj);
		ClassOrInterfaceDeclaration cid = cu.findAll(ClassOrInterfaceDeclaration.class).iterator().next();
		
		Map map = new HashMap();
		
		handlePackage(map,cu);
		handleImports(map,cu);
		
		handleName(map,cid);
		handleType(map,cid);
		handleExtends(map,cid);
		handleImplements(map,cid);
		handleAnnotations(map,cid);
		handleJavadoc(map,cid);
		handleModifiers(map,cid);
		handleMethods(map,cid);
		handleFields(map,cid);
		handleProps(map);
		
		return map;
	}
	
	
	
	
	
	
	private void handlePackage(Map map, CompilationUnit cu)
	{
		PackageDeclaration pd = cu.getPackageDeclaration().orElse(null);
		String packageName = pd!=null? pd.getNameAsString() : null;
		put(map,PACKAGE,packageName);
	}
	
	private void handleImports(Map map, CompilationUnit cu)
	{
		NodeList<ImportDeclaration> nodeList = cu.getImports();
		List importList = new ArrayList();
		List importStaticList = new ArrayList();
		
		for(ImportDeclaration id : nodeList)
		{
			String name = id.getNameAsString();
			if(id.isAsterisk()) name = name+".*";
			
			if(id.isStatic()) importStaticList.add(name);
			else importList.add(name);
		}
		
		if(!importList.isEmpty())
		put(map,IMPORTS,importList);
		
		if(!importStaticList.isEmpty())
		put(map,STATIC_IMPORTS,importStaticList);
	}
	
	private void handleName(Map map, ClassOrInterfaceDeclaration cid)
	{
		put(map,NAME,cid.getNameAsString());
	}
	
	private void handleType(Map map, ClassOrInterfaceDeclaration cid)
	{
		String type = cid.isInterface() ? "interface" : "class"; //enum ?
		put(map,TYPE,type);
	}
	
	private void handleAnnotations(Map map, ClassOrInterfaceDeclaration cid) throws Exception
	{
		List annotations = (List) buildAnno.t(cid);
		if(!annotations.isEmpty()) put(map,ANNOTATIONS,annotations);
	}
	
	private void handleJavadoc(Map map, ClassOrInterfaceDeclaration cid)
	{
		Javadoc javadoc = cid.getJavadoc().orElse(null);
		if(javadoc!=null) put(map,JAVADOC,javadoc.toText());
	}
	
	private void handleMethods(Map map, ClassOrInterfaceDeclaration cid) throws Exception
	{
		List methods = new ArrayList();
		List<MethodDeclaration> list = cid.findAll(MethodDeclaration.class);
		for(MethodDeclaration md : list) methods.add(buildMethod.t(md));
		if(!methods.isEmpty()) put(map,METHODS,methods);
	}
	
	
	private void handleFields(Map map, ClassOrInterfaceDeclaration cid) throws Exception
	{
		List fields = new ArrayList();
		List<FieldDeclaration> list = cid.findAll(FieldDeclaration.class);
		for(FieldDeclaration fd : list)
		fields.add(buildField.t(fd));
		if(!fields.isEmpty()) put(map,FIELDS,fields);
	}
	
	
	private void handleModifiers(Map map, ClassOrInterfaceDeclaration cid)
	{
		List modifiers = new ArrayList();
		Iterator<Modifier> it = cid.getModifiers().iterator();
		while(it.hasNext()) modifiers.add(it.next().name().toLowerCase());
		if(!modifiers.isEmpty()) put(map,MODIFIERS,modifiers);
	}
	
	
	private void handleImplements(Map map, ClassOrInterfaceDeclaration cid)
	{
		NodeList<ClassOrInterfaceType> nodeList = cid.getImplementedTypes();
		List implementsList = new ArrayList();
		for(ClassOrInterfaceType cit : nodeList)
		implementsList.add(cit.getNameAsString());
		
		if(!implementsList.isEmpty())
		put(map,IMPLEMENTS,implementsList);
	}
	
	private void handleExtends(Map map, ClassOrInterfaceDeclaration cid)
	{
		NodeList<ClassOrInterfaceType> nodeList = cid.getExtendedTypes();
		List extendsList = new ArrayList();
		for(ClassOrInterfaceType cit : nodeList)
		extendsList.add(cit.getNameAsString());
		
		if(!extendsList.isEmpty())
		put(map,EXTENDS,extendsList);
	}
	
	
	
	private void handleProps(Map map) throws Exception
	{
		List methods = (List) get(map, METHODS);
		Map props = (Map) generateProps.t(methods);
		map.put(PROPS,props);
	}
	
	
	
	private void put(Map map, String key, Object value)
	{if(value!=null) map.put(key,value);}
	
	private Object get(Map map, String key)
	{return map.containsKey(key) ? map.get(key) : null;}
}