package a.entity.gus06.data.perform.add;

import a.framework.*;
import java.util.List;
import java.util.Set;
import java.awt.Rectangle;
import javax.swing.text.JTextComponent;
import javax.swing.text.Document;
import java.awt.Container;
import java.io.File;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20160128";}
	
	
	private Service performNumber;
	private Service performList;
	private Service performSet;
	private Service performRect;
	private Service performContainer;
	private Service performFile;
	
	private Service performIntArray;
	private Service performLongArray;
	private Service performDoubleArray;
	private Service performFloatArray;
	
	private Service performIntMatrix;
	private Service performLongMatrix;
	private Service performDoubleMatrix;
	private Service performFloatMatrix;
	
	
	public EntityImpl() throws Exception
	{
		performNumber = Outside.service(this,"gus06.math.number.add2");
		performList = Outside.service(this,"gus06.list.add");
		performSet = Outside.service(this,"gus06.set.add");
		performRect = Outside.service(this,"gus06.awt.rectangle.add");
		performContainer = Outside.service(this,"gus06.awt.container.add");
		performFile = Outside.service(this,"gus06.dirfile.path.addpart");
		
		performIntArray = Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.sum.motley.intarray");
		performLongArray = Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.sum.motley.longarray");
		performDoubleArray = Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.sum.motley.doublearray");
		performFloatArray = Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.sum.motley.floatarray");
		
		performIntMatrix = Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.sum.motley.intmatrix");
		performLongMatrix = Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.sum.motley.longmatrix");
		performDoubleMatrix = Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.sum.motley.doublematrix");
		performFloatMatrix = Outside.service(this,"gus06.sys.parser3.resolver1.op.seq.sum.motley.floatmatrix");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		
		if(input instanceof List)		{performList.p(o);return;}
		if(input instanceof Set)		{performSet.p(o);return;}
		if(input instanceof Rectangle)		{performRect.p(o);return;}
		if(input instanceof StringBuffer)	{performSb(o);return;}
		if(input instanceof JTextComponent)	{performTextComp(o);return;}
		if(input instanceof Container)		{performContainer.p(o);return;}
		
		throw new Exception("Invalid data type: "+input.getClass().getName());
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		
		if(input instanceof String)		return ((String) input)+o[1];
		if(input instanceof Number)		return add((Number) input,(Number) o[1]);
		if(input instanceof List)		return performList.t(obj);
		if(input instanceof Set)		return performSet.t(obj);
		if(input instanceof File)		return performFile.t(obj);
		
		if(input instanceof int[][])		return performIntMatrix.t(obj);
		if(input instanceof long[][])		return performLongMatrix.t(obj);
		if(input instanceof double[][])		return performDoubleMatrix.t(obj);
		if(input instanceof float[][])		return performFloatMatrix.t(obj);
		
		if(input instanceof int[])		return performIntArray.t(obj);
		if(input instanceof long[])		return performLongArray.t(obj);
		if(input instanceof double[])		return performDoubleArray.t(obj);
		if(input instanceof float[])		return performFloatArray.t(obj);
		
		throw new Exception("Invalid data type: "+input.getClass().getName());
	}
	
	
	private void performSb(Object[] o)
	{
		StringBuffer sb = (StringBuffer) o[0];
		String s = (String) o[1];
		
		sb.append(s);
	}
	
	
	private void performTextComp(Object[] o) throws Exception
	{
		JTextComponent comp = (JTextComponent) o[0];
		String s = (String) o[1];
		
		Document doc = comp.getDocument();
		int len = doc.getLength();
		doc.insertString(len,s,null);
	}
	
	
	private Object add(Number n1, Number n2) throws Exception
	{return performNumber.t(new Object[]{n1,n2});}
}
