package com.ch1ck3n.salmonac.checks.movement.jesus;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.MathUtil;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;

public class JesusC extends Check {
    public JesusC(String name, Category category, Punishment punishment, String description) {
        super(name, category, punishment, description);
        this.setType("(C)");
        this.setSubCategory("Jesus");
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        // Type C
        if( e.getRespawnTick() < 60 ) return;

        if( e.getLilyAround() != 0 ) return;
        if( e.isTouchingStair() ) return;

        if( e.isInLiquid() ) return;
        if( e.isOnLadder() ) return;

        // Check
//        if ( e.isLastTouchingLiquid() && (!e.isServerGround() ||
//                (e.getPlayer().getLocation().add(0.0d, 0.4d, 0.0d).getBlock().getType() == Material.WATER ||
//                e.getPlayer().getLocation().add(0.0d, 0.4d, 0.0d).getBlock().getType() == Material.STATIONARY_WATER)) &&
//                e.isLastMathGround() && e.getLastDeltaY() < 0 && e.getDeltaY() > 0 && Math.abs(e.getDeltaY() - e.getLastDeltaY()) > 0.1 ) {
//            e.getSalmonPlayer().jesusCBuffer.onTick();
//            if (e.getSalmonPlayer().jesusCBuffer.getTick() > 1) {
//                this.setVlPerFail(MathUtil.getVlFromDouble(e.getDeltaY() - e.getLastDeltaY()));
//                flag(e.getPlayer(), "DeltaY = " + MathUtil.getInfoFromDouble10(e.getDeltaY()) +
//                        "\nLastDeltaY = " + MathUtil.getInfoFromDouble10(e.getLastDeltaY()));
//            }
//        }
    }
}
