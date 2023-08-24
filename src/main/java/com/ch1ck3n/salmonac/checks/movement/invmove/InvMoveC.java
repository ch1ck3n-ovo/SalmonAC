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

public class InvMoveC extends Check {
    public InvMoveC(String name, Category category, Punishment punishment, String description) {
        super(name, category, punishment, description);
        this.setType("Attack");
        this.setSubCategory("InvMove");
        this.setVlPerFail(2.0f);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        SalmonPlayer salmonPlayer = SalmonAC.getInstance().getPlayerManager().getPlayer( (Player)e.getWhoClicked() );

        // Type C
        // Click gui while attacking
        if( salmonPlayer.getPlayer().getGameMode() == GameMode.CREATIVE ) return;

        // Check
        if (salmonPlayer.getAttackTick() == 0) {
            flag(salmonPlayer.getPlayer(), "AttackTick = " + salmonPlayer.getAttackTick() );
        }
    }
}
