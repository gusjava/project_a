package a.entity.gus06.sys.filemanagement1.gui.gui1_4.analyze.doubloons2;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.io.PrintStream;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20191215";}


	private Service buildFileMap;
	private Service factoryGui;
	private Service perform2;
	private Service formatSize;
	
	
	private Object engine;

	public EntityImpl() throws Exception
	{
		buildFileMap = Outside.service(this,"gus06.sys.filemanagement1.tool.scan.filemap.latest");
		factoryGui = Outside.service(this,"*gus06.data.factory.gui1");
		perform2 = Outside.service(this,"gus06.sys.filemanagement1.tool.doubloon.perform2");
		formatSize = Outside.service(this,"gus06.string.transform.format.datasize.fr");
		
		G factory = this::build;
		
		factoryGui.v("buttonText","Search for doubloons");
		factoryGui.p(factory);
	}
	
	
	public Object i() throws Exception
	{return factoryGui.i();}
	
	
	public void p(Object obj) throws Exception
	{
		engine = obj;
	}
	
	
	private Object build() throws Exception
	{
		if(engine==null) return null;
		
		Map fileMap = (Map) buildFileMap.t(engine);
		Map result = (Map) perform2.t(new Object[]{engine, fileMap});
		
		return result;
	}
}