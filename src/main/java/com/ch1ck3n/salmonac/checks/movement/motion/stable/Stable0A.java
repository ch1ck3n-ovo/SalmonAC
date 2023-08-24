package com.ch1ck3n.salmonac.checks.movement.motion.stable;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.MathUtil;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;

public class Stable0A extends Check {
    public Stable0A(String name, Category category, Punishment punishment, String description) {
        super(name, category, punishment, description);
        this.setType("Stable.Motion");
        this.setSubCategory("Motion");
        this.setVlPerFail(2.0f);
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        if( e.getPlayer().getGameMode() == GameMode.CREATIVE ) return;

        // Disable when player is onLadder, touchingClimbable, touchingLiquid, touchingSlab or touchingStair
        if( e.getRespawnTick() < 20 ) return;
        if( e.getClimbTick() < 4 ) return;
        if( e.getWebTick() == 0 ) return;

        if( e.isServerGround() ) return;
        if( e.isTouchingClimable() ) return;
        if( e.isTouchingLiquid() ) return;
        if( e.isTouchingSlab() ) return;
        if( e.isTouchingStair() ) return;

        if( e.isOnLadder() ) return;

        // Check
        if (e.getDeltaY() != 0 && e.getLastDeltaY() != 0 &&
                Math.abs(e.getDeltaY()) == Math.abs(e.getLastDeltaY())) {
            flag( e.getPlayer(), "DeltaY = " + MathUtil.getInfoFromDouble10(Math.abs(e.getDeltaY())) +
                    "\nLastDeltaY = " + MathUtil.getInfoFromDouble10(Math.abs(e.getLastDeltaY())) );
        }
    }
}
