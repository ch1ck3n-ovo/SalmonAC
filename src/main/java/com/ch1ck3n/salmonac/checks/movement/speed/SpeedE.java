package com.ch1ck3n.salmonac.checks.movement.speed;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.MathUtil;
import com.ch1ck3n.salmonac.utils.PlayerUtil;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;

public class SpeedE extends Check {
    public SpeedE(String name, Category category, Punishment punishment, String description) {
        super(name, category, punishment, description);
        this.setType("KeepSprint");
        this.setVlPerFail(2.0f);
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        if( e.getPlayer().getGameMode() == GameMode.CREATIVE ) return;

//        if( e.getRespawnTick() > -1 ) return;

        float f4 = 0.91F;
        if (e.isServerGround()) {
            if (e.getIceTick() == 0) f4 *= 0.98f;
            else if (e.getSlimeTick() == 0) f4 *= 0.8f;
            else f4 *= 0.6f;
        }
        float f = 0.16277136F / (f4 * f4 * f4);
        float f5;
        if (e.isServerGround()) f5 = e.getPlayer().isSprinting() ? 0.13f : 0.1f * f;
        else f5 = e.getPlayer().isSprinting() ? 0.026f : 0.02f;

        float forward = 0, strafe = 0, friction = f5;

        forward = PlayerUtil.movementInput(e.getDeltaX(), e.getDeltaZ(), e.getDeltaXZ(), e.getPlayer().getLocation().getYaw(), 0.05f)[0];
        strafe = PlayerUtil.movementInput(e.getDeltaX(), e.getDeltaZ(), e.getDeltaXZ(), e.getPlayer().getLocation().getYaw(), 0.05f)[1];

//        e.getPlayer().sendMessage(forward/0.98f+"/"+strafe/0.98f+"/"+e.isServerGround());

        if(e.getPlayer().isBlocking()) {
            forward *= 0.2f;
            strafe *= 0.2f;
        }

        if (f >= 1.0E-4F) {
            f = (float) Math.sqrt(forward * forward + strafe * strafe);

            if (f < 1.0F) f = 1.0F;

            float yaw = e.getPlayer().getLocation().getYaw(), xPred, zPred;

            f = friction / f;
            strafe = strafe * f;
            forward = forward * f;

            float f1 = (float) Math.sin(yaw * (float) Math.PI / 180.0F);
            float f2 = (float) Math.cos(yaw * (float) Math.PI / 180.0F);

            f4 = 0.91F;
            if (e.isServerGround()) {
                if (e.getIceTick() == 0) f4 *= 0.98f;
                else if (e.getSlimeTick() == 0) f4 *= 0.8f;
                else f4 *= 0.6f;
            }

            xPred = (float) (e.getLastDeltaX() * f4 + (strafe * f2 - forward * f1));
            zPred = (float) (e.getLastDeltaZ() * f4 + (forward * f2 + strafe * f1));

            xPred = xPred * f4;
            zPred = zPred * f4;

            float x1 = Float.parseFloat(MathUtil.getInfoFromDouble3(e.getLastDeltaX()*f4));
            float z1 = Float.parseFloat(MathUtil.getInfoFromDouble3(e.getLastDeltaZ()*f4));
            float x2 = Float.parseFloat(MathUtil.getInfoFromDouble3(xPred));
            float z2 = Float.parseFloat(MathUtil.getInfoFromDouble3(zPred));
            float x3 = Float.parseFloat(MathUtil.getInfoFromDouble3(x1/x2));
            float z3 = Float.parseFloat(MathUtil.getInfoFromDouble3(z1/z2));

            if( e.getRespawnTick() < 60 ) return;
            if( e.getTeleportTick() < 20 ) return;
            if( e.getDeltaXZ() == 0 ) return;
            if( Float.isInfinite(x3) ) return;
            if( Float.isInfinite(z3) ) return;
            if( Float.isNaN(x3) ) return;
            if( Float.isNaN(z3) ) return;
            if( e.isTouchingSlab() ) return;
            if( e.isTouchingStair() ) return;

//            e.getPlayer().sendMessage(x3+"/"+z3+"/"+Math.hypot(x3, z3));

            double d1 = Math.hypot(x1, z1);
            double d2 = Math.hypot(x2, z2);

            if( e.getAttackTick() <= 1 && Math.hypot(x3, z3) > 1.3f) {
                flag(e.getPlayer(), "Speed = " + Math.hypot(x3, z3) );
            }

//            if( d2 - d1 > 0.01 && e.getDeltaYaw() != 0.0f && Math.abs(e.getDeltaYaw()) < 2.5f /*&& e.isServerGround() && e.isLastServerGround() */) {
//                e.getSalmonPlayer().speedEBuffer.onTick();
//                if ( e.getSalmonPlayer().speedEBuffer.getTick() > 2 ) {
//                    this.setVlPerFail(MathUtil.getVlFromDouble((x2 - x1) + (z2 - x1)) * 10);
//                    flag(e.getPlayer(), "DeltaX = " + x1 +
//                            "\nPredictionZ = " + z2 +
//                            "\nDeltaZ = " + z1 +
//                            "\nPredictionZ = " + z2);
//                    e.getSalmonPlayer().speedEBuffer.clearTick();
//                }
//            }else { e.getSalmonPlayer().speedEBuffer.reduceTick(); }
        }
    }
}
