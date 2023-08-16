package com.ch1ck3n.salmonac.managers;

import com.ch1ck3n.salmonac.utils.SalmonPlayer;
import org.bukkit.entity.Player;

import java.util.concurrent.ConcurrentLinkedQueue;

public class PlayerManager {

    ConcurrentLinkedQueue<SalmonPlayer> players = new ConcurrentLinkedQueue<>();

    public PlayerManager(){

    }

    @Deprecated
    public SalmonPlayer getPlayerNullIfNotRegistered(Player player){
        for( SalmonPlayer salmonPlayer : players ){
            if( salmonPlayer.getPlayer() == player ){
                return salmonPlayer;
            }
        }
        return null;
    }

    public SalmonPlayer getPlayer(Player player){
        SalmonPlayer salmonPlayer = getPlayerNullIfNotRegistered( player );
        if( salmonPlayer != null ){
            return salmonPlayer;
        }
        return registerPlayer( player );
    }

    @Deprecated
    public SalmonPlayer registerPlayer(Player player){
        SalmonPlayer salmonPlayer = new SalmonPlayer( player );
        players.add(salmonPlayer);
        return salmonPlayer;
    }

    @Deprecated
    public void unregisterPlayer(Player player){
        SalmonPlayer salmonPlayer = getPlayerNullIfNotRegistered( player );
        if( salmonPlayer != null ){
            players.remove(salmonPlayer);
        }
    }

}
