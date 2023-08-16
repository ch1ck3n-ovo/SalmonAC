package com.ch1ck3n.salmonac.checks.movement.step;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.MathUtil;
import org.bukkit.event.EventHandler;

public class StepB extends Check {
    public StepB(String name, Response response, Punishment punishment, String description) {
        super(name, response, punishment, description);
        this.setType("(B)");
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        // Type B
        if( e.getRespawnTick() < 20 ) return;
        if( e.getSetBackTick() < 2 ) return;
        if( e.getTeleportTick() < 20 ) return;
        if( e.isInBlock() ) return;
        if( e.isTouchingClimable() ) return;
        if( e.isTouchingLiquid() ) return;

        if (e.isClientGround() && e.isLastClientGround() && e.isServerGround() && e.getDeltaY() > 0D && e.getDeltaY() % 0.125D != 0 ) {
            this.setVlPerFail(MathUtil.getVlFromDouble(e.getDeltaY()));
            flag(e.getPlayer(), "StepHeight = " + e.getDeltaY() +
                    "\nClientGround = " + e.isClientGround() +
                    "\nLastClientGround = " + e.isLastClientGround());
        }
    }
}
