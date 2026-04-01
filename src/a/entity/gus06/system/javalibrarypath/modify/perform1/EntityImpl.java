package a.entity.gus06.system.javalibrarypath.modify.perform1;

//import jdk.internal.loader.NativeLibraries;
import java.lang.reflect.Field;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.VarHandle;
import java.util.Arrays;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20220312";}


	
	public EntityImpl() throws Exception
	{}
	

	public void p(Object obj) throws Exception
	{
		setJavaLibraryPathProp((String) obj);
	}
	
	private void setJavaLibraryPathProp(String value) throws Exception
	{
//		System.setProperty("java.library.path",value);
//	 	resetSysPathJRE8();
//		resetSysPathJRE12();
//		resetSysPathJRE17();
	}
	
	private void resetSysPathJRE8() throws Exception
	{
//		final Field field = ClassLoader.class.getDeclaredField("sys_paths");
//		field.setAccessible(true);
//		field.set(null,null);
	}
	
	private void resetSysPathJRE12() throws Exception
	{
//		Lookup cl = MethodHandles.privateLookupIn(ClassLoader.class, MethodHandles.lookup());
//		VarHandle sys_paths = cl.findStaticVarHandle(ClassLoader.class, "sys_paths", String[].class);
//		sys_paths.set(null);
	}
	
	private void resetSysPathJRE17() throws Exception
	{
//		final Class<?>[] declClassArr = NativeLibraries.class.getDeclaredClasses();
//		final Class<?> libraryPaths = Arrays.stream(declClassArr).filter(klass -> klass.getSimpleName().equals("LibraryPaths")).findFirst().get();
//		final Field field = libraryPaths.getDeclaredField("USER_PATHS");
//		final MethodHandles.Lookup lookup = MethodHandles.privateLookupIn(Field.class, MethodHandles.lookup());
//		final VarHandle varHandle = lookup.findVarHandle(Field.class, "modifiers", int.class);
//		varHandle.set(field, field.getModifiers() & ~Modifier.FINAL);
	}
	
	
	/*
	 * changer directement la valeur de usr_paths dans la classe ClassLoader
	 */
	
	private void addLibraryPath2(String pathToAdd) throws Exception
	{
//		final Field usrPathsField = ClassLoader.class.getDeclaredField("usr_paths");
//		usrPathsField.setAccessible(true);
//	 
//		//get array of paths
//		final String[] paths = (String[])usrPathsField.get(null);
//	 
//		//check if the path to add is already present
//		for(int i=0;i<paths.length;i++)
//		if(paths[i].equals(pathToAdd)) return;
//	 
//		//add the new path
//		final String[] newPaths = Arrays.copyOf(paths,paths.length+1);
//		newPaths[newPaths.length-1] = pathToAdd;
//		usrPathsField.set(null,newPaths);
	}
}