package a.entity.gus06.sys.vuejsparser1.script.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Deque;
import java.util.ArrayDeque;

public class CustomListener extends JavaScriptParserBaseListener
{
	private final Map<String,Object> root = new HashMap<>();
	private final Deque<Object> stack = new ArrayDeque<>();
	private final Deque<String> keyStack = new ArrayDeque<>();

	private Object current()
	{return stack.isEmpty() ? null : stack.peek();}

	public Map<String,Object> getRoot()
	{return root;}

	private void attach(Object value)
	{
		Object parent = current();

		if(parent instanceof Map)
		{
			if(keyStack.isEmpty()) return;
			String key = keyStack.pop();
			((Map) parent).put(key, value);
		}
		else if(parent instanceof List)
		{
			((List) parent).add(value);
		}
	}
	
	@Override
	public void enterProgram(JavaScriptParser.ProgramContext ctx)
	{
		stack.push(root);
	}
	
	@Override
	public void enterImportExpression(JavaScriptParser.ImportExpressionContext ctx)
	{
		Map<String,Object> current = (Map<String,Object>) stack.peek();
		List<String> imports = (List<String>) current.computeIfAbsent("imports", k -> new ArrayList<>());
		imports.add(ctx.getText());
	}
	
	@Override
	public void enterExportDefaultDeclaration(JavaScriptParser.ExportDefaultDeclarationContext ctx)
	{
		keyStack.push("default");
	}


	@Override
	public void enterObjectLiteral(JavaScriptParser.ObjectLiteralContext ctx)
	{
		Map<String,Object> map = new HashMap<>();
		attach(map);
		stack.add(map);
	}

	@Override
	public void exitObjectLiteral(JavaScriptParser.ObjectLiteralContext ctx)
	{
		stack.pop();
	}

	@Override
	public void enterArrayLiteral(JavaScriptParser.ArrayLiteralContext ctx)
	{
		List<Object> list = new ArrayList<>();
		attach(list);
		stack.add(list);
	}

	@Override
	public void exitArrayLiteral(JavaScriptParser.ArrayLiteralContext ctx)
	{
		stack.pop();
	}

	@Override
	public void enterPropertyExpressionAssignment(JavaScriptParser.PropertyExpressionAssignmentContext ctx)
	{
		String key = ctx.propertyName().getText();
		keyStack.add(key);

		Object value = extractValue(ctx.singleExpression());
		if(!(value instanceof Map) && !(value instanceof List))
		{
			attach(value);
		}
	}

	private Object extractValue(JavaScriptParser.SingleExpressionContext ctx)
	{
		if(ctx instanceof JavaScriptParser.ObjectLiteralExpressionContext)
			return new HashMap<>();
		if(ctx instanceof JavaScriptParser.ArrayLiteralExpressionContext)
			return new ArrayList<>();
		if(ctx instanceof JavaScriptParser.FunctionExpressionContext)
			return "function";
		if(ctx instanceof JavaScriptParser.LiteralExpressionContext)
			return ctx.getText();
		return ctx.getText();
	}
	
	@Override
	public void enterArrayElement(JavaScriptParser.ArrayElementContext ctx)
	{
		Object current = current();
		if(!(current instanceof List)) return;
	
		if(ctx.singleExpression() == null) return;
	
		Object value = extractValue(ctx.singleExpression());
		if(value instanceof Map || value instanceof List) return;
	
		((List) current).add(value);
	}
	
	@Override
	public void enterFunctionProperty(JavaScriptParser.FunctionPropertyContext ctx)
	{
		Object current = current();
		if(!(current instanceof Map)) return;
	
		String key = ctx.propertyName().getText();
		((Map) current).put(key, "function");
	}
}
