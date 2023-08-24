package com.ch1ck3n.salmonac.checks.movement.speed.strafe;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.MathUtil;
import com.ch1ck3n.salmonac.utils.PlayerUtil;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;

public class StrafeA extends Check {
    public StrafeA(String name, Category category, Punishment punishment, String description) {
        super(name, category, punishment, description);
        this.setType("Strafe.AirA");
        this.setSubCategory("Speed");
        this.setVlPerFail(2.0f);
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        if( e.getPlayer().getGameMode() == GameMode.CREATIVE ) return;

        if( e.getRespawnTick() < 20 ) return;

        float[] movementInput = PlayerUtil.movementInput(e.getDeltaX(), e.getDeltaZ(), e.getDeltaXZ(), e.getPlayer().getLocation().getYaw(), 0.02f);

        if( e.getClimbTick() < 4 || e.getCollidingHorizontallyTick() < 6 || e.isLastServerGround() || e.isTouchingWater() ) {
            e.getSalmonPlayer().strafeABuffer.clearTick();
            e.getSalmonPlayer().strafeASampleList.clear();
            e.getSalmonPlayer().flag1 = false;
            e.getSalmonPlayer().flag2 = false;
            return;
        }

        float forward = movementInput[0] / 0.98f;
        float strafe = movementInput[1] / 0.98f;

        if( !e.isLastServerGround() && e.isJumping() && forward != 0 &&
                Math.abs(e.getDeltaYaw() % 360) < 10.0f && Math.abs(e.getLastDeltaYaw() % 360) < 10.0f &&
                Math.abs(e.getDeltaYawAccel()) < 10.0f && Math.abs(e.getLastDeltaYawAccel()) < 10.0f ) {
            e.getSalmonPlayer().strafeASampleList.add((long) strafe);
            if( strafe == 1.0f ) e.getSalmonPlayer().flag1 = true;
            if( strafe == -1.0f ) e.getSalmonPlayer().flag2 = true;
            if( e.getSalmonPlayer().flag1 && e.getSalmonPlayer().flag2 ) {
                if( e.getSalmonPlayer().strafeASampleList.count(0L) < 5 - (e.getPlayer().isSprinting() ? 0 : 2) ) {
                    e.getSalmonPlayer().strafeABuffer.onTick();
                    if ( e.getSalmonPlayer().strafeABuffer.getTick() > 3 ) {
                        flag(e.getPlayer(), "StrafeLeft = " + e.getSalmonPlayer().flag1 +
                                "\nStrafeRight = " + e.getSalmonPlayer().flag2 +
                                "\nDeltaYaw = " + MathUtil.getInfoFromFloat3(Math.abs(e.getDeltaYaw() % 360)) +
                                "\nLastDeltaYaw = " + MathUtil.getInfoFromFloat3(Math.abs(e.getLastDeltaYaw() % 360)) +
                                "\nDeltaYawAccel = " + MathUtil.getInfoFromFloat3(Math.abs(e.getDeltaYawAccel() % 360)) +
                                "\nLastDeltaYawAccel = " + MathUtil.getInfoFromFloat3(Math.abs(e.getLastDeltaYawAccel() % 360)) +
                                "\nSampleCount = " + e.getSalmonPlayer().strafeASampleList.count(0L) +
                                "\nBufferTick = " + e.getSalmonPlayer().strafeABuffer.getTick() );
                        e.getSalmonPlayer().strafeABuffer.clearTick();
                        e.getSalmonPlayer().strafeASampleList.clear();
                    }
                }else {
                    e.getSalmonPlayer().strafeABuffer.reduceTick();
                    e.getSalmonPlayer().strafeASampleList.clear();
                    e.getSalmonPlayer().flag1 = false;
                    e.getSalmonPlayer().flag2 = false;
                }
            }
        }
    }
}
