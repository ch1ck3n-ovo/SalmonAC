package com.ch1ck3n.salmonac.checks.movement.jesus;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import org.bukkit.event.EventHandler;

public class JesusB extends Check {
    public JesusB(String name, Response response, Punishment punishment, String description) {
        super(name, response, punishment, description);
        this.setType("StableAccel");
        this.setVlPerFail(2.0f);
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        // Type B (StableAccel)
        if( e.getRespawnTick() < 20 ) return;
        if( e.isOnLadder() ) return;
        if( e.isInLiquid() ) return;
        if( !e.isTouchingLiquid() ) return;
        if( e.isTouchingStair() ) return;

        // Check
        if ( e.getDeltaYAccel() != 0 && e.getLastDeltaYAccel() != 0 && Math.abs(e.getDeltaYAccel()) == Math.abs(e.getLastDeltaYAccel()) ) {
            e.getSalmonPlayer().jesusBBuffer.onTick();
            if (e.getSalmonPlayer().jesusBBuffer.getTick() > 3) {
                flag(e.getPlayer(), "DeltaYAccel = " + String.format("%.10f", Math.abs(e.getDeltaYAccel())) +
                        "\nLastDeltaYAccel = " + String.format("%.10f", Math.abs(e.getLastDeltaYAccel())));
            }
        }
    }
}
