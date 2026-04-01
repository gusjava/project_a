package a.entity.gus06.data.viewer.object.objtoname;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.List;
import java.util.Date;
import java.awt.Color;
import javax.swing.*;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.RenderedImage;
import java.math.BigDecimal;
import java.math.BigInteger;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140731";}

	public static final String STRING = "gus.data.viewer.string";
	public static final String STRINGBUFFER = "gus.data.viewer.stringbuffer";
	public static final String NUMBER = "gus.data.viewer.number";
	public static final String BOOLEAN = "gus.data.viewer.boolean1";
	public static final String DATE = "gus.data.viewer.date";
	public static final String COLOR = "gus.data.viewer.color";
	public static final String FONT = "gus.data.viewer.font";
	public static final String ICON = "gus.data.viewer.icon";
	public static final String ACTION = "gus.data.viewer.action";
	public static final String FILE = "gus.data.viewer.file";
	public static final String URL = "gus.data.viewer.url";
	public static final String ENTITY = "gus.data.viewer.entity";
	public static final String SERVICE = "gus.data.viewer.service";
	public static final String RUNNABLE = "gus.data.viewer.runnable";
	public static final String JCOMPONENT = "gus.data.viewer.jcomponent";
	public static final String EXCEPTION = "gus.data.viewer.exception";
	public static final String URLCLASSLOADER = "gus.data.viewer.urlclassloader";
	public static final String CLASS1 = "gus.data.viewer.class1";
	public static final String METHOD = "gus.data.viewer.method";
	public static final String FIELD = "gus.data.viewer.field";
	public static final String IMAGE = "gus.data.viewer.image";
	
	public static final String MAP = "gus.data.viewer.map";
	public static final String LIST = "gus.data.viewer.list";
	public static final String SET = "gus.data.viewer.set";
	
	public static final String ARRAY = "gus.data.viewer.array";
	public static final String ARRAY2 = "gus.data.viewer.array2";
	public static final String FONTARRAY = "gus.data.viewer.fontarray";
	public static final String FILEARRAY = "gus.data.viewer.filearray";
	public static final String BYTEARRAY = "gus.data.viewer.bytearray";
	
	public static final String E = "gus.data.viewer.e";
	public static final String I = "gus.data.viewer.i";
	public static final String G = "gus.data.viewer.g";
	public static final String T = "gus.data.viewer.t";
	public static final String H = "gus.data.viewer.h";
	public static final String F = "gus.data.viewer.f";
	public static final String P = "gus.data.viewer.p";
	public static final String R = "gus.data.viewer.r";
	public static final String V = "gus.data.viewer.v";
	public static final String S = "gus.data.viewer.s";
	public static final String S1 = "gus.data.viewer.s1";
	
	public static final String TOSTRING = "gus.data.viewer.tostring";

	
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		Map names = new HashMap();
		
		if(obj instanceof String) names.put("String",STRING);
		if(obj instanceof StringBuffer) names.put("StringBuffer",STRINGBUFFER);
		if(obj instanceof StringBuilder) names.put("StringBuilder",TOSTRING);
		if(obj instanceof Integer) names.put("Integer",NUMBER);
		if(obj instanceof Long) names.put("Long",NUMBER);
		if(obj instanceof Double) names.put("Double",NUMBER);
		if(obj instanceof Float) names.put("Float",NUMBER);
		if(obj instanceof Short) names.put("Short",NUMBER);
		if(obj instanceof Byte) names.put("Byte",NUMBER);
		if(obj instanceof Boolean) names.put("Boolean",BOOLEAN);
		if(obj instanceof BigDecimal) names.put("BigDecimal",TOSTRING);
		if(obj instanceof BigInteger) names.put("BigInteger",TOSTRING);
		if(obj instanceof Date) names.put("Date",DATE);
		if(obj instanceof Color) names.put("Color",COLOR);
		if(obj instanceof Font) names.put("Font",FONT);
		if(obj instanceof Icon) names.put("Icon",ICON);
		if(obj instanceof Action) names.put("Action",ACTION);
		if(obj instanceof File) names.put("File",FILE);
		if(obj instanceof URL) names.put("URL",URL);
		if(obj instanceof Runnable) names.put("Runnable",RUNNABLE);
		if(obj instanceof JComponent) names.put("JComponent",JCOMPONENT);
		if(obj instanceof Exception) names.put("Exception",EXCEPTION);
		if(obj instanceof URLClassLoader) names.put("URLClassLoader",URLCLASSLOADER);
		if(obj instanceof Class) names.put("Class",CLASS1);
		if(obj instanceof Method) names.put("Method",METHOD);
		if(obj instanceof Field) names.put("Field",FIELD);
		
		if(obj instanceof Map) names.put("Map",MAP);
		if(obj instanceof List) names.put("List",LIST);
		if(obj instanceof Set) names.put("Set",SET);
		
		if(obj instanceof Font[]) names.put("Font[]",FONTARRAY);
		if(obj instanceof File[]) names.put("File[]",FILEARRAY);
		if(obj instanceof byte[]) names.put("byte[]",BYTEARRAY);
		
		if(obj instanceof Object[][]) names.put("Object[][]",ARRAY2);
		else if(obj instanceof Object[]) names.put("Object[]",ARRAY);
		
		if(obj instanceof Image) names.put("Image",IMAGE);
		else if(obj instanceof RenderedImage) names.put("RenderedImage",IMAGE);
		
		if(obj instanceof Entity) names.put("Entity",ENTITY);
		if(obj instanceof Service) names.put("Service",SERVICE);
		if(obj instanceof E) names.put("E",E);
		if(obj instanceof I) names.put("I",I);
		if(obj instanceof G) names.put("G",G);
		if(obj instanceof T) names.put("T",T);
		if(obj instanceof H) names.put("H",H);
		if(obj instanceof F) names.put("F",F);
		if(obj instanceof P) names.put("P",P);
		if(obj instanceof R) names.put("R",R);
		if(obj instanceof V) names.put("V",V);
		if(obj instanceof S) names.put("S",S);
		if(obj instanceof S1) names.put("S1",S1);
		
		return names;
	}
}