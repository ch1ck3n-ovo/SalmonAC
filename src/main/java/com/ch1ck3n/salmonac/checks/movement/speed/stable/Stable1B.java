package com.ch1ck3n.salmonac.checks.movement.speed.stable;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.MathUtil;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;

public class Stable1B extends Check {
    public Stable1B(String name, Category category, Punishment punishment, String description) {
        super(name, category, punishment, description);
        this.setType("Stable.Accel");
        this.setSubCategory("Speed");
        this.setVlPerFail(2.0f);
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        if( e.getPlayer().getGameMode() == GameMode.CREATIVE ) return;

        if( e.getRespawnTick() < 60 ) return;
        if( e.getCollidingHorizontallyTick() < 2 ) return;
        if( e.getTeleportTick() < 20 ) return;

        if( e.isInBlock() ) return;

        // Check
        if (e.getDeltaXZAccel() != 0 && e.getLastDeltaXZAccel() != 0 && e.getDeltaXZAccel() >= 0.00001 &&
                Math.abs(e.getDeltaXZAccel()) == Math.abs(e.getLastDeltaXZAccel())) {
            flag(e.getPlayer(), "DeltaXZAccel = " + MathUtil.getInfoFromDouble10(Math.abs(e.getDeltaXZAccel())) +
                    "\nLastDeltaXZAccel = " + MathUtil.getInfoFromDouble10(Math.abs(e.getLastDeltaXZAccel())));
        }
    }
}
