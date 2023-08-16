package com.ch1ck3n.salmonac.checks.movement.motion;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.MathUtil;
import com.ch1ck3n.salmonac.utils.PlayerUtil;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.potion.PotionEffectType;

public class MotionG extends Check {
    public MotionG(String name, Response response, Punishment punishment, String description) {
        super(name, response, punishment, description);
        this.setType("StableAccel");
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        if( e.getPlayer().getGameMode() == GameMode.CREATIVE ) return;

        // Type G (StableAccel)
        if( e.getRespawnTick() < 20 ) return;
        if( e.isOnLadder() ) return;
        if( e.getTeleportTick() < 20 ) return;
        if( e.isTouchingClimable() ) return;
        if( e.isTouchingLiquid() ) return;
        if( e.isTouchingSlab() ) return;
        if( e.isTouchingStair() ) return;

        // Check
        if ( e.getDeltaYAccel() != 0 && e.getLastDeltaYAccel() != 0 && Math.abs(e.getDeltaYAccel()) == Math.abs(e.getLastDeltaYAccel()) ) {
            this.setVlPerFail(MathUtil.getVlFromDouble(e.getDeltaYAccel()) * 4);
            flag(e.getPlayer(), "DeltaYAccel = " + String.format("%.10f", Math.abs(e.getDeltaYAccel())) +
                    "\nLastDeltaYAccel = " + String.format("%.10f", Math.abs(e.getLastDeltaYAccel())));
        }
    }
}
