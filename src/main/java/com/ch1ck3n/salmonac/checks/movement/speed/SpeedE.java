package com.ch1ck3n.salmonac.checks.movement.speed;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.MathUtil;
import com.ch1ck3n.salmonac.utils.PlayerUtil;
import org.bukkit.GameMode;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;

public class SpeedE extends Check {
    public SpeedE(String name, Category category, Punishment punishment, String description) {
        super(name, category, punishment, description);
        this.setType("MaxMotion");
        this.setVlPerFail(2.0f);
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
//        if( e.getPlayer().getGameMode() == GameMode.CREATIVE ) return;

        if( e.getRespawnTick() > -1 ) return;

        float forward = 0, strafe = 0, xPred = 0, zPred = 0, x1 = 0, x2 = 0, z1 = 0, z2 = 0, x3 = 0, z3 = 0;

        forward = PlayerUtil.movementInput(e.getDeltaX(), e.getDeltaZ(), e.getDeltaXZ(), e.getPlayer().getLocation().getYaw(), 0.05f)[0];
        strafe = PlayerUtil.movementInput(e.getDeltaX(), e.getDeltaZ(), e.getDeltaXZ(), e.getPlayer().getLocation().getYaw(), 0.05f)[1];

        if(e.getPlayer().isBlocking()) {
            forward *= 0.2f;
            strafe *= 0.2f;
        }

        if( !e.isTouchingWater() ) {
            if( !e.isTouchingLava() ) {
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

                f = forward * forward + strafe * strafe;
                if (f >= 1.0E-4F) {
                    f = (float) Math.sqrt(f);
                    if (f < 1.0F) f = 1.0F;
                    float yaw = e.getPlayer().getLocation().getYaw();
                    float friction = f5;

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

                    x1 = Float.parseFloat(MathUtil.getInfoFromDouble3(e.getLastDeltaX() * f4));
                    z1 = Float.parseFloat(MathUtil.getInfoFromDouble3(e.getLastDeltaZ() * f4));
                    x2 = Float.parseFloat(MathUtil.getInfoFromDouble3(xPred));
                    z2 = Float.parseFloat(MathUtil.getInfoFromDouble3(zPred));
                    x3 = Float.parseFloat(MathUtil.getInfoFromDouble3(x1/x2));
                    z3 = Float.parseFloat(MathUtil.getInfoFromDouble3(z1/z2));
                    e.getPlayer().sendMessage("None/"+x3+"/"+z3);
                }
            }else {
                float f = forward * forward + strafe * strafe;
                if (f >= 1.0E-4F) {
                    f = (float) Math.sqrt(f);
                    if (f < 1.0F) f = 1.0F;
                    float yaw = e.getPlayer().getLocation().getYaw();
                    float friction = 0.02f;

                    f = friction / f;
                    strafe = strafe * f;
                    forward = forward * f;

                    float f1 = (float) Math.sin(yaw * (float) Math.PI / 180.0F);
                    float f2 = (float) Math.cos(yaw * (float) Math.PI / 180.0F);

                    xPred = (float) (e.getLastDeltaX() * 0.5f + (strafe * f2 - forward * f1));
                    zPred = (float) (e.getLastDeltaZ() * 0.5f + (forward * f2 + strafe * f1));

                    xPred = xPred * 0.5f;
                    zPred = zPred * 0.5f;

                    x1 = Float.parseFloat(MathUtil.getInfoFromDouble3(e.getLastDeltaX() * 0.5f));
                    z1 = Float.parseFloat(MathUtil.getInfoFromDouble3(e.getLastDeltaZ() * 0.5f));
                    x2 = Float.parseFloat(MathUtil.getInfoFromDouble3(xPred));
                    z2 = Float.parseFloat(MathUtil.getInfoFromDouble3(zPred));
                    x3 = Float.parseFloat(MathUtil.getInfoFromDouble3(x1/x2));
                    z3 = Float.parseFloat(MathUtil.getInfoFromDouble3(z1/z2));
                    e.getPlayer().sendMessage("Lava/"+x3+"/"+z3);
                }
            }
        }else {
            float f1 = 0.8F;
            float f2 = 0.02F;
            float f3 = PlayerUtil.getBootsEnchantmentLevel(e.getPlayer(), Enchantment.DEPTH_STRIDER);

            if (f3 > 3.0F) {
                f3 = 3.0F;
            }

            if (!e.isServerGround()) {
                f3 *= 0.5F;
            }

            if (f3 > 0.0F) {
                f1 += (0.54600006F - f1) * f3 / 3.0F;
                f2 += (0.16277136F * 1.0F - f2) * f3 / 3.0F;
            }

            float f = forward * forward + strafe * strafe;

            if (f >= 1.0E-4F) {
                f = (float) Math.sqrt(f);
                if (f < 1.0F) f = 1.0F;
                float yaw = e.getPlayer().getLocation().getYaw();
                float friction = f2;

                f = friction / f;
                strafe = strafe * f;
                forward = forward * f;

                float g1 = (float) Math.sin(yaw * (float) Math.PI / 180.0F);
                float g2 = (float) Math.cos(yaw * (float) Math.PI / 180.0F);

                xPred = (float) (e.getLastDeltaX() * f1 + (strafe * g2 - forward * g1));
                zPred = (float) (e.getLastDeltaZ() * f1 + (forward * g2 + strafe * g1));

                xPred = xPred * f1;
                zPred = zPred * f1;

                x1 = Float.parseFloat(MathUtil.getInfoFromDouble3(e.getLastDeltaX() * f1));
                z1 = Float.parseFloat(MathUtil.getInfoFromDouble3(e.getLastDeltaZ() * f1));
                x2 = Float.parseFloat(MathUtil.getInfoFromDouble3(xPred));
                z2 = Float.parseFloat(MathUtil.getInfoFromDouble3(zPred));
                x3 = Float.parseFloat(MathUtil.getInfoFromDouble3(x1/x2));
                z3 = Float.parseFloat(MathUtil.getInfoFromDouble3(z1/z2));
                e.getPlayer().sendMessage("Water/"+x3+"/"+z3);
            }
        }

//        e.getPlayer().sendMessage(x3+"/"+z3);
    }
}
