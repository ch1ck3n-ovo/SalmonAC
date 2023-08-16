package com.ch1ck3n.salmonac.events;

import com.ch1ck3n.salmonac.SalmonAC;
import com.ch1ck3n.salmonac.utils.SalmonPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryEventListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        SalmonPlayer salmonPlayer = getCustomPlayer( (Player)e.getWhoClicked() );
        if( salmonPlayer == null ) return;

        salmonPlayer.guiClickedTick = 0;

        if( e.getCurrentItem() != null ) salmonPlayer.guiClickedCounter.onTick();
    }

    public SalmonPlayer getCustomPlayer(Player player){
        return SalmonAC.getInstance().getPlayerManager().getPlayer( player );
    }
}
