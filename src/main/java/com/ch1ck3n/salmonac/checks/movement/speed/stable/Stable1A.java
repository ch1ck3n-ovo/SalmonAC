package com.ch1ck3n.salmonac.checks.movement.speed.stable;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.MathUtil;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;

public class Stable1A extends Check {
    public Stable1A(String name, Category category, Punishment punishment, String description) {
        super(name, category, punishment, description);
        this.setType("Stable.Motion");
        this.setSubCategory("Speed");
        this.setVlPerFail(2.0f);
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        if( e.getPlayer().getGameMode() == GameMode.CREATIVE ) return;

        // Type A (Stable)
        if( e.getRespawnTick() < 20 ) return;
        if( e.getCollidingHorizontallyTick() < 2 ) return;
        if( e.getCollidingVerticallyUpTick() == 0 ) return;
        if( e.getServerAirTick() < 2 ) return;
        if( e.getSetBackTick() < 2 ) return;
        if( e.getWebTick() == 0 ) return;

        if( e.isFuzzyCollidingHorizontally() ) return;
        if( e.isServerGround() ) return;
        if( e.isTouchingLiquid() ) return;

        if( e.isOnLadder() ) return;

        // Check
        if (e.getDeltaXZ() != 0 && e.getLastDeltaXZ() != 0 && Math.abs(e.getDeltaXZ()) == Math.abs(e.getLastDeltaXZ())) {
            flag(e.getPlayer(), "DeltaXZ = " + MathUtil.getInfoFromDouble10(Math.abs(e.getDeltaXZ())) +
                    "\nLastDeltaXZ = " + MathUtil.getInfoFromDouble10(Math.abs(e.getLastDeltaXZ())));
        }
    }
}
