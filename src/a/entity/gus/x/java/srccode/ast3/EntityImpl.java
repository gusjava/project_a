package a.entity.gus.x.java.srccode.ast3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.InitializerDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.stmt.*;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.ReferenceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.type.TypeParameter;

import a.framework.Entity;
import a.framework.T;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20260419";}

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
			vd.getInitializer().ifPresent(init -> map.put("initializer", buildExpression(init)));
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
		map.put("sign", buildSignature(cd));
		
		return map;
	}
	
	private String buildSignature(CallableDeclaration<?> cd)
	{
		StringBuilder sb = new StringBuilder();
	
		// MODIFIERS (sorted)
		List<String> mods = cd.getModifiers().stream()
			.map(m -> m.getKeyword().asString())
			.sorted()
			.toList();
	
		for (String m : mods)
			sb.append(m).append(" ");
	
		// TYPE PARAMETERS (methods only)
		if (cd instanceof MethodDeclaration md && !md.getTypeParameters().isEmpty())
		{
			sb.append("<");
	
			List<String> tps = md.getTypeParameters().stream()
				.map(TypeParameter::asString)
				.sorted()
				.toList();
	
			for (int i = 0; i < tps.size(); i++)
			{
				sb.append(tps.get(i));
				if (i < tps.size() - 1)
					sb.append(", ");
			}
	
			sb.append("> ");
		}
	
		// NAME
		sb.append(cd.getNameAsString());
	
		// PARAMS (sorted)
		sb.append("(");
	
		List<Parameter> params = new ArrayList<>(cd.getParameters());
	
		params.sort((a, b) ->
			(a.getType().asString() + a.getNameAsString())
			.compareTo(b.getType().asString() + b.getNameAsString())
		);
	
		for (int i = 0; i < params.size(); i++)
		{
			Parameter p = params.get(i);
	
			sb.append(p.getType().asString())
			  .append(" ")
			  .append(p.getNameAsString());
	
			if (i < params.size() - 1)
				sb.append(", ");
		}
	
		sb.append(")");
	
		// THROWS (sorted)
		if (!cd.getThrownExceptions().isEmpty())
		{
			sb.append(" throws ");
	
			List<String> thr = cd.getThrownExceptions().stream()
				.map(Type::asString)
				.sorted()
				.toList();
	
			for (int i = 0; i < thr.size(); i++)
			{
				sb.append(thr.get(i));
				if (i < thr.size() - 1)
					sb.append(", ");
			}
		}
	
		return sb.toString();
	}

	private Map buildMethod(MethodDeclaration md)
	{
		Map map = new HashMap();
		
		map.put("name", md.getNameAsString());
		String returnType = md.getType().asString();
		if (!returnType.equals("void")) map.put("return", returnType);
		
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
		
		List body = buildBody(md.getBody().get());
		if (body!=null) map.put("body", body);
		
		map.put("sign", buildSignature(md));
		
		return map;
	}
	
	private String buildSignature(MethodDeclaration md)
	{
		StringBuilder sb = new StringBuilder();
	
		// Modifiers
		List<String> mods = md.getModifiers().stream()
		.map(m ->  m.getKeyword().asString()).sorted().toList();
		for (String mod : mods) sb.append(mod).append(" ");
	
		// Type params
		if (!md.getTypeParameters().isEmpty())
		{
			sb.append("<");
			
			List<String> tps = md.getTypeParameters().stream()
			.map(TypeParameter::asString).sorted().toList();
			
			for (int i = 0; i < tps.size(); i++)
			{
				sb.append(tps.get(i));
				if (i < tps.size() - 1) sb.append(", ");
			}
			sb.append("> ");
		}
	
		// Return type
		sb.append(md.getType().asString()).append(" ");
	
		// Name
		sb.append(md.getNameAsString());
	
		// Params
		sb.append("(");
		for (int i = 0; i < md.getParameters().size(); i++)
		{
			Parameter p = md.getParameter(i);
			sb.append(p.getType().asString())
			  .append(" ")
			  .append(p.getNameAsString());
	
			if (i < md.getParameters().size() - 1)
				sb.append(", ");
		}
		sb.append(")");
	
		// Throws
		if (!md.getThrownExceptions().isEmpty())
		{
			sb.append(" throws ");
			for (int i = 0; i < md.getThrownExceptions().size(); i++)
			{
				sb.append(md.getThrownExceptions().get(i).asString());
				if (i < md.getThrownExceptions().size() - 1)
					sb.append(", ");
			}
		}
	
		return sb.toString();
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
		{
			Map m = new HashMap();
			m.put("name", ae.getNameAsString());
			if (ae instanceof SingleMemberAnnotationExpr)
			{
				SingleMemberAnnotationExpr sma = (SingleMemberAnnotationExpr) ae;
				m.put("value", buildExpression(sma.getMemberValue()));
			}
			else if (ae instanceof NormalAnnotationExpr)
			{
				NormalAnnotationExpr na = (NormalAnnotationExpr) ae;
				List pairs = new ArrayList();
				for (MemberValuePair mvp : na.getPairs())
				{
					Map pm = new HashMap();
					pm.put("key", mvp.getNameAsString());
					pm.put("value", buildExpression(mvp.getValue()));
					pairs.add(pm);
				}
				if (!pairs.isEmpty()) m.put("pairs", pairs);
			}
			list.add(m);
		}
		return list;
	}

	private List buildModifiers(Iterator<Modifier> it)
	{
		List mods = new ArrayList();
		while (it.hasNext()) mods.add(it.next().getKeyword().name().toLowerCase());
		return mods;
	}

	private List buildBody(BlockStmt body)
	{
		if(body==null) return null;
		List stmts = new ArrayList();
		for (Statement stmt : body.getStatements())
			stmts.add(buildStatment(stmt));
		return stmts;
	}

	private Map buildStatment(Statement stmt)
	{
		Map m = new HashMap();
		m.put("type", stmt.getClass().getSimpleName());

		if (stmt instanceof IfStmt)
		{
			IfStmt ifs = (IfStmt) stmt;
			m.put("condition", buildExpression(ifs.getCondition()));
			m.put("then", buildStatment(ifs.getThenStmt()));
			if (ifs.getElseStmt().isPresent())
				m.put("else", buildStatment(ifs.getElseStmt().get()));
		}
		else if (stmt instanceof ReturnStmt)
		{
			ReturnStmt rs = (ReturnStmt) stmt;
			if (rs.getExpression().isPresent())
				m.put("exp", buildExpression(rs.getExpression().get()));
		}
		else if (stmt instanceof BlockStmt)
		{
			m.put("body", buildBody((BlockStmt) stmt));
		}
		else if (stmt instanceof ExpressionStmt)
		{
			m.put("exp", buildExpression(((ExpressionStmt) stmt).getExpression()));
		}
		else if (stmt instanceof ForStmt)
		{
			ForStmt fs = (ForStmt) stmt;
			List initList = new ArrayList();
			for (Expression e : fs.getInitialization())
				initList.add(buildExpression(e));
			List updateList = new ArrayList();
			for (Expression e : fs.getUpdate())
				updateList.add(buildExpression(e));
			m.put("init", initList);
			if (fs.getCompare().isPresent())
				m.put("compare", buildExpression(fs.getCompare().get()));
			m.put("update", updateList);
			m.put("body", buildStatment(fs.getBody()));
		}
		else if (stmt instanceof ForEachStmt)
		{
			ForEachStmt fes = (ForEachStmt) stmt;
			m.put("variable", buildExpression(fes.getVariable()));
			m.put("iterable", buildExpression(fes.getIterable()));
			m.put("body", buildStatment(fes.getBody()));
		}
		else if (stmt instanceof WhileStmt)
		{
			WhileStmt ws = (WhileStmt) stmt;
			m.put("condition", buildExpression(ws.getCondition()));
			m.put("body", buildStatment(ws.getBody()));
		}
		else if (stmt instanceof DoStmt)
		{
			DoStmt ds = (DoStmt) stmt;
			m.put("condition", buildExpression(ds.getCondition()));
			m.put("body", buildStatment(ds.getBody()));
		}
		else if (stmt instanceof TryStmt)
		{
			TryStmt ts = (TryStmt) stmt;
			m.put("try", buildBody(ts.getTryBlock()));
			List catches = new ArrayList();
			for (CatchClause cc : ts.getCatchClauses())
			{
				Map cm = new HashMap();
				Parameter p = cc.getParameter();
				Map pm = new HashMap();
				pm.put("name", p.getNameAsString());
				pm.put("type", p.getType().asString());
				cm.put("param", pm);
				cm.put("body", buildBody(cc.getBody()));
				catches.add(cm);
			}
			m.put("catches", catches);
			if (ts.getFinallyBlock().isPresent())
				m.put("finally", buildBody(ts.getFinallyBlock().get()));
		}
		else if (stmt instanceof SwitchStmt)
		{
			SwitchStmt ss = (SwitchStmt) stmt;
			m.put("selector", buildExpression(ss.getSelector()));
			List entries = new ArrayList();
			for (SwitchEntry se : ss.getEntries()) {
				Map em = new HashMap();
				if (se.getLabels().isEmpty())
					em.put("default", true);
				else if (se.getLabels().size() == 1)
					em.put("label", buildExpression(se.getLabels().get(0)));
				else {
					List labels = new ArrayList();
					for (Expression lbl : se.getLabels())
						labels.add(buildExpression(lbl));
					em.put("labels", labels);
				}
				List stmts2 = new ArrayList();
				for (Statement s2 : se.getStatements())
					stmts2.add(buildStatment(s2));
				em.put("stmts", stmts2);
				entries.add(em);
			}
			m.put("entries", entries);
		}
		else if (stmt instanceof ThrowStmt)
		{
			m.put("exp", buildExpression(((ThrowStmt) stmt).getExpression()));
		}
		else if (stmt instanceof BreakStmt)
		{
			BreakStmt bs = (BreakStmt) stmt;
			bs.getLabel().ifPresent(l -> m.put("label", l.toString()));
		}
		else if (stmt instanceof ContinueStmt)
		{
			ContinueStmt cs = (ContinueStmt) stmt;
			cs.getLabel().ifPresent(l -> m.put("label", l.toString()));
		}
		else if (stmt instanceof SynchronizedStmt)
		{
			SynchronizedStmt ss = (SynchronizedStmt) stmt;
			m.put("condition", buildExpression(ss.getExpression()));
			m.put("body", buildBody(ss.getBody()));
		}
		else if (stmt instanceof LabeledStmt)
		{
			LabeledStmt ls = (LabeledStmt) stmt;
			m.put("label", ls.getLabel().toString());
			m.put("body", buildStatment(ls.getStatement()));
		}
		else if (stmt instanceof AssertStmt)
		{
			AssertStmt as = (AssertStmt) stmt;
			m.put("check", buildExpression(as.getCheck()));
			as.getMessage().ifPresent(msg -> m.put("message", buildExpression(msg)));
		}
		else if (stmt instanceof ExplicitConstructorInvocationStmt)
		{
			ExplicitConstructorInvocationStmt ec = (ExplicitConstructorInvocationStmt) stmt;
			m.put("isThis", ec.isThis());
			List args = new ArrayList();
			for (Expression e : ec.getArguments())
				args.add(buildExpression(e));
			m.put("args", args);
			ec.getExpression().ifPresent(scope -> m.put("scope", buildExpression(scope)));
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

		if (expr instanceof NameExpr)
		{
			m.put("name", ((NameExpr) expr).getNameAsString());
		}
		else if (expr instanceof LiteralStringValueExpr)
		{
			m.put("value", expr.toString());
		}
		else if (expr instanceof NullLiteralExpr)
		{
			m.put("value", "null");
		}
		else if (expr instanceof BooleanLiteralExpr)
		{
			m.put("value", String.valueOf(((BooleanLiteralExpr) expr).getValue()));
		}
		else if (expr instanceof ThisExpr)
		{
		}
		else if (expr instanceof SuperExpr)
		{
		}
		else if (expr instanceof ClassExpr)
		{
			m.put("javaType", ((ClassExpr) expr).getType().asString());
		}
		else if (expr instanceof TypeExpr)
		{
			m.put("javaType", ((TypeExpr) expr).getType().asString());
		}
		else if (expr instanceof AssignExpr)
		{
			AssignExpr ae = (AssignExpr) expr;
			m.put("operator", ae.getOperator().toString());
			m.put("target", buildExpression(ae.getTarget()));
			m.put("value", buildExpression(ae.getValue()));
		}
		else if (expr instanceof BinaryExpr)
		{
			BinaryExpr be = (BinaryExpr) expr;
			m.put("operator", be.getOperator().toString());
			m.put("left", buildExpression(be.getLeft()));
			m.put("right", buildExpression(be.getRight()));
		}
		else if (expr instanceof UnaryExpr)
		{
			UnaryExpr ue = (UnaryExpr) expr;
			m.put("operator", ue.getOperator().toString());
			m.put("exp", buildExpression(ue.getExpression()));
		}
		else if (expr instanceof ConditionalExpr)
		{
			ConditionalExpr ce = (ConditionalExpr) expr;
			m.put("condition", buildExpression(ce.getCondition()));
			m.put("then", buildExpression(ce.getThenExpr()));
			m.put("else", buildExpression(ce.getElseExpr()));
		}
		else if (expr instanceof EnclosedExpr)
		{
			m.put("inner", buildExpression(((EnclosedExpr) expr).getInner()));
		}
		else if (expr instanceof CastExpr)
		{
			CastExpr ce = (CastExpr) expr;
			m.put("castType", ce.getType().asString());
			m.put("exp", buildExpression(ce.getExpression()));
		}
		else if (expr instanceof InstanceOfExpr)
		{
			InstanceOfExpr ioe = (InstanceOfExpr) expr;
			m.put("exp", buildExpression(ioe.getExpression()));
			m.put("checkType", ioe.getType().asString());
		}
		else if (expr instanceof MethodCallExpr)
		{
			MethodCallExpr mc = (MethodCallExpr) expr;
			m.put("name", mc.getNameAsString());
			List args = new ArrayList();
			for (Expression e : mc.getArguments())
				args.add(buildExpression(e));
			m.put("args", args);
			if (mc.getScope().isPresent())
				m.put("scope", buildExpression(mc.getScope().get()));
		}
		else if (expr instanceof ObjectCreationExpr)
		{
			ObjectCreationExpr oce = (ObjectCreationExpr) expr;
			m.put("class", oce.getType().asString());
			List args = new ArrayList();
			for (Expression e : oce.getArguments())
				args.add(buildExpression(e));
			m.put("args", args);
			if (oce.getScope().isPresent())
				m.put("scope", buildExpression(oce.getScope().get()));
			if (oce.getAnonymousClassBody().isPresent())
			{
				List members = new ArrayList();
				for (BodyDeclaration<?> member : oce.getAnonymousClassBody().get())
				{
					if (member instanceof MethodDeclaration)
						members.add(buildMethod((MethodDeclaration) member));
					else if (member instanceof FieldDeclaration)
						members.addAll(buildFields((FieldDeclaration) member));
					else if (member instanceof TypeDeclaration)
						members.add(buildType((TypeDeclaration<?>) member));
				}
				if (!members.isEmpty()) m.put("anonymousBody", members);
			}
		}
		else if (expr instanceof FieldAccessExpr)
		{
			FieldAccessExpr fae = (FieldAccessExpr) expr;
			m.put("scope", buildExpression(fae.getScope()));
			m.put("field", fae.getNameAsString());
		}
		else if (expr instanceof ArrayAccessExpr)
		{
			ArrayAccessExpr aae = (ArrayAccessExpr) expr;
			m.put("name", buildExpression(aae.getName()));
			m.put("index", buildExpression(aae.getIndex()));
		}
		else if (expr instanceof ArrayCreationExpr)
		{
			ArrayCreationExpr ace = (ArrayCreationExpr) expr;
			m.put("type", ace.getElementType().asString());
			if (ace.getInitializer().isPresent())
				m.put("initializer", buildExpression(ace.getInitializer().get()));
		}
		else if (expr instanceof ArrayInitializerExpr)
		{
			ArrayInitializerExpr aie = (ArrayInitializerExpr) expr;
			List values = new ArrayList();
			for (Expression e : aie.getValues())
				values.add(buildExpression(e));
			m.put("values", values);
		}
		else if (expr instanceof VariableDeclarationExpr)
		{
			VariableDeclarationExpr vde = (VariableDeclarationExpr) expr;
			List vars = new ArrayList();
			for (VariableDeclarator vd : vde.getVariables())
			{
				Map vm = new HashMap();
				vm.put("name", vd.getNameAsString());
				vm.put("type", vd.getType().asString());
				if (vd.getInitializer().isPresent())
					vm.put("init", buildExpression(vd.getInitializer().get()));
				vars.add(vm);
			}
			m.put("variables", vars);
		}
		else if (expr instanceof MethodReferenceExpr)
		{
			MethodReferenceExpr mre = (MethodReferenceExpr) expr;
			m.put("scope", buildExpression(mre.getScope()));
			m.put("identifier", mre.getIdentifier());
		}
		else if (expr instanceof LambdaExpr)
		{
			LambdaExpr le = (LambdaExpr) expr;
			List params = new ArrayList();
			for (Parameter p : le.getParameters())
			{
				Map pm = new HashMap();
				pm.put("name", p.getNameAsString());
				String ptype = p.getType().asString();
				if (!ptype.isEmpty()) pm.put("type", ptype);
				params.add(pm);
			}
			m.put("params", params);
			Statement body = le.getBody();
			if (body instanceof ExpressionStmt)
				m.put("body", buildExpression(((ExpressionStmt) body).getExpression()));
			else if (body instanceof BlockStmt)
				m.put("body", buildBody((BlockStmt) body));
			else
				m.put("body", buildStatment(body));
		}
		else
		{
			m.put("raw", expr.toString());
		}

		return m;
	}
}
