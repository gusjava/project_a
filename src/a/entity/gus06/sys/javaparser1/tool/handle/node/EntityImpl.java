package a.entity.gus06.sys.javaparser1.tool.handle.node;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import com.github.javaparser.*;
import com.github.javaparser.ast.*;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.nodeTypes.*;
import com.github.javaparser.ast.type.*;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.body.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180225";}
	
	public static final String KEY_CHILDREN = "children";
	public static final String KEY_TYPE = "type";
	public static final String KEY_NAME = "name";
	public static final String KEY_RANGE = "range";
	public static final String KEY_COMMENT = "comment";
	public static final String KEY_MODIFIERS = "modifiers";
	public static final String KEY_EXTENDS = "extends";
	public static final String KEY_IMPLEMENTS = "implements";
	public static final String KEY_ANNOTATIONS = "annotations";
	public static final String KEY_CLASS = "class";
	public static final String KEY_INTERFACE = "interface";
	public static final String KEY_PACKAGE = "package";
	public static final String KEY_IMPORTS = "imports";
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Node node = (Node) o[0];
		Map map = (Map) o[1];
		
		handleNode(node,map);
	}
	
	
	
	private void handleNode(Node node, Map map) throws Exception
	{
		put(map,KEY_CHILDREN,		buildChildren(node));
//		put(map,KEY_TYPE,		buildType(node));
//		put(map,KEY_NAME,		buildName(node));
//		put(map,KEY_COMMENT,		buildComment(node));
//		put(map,KEY_RANGE,		buildRange(node));
//		put(map,KEY_MODIFIERS,		buildModifierSet(node));
//		put(map,KEY_EXTENDS,		buildExtendsList(node));
//		put(map,KEY_IMPLEMENTS,		buildImplementsList(node));
//		put(map,KEY_CLASS,		buildClass(node));
//		put(map,KEY_INTERFACE,		buildInterface(node));
//		put(map,KEY_PACKAGE,		buildPackage(node));
//		put(map,KEY_IMPORTS,		buildImports(node));
	}
	
	
	
	private List buildChildren(Node node) throws Exception
	{
		List children = new ArrayList();
		List<Node> children1 = node.getChildNodes();
		for(Node child1 : children1) if(!ignoreAsChild(child1))
		{
			Map m = new HashMap();
			handleNode(node,m);
			children.add(m);
		}
		return children.isEmpty() ? null : children;
	}
	
	
	
	
	private String buildType(Node node)
	{
		return node.getClass().getSimpleName();
	}
	
	private String buildName(Node node)
	{
		if(!(node instanceof NodeWithName)) return null;
		return ((NodeWithName) node).getNameAsString();
	}
	
	private String buildComment(Node node)
	{
		Comment c = node.getComment().orElse(null);
		return c!=null ? c.toString() : null;
	}
	
	private int[] buildRange(Node node)
	{
		Range range = node.getRange().orElse(null);
		if(range==null) return null;
		
		int x1 = range.begin.line;
		int y1 = range.begin.column;
		
		int x2 = range.end.line;
		int y2 = range.end.column;
		
		return new int[]{x1,y1,x2,y2};
	}
	
	private Set buildModifierSet(Node node)
	{
		if(!(node instanceof NodeWithModifiers)) return null;
		NodeWithModifiers nwm = (NodeWithModifiers) node;
		Set set = new HashSet();
		Iterator<Modifier> it = nwm.getModifiers().iterator();
		while(it.hasNext())
		{
			Modifier m = it.next();
			set.add(m.getKeyword().name().toLowerCase());
		}
		return set;
	}
	
	private List buildExtendsList(Node node) throws Exception
	{
		if(!(node instanceof NodeWithExtends)) return null;
		NodeWithExtends nwe = (NodeWithExtends) node;
		List list = new ArrayList();
		Iterator<ClassOrInterfaceType> it = nwe.getExtendedTypes().iterator();
		while(it.hasNext())
		{
			ClassOrInterfaceType cit = it.next();
			Map m = new HashMap();
			handleNode(cit,m);
			list.add(m);
		}
		return list.isEmpty() ? null : list;
	}
	
	private List buildImplementsList(Node node) throws Exception
	{
		if(!(node instanceof NodeWithImplements)) return null;
		NodeWithImplements nwi = (NodeWithImplements) node;
		List list = new ArrayList();
		Iterator<ClassOrInterfaceType> it = nwi.getImplementedTypes().iterator();
		while(it.hasNext())
		{
			ClassOrInterfaceType cit = it.next();
			Map m = new HashMap();
			handleNode(cit,m);
			list.add(m);
		}
		return list.isEmpty() ? null : list;
	}
	
	private List buildAnnotationList(Node node) throws Exception
	{
		if(!(node instanceof NodeWithAnnotations)) return null;
		NodeWithAnnotations nwa = (NodeWithAnnotations) node;
		List list = new ArrayList();
		Iterator<AnnotationExpr> it = nwa.getAnnotations().iterator();
		while(it.hasNext())
		{
			AnnotationExpr ae = it.next();
			Map m = new HashMap();
			handleNode(ae,m);
			list.add(m);
		}
		return list.isEmpty() ? null : list;
	}
	
	private Map buildClass(Node node) throws Exception
	{
		List<Node> children = node.getChildNodes();
		for(Node child : children)
		{
			if(child instanceof ClassOrInterfaceDeclaration)
			{
				ClassOrInterfaceDeclaration cid = (ClassOrInterfaceDeclaration) child;
				if(!cid.isInterface())
				{
					Map m = new HashMap();
					handleNode(cid,m);
					return m;
				}
			}
		}
		return null;
	}
	
	private Map buildInterface(Node node) throws Exception
	{
		List<Node> children = node.getChildNodes();
		for(Node child : children)
		{
			if(child instanceof ClassOrInterfaceDeclaration)
			{
				ClassOrInterfaceDeclaration cid = (ClassOrInterfaceDeclaration) child;
				if(cid.isInterface())
				{
					Map m = new HashMap();
					handleNode(cid,m);
					return m;
				}
			}
		}
		return null;
	}
	
	private Map buildPackage(Node node) throws Exception
	{
		List<Node> children = node.getChildNodes();
		for(Node child : children)
		{
			if(child instanceof PackageDeclaration)
			{
				PackageDeclaration pd = (PackageDeclaration) child;
				Map m = new HashMap();
				handleNode(pd,m);
				return m;
			}
		}
		return null;
	}
	
	private List buildImports(Node node) throws Exception
	{
		List<Node> children = node.getChildNodes();
		List list = new ArrayList();
		for(Node child : children)
		{
			if(child instanceof ImportDeclaration)
			{
				ImportDeclaration pd = (ImportDeclaration) child;
				Map m = new HashMap();
				handleNode(pd,m);
				list.add(m);
			}
		}
		return list.isEmpty() ? null : list;
	}
	
	
	
	
	
	
	
	private boolean ignoreAsChild(Node node)
	{
		if(node instanceof Name) return true;
		return false;
	}
	
	public void put(Map map, String key, Object value)
	{if(value!=null) map.put(key,value);}
}
