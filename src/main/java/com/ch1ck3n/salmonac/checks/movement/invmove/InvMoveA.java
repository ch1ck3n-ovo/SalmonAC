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

public class InvMoveA extends Check {
    public InvMoveA(String name, Response response, Punishment punishment, String description) {
        super(name, response, punishment, description);
        this.setType("Sprinting");
        this.setVlPerFail(2.0f);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        SalmonPlayer salmonPlayer = SalmonAC.getInstance().getPlayerManager().getPlayer((Player) e.getWhoClicked());

        // Type A
        // Click gui while sprinting
        if (salmonPlayer.getPlayer().getGameMode() == GameMode.CREATIVE) return;

        // Check
        if (salmonPlayer.getPlayer().isSprinting()) {
            flag(salmonPlayer.getPlayer(), "SprintingA = " + salmonPlayer.getPlayer().isSprinting() +
                    (this.getResponse() == Response.CANCEL ? "\n\nEvent is cancelled" : ""));
            if (this.getResponse() == Response.CANCEL) {
                e.setCancelled(true);
            }
        }
    }
}
