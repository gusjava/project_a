package a.entity.gus.x.java.srccode.ast3;

import a.framework.*;
import java.util.*;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.type.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20260418";}

	public Object t(Object obj) throws Exception
	{
		String src = (String) obj;
		CompilationUnit cu = JavaParser.parse(src);
		return buildStruct(cu);
	}

	private Map buildStruct(CompilationUnit cu)
	{
		Map map = new HashMap();
		cu.getPackageDeclaration().ifPresent(pd ->
			map.put("package", pd.getNameAsString()));
		List imports = new ArrayList();
		for (ImportDeclaration id : cu.getImports())
		{
			String name = id.getNameAsString();
			if (id.isAsterisk()) name += ".*";
			imports.add(name);
		}
		if (!imports.isEmpty()) map.put("imports", imports);
		List types = new ArrayList();
		for (TypeDeclaration<?> td : cu.getTypes())
			types.add(buildType(td));
		if (!types.isEmpty()) map.put("types", types);
		return map;
	}

	private Map buildType(TypeDeclaration<?> td)
	{
		Map map = new HashMap();
		map.put("name", td.getNameAsString());
		if (td instanceof ClassOrInterfaceDeclaration)
		{
			ClassOrInterfaceDeclaration cid = (ClassOrInterfaceDeclaration) td;
			if (cid.isInterface()) map.put("kind", "interface");
			else if (cid.isAbstract()) map.put("kind", "abstract");
			else map.put("kind", "class");
			List typeParams = buildTypeParams(cid.getTypeParameters());
			if (!typeParams.isEmpty()) map.put("typeParameters", typeParams);
			List ext = new ArrayList();
			for (ClassOrInterfaceType t : cid.getExtendedTypes())
				ext.add(t.asString());
			if (!ext.isEmpty()) map.put("extends", ext);
			List impl = new ArrayList();
			for (ClassOrInterfaceType t : cid.getImplementedTypes())
				impl.add(t.asString());
			if (!impl.isEmpty()) map.put("implements", impl);
		}
		else if (td instanceof EnumDeclaration)
		{
			map.put("kind", "enum");
			EnumDeclaration ed = (EnumDeclaration) td;
			List constants = new ArrayList();
			for (EnumConstantDeclaration ecd : ed.getEntries())
				constants.add(ecd.getNameAsString());
			if (!constants.isEmpty()) map.put("constants", constants);
		}
		else map.put("kind", "annotation");
		List mods = buildModifiers(td.getModifiers().iterator());
		if (!mods.isEmpty()) map.put("modifiers", mods);
		List annots = buildAnnotations(td.getAnnotations());
		if (!annots.isEmpty()) map.put("annotations", annots);
		List fields = new ArrayList();
		List constructors = new ArrayList();
		List methods = new ArrayList();
		List nested = new ArrayList();
		List inits = new ArrayList();
		for (BodyDeclaration<?> member : td.getMembers())
		{
			if (member instanceof FieldDeclaration)
				fields.addAll(buildFields((FieldDeclaration) member));
			else if (member instanceof ConstructorDeclaration)
				constructors.add(buildConstructor((ConstructorDeclaration) member));
			else if (member instanceof MethodDeclaration)
				methods.add(buildMethod((MethodDeclaration) member));
			else if (member instanceof TypeDeclaration)
				nested.add(buildType((TypeDeclaration<?>) member));
			else if (member instanceof InitializerDeclaration)
			{
				InitializerDeclaration init = (InitializerDeclaration) member;
				Map initMap = new HashMap();
				initMap.put("kind", init.isStatic() ? "static" : "instance");
				initMap.put("body", init.getBody().toString());
				inits.add(initMap);
			}
		}
		if (!fields.isEmpty()) map.put("fields", fields);
		if (!constructors.isEmpty()) map.put("constructors", constructors);
		if (!methods.isEmpty()) map.put("methods", methods);
		if (!nested.isEmpty()) map.put("nested", nested);
		if (!inits.isEmpty()) map.put("initializers", inits);
		return map;
	}

	private List buildFields(FieldDeclaration fd)
	{
		List result = new ArrayList();
		List mods = buildModifiers(fd.getModifiers().iterator());
		List annots = buildAnnotations(fd.getAnnotations());
		for (VariableDeclarator vd : fd.getVariables())
		{
			Map map = new HashMap();
			map.put("name", vd.getNameAsString());
			map.put("type", vd.getType().asString());
			if (!mods.isEmpty()) map.put("modifiers", mods);
			if (!annots.isEmpty()) map.put("annotations", annots);
			vd.getInitializer().ifPresent(init -> map.put("initializer", init.toString()));
			result.add(map);
		}
		return result;
	}

	private Map buildConstructor(ConstructorDeclaration cd)
	{
		Map map = new HashMap();
		map.put("name", cd.getNameAsString());
		List mods = buildModifiers(cd.getModifiers().iterator());
		if (!mods.isEmpty()) map.put("modifiers", mods);
		List annots = buildAnnotations(cd.getAnnotations());
		if (!annots.isEmpty()) map.put("annotations", annots);
		List typeParams = buildTypeParams(cd.getTypeParameters());
		if (!typeParams.isEmpty()) map.put("typeParameters", typeParams);
		List params = buildParams(cd.getParameters());
		if (!params.isEmpty()) map.put("params", params);
		List throws_ = buildThrows(cd.getThrownExceptions());
		if (!throws_.isEmpty()) map.put("throws", throws_);
		map.put("body", cd.getBody().toString());
		return map;
	}

	private Map buildMethod(MethodDeclaration md)
	{
		Map map = new HashMap();
		map.put("name", md.getNameAsString());
		String ret = md.getType().asString();
		if (!ret.equals("void")) map.put("return", ret);
		List mods = buildModifiers(md.getModifiers().iterator());
		if (!mods.isEmpty()) map.put("modifiers", mods);
		List annots = buildAnnotations(md.getAnnotations());
		if (!annots.isEmpty()) map.put("annotations", annots);
		List typeParams = buildTypeParams(md.getTypeParameters());
		if (!typeParams.isEmpty()) map.put("typeParameters", typeParams);
		List params = buildParams(md.getParameters());
		if (!params.isEmpty()) map.put("params", params);
		List throws_ = buildThrows(md.getThrownExceptions());
		if (!throws_.isEmpty()) map.put("throws", throws_);
		md.getBody().ifPresent(body -> map.put("body", body.toString()));
		return map;
	}

	private List buildParams(NodeList<Parameter> parameters)
	{
		List params = new ArrayList();
		for (Parameter p : parameters)
		{
			Map pm = new HashMap();
			pm.put("name", p.getNameAsString());
			pm.put("type", p.getType().asString());
			List annots = buildAnnotations(p.getAnnotations());
			if (!annots.isEmpty()) pm.put("annotations", annots);
			params.add(pm);
		}
		return params;
	}

	private List buildThrows(NodeList<ReferenceType> thrown)
	{
		List list = new ArrayList();
		for (ReferenceType rt : thrown)
			list.add(rt.asString());
		return list;
	}

	private List buildTypeParams(NodeList<TypeParameter> typeParameters)
	{
		List list = new ArrayList();
		for (TypeParameter tp : typeParameters)
			list.add(tp.asString());
		return list;
	}

	private List buildAnnotations(NodeList<AnnotationExpr> annotations)
	{
		List list = new ArrayList();
		for (AnnotationExpr ae : annotations)
			list.add(ae.toString());
		return list;
	}

	private List buildModifiers(Iterator<Modifier> it)
	{
		List mods = new ArrayList();
		while (it.hasNext()) mods.add(it.next().name().toLowerCase());
		return mods;
	}
}