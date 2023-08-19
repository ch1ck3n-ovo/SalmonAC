package com.ch1ck3n.salmonac.checks.movement.sprinting;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.MathUtil;
import com.ch1ck3n.salmonac.utils.PlayerUtil;
import org.bukkit.event.EventHandler;

public class SprintingF extends Check {
    public SprintingF(String name, Category category, Punishment punishment, String description) {
        super(name, category, punishment, description);
        this.setType("(F)");
        this.setVlPerFail(4.0f);
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        // Type E
        if( e.getRespawnTick() < 40 ) return;
        if( !e.isServerGround() ) return;

        // Check
        float[] moveAndStrafe = PlayerUtil.movementInput(e.getDeltaX(), e.getDeltaZ(), e.getDeltaXZ(), e.getPlayer().getLocation().getYaw(), 0.1f);

//        e.getPlayer().sendMessage(moveAndStrafe[0]/0.98f+"/"+moveAndStrafe[1]/0.98f);

        if( moveAndStrafe[0] == -0.98f && moveAndStrafe[1] == 0 && e.getPlayer().isSprinting() && Math.abs(e.getDeltaYaw()) < 45.0f ) {
            flag(e.getPlayer(), "MoveForward = " + moveAndStrafe[0] / 0.98f +
                    "\nMoveStrafe = " + moveAndStrafe[1] / 0.98f +
                    "\nSprinting = " + e.getPlayer().isSprinting() +
                    "\nDeltaYaw = " + MathUtil.getInfoFromFloat10(Math.abs(e.getDeltaYaw())) +
                    "\nLastDeltaYaw = " + MathUtil.getInfoFromFloat10(Math.abs(e.getLastDeltaYaw())) );
        }
//        else if( moveAndStrafe[0] == 0 && moveAndStrafe[1] != 0 && e.getPlayer().isSprinting() &&
//                Math.abs(e.getLastDeltaYaw()) < 10.0f && Math.abs(e.getDeltaYaw()) < 5.0f ) {
//            flag(e.getPlayer(), "MoveForward = " + moveAndStrafe[0] / 0.98f +
//                    "\nMoveStrafe = " + moveAndStrafe[1] / 0.98f +
//                    "\nSprinting = " + e.getPlayer().isSprinting() +
//                    "\nDeltaYaw = " + MathUtil.getInfoFromFloat10(Math.abs(e.getDeltaYaw())) +
//                    "\nLastDeltaYaw = " + MathUtil.getInfoFromFloat10(Math.abs(e.getLastDeltaYaw())) );
//        }
    }
}
