package com.ch1ck3n.salmonac.checks.movement.sprinting;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import org.bukkit.event.EventHandler;

public class SprintingE extends Check {
    public SprintingE(String name, Category category, Punishment punishment, String description) {
        super(name, category, punishment, description);
        this.setType("NoMove");
        this.setSubCategory("Sprinting");
        this.setVlPerFail(4.0f);
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        // Type E
        if( e.getRespawnTick() < 60 ) return;

        // Check
        if( e.getDeltaXZ() == 0 && e.getLastDeltaXZ() == 0 && e.getPlayer().isSprinting() ) {
            e.getSalmonPlayer().sprintingEBuffer.onTick();
            if ( e.getSalmonPlayer().sprintingEBuffer.getTick() > 1 ) {
                flag(e.getPlayer(), "Moving = " + (e.getDeltaXZ() == 0 && e.getLastDeltaXZ() == 0) +
                        "\nSprinting = " + e.getPlayer().isSprinting());
            }
        }
    }
}
