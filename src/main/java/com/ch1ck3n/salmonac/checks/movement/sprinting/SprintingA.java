package com.ch1ck3n.salmonac.checks.movement.sprinting;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import org.bukkit.event.EventHandler;

public class SprintingA extends Check {
    public SprintingA(String name, Category category, Punishment punishment, String description) {
        super(name, category, punishment, description);
        this.setType("(A)");
        this.setVlPerFail(2.0f);
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        // Type A
        if( e.getPlayer().isBlocking() && e.getPlayer().isSprinting() ) {
            flag(e.getPlayer(), "Blocking = " + e.getPlayer().isBlocking() +
                    "\nSprinting = " + e.getPlayer().isSprinting());
        }
    }
}
