package com.ch1ck3n.salmonac.checks.movement.jesus;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.MathUtil;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;

public class JesusA extends Check {
    public JesusA(String name, Category category, Punishment punishment, String description) {
        super(name, category, punishment, description);
        this.setType("Collide");
        this.setSubCategory("Jesus");
        this.setVlPerFail(1.0f);
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        // Type A
        if( e.getRespawnTick() < 20 ) return;
        if( e.getSetBackTick() < 2 ) return;

        if( e.isCollidingVerticallyUp() &&
                (e.getPlayer().getLocation().add(0.0d, 0.4d, 0.0d).getBlock().getType() == Material.WATER ||
                e.getPlayer().getLocation().add(0.0d, 0.4d, 0.0d).getBlock().getType() == Material.STATIONARY_WATER) ) return;
        if( e.isInLiquid() ) return;

        // Check
        if (!e.isServerGround() && !e.isLastServerGround() &&
                e.isTouchingLiquid() && e.getLilyAround() == 0 &&
                e.getLastDeltaY() == 0 && e.getDeltaY() == 0) {
            flag(e.getPlayer(), "ServerGround = " + e.isServerGround() +
                    "\nLastServerGround = " + e.isLastServerGround() +
                    "\nDeltaY = " + MathUtil.getInfoFromDouble10(e.getDeltaY()) +
                    "\nLastDeltaY = " + MathUtil.getInfoFromDouble10(e.getLastDeltaY()));
        }
    }
}
