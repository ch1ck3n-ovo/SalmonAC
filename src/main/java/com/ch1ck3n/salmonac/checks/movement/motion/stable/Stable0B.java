package com.ch1ck3n.salmonac.checks.movement.motion.stable;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.MathUtil;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;

public class Stable0B extends Check {
    public Stable0B(String name, Category category, Punishment punishment, String description) {
        super(name, category, punishment, description);
        this.setType("Stable.Accel");
        this.setSubCategory("Motion");
        this.setVlPerFail(2.0f);
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        if( e.getPlayer().getGameMode() == GameMode.CREATIVE ) return;

        if( e.getRespawnTick() < 60 ) return;
        if( e.getTeleportTick() < 20 ) return;
        if( e.getWebTick() == 0 ) return;

        if( e.isServerGround() ) return;
        if( e.isServerGround() && e.getDeltaY() % 0.125D != 0 ) return;
        if( e.isTouchingClimable() ) return;
        if( e.isTouchingLiquid() ) return;
        if( e.isTouchingSlab() ) return;
        if( e.isTouchingStair() ) return;

        if( e.isOnLadder() ) return;

        // Check
        if ( e.getDeltaYAccel() != 0 && e.getLastDeltaYAccel() != 0 && Math.abs(e.getDeltaYAccel()) == Math.abs(e.getLastDeltaYAccel()) ) {
            flag(e.getPlayer(), "DeltaYAccel = " + MathUtil.getInfoFromDouble10(Math.abs(e.getDeltaYAccel())) +
                    "\nLastDeltaYAccel = " + MathUtil.getInfoFromDouble10(Math.abs(e.getLastDeltaYAccel())));
        }
    }
}
