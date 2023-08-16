package com.ch1ck3n.salmonac.checks.movement.sprinting;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import org.bukkit.event.EventHandler;

public class SprintingA extends Check {
    public SprintingA(String name, Check.Response response, Punishment punishment, String description) {
        super(name, response, punishment, description);
        this.setType("(A)");
        this.setVlPerFail(2.0f);
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        // Type A
        if( e.getPlayer().isBlocking() && e.getPlayer().isSprinting() ) {
            flag(e.getPlayer(), "BlockingA = " + e.getPlayer().isBlocking() +
                    "\nSprintingt = " + e.getPlayer().isSprinting());
        }
    }
}
