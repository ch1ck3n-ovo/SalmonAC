package com.ch1ck3n.salmonac.commands;

import com.ch1ck3n.salmonac.utils.SalmonPlayer;
import com.ch1ck3n.salmonac.SalmonAC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class SalmonACCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        SalmonAC instance = SalmonAC.getInstance();
        if( !(sender instanceof Player) ) return true;
        if( !sender.hasPermission("salmon.admin" ) ) {
            sender.sendMessage( instance.getPrefix() + "§c You don't have permission to do this!" );
            return true;
        }

        if( args.length == 0 ) {
            sendHelp(sender, instance);
        }else if( args.length == 1 ) {
            if( args[0].equalsIgnoreCase("broadcast") ) {
                instance.setBroadcast( !instance.shouldBroadcast() );
                sender.sendMessage( instance.getPrefix() + "§7 Broadcast is " + ( instance.shouldBroadcast() ? "§a§lON" : "§c§lOFF" ));
            }else if( args[0].equalsIgnoreCase("debug") ) {
                instance.setDebugMode( !instance.isDebugMode() );
                sender.sendMessage( instance.getPrefix() + "§7 DebugMode is " + ( instance.isDebugMode() ? "§a§lON" : "§c§lOFF" ));
            }else if( args[0].equalsIgnoreCase("ping") ) {
                SalmonPlayer salmonPlayer = instance.getPlayerManager().getPlayer( ((Player)sender).getPlayer() );
                sender.sendMessage( instance.getPrefix() + "§7 Your Ping is " + (salmonPlayer.getPing()));
            }else if( args[0].equalsIgnoreCase("verbose") ) {
                SalmonPlayer salmonPlayer = instance.getPlayerManager().getPlayer( ((Player)sender).getPlayer() );
                salmonPlayer.setVerboseStatus(!salmonPlayer.getVerboseStatus());
                sender.sendMessage( instance.getPrefix() + "§7 Verbose is " + ( salmonPlayer.getVerboseStatus() ? "§a§lON" : "§c§lOFF" ));
            }else {
                sendHelp(sender, instance);
            }
        }else if( args.length == 2 ) {
            if( args[0].equalsIgnoreCase("ping") ) {
                Player p = Bukkit.getPlayer( args[1] );
                sender.sendMessage( instance.getPrefix() + p.getName() + "§7's Ping is " + (SalmonAC.getInstance().getPlayerManager().getPlayer(p).getPing()));
            }else if( args[0].equalsIgnoreCase("reset") ) {
                Player p = Bukkit.getPlayer( args[1] );
                if( p == null ){
                    sender.sendMessage( instance.getPrefix() + "§e " + args[1] + "§7 is offline" );
                }else {
                    instance.getPlayerManager().getPlayer(p).checksVL = new HashMap<>();
                    instance.getPlayerManager().getPlayer(p).totalVL = 0;
                    sender.sendMessage(instance.getPrefix() + "§e " + p.getName() + "§7's vl are all reset");
                }
            }else {
                sendHelp(sender, instance);
            }
        }else{
            sendHelp(sender, instance);
        }
        return true;
    }

    public void sendHelp(CommandSender sender, SalmonAC instance) {
        sender.sendMessage(
                "\n§e§m-§e§l▶◀§e§m§l-----§r §fSalmonAC§7 " + instance.getDescription().getVersion() + " §e§m§l-----§e§l▶◀§e§m-§r\n " +
                        "\n§6/salmon §f- show helps" +
                        "\n§6/salmon broadcast §7- §fToggles broadcast" +
                        "\n§6/salmon debug §7- §fToggles debug mode" +
                        "\n§6/salmon ping <player> §7- §fTo get player's ping." +
                        "\n§6/salmon reset <player> §7- §fTo reset player's vl." +
                        "\n§6/salmon verbose §7- §fToggles verbose" +
                        "\n \n§e§m-§e§l▶◀§e§m§l---------------------§e§l▶◀§e§m-");
    }
}
