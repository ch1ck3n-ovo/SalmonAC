package com.ch1ck3n.salmonac.checks.movement.invmove;

import com.ch1ck3n.salmonac.SalmonAC;
import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.utils.SalmonPlayer;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InvMoveE extends Check {
    public InvMoveE(String name, Response response, Punishment punishment, String description) {
        super(name, response, punishment, description);
        this.setType("Placing Block");
        this.setVlPerFail(2.0f);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        SalmonPlayer salmonPlayer = SalmonAC.getInstance().getPlayerManager().getPlayer( (Player)e.getWhoClicked() );

        // Type E
        // Click gui while placing block
        if( salmonPlayer.getPlayer().getGameMode() == GameMode.CREATIVE ) return;

        // Check
        if (salmonPlayer.getPlaceBlockTick() == 0) {
            flag(salmonPlayer.getPlayer(), "PlaceBlockTick = " + salmonPlayer.getPlaceBlockTick() +
                    (this.getResponse() == Response.CANCEL ? "\n\nEvent is cancelled" : ""));
            if (this.getResponse() == Response.CANCEL) {
                e.setCancelled(true);
            }
        }
    }
}
