package com.ch1ck3n.salmonac.checks.movement.speed;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.MathUtil;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;

public class SpeedB extends Check {
    public SpeedB(String name, Response response, Punishment punishment, String description) {
        super(name, response, punishment, description);
        this.setType("StableAccel");
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        if( e.getPlayer().getGameMode() == GameMode.CREATIVE ) return;

        // Type B (StableAccel)
        if( e.getRespawnTick() < 20 ) return;
        if( e.getCollidingHorizontallyTick() < 2 ) return;
        if( e.isInBlock() ) return;

        // Check
        if (e.getDeltaXZAccel() != 0 && e.getLastDeltaXZAccel() != 0 && Math.abs(e.getDeltaXZAccel()) == Math.abs(e.getLastDeltaXZAccel())) {
            this.setVlPerFail(MathUtil.getVlFromDouble(e.getDeltaXZAccel()) * 10);
            flag(e.getPlayer(), "DeltaXZAccel = " + e.getDeltaXZAccel() +
                    "\nLastDeltaXZAccel = " + e.getLastDeltaXZAccel());
        }
    }
}
