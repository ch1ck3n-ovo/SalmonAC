package com.ch1ck3n.salmonac.checks.movement.jesus;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import org.bukkit.event.EventHandler;

public class JesusC extends Check {
    public JesusC(String name, Response response, Punishment punishment, String description) {
        super(name, response, punishment, description);
        this.setType("(C)");
        this.setVlPerFail(2.5f);
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        // Type C
        if( e.getRespawnTick() < 20 ) return;
        if( e.getLilyAround() != 0 ) return;
        if( e.isInLiquid() ) return;
        if( e.isOnLadder() ) return;
        if( e.isTouchingStair() ) return;

        // Check
        if ( e.isLastTouchingLiquid() && e.isLastMathGround() && e.getLastDeltaY() < 0 && e.getDeltaY() > 0 && Math.abs(e.getDeltaY() - e.getLastDeltaY()) > 0.1 ) {
//                e.getCustomPlayer().jesusCBuffer.onTick();
//                if (e.getCustomPlayer().jesusCBuffer.getTick() > 2) {
                flag(e.getPlayer(), "DeltaY = " + String.format("%.10f", e.getDeltaY()) +
                        "\nLastDeltaY = " + String.format("%.10f", e.getLastDeltaY()));
//                }
        }
    }
}
