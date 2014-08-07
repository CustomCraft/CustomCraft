package com.disney.customcraft.transform;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.launchwrapper.IClassTransformer;
import scala.tools.asm.ClassReader;
import scala.tools.asm.ClassWriter;
import scala.tools.asm.Label;
import scala.tools.asm.MethodVisitor;
import scala.tools.asm.tree.ClassNode;
import scala.tools.asm.tree.MethodNode;

import com.disney.customcraft.CustomLoader;
import com.disney.customcraft.CustomUtil;
import com.disney.customcraft.handlers.LogHandler;

public class CustomClassTransformer implements IClassTransformer {

	//diagnostics
	int removed;
	int added;
	
	//classes
	public static List<ObfuscatedClass> classList;
	
	public static ObfuscatedClass World = new ObfuscatedClass("net.minecraft.world.World", "afn", null);
	public static ObfuscatedClass WorldServer = new ObfuscatedClass("net.minecraft.world.WorldServer", "mj", "com.disney.customcraft.transform.OverrideWorldServer");
	public static ObfuscatedClass WorldProvider = new ObfuscatedClass("net.minecraft.world.WorldProvider", "apa", "com.disney.customcraft.transform.OverrideWorldProvider");
	public static ObfuscatedClass EntityRenderer = new ObfuscatedClass("net.minecraft.client.renderer.EntityRenderer", "bll", "com.disney.customcraft.transform.OverrideEntityRenderer");	
	
	public static ObfuscatedMethod WorldServer_tick = new ObfuscatedMethod("tick", "b", "()V");
	public static ObfuscatedMethod WorldProvider_angle = new ObfuscatedMethod("calculateCelestialAngle", "a", "(JF)F");
	public static ObfuscatedMethod WorldProvider_moon = new ObfuscatedMethod("getMoonPhase", "a", "(J)I");
	public static ObfuscatedMethod EntityRenderer_update = new ObfuscatedMethod("updateLightmap", "h", "(F)V");
	
	/**
	 * Initialise the class/method lists before they are transformed.
	 */
	public static void init() {
		if (classList == null) {
			WorldServer.add(WorldServer_tick);
			WorldProvider.add(WorldProvider_angle);
			WorldProvider.add(WorldProvider_moon);
			EntityRenderer.add(EntityRenderer_update);
			
			classList = new ArrayList<ObfuscatedClass>();
			classList.add(WorldServer);
			classList.add(WorldProvider);
			classList.add(EntityRenderer);
		}
	}

	@Override
	public byte[] transform(String className, String newName, byte[] byteCode) {
		byte[] byteFinal = null;
		
		for(ObfuscatedClass oClass : classList) {
			if(oClass.isNameEqual(className)) {
				removed = 0;
				added = 0;
				
				byte[] byteReplace = CustomUtil.readDummyClass(oClass.fileLoc);
				
				if(byteReplace != null) {
					ClassNode classNode = new ClassNode(262144);
		    		ClassReader classReader = new ClassReader(byteCode);
		    		classReader.accept(classNode, 0);
		    		
		    		removeMethods(classNode, oClass);
		    		
		    		addMethods(classNode, oClass, byteReplace);
		    		
		    		ClassWriter classWriter = new ClassWriter(3);
					classNode.accept(classWriter);
		    		byteFinal = classWriter.toByteArray();
				}
			}
		}
		
		if (byteFinal != null) {
			LogHandler.log("Transformed class : " + className + ", added " + added + " methods, removed " + removed + " methods.");
    		return byteFinal;
    	}
		return byteCode;
	}
	
	/**
	 * Removes all methods contained in the provided ObfuscatedClass from the classNode
	 * @param classNode The node being transformed.
	 * @param oClass The ObfuscatedClass containing information on what to transform.
	 * @return <b>true</b> if the code is obfuscated.<br><b>false</b> otherwise.
	 */
	private boolean removeMethods(ClassNode classNode, ObfuscatedClass oClass) {
		boolean obfuscated = false;
		Iterator i = classNode.methods.iterator();
		while (i.hasNext()) {
			MethodNode method = (MethodNode)i.next();
			
			for(ObfuscatedMethod oMethod : oClass.methodList) {
				if(oMethod.isNameEqual(method.name) && oMethod.isDescEqual(method.desc)) {
					i.remove();
					obfuscated = oMethod.isObfuscated(method.name);
					removed++;
				}
			}
		}
		return obfuscated;
	}
	
	/**
	 * Adds all methods contained in the provided ObfuscatedClass from the byteReplace to the baseClass.
	 * @param baseClass The node being transformed.
	 * @param oClass The ObfuscatedClass containing information on what to transform.
	 * @param byteReplace The byte array containing the new transformed methods. 
	 * @param obfuscated Whether or not the code is obfuscated.
	 */
	private void addMethods(ClassNode baseClass, ObfuscatedClass oClass, byte[] byteReplace) {
		ClassNode classNode = new ClassNodeModified(262144, oClass);
		ClassReader classReader = new ClassReader(byteReplace);
		classReader.accept(classNode, 0);

		Iterator i = classNode.methods.iterator();
		while (i.hasNext()) {
			MethodNode method = (MethodNode)i.next();
			
			for(ObfuscatedMethod oMethod : oClass.methodList) {
				if(oMethod.isNameEqual(method.name) && oMethod.isDescEqual(method.desc)) {
					baseClass.methods.add(method);
					added++;
				}
			}
		}
	}
	
	public class ClassNodeModified extends ClassNode {
		int api;
		ObfuscatedClass oClass;
		boolean obfuscated;

		/**
		 * Creates a custom type of ClassNode that allows our override methods to be altered so that they fit into the replaced code. 
		 * @param api The ASM API version implemented, see ClassNode
		 * @param oClass The ObfuscatedClass containing information on what to transform.
		 * @param obfuscated Whether or not the code is obfuscated.
		 */
		public ClassNodeModified(int api, ObfuscatedClass oClass) {
			super();
			this.api = api;
			this.oClass = oClass;
			this.obfuscated = CustomLoader.obfuscated;
		}

		@Override
		public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
			MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);

			if (mv != null) {
				for(ObfuscatedMethod method : oClass.methodList) {
					if(method.isNameEqual(name) && method.isDescEqual(desc)) {
						mv = new MethodVisitor_Override_WorldClient(api, mv, oClass, obfuscated);
					}
				}
			}
			return mv;
		}

		public class MethodVisitor_Override_WorldClient extends MethodVisitor {
			ObfuscatedClass oClass;
			boolean obfuscated;
			
			/**
			 * Creates a custom type of MethodVisitor that allows our override methods to be altered so that they fit into the replaced code. 
			 * @param api The ASM API version implemented, see ClassNode
			 * @param mv The method visitor that this is based on.
			 * @param oClass The ObfuscatedClass containing information on what to transform.
			 * @param obfuscated Whether or not the code is obfuscated.
			 */
			public MethodVisitor_Override_WorldClient(int api, MethodVisitor mv, ObfuscatedClass oClass, boolean obfuscated) {
				super(262144, mv);
				this.oClass = oClass;
				this.obfuscated = obfuscated;
			}

			@Override
			public void visitFieldInsn(int opcode, String owner, String name, String desc) {
				if (owner.equals(oClass.fileLoc.replace('.', '/'))) {
					owner = oClass.getName(obfuscated).replace('.', '/');
				}
				super.visitFieldInsn(opcode, owner, name, desc);
			}

			@Override
			public void visitMethodInsn(int opcode, String owner, String name, String desc) {
				if(owner.equals(WorldServer.getName(obfuscated).replace('.', '/'))) {
					owner = World.getName(obfuscated).replace('.', '/');
				}
				if (owner.equals(oClass.fileLoc.replace('.', '/'))) {
					owner = oClass.getName(obfuscated).replace('.', '/');
				}
				super.visitMethodInsn(opcode, owner, name, desc);
			}

			@Override
			public void visitFrame(int type, int nLocal, Object[] local, int nStack, Object[] stack) {
				for (int index = 0; index < local.length; index++) {
					if (local[index] != null) {
						if (local[index].equals(oClass.fileLoc.replace('.', '/'))) {
							local[index] = oClass.getName(obfuscated).replace('.', '/');
						}
					}
				}
				super.visitFrame(type, nLocal, local, nStack, stack);
			}

			@Override
			public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index) {
				if (desc.equals("L" + oClass.fileLoc.replace('.', '/') + ";")) {
					desc = "L" + oClass.getName(obfuscated).replace('.', '/') + ";";
				}
				super.visitLocalVariable(name, desc, signature, start, end, index);
			}
		}
	}
	
	public static class ObfuscatedClass {
		String name;
		String nameObf;
		String fileLoc;
		List<ObfuscatedMethod> methodList = new ArrayList<ObfuscatedMethod>();
		
		/**
		 * Creates a structure containing information on how to transform a class.
		 * @param name The fullname of the class, including location.
		 * @param nameObf The obfuscated name of the class, see conf/packaged.srg
		 * @param fileLoc The location of the override file used to replace the class.
		 */
		public ObfuscatedClass(String name, String nameObf, String fileLoc) {
			this.name = name;
			this.nameObf = nameObf;
			this.fileLoc = fileLoc;
		}
		
		/**
		 * Adds an obfuscated method to the list of methods that should be replaced. 
		 * @param method An obfuscated method.
		 */
		public void add(ObfuscatedMethod method) {
			methodList.add(method);
		}
		
		/**
		 * Returns the name of the class taking into account whether or not the code is obfuscated.
		 * @param obfuscated Whether the code is obfuscated.
		 * @return The name of the class.
		 */
		public String getName(boolean obfuscated) {
			return obfuscated ? nameObf : name;
		}
		
		/**
		 * Returns whether the provided name matches either the the name or obfuscated name of this class.
		 * @param className The name of the class.
		 * @return <b>true</b> if one of the names match.<br><b>false</b> otherwise.
		 */
		public boolean isNameEqual(String className) {
			return (name.equals(className) || nameObf.equals(className));
		}
	}
	
	public static class ObfuscatedMethod {
		String name;
		String nameObf;
		String desc;
		
		/**
		 * Creates a structure containing information on replacing a method.
		 * @param name The name of the method to replace.
		 * @param nameObf The obfuscated name of the method.
		 * @param desc The ASM description of the method.
		 */
		public ObfuscatedMethod(String name, String nameObf, String desc) {
			this.name = name;
			this.nameObf = nameObf;
			this.desc = desc;
		}
		
		/**
		 * Returns whether the provided name matches either the the name or obfuscated name of this class.
		 * @param className The name of the class.
		 * @return <b>true</b> if one of the names match.<br><b>false</b> otherwise.
		 */
		public boolean isNameEqual(String className) {
			return (name.equals(className) || nameObf.equals(className));
		}
		
		/**
		 * Returns whether the provided description matches the description of this class.
		 * @param className The decription of the class.
		 * @return <b>true</b> if it matches.<br><b>false</b> otherwise.
		 */
		public boolean isDescEqual(String classDesc) {
			return desc.equals(classDesc);
		}
		
		/**
		 * Returns whether or not the provided className is obfuscated.
		 * @param className The className to check.
		 * @return <b>true</b> if it is obfuscated.<br><b>false</b> otherwise.
		 */
		public boolean isObfuscated(String className) {
			return name.equals(nameObf);
		}
	}	
}
