package com.ch1ck3n.salmonac.checks.movement.sprinting;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.PlayerUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.potion.PotionEffectType;

public class SprintingE extends Check {
    public SprintingE(String name, Category category, Punishment punishment, String description) {
        super(name, category, punishment, description);
        this.setType("(E)");
        this.setVlPerFail(4.0f);
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        // Type E
        if( e.getRespawnTick() < 60 ) return;

        // Check
        if( e.getDeltaXZ() == 0 && e.getLastDeltaXZ() == 0 && e.getPlayer().isSprinting() ) {
            flag(e.getPlayer(), "Moving = " + (e.getDeltaXZ() == 0 && e.getLastDeltaXZ() == 0) +
                    "\nSprinting = " + e.getPlayer().isSprinting());
        }
    }
}
