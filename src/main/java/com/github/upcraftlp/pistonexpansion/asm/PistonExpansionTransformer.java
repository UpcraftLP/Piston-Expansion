package com.github.upcraftlp.pistonexpansion.asm;

import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import java.util.Iterator;

@SuppressWarnings("unused")
@IFMLLoadingPlugin.MCVersion(Loader.MC_VERSION)
public class PistonExpansionTransformer implements IClassTransformer {

    @Override
    public byte[] transform(String name, String deobfClassName, byte[] basicClass) {
        if(deobfClassName.equals("net.minecraft.block.state.BlockPistonStructureHelper")) {

            //setup
            PistonExpansionLoadingPlugin.getLog().debug("transforming class {}...", deobfClassName);
            ClassNode cn = new ClassNode();
            ClassReader cr = new ClassReader(basicClass);
            cr.accept(cn, 0);
            Iterator<MethodNode> methods = cn.methods.iterator();

            //transform
            String methodName = PistonExpansionLoadingPlugin.isDeobfuscatedEnvironment() ? "addBlockLine" : "func_177251_a";
            while(methods.hasNext()) {
                MethodNode node = methods.next();
                PistonExpansionLoadingPlugin.getLog().error(node.name);
                if(node.name.equals(methodName)) {
                    for(int i = 0; i < node.instructions.size(); i++) {
                        AbstractInsnNode abstractInsnNode = node.instructions.get(i);
                        if(abstractInsnNode instanceof IntInsnNode) {
                            IntInsnNode intNode = (IntInsnNode) abstractInsnNode;
                            if(intNode.getOpcode() == Opcodes.BIPUSH && intNode.operand == 12) { //lines 105, 124 and 180
                                InsnList instructionsList = new InsnList();
                                instructionsList.add(new VarInsnNode(Opcodes.ALOAD, 0)); //load "this"
                                instructionsList.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "com/github/upcraftlp/pistonexpansion/GameRuleHandler", "getPistonPushLimit", "(Lnet/minecraft/block/state/BlockPistonStructureHelper;)I", false));
                                node.instructions.insertBefore(abstractInsnNode, instructionsList);
                                node.instructions.remove(abstractInsnNode);
                                i++;
                            }
                        }
                    }
                }
            }

            //asm specific for cleaning up and returning the final bytes for JVM processing
            ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
            cn.accept(writer);
            basicClass = writer.toByteArray();
            PistonExpansionLoadingPlugin.getLog().debug("successfully transformed {}!", deobfClassName);
        }
        return basicClass;
    }
    
}
