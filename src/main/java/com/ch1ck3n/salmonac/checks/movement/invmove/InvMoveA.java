package com.ch1ck3n.salmonac.checks.movement.invmove;

import com.ch1ck3n.salmonac.SalmonAC;
import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.utils.SalmonPlayer;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InvMoveA extends Check {
    public InvMoveA(String name, Category category, Punishment punishment, String description) {
        super(name, category, punishment, description);
        this.setType("Sprint");
        this.setSubCategory("InvMove");
        this.setVlPerFail(2.0f);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        SalmonPlayer salmonPlayer = SalmonAC.getInstance().getPlayerManager().getPlayer((Player) e.getWhoClicked());

        // Type A
        // Click gui while sprinting
        if( salmonPlayer.getPlayer().getGameMode() == GameMode.CREATIVE ) return;

        // Check
        if (salmonPlayer.getPlayer().isSprinting()) {
            flag(salmonPlayer.getPlayer(), "Sprinting = " + salmonPlayer.getPlayer().isSprinting() );
        }
    }
}
