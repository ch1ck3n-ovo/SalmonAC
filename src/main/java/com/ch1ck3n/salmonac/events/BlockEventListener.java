package com.ch1ck3n.salmonac.events;

import com.ch1ck3n.salmonac.SalmonAC;
import com.ch1ck3n.salmonac.utils.SalmonPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockEventListener implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        SalmonPlayer salmonPlayer = getCustomPlayer(e.getPlayer());
        if (salmonPlayer == null) return;

        salmonPlayer.placeBlockTick = 0;
    }

    public SalmonPlayer getCustomPlayer(Player player){
        return SalmonAC.getInstance().getPlayerManager().getPlayer(player);
    }
}
