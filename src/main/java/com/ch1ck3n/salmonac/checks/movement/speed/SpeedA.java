package com.ch1ck3n.salmonac.checks.movement.speed;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.MathUtil;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;

public class SpeedA extends Check {
    public SpeedA(String name, Response response, Punishment punishment, String description) {
        super(name, response, punishment, description);
        this.setType("StableMotion");
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        if( e.getPlayer().getGameMode() == GameMode.CREATIVE ) return;

        // Type A (Stable)
        if( e.getRespawnTick() < 20 ) return;
        if( e.getCollidingHorizontallyTick() < 2 ) return;
        if( e.getCollidingVerticallyUpTick() == 0 ) return;
        if( e.isOnLadder() ) return;
        if( e.getServerAirTick() < 2 ) return;
        if( e.isServerGround() ) return;
        if( e.getSetBackTick() < 2 ) return;
        if( e.isTouchingLiquid() ) return;
        if( e.getWebTick() == 0 ) return;

        // Check
        if (e.getDeltaXZ() != 0 && e.getLastDeltaXZ() != 0 && e.getDeltaXZ() == e.getLastDeltaXZ()) {
            this.setVlPerFail(MathUtil.getVlFromDouble(e.getDeltaXZ()) * 2);
            flag(e.getPlayer(), "DeltaXZ = " + e.getDeltaXZ() +
                    "\nLastDeltaXZ = " + e.getLastDeltaXZ());
        }
    }
}
