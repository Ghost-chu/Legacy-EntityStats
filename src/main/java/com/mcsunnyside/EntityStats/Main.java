package com.mcsunnyside.EntityStats;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class Main extends JavaPlugin {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!sender.hasPermission("entitystats.use"))
            return false;
        if(args.length < 1) {
            sender.sendMessage("请使用/es <生物名称>");
            return true;
        }
        EntityType type = null;
        try {
            type = EntityType.valueOf(args[0]);
        }catch (IllegalArgumentException e){
            sender.sendMessage("生物不存在");
            return true;
        }
        sender.sendMessage("Please wait...");
        for (World world : Bukkit.getWorlds()){
            sender.sendMessage("World: "+world.getName());
            for (Chunk chunk : world.getLoadedChunks().clone()){
                int cost = 0;
                for (Entity entitys : chunk.getEntities().clone()){
                    if(entitys.getType().equals(type))
                        cost ++;
                }
                if(cost > 0){
                    sender.sendMessage("Chunk "+chunk.getX()+","+chunk.getZ()+" have "+cost+type.name()+"(s)");
                }
            }
        }
        sender.sendMessage("统计完毕");


        return true;
    }
}
