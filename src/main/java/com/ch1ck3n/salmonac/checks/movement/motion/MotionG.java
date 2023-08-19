package com.ch1ck3n.salmonac.checks.movement.motion;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.MathUtil;
import com.ch1ck3n.salmonac.utils.PlayerUtil;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.potion.PotionEffectType;

public class MotionG extends Check {
    public MotionG(String name, Category category, Punishment punishment, String description) {
        super(name, category, punishment, description);
        this.setType("StableAccel");
        this.setVlPerFail(2.0f);
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        if( e.getPlayer().getGameMode() == GameMode.CREATIVE ) return;

        // Type G (StableAccel)
        if( e.getRespawnTick() < 60 ) return;
        if( e.isOnLadder() ) return;
        if( e.getTeleportTick() < 20 ) return;
        if( e.isTouchingClimable() ) return;
        if( e.isTouchingLiquid() ) return;
        if( e.isTouchingSlab() ) return;
        if( e.isTouchingStair() ) return;
        if( e.getWebTick() == 0 ) return;

        // Check
        if ( e.getDeltaYAccel() != 0 && e.getLastDeltaYAccel() != 0 && Math.abs(e.getDeltaYAccel()) == Math.abs(e.getLastDeltaYAccel()) &&
                (e.isServerGround() && e.getDeltaY() % 0.125D != 0) ) {
            flag(e.getPlayer(), "DeltaYAccel = " + MathUtil.getInfoFromDouble10(Math.abs(e.getDeltaYAccel())) +
                    "\nLastDeltaYAccel = " + MathUtil.getInfoFromDouble10(Math.abs(e.getLastDeltaYAccel())));
        }
    }
}
