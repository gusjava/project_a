package a.entity.gus.y.entityeditor1.builddata;

import java.io.File;
import java.sql.Connection;
import java.util.List;
import java.util.Set;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240113";}

	private Service findPackageDir;
	private Service findJavaFiles;
	private Service findMainJavaFile;
	private Service findDocFile;
	private Service findDownLinks;
	private Service findUpLinks;
	private Service findServices;
	private Service findResources;
	private Service findNamesSameAs;
	private Service findCompileErr;
	private Service findXyzErr;
	private Service findMissingLink;

	public EntityImpl() throws Exception
	{
		findPackageDir = Outside.service(this, "gus.x.entity.src.find.packagedir");
		findJavaFiles = Outside.service(this, "gus.x.dir.listing0.files.java");
		findMainJavaFile = Outside.service(this, "gus.x.entity.src.find.entityfile");
		findDocFile = Outside.service(this, "gus.x.entity.doc1.fr.find.file");
		findDownLinks = Outside.service(this, "gus.y.entitydb1.entity_link.find2");
		findUpLinks = Outside.service(this, "gus.y.entitydb1.entity_link.find1");
		findServices = Outside.service(this, "gus.y.entitydb1.entity_service.find");
		findResources = Outside.service(this, "gus.y.entitydb1.entity_resource.find");
		findNamesSameAs = Outside.service(this, "gus.y.entitydb1.entity.names.sameas");
		findCompileErr = Outside.service(this, "gus.y.entitydb1.entity_compile_err.infos.w_name");
		findXyzErr = Outside.service(this, "gus.y.entitydb1.entity_xyz_err.find");
		findMissingLink = Outside.service(this, "gus.y.entitydb1.entity_missing_link.find");
	}

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 2) throw new Exception("Invalid data number: " + o.length);
		return new Holder(o[0], (String) o[1]);
	}

	private class Holder extends S1 implements R, V, F, E
	{
		private Object engine;
		private String entityName;

		private File packageDir;
		private File[] javaFiles;
		private File mainJavaFile;
		private File docFile;
		
		private Set downLinks;
		private Set upLinks;
		private Set services;
		private Set resources;
		
		private List same;
		private List compileErrList;
		private List xyzErrList;
		private List missingLinkList;

		private Object infos;

		public Holder(Object engine, String entityName)
		{
			this.engine = engine;
			this.entityName = entityName;
		}

		public boolean f(Object obj) throws Exception
		{
			return ((F) engine).f(obj);
		}

		public void v(String key, Object obj) throws Exception
		{
			if (key.equals("srcSaved"))
			{
				((V) engine).v("srcSaved", entityName);
				return;
			}
			if (key.equals("srcCleared"))
			{
				((V) engine).v("srcCleared", entityName);
				return;
			}
			if (key.equals("srcModified"))
			{
				handleSrcModified(obj);
				return;
			}
			if (key.equals("fileAdded"))
			{
				handleFileAdded(obj);
				return;
			}
			if (key.equals("fileDeleted"))
			{
				handleFileDeleted(obj);
				return;
			}
			if (key.equals("fileRenamed"))
			{
				handleFileRenamed(obj);
				return;
			}
			if (key.equals("fileDuplicated"))
			{
				handleFileDuplicated(obj);
				return;
			}
			if (key.equals("select"))
			{
				((V) engine).v("select", obj);
				return;
			}
			throw new Exception("Unsupported key: " + key);
		}

		public void e() throws Exception
		{
			clearData();
		}

		public Object r(String key) throws Exception
		{
			if (key.equals("engine")) return engine;
			if (key.equals("entityName")) return entityName;
			if (key.equals("cx")) return cx();
			if (key.equals("rootDir")) return rootDir();
			if (key.equals("packageDir")) return packageDir();
			if (key.equals("javaFiles")) return javaFiles();
			if (key.equals("mainJavaFile")) return mainJavaFile();
			if (key.equals("docFile")) return docFile();
			if (key.equals("downLinks")) return downLinks();
			if (key.equals("upLinks")) return upLinks();
			if (key.equals("services")) return services();
			if (key.equals("resources")) return resources();
			if (key.equals("same")) return same();
			if (key.equals("compileErrList")) return compileErrList();
			if (key.equals("xyzErrList")) return xyzErrList();
			if (key.equals("missingLinkList")) return missingLinkList();
			if (key.equals("infos")) return infos;
			
			if (key.equals("keys")) return new String[] { 
			"engine", "entityName", "cx", "rootDir", 
			"packageDir", "javaFiles", "mainJavaFile", "docFile", 
			"downLinks", "upLinks", 
			"services", "resources", 
			"compileErrList", 
			"xyzErrList", 
			"missingLinkList", 
			"infos" };
			
			throw new Exception("Uknown key: " + key);
		}

		private Connection cx() throws Exception
		{
			return (Connection) ((R) engine).r("cx");
		}

		private File rootDir() throws Exception
		{
			return (File) ((R) engine).r("rootDir");
		}

		private File packageDir() throws Exception
		{
			if (packageDir == null)
				packageDir = (File) findPackageDir.t(new Object[] { rootDir(), entityName });
			return packageDir;
		}

		private File[] javaFiles() throws Exception
		{
			if (javaFiles == null)
				javaFiles = (File[]) findJavaFiles.t(packageDir());
			return javaFiles;
		}
		
		private File mainJavaFile() throws Exception
		{
			if (mainJavaFile == null)
				mainJavaFile = (File) findMainJavaFile.t(packageDir());
			return mainJavaFile;
		}

		private File docFile() throws Exception
		{
			if (docFile == null)
				docFile = (File) findDocFile.t(new Object[] { rootDir(), entityName });
			return docFile;
		}

		private Set downLinks() throws Exception
		{
			if (downLinks == null)
				downLinks = (Set) findDownLinks.t(new Object[] { cx(), entityName });
			return downLinks;
		}

		private Set upLinks() throws Exception
		{
			if (upLinks == null)
				upLinks = (Set) findUpLinks.t(new Object[] { cx(), entityName });
			return upLinks;
		}

		private Set services() throws Exception
		{
			if (services == null)
				services = (Set) findServices.t(new Object[] { cx(), entityName });
			return services;
		}

		private Set resources() throws Exception
		{
			if (resources == null)
				resources = (Set) findResources.t(new Object[] { cx(), entityName });
			return resources;
		}

		private List same() throws Exception
		{
			if (same == null)
				same = (List) findNamesSameAs.t(new Object[] { cx(), entityName });
			return same;
		}

		private List compileErrList() throws Exception
		{
			if (compileErrList == null)
				compileErrList = (List) findCompileErr.t(new Object[] { cx(), entityName });
			return compileErrList;
		}

		private List xyzErrList() throws Exception
		{
			if (xyzErrList == null)
				xyzErrList = (List) findXyzErr.t(new Object[] { cx(), entityName });
			return xyzErrList;
		}

		private List missingLinkList() throws Exception
		{
			if (missingLinkList == null)
				missingLinkList = (List) findMissingLink.t(new Object[] { cx(), entityName });
			return missingLinkList;
		}

		private void clearData()
		{
			javaFiles = null;
			mainJavaFile = null;
			downLinks = null;
			upLinks = null;
			services = null;
			resources = null;
			compileErrList = null;
			xyzErrList = null;
			missingLinkList = null;
		}

		/*
		 * HANDLE EVENTS
		 */

		private void handleSrcModified(Object infos) throws Exception
		{
			this.infos = infos;
			((V) engine).v("entityModified", entityName);
			clearData();
			srcModified();
		}

		private void handleFileAdded(Object infos) throws Exception
		{
			this.infos = infos;
			((V) engine).v("entityModified", entityName);
			clearData();
			fileAdded();
		}

		private void handleFileDeleted(Object infos) throws Exception
		{
			this.infos = infos;
			((V) engine).v("entityModified", entityName);
			clearData();
			fileDeleted();
		}

		private void handleFileRenamed(Object infos) throws Exception
		{
			this.infos = infos;
			((V) engine).v("entityModified", entityName);
			clearData();
			fileRenamed();
		}

		private void handleFileDuplicated(Object infos) throws Exception
		{
			this.infos = infos;
			((V) engine).v("entityModified", entityName);
			clearData();
			fileDuplicated();
		}

		/*
		 * GENERATE EVENTS
		 */

		private void srcModified()
		{send(this, "srcModified()");}

		private void fileAdded()
		{send(this, "fileAdded()");}

		private void fileRenamed()
		{send(this, "fileRenamed()");}

		private void fileDuplicated()
		{send(this, "fileDuplicated()");}

		private void fileDeleted()
		{send(this, "fileDeleted()");}
	}
}
