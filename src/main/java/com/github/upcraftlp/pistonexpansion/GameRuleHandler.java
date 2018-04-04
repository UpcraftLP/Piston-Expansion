package com.github.upcraftlp.pistonexpansion;

import com.github.upcraftlp.pistonexpansion.config.PistonExpansionConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.BlockPistonExtension;
import net.minecraft.block.BlockPistonMoving;
import net.minecraft.block.state.BlockPistonStructureHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = PistonExpansion.MODID)
public class GameRuleHandler {

    private static final String PISTON_PUSH_LIMIT = "pistonPushLimit";

    @SubscribeEvent
    public static void onWorldInit(WorldEvent.Load event) {
        GameRules gamerules = event.getWorld().getGameRules();
        if(!gamerules.hasRule(PISTON_PUSH_LIMIT)) {
            gamerules.addGameRule(PISTON_PUSH_LIMIT, Integer.toString(PistonExpansionConfig.defaultPistonPushLimit), GameRules.ValueType.NUMERICAL_VALUE);
            PistonExpansion.getLogger().info("added game rule {} to world.", PISTON_PUSH_LIMIT);
        }
    }

    @SuppressWarnings("unused")
    public static int getPistonPushLimit(BlockPistonStructureHelper pistonHelper) {
        World world = pistonHelper.world;
        IBlockState state = world.getBlockState(pistonHelper.pistonPos);
        Block piston = state.getBlock();
        if(PistonExpansionConfig.ignoreSticky && (piston instanceof BlockPistonBase && ((BlockPistonBase) piston).isSticky) || (piston instanceof BlockPistonMoving && state.getValue(BlockPistonMoving.TYPE) == BlockPistonExtension.EnumPistonType.STICKY)) return 12; //FIXME workaround for sticky pistons retracting
        return MathHelper.clamp(world.getGameRules().getInt(PISTON_PUSH_LIMIT), 0, PistonExpansionConfig.maxPistonPushLimit);
    }
}
