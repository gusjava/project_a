package a.entity.gus.x.java.srccode.ast1;

import a.framework.*;
import java.util.*;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.type.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20260418";}

	public Object t(Object obj) throws Exception
	{
		String src = (String) obj;
		JavaParser jp = new JavaParser();
		CompilationUnit cu = jp.parse(src).getResult().get();
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

		for (TypeDeclaration<?> td : cu.getTypes())
		{
			if (td instanceof ClassOrInterfaceDeclaration)
			{
				map.put("class", buildClass((ClassOrInterfaceDeclaration) td));
				break;
			}
		}

		return map;
	}

	private Map buildClass(ClassOrInterfaceDeclaration cid)
	{
		Map map = new HashMap();
		map.put("name", cid.getNameAsString());

		List impl = new ArrayList();
		for (ClassOrInterfaceType t : cid.getImplementedTypes())
			impl.add(t.getNameAsString());
		if (!impl.isEmpty()) map.put("implements", impl);

		List ext = new ArrayList();
		for (ClassOrInterfaceType t : cid.getExtendedTypes())
			ext.add(t.getNameAsString());
		if (!ext.isEmpty()) map.put("extends", ext);

		List fields = new ArrayList();
		List constructors = new ArrayList();
		List methods = new ArrayList();

		for (BodyDeclaration<?> member : cid.getMembers())
		{
			if (member instanceof FieldDeclaration)
				fields.add(buildField((FieldDeclaration) member));
			else if (member instanceof ConstructorDeclaration)
				constructors.add(buildConstructor((ConstructorDeclaration) member));
			else if (member instanceof MethodDeclaration)
				methods.add(buildMethod((MethodDeclaration) member));
		}

		if (!fields.isEmpty()) map.put("fields", fields);
		if (!constructors.isEmpty()) map.put("constructors", constructors);
		if (!methods.isEmpty()) map.put("methods", methods);

		return map;
	}

	private Map buildField(FieldDeclaration fd)
	{
		Map map = new HashMap();
		VariableDeclarator vd = fd.getVariables().get(0);
		map.put("name", vd.getNameAsString());
		map.put("type", vd.getType().asString());
		List mods = buildModifiers(fd.getModifiers().iterator());
		if (!mods.isEmpty()) map.put("modifiers", mods);
		return map;
	}

	private Map buildConstructor(ConstructorDeclaration cd)
	{
		Map map = new HashMap();
		List params = buildParams(cd.getParameters().iterator());
		if (!params.isEmpty()) map.put("params", params);
		List mods = buildModifiers(cd.getModifiers().iterator());
		if (!mods.isEmpty()) map.put("modifiers", mods);
		return map;
	}

	private Map buildMethod(MethodDeclaration md)
	{
		Map map = new HashMap();
		map.put("name", md.getNameAsString());
		String ret = md.getType().asString();
		if (!ret.equals("void")) map.put("return", ret);
		List params = buildParams(md.getParameters().iterator());
		if (!params.isEmpty()) map.put("params", params);
		List mods = buildModifiers(md.getModifiers().iterator());
		if (!mods.isEmpty()) map.put("modifiers", mods);
		return map;
	}

	private List buildParams(Iterator<Parameter> it)
	{
		List params = new ArrayList();
		while (it.hasNext())
		{
			Parameter p = it.next();
			Map pm = new HashMap();
			pm.put("name", p.getNameAsString());
			pm.put("type", p.getType().asString());
			params.add(pm);
		}
		return params;
	}

	private List buildModifiers(Iterator<Modifier> it)
	{
		List mods = new ArrayList();
		while (it.hasNext()) mods.add(it.next().getKeyword().name().toLowerCase());
		return mods;
	}
}