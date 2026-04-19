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
import com.github.javaparser.ast.stmt.*;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;

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
				initMap.put("body", buildBody(init.getBody()));
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
		map.put("body", buildBody(cd.getBody()));
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
		md.getBody().ifPresent(body -> map.put("body", buildBody(body)));
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
	
	private List buildBody(BlockStmt body)
	{
		List<Statement> statements = body.getStatements();
		List stmts = new ArrayList();
		for(Statement stmt : statements)
			stmts.add(buildStatment(stmt));
		return stmts;
	}
	
	private Map buildStatment(Statement stmt)
	{
		Map m = new HashMap();
		m.put("type", stmt.getClass().getSimpleName());
		
		if(stmt instanceof IfStmt)
		{
			IfStmt ifs = (IfStmt) stmt;
			m.put("condition", ifs.getCondition().toString());
			m.put("then", buildStatment(ifs.getThenStmt()));
			
			if(ifs.getElseStmt().isPresent())
			m.put("else", buildStatment(ifs.getElseStmt().get()));
		}
		else if(stmt instanceof ReturnStmt)
		{
			ReturnStmt rs = (ReturnStmt) stmt;
			if(rs.getExpression().isPresent())
			m.put("exp", rs.getExpression().get().toString());
		}
		else if(stmt instanceof BlockStmt)
		{
			BlockStmt bs = (BlockStmt) stmt;
			m.put("body", buildBody(bs));
		}
		else if(stmt instanceof ExpressionStmt)
		{
			ExpressionStmt es = (ExpressionStmt) stmt;
			m.put("exp", es.getExpression().toString());
		}
		else if(stmt instanceof ForStmt)
		{
			ForStmt fs = (ForStmt) stmt;
			
			List initList = new ArrayList();
			for(Expression e : fs.getInitialization())
				initList.add(e.toString());
			
			List updateList = new ArrayList();
			for(Expression e : fs.getUpdate())
				updateList.add(e.toString());
			
			m.put("init", initList);
			if(fs.getCompare().isPresent())
			m.put("compare", fs.getCompare().get().toString());
			m.put("update", updateList);
			m.put("body", buildStatment(fs.getBody()));
		}
		else if(stmt instanceof WhileStmt)
		{
			WhileStmt ws = (WhileStmt) stmt;
			
			m.put("condition", ws.getCondition().toString());
			m.put("body", buildStatment(ws.getBody()));
		}
		else if(stmt instanceof TryStmt)
		{
			TryStmt ts = (TryStmt) stmt;
			
			m.put("try", buildBody(ts.getTryBlock()));
			
			List catches = new ArrayList();
			for(CatchClause cc : ts.getCatchClauses())
			{
				Map cm = new HashMap();
				cm.put("param", cc.getParameter().toString());
				cm.put("body", buildBody(cc.getBody()));
				catches.add(cm);
			}
			m.put("catches", catches);
			
			if(ts.getFinallyBlock().isPresent())
				m.put("finally", buildBody(ts.getFinallyBlock().get()));
		}
		else if(stmt instanceof ExpressionStmt)
		{
			ExpressionStmt es = (ExpressionStmt) stmt;
			if(es.getExpression() instanceof VariableDeclarationExpr)
			{
				VariableDeclarationExpr vde = (VariableDeclarationExpr) es.getExpression();
				List vars = new ArrayList();
				for(VariableDeclarator vd : vde.getVariables())
				{
					Map vm = new HashMap();
					vm.put("name", vd.getNameAsString());
					vm.put("type", vd.getType().toString());
					
					if(vd.getInitializer().isPresent())
						vm.put("init", buildExpression(vd.getInitializer().get()));
					
					vars.add(vm);
				}
				
				m.put("variables", vars);
			}
		}
		else
		{
			m.put("raw", stmt.toString());
		}
		
		return m;
	}
	
	private Map buildExpression(Expression expr)
	{
		Map m = new HashMap();
		m.put("type", expr.getClass().getSimpleName());
		
		if(expr instanceof NameExpr)
		{
			m.put("name", ((NameExpr) expr).getNameAsString());
		}
		else if(expr instanceof LiteralStringValueExpr)
		{
			m.put("value", expr.toString());
		}
		else if(expr instanceof BinaryExpr)
		{
			BinaryExpr be = (BinaryExpr) expr;
			m.put("operator", be.getOperator().toString());
			m.put("left", buildExpression(be.getLeft()));
			m.put("right", buildExpression(be.getRight()));
		}
		else if(expr instanceof MethodCallExpr)
		{
			MethodCallExpr mc = (MethodCallExpr) expr;
			m.put("name", mc.getNameAsString());
			
			List args = new ArrayList();
			for(Expression e : mc.getArguments())
				args.add(buildExpression(e));
			
			m.put("args", args);
			
			if(mc.getScope().isPresent())
				m.put("scope", buildExpression(mc.getScope().get()));
		}
		else
		{
			m.put("raw", expr.toString());
		}
		return m;
	}
}