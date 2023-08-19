package com.ch1ck3n.salmonac.checks.movement.step;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.MathUtil;
import org.bukkit.event.EventHandler;

public class StepA extends Check {
    public StepA(String name, Category category, Punishment punishment, String description) {
        super(name, category, punishment, description);
        this.setType("(A)");
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {

        // Type A
        if( e.getRespawnTick() < 20 ) return;
        if( e.getSetBackTick() < 2 ) return;
        if( e.getTeleportTick() < 20 ) return;
        if( e.isInBlock() ) return;

        // Check
        if (e.isServerGround() && e.isLastServerGround() && e.getDeltaY() > 0.6) {
            this.setVlPerFail(MathUtil.getVlFromDouble(e.getDeltaY()));
            flag(e.getPlayer(), "StepHeight = " + e.getDeltaY() +
                    "\nServerGround = " + e.isServerGround() +
                    "\nLastServerGround = " + e.isLastServerGround());
        }
    }
}
