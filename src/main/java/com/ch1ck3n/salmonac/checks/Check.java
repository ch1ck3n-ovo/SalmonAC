package com.ch1ck3n.salmonac.checks;

import com.ch1ck3n.salmonac.SalmonAC;
import com.ch1ck3n.salmonac.managers.CheckManager;
import com.ch1ck3n.salmonac.utils.SalmonPlayer;
import com.ch1ck3n.salmonac.managers.PlayerManager;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Check implements Listener {

    Category category;
    public Category getCategory() {
        return category;
    }
    String description;
    public String getDesc(){
        return description;
    }
    float maxVL;
    public float getMaxVL(){
        return maxVL;
    }
    public void setMaxVL(float f){
        maxVL = f;
    }
    String name;
    public String getCheckName(){
        return name;
    }
    Punishment punishment;
    String subCategory = "";
    public String getSubCategory(){
        return subCategory;
    }
    public void setSubCategory(String s){
        subCategory = s;
    }
    String type = "";
    public String getType(){
        return type;
    }
    public void setType(String s){
        type = s;
    }
    float vlPerFail;
    public void setVlPerFail(float f){
        vlPerFail = f;
    }

    public enum Category {
        COMBAT("Combat"), MOVEMENT("Movement"),
        PLAYER("Player"), WORLD("World");
        private String s;
        Category(String s) {
            this.s = s;
        }
    }
    public enum Punishment { NONE, KICK, BAN }

    public Check(String name, Category category, Punishment punishment, String description){
        this.category = category;
        this.description = description;
        this.maxVL = 10.0f;
        this.name = name;
        this.punishment = punishment;
        this.vlPerFail = 1.0f;
    }

    public void debug(Player p, String info) {
        SalmonAC instance = SalmonAC.getInstance();
        if( instance.isDebugMode() ) {
            info = info.replace("/", "§7/§r");
            p.sendMessage("§8[§d" + this.name + "§8] §r" + info);
        }
    }

    public void flag(Player p){
        send(p,"NONE");
    }

    public void flag(Player p, String info) {
        send(p,info);
    }

    private void send(Player p,String info){
        CheckManager checkManager = SalmonAC.getInstance().getCheckManager();
        PlayerManager playerManager = SalmonAC.getInstance().getPlayerManager();
        SalmonPlayer violator = playerManager.getPlayer( p );
        float vl = violator.onFailed( String.valueOf(name), vlPerFail );
        String message = SalmonAC.getInstance().getPrefix() + " §e" +
                p.getName() + "§7 failed §e" +
                (getSubCategory() != "" && !name.contains(getSubCategory()) ? getSubCategory() + "/" : "") + name +
                " §8[§7x§f" + String.format("%.1f", vl) + "§8]";
        TextComponent textComponent = new TextComponent( message );
        textComponent.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT ,
                new ComponentBuilder(
                        "§7Description: \n§r§f" + description +
                                "\n\n§7Information: \n§r§f" + info +
                                "\n\n§7Category: §f" + category.s + (getSubCategory() != "" ? "." + getSubCategory() : "") +
                                "\n§7Type: §f" + type +
                                "\n§7CheckVL: §f" + String.format("%.1f", vl) + "/" + maxVL + " §c(+" + vlPerFail + ")" +
                                "\n§7Click To Teleport" ).create() ) );
        textComponent.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND , "/tp " + p.getName() ) );

        for( Player player : Bukkit.getOnlinePlayers() ) {
            if( playerManager.getPlayer( player ).getVerboseStatus() ) {
                player.spigot().sendMessage( textComponent );
            }
        }

        if( vl >= maxVL && vl > 0 ){
            violator.resetCheckVL(name);
            Bukkit.getScheduler().runTask( SalmonAC.getInstance(), new Runnable() {
                @Override
                public void run() {
                    SalmonAC instance = SalmonAC.getInstance();
                    String reason = instance.getPrefix() + " §4Unfair Advantage";
                    if ( punishment == Punishment.KICK ) {
                        String message = instance.getPrefix() + " " + p.getName() + "§7 is §ckicked §7for §4Unfair Advantage";
                        if( !instance.isDebugMode() ) {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kick " + p.getName() + " " + reason);
                            broadcast(instance, message);
                        }else{
                            p.sendMessage(instance.getPrefix() + "§7 You are §ckicked §7for §e" +
                                    (getSubCategory() != "" && !name.contains(getSubCategory()) ? getSubCategory() + "/" : "") + name );
                        }
                    }else if ( punishment == Punishment.BAN ) {
                        String message = instance.getPrefix() + " " + p.getName() + "§7 is §cbanned §7for §4Unfair Advantage";
                        if( !instance.isDebugMode() ) {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ban " + p.getName() + " " + reason);
                            broadcast(instance, message);
                        }else{
                            p.sendMessage(instance.getPrefix() + "§7 You are §cbanned §7for §e" +
                                    (getSubCategory() != "" && !name.contains(getSubCategory()) ? getSubCategory() + "/" : "") + name );
                        }
                    }
                }
            });
        }
    }

    public void broadcast(SalmonAC instance, String s) {
        if( instance.shouldBroadcast() ) {
            Bukkit.getConsoleSender().sendMessage(s);
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.spigot().sendMessage(new TextComponent(s));
            }
        }
    }
}
