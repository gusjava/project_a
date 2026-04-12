package a.entity.gus06.java.bytecode.jdepend.analyze.imports;

import java.io.*;
import java.util.*;

public class ClassFileParser {

	public static final int JAVA_MAGIC = 0xCAFEBABE;
	public static final char CLASS_DESCRIPTOR = 'L';
	public static final int ACC_INTERFACE = 0x200;
	public static final int ACC_ABSTRACT = 0x400;
	
	public static final int CONSTANT_UTF8 = 1;
	public static final int CONSTANT_UNICODE = 2;
	public static final int CONSTANT_INTEGER = 3;
	public static final int CONSTANT_FLOAT = 4;
	public static final int CONSTANT_LONG = 5;
	public static final int CONSTANT_DOUBLE = 6;
	public static final int CONSTANT_CLASS = 7;
	public static final int CONSTANT_STRING = 8;
	public static final int CONSTANT_FIELD = 9;
	public static final int CONSTANT_METHOD = 10;
	public static final int CONSTANT_INTERFACEMETHOD = 11;
	public static final int CONSTANT_NAMEANDTYPE = 12;
	public static final int CONSTANT_METHODHANDLE = 15;
	public static final int CONSTANT_METHODTYPE = 16;
	public static final int CONSTANT_DYNAMIC = 17;   // Java 11
	public static final int CONSTANT_INVOKEDYNAMIC = 18;
	public static final int CONSTANT_MODULE = 19;    // Java 9
	public static final int CONSTANT_PACKAGE = 20;   // Java 9

	
	private String className;
	private String superClassName;
	private JavaClass jClass;
	private Constant[] constantPool;
	
	private DataInputStream in;
	
	private byte _byte() throws IOException
	{return in.readByte();}
	
	private int _unsigned() throws IOException
	{return in.readUnsignedShort();}

	private int _int() throws IOException
	{return in.readInt();}
	
	private float _float() throws IOException
	{return in.readFloat();}
	
	private long _long() throws IOException
	{return in.readLong();}
	
	private double _double() throws IOException
	{return in.readDouble();}
	
	private String _UTF() throws IOException
	{return in.readUTF();}
	
	
	public JavaClass parse(InputStream is) throws Exception
	{
		in = new DataInputStream(is);
		
		jClass = new JavaClass("Unknown");

		//parse java magic
		int magic = _int();
		if(magic!=JAVA_MAGIC) throw new Exception("Invalid magic number");
		
		// parse minor and major version
		_unsigned();
		_unsigned();
		
		// parse constant pool
		parseConstantPool();
		
		parseAccessFlags();
		parseClassName();
		parseSuperClassName();
		parseInterfaces();
		parseFields();
		parseMethods();
		parseAttributes();
		addClassConstantReferences();

		in.close();
		return jClass;
	}
	
	private void parseConstantPool() throws Exception
	{
		int nb = _unsigned();
		constantPool = new Constant[nb];

		for (int i=1;i<nb;i++)
		{
			constantPool[i] = new Constant();
			// 8-byte constants use two constant pool entries
			byte tag = constantPool[i].tag;
			if(tag==CONSTANT_DOUBLE || tag==CONSTANT_LONG) i++;
		}
	}

	private void parseAccessFlags() throws IOException
	{
		int accessFlags = in.readUnsignedShort();
		boolean isAbstract = ((accessFlags & ACC_ABSTRACT) != 0);
		boolean isInterface = ((accessFlags & ACC_INTERFACE) != 0);
	}
	
	private void parseClassName() throws IOException
	{
		int entryIndex = _unsigned();
		className = getClassConstantName(entryIndex);
		jClass.setName(className);
		jClass.setPackageName(getPackageName(className));
	}
	
	private void parseSuperClassName() throws IOException
	{
		int entryIndex = _unsigned();
		superClassName = getClassConstantName(entryIndex);
		addImport(getPackageName(superClassName));
	}

	private void parseInterfaces() throws IOException
	{
		int interfacesCount = _unsigned();
		String[] interfaceNames = new String[interfacesCount];
		for (int i=0;i<interfacesCount;i++)
		{
			int entryIndex = _unsigned();
			interfaceNames[i] = getClassConstantName(entryIndex);
			addImport(getPackageName(interfaceNames[i]));
		}
	}
	
	private void parseFields() throws IOException
	{
		int nb = _unsigned();
		
		for(int i=0;i<nb;i++)
		{
			FieldOrMethodInfo field = parseFieldOrMethodInfo();
			String descriptor = toUTF8(field.getDescriptorIndex());
			String[] types = descriptorToTypes(descriptor);
			for(int t=0;t<types.length;t++)
			addImport(getPackageName(types[t]));
		}
	}

	private void parseMethods() throws IOException
	{
		int nb = _unsigned();
		for (int i=0;i<nb;i++)
		{
			FieldOrMethodInfo method = parseFieldOrMethodInfo();
			String descriptor = toUTF8(method.getDescriptorIndex());
			String[] types = descriptorToTypes(descriptor);
			for (int t=0;t<types.length;t++)
			if(types[t].length()>0) addImport(getPackageName(types[t]));
		}
	}
	
	private class Constant
	{
		public byte tag;
		public int nameIndex = -1;
		public int typeIndex = -1;
		public Object value = null;
		
		public Constant() throws Exception
		{
			tag = _byte();
			switch (tag)
			{
//				public static final int CONSTANT_UTF8 = 1;
//				public static final int CONSTANT_UNICODE = 2;
//				public static final int CONSTANT_INTEGER = 3;
//				public static final int CONSTANT_FLOAT = 4;
//				public static final int CONSTANT_LONG = 5;
//				public static final int CONSTANT_DOUBLE = 6;
//				public static final int CONSTANT_CLASS = 7;
//				public static final int CONSTANT_STRING = 8;
//				public static final int CONSTANT_FIELD = 9;
//				public static final int CONSTANT_METHOD = 10;
//				public static final int CONSTANT_INTERFACEMETHOD = 11;
//				public static final int CONSTANT_NAMEANDTYPE = 12;
//				public static final int CONSTANT_METHODHANDLE = 15;
//				public static final int CONSTANT_METHODTYPE = 16;
//				public static final int CONSTANT_DYNAMIC = 17;   // Java 11
//				public static final int CONSTANT_INVOKEDYNAMIC = 18;
//				public static final int CONSTANT_MODULE = 19;    // Java 9
//				public static final int CONSTANT_PACKAGE = 20;   // Java 9
	
				case CONSTANT_UTF8:init(_UTF());break;
				case CONSTANT_UNICODE:init(_UTF());break;
				case CONSTANT_INTEGER:init(_int());break;
				case CONSTANT_FLOAT:init(_float());break;
				case CONSTANT_LONG:init(_long());break;
				case CONSTANT_DOUBLE:init(_double());break;
				case CONSTANT_CLASS:init(_unsigned());break;
				case CONSTANT_STRING:init(_unsigned());break;
				case CONSTANT_FIELD:_unsigned();_unsigned();break;
				case CONSTANT_METHOD:_unsigned();_unsigned();break;
				case CONSTANT_INTERFACEMETHOD:_unsigned();_unsigned();break;
				case CONSTANT_NAMEANDTYPE:init(_unsigned(),_unsigned());break;
				case CONSTANT_METHODHANDLE:init(_byte(),_unsigned());break;
				case CONSTANT_METHODTYPE:_unsigned();break;
				case CONSTANT_DYNAMIC:_unsigned();_unsigned();break;
				case (CONSTANT_INVOKEDYNAMIC):_unsigned();_unsigned();break;
				case CONSTANT_MODULE:init(_unsigned());break;
				case CONSTANT_PACKAGE:init(_unsigned());break;
				
				default: break;
			}
		}

		private void init(int _nameIndex)
		{nameIndex = _nameIndex;}

		private void init(Object _value)
		{value = _value;}

		private void init(int _nameIndex, int _typeIndex)
		{nameIndex = _nameIndex;typeIndex = _typeIndex;}
	}
	
	private FieldOrMethodInfo parseFieldOrMethodInfo() throws IOException
	{
		FieldOrMethodInfo result = new FieldOrMethodInfo(
				in.readUnsignedShort(),
				in.readUnsignedShort(), 
				in.readUnsignedShort());

		int attributesCount = in.readUnsignedShort();
		for(int a=0;a<attributesCount;a++) parseAttribute();
		return result;
	}

	private void parseAttributes() throws IOException
	{
		int attributesCount = in.readUnsignedShort();

		for(int i=0;i<attributesCount;i++)
		{
			AttributeInfo attribute = parseAttribute();
			// Section 4.7.7 of VM Spec - Class File Format
			if(attribute.name!=null)
				if(attribute.name.equals("SourceFile"))
				{
					byte[] b = attribute.value;
					int b0 = b[0] < 0 ? b[0] + 256 : b[0];
					int b1 = b[1] < 0 ? b[1] + 256 : b[1];
					int pe = b0 * 256 + b1;

					String descriptor = toUTF8(pe);
					jClass.setSourceFile(descriptor);
				}
		}
	}

	private AttributeInfo parseAttribute() throws IOException
	{
		AttributeInfo result = new AttributeInfo();
		int nameIndex = in.readUnsignedShort();
		
		if(nameIndex!=-1)
		result.name = toUTF8(nameIndex);

		int attributeLength = in.readInt();
		byte[] value = new byte[attributeLength];
		for(int b=0;b<attributeLength;b++)
		value[b] = in.readByte();
		
		result.value = value;
		return result;
	}
	
	private Constant getConstantPoolEntry(int entryIndex) throws IOException
	{
		if(entryIndex<0 || entryIndex>=constantPool.length)
		   throw new IOException("Illegal constant pool index : "+entryIndex+" (constantPool.length="+constantPool.length+")");
		return constantPool[entryIndex];
	}
	
	private void addClassConstantReferences() throws IOException
	{
		for(int j=1;j<constantPool.length;j++)
		{
			if(constantPool[j].tag==CONSTANT_CLASS)
			{
				String name = toUTF8(constantPool[j].nameIndex);
				addImport(getPackageName(name));
			}
			if(constantPool[j].tag==CONSTANT_DOUBLE || constantPool[j].tag==CONSTANT_LONG)
			j++;
		}
	}
	

	private void addAnnotationReferences(AttributeInfo annotation) throws IOException 
	{
		// JVM Spec 4.8.15
		byte[] data = annotation.value;
		int numAnnotations = u2(data, 0);
		int annotationIndex = 2;
		addAnnotationReferences(data, annotationIndex, numAnnotations);
	}

	private int addAnnotationReferences(byte[] data, int index, int numAnnotations) throws IOException 
	{
		int visitedAnnotations = 0;
		while (visitedAnnotations < numAnnotations) {
		int typeIndex = u2(data, index);
		int numElementValuePairs = u2(data, index = index + 2);
		addImport(getPackageName(toUTF8(typeIndex).substring(1)));
		int visitedElementValuePairs = 0;
		index += 2;
		while (visitedElementValuePairs < numElementValuePairs) {
		index = addAnnotationElementValueReferences(data, index = index + 2);
		visitedElementValuePairs++;
		}
		visitedAnnotations++;
		}
		return index;
	}
    
	private int addAnnotationElementValueReferences(byte[] data, int index) throws IOException
	{
    	byte tag = data[index];
    	index += 1;
    	switch (tag) {
        	case 'B':
        	case 'C':
        	case 'D':
        	case 'F':
        	case 'I':
        	case 'J':
        	case 'S':
    		case 'Z':
    		case 's':
    			index += 2;
    			break;
    			
    		case 'e':
    			int enumTypeIndex = u2(data, index);
    			addImport(getPackageName(toUTF8(enumTypeIndex).substring(1)));
    			index += 4;
    			break;
    			
    		case 'c':
    			int classInfoIndex = u2(data, index);
    			addImport(getPackageName(toUTF8(classInfoIndex).substring(1)));
    			index += 2;
    			break;
    			
    		case '@':
    			index = addAnnotationReferences(data, index, 1);
    			break;
    			
    		case '[':
    			int numValues = u2(data, index);
    			index = index + 2;
    			for (int i = 0; i < numValues; i++) {
    				index = addAnnotationElementValueReferences(data, index);
    			}
    			break;
    	}
    	return index;
    }

	private int u2(byte[] data, int index) 
	{
		return (data[index] << 8 & 0xFF00)  | (data[index+1] & 0xFF);
	}
	
	private String getClassConstantName(int entryIndex) throws IOException
	{
		Constant entry = getConstantPoolEntry(entryIndex);
		if (entry == null) return "";
		return slashesToDots(toUTF8(entry.nameIndex));
	}

	private String toUTF8(int entryIndex) throws IOException
	{
		Constant entry = getConstantPoolEntry(entryIndex);
		if (entry == null) return "";
		if(entry.tag==CONSTANT_UTF8) return (String) entry.value;
		throw new IOException("Constant pool entry is not a UTF8 type: "+entryIndex);
	}

	private void addImport(String importPackage)
	{
		if(importPackage!=null)
		jClass.addImportedPackage(new JavaPackage(importPackage));
	}

	private String slashesToDots(String s)
	{return s.replace('/', '.');}

	private String getPackageName(String s)
	{
		if(s.length()>0 && s.charAt(0)=='[')
		{
			String types[] = descriptorToTypes(s);
			if(types.length==0) return null; 
			s = types[0];
		}
		s = slashesToDots(s);
		int index = s.lastIndexOf(".");
		if(index>0) return s.substring(0,index);
		
		return "Default";
	}

	private String[] descriptorToTypes(String descriptor)
	{
		int typesCount = 0;
		for(int index=0;index<descriptor.length();index++)
		if(descriptor.charAt(index)==';') typesCount++;

		String types[] = new String[typesCount];

		int typeIndex = 0;
		for(int index=0;index<descriptor.length();index++)
		{
			int startIndex = descriptor.indexOf(CLASS_DESCRIPTOR,index);
			if(startIndex<0) break;
			
			index = descriptor.indexOf(';',startIndex+1);
			types[typeIndex++] = descriptor.substring(startIndex+1,index);
		}
		return types;
	}

	class FieldOrMethodInfo
	{
		private int _accessFlags;
		private int _nameIndex;
		private int _descriptorIndex;
		private AttributeInfo _runtimeVisibleAnnotations;

		FieldOrMethodInfo(int accessFlags, int nameIndex, int descriptorIndex)
		{
			_accessFlags = accessFlags;
			_nameIndex = nameIndex;
			_descriptorIndex = descriptorIndex;
		}

		int accessFlags(){return _accessFlags;}
		int getNameIndex(){return _nameIndex;}
		int getDescriptorIndex(){return _descriptorIndex;}
	}
	
	class AttributeInfo
	{
		public String name;
		public byte[] value;
	}
}