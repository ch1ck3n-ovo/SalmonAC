package com.ch1ck3n.salmonac.checks.movement.motion;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.MathUtil;
import com.ch1ck3n.salmonac.utils.PlayerUtil;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.potion.PotionEffectType;

public class MotionB extends Check {
    public MotionB(String name, Response response, Punishment punishment, String description) {
        super(name, response, punishment, description);
        this.setType("StableMotion");
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        if( e.getPlayer().getGameMode() == GameMode.CREATIVE ) return;

        // Type B (Stable)
        // Disable when player is onLadder, touchingClimbable, touchingLiquid, touchingSlab or touchingStair
        if( e.getRespawnTick() < 20 ) return;
        if( e.isOnLadder() ) return;
        if( e.isTouchingClimable() ) return;
        if( e.isTouchingLiquid() ) return;
        if( e.isTouchingSlab() ) return;
        if( e.isTouchingStair() ) return;

        // Check
        if (e.getDeltaY() != 0 && e.getLastDeltaY() != 0 && e.getDeltaY() == e.getLastDeltaY()) {
            this.setVlPerFail(MathUtil.getVlFromDouble(e.getDeltaY()) * 2);
            flag( e.getPlayer(), "DeltaY = " + String.format("%.10f", e.getDeltaY()) +
                    "\nLastDeltaY = " + String.format("%.10f", e.getLastDeltaY()) );
        }
    }
}
