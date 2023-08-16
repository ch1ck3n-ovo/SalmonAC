package com.ch1ck3n.salmonac.checks.movement.jesus;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import org.bukkit.event.EventHandler;

public class JesusA extends Check {
    public JesusA(String name, Response response, Punishment punishment, String description) {
        super(name, response, punishment, description);
        this.setType("Collide");
        this.setVlPerFail(1.0f);
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        // Type A
        if( e.getRespawnTick() < 20 ) return;
        if( e.isInLiquid() ) return;
        if( e.getSetBackTick() < 2 ) return;

        // Check
        if (!e.isServerGround() && !e.isLastServerGround() &&
                e.isTouchingLiquid() && e.getLilyAround() == 0 &&
                e.getLastDeltaY() == 0 && e.getDeltaY() == 0) {
            flag(e.getPlayer(), "ServerGround = " + e.isServerGround() +
                    "\nLastServerGround = " + e.isLastServerGround() +
                    "\nDeltaY = " + e.getDeltaY() +
                    "\nLastDeltaY = " + e.getLastDeltaY());
        }
    }
}
