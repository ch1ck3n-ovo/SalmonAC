package com.ch1ck3n.salmonac.checks.movement.sprinting;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import org.bukkit.event.EventHandler;

public class SprintingB extends Check {
    public SprintingB(String name, Response response, Punishment punishment, String description) {
        super(name, response, punishment, description);
        this.setType("(B)");
        this.setVlPerFail(2.0f);
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        // Type B
        if( e.getHungryTick() < 2 ) return;

        // Check
        if (e.getPlayer().getFoodLevel() <= 6 && e.getPlayer().isSprinting()) {
            flag(e.getPlayer(), "FoodLevel = " + e.getPlayer().getFoodLevel() +
                    "\nSprinting = " + e.getPlayer().isSprinting());
        }
    }
}
