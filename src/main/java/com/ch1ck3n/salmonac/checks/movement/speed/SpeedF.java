package com.ch1ck3n.salmonac.checks.movement.speed;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.MathUtil;
import com.ch1ck3n.salmonac.utils.PlayerUtil;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;

public class SpeedF extends Check {
    public SpeedF(String name, Category category, Punishment punishment, String description) {
        super(name, category, punishment, description);
        this.setType("AirStrafe(A)");
        this.setVlPerFail(2.0f);
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        if( e.getPlayer().getGameMode() == GameMode.CREATIVE ) return;

        if( e.getRespawnTick() < 20 ) return;
        if( e.isLastServerGround() ||  e.getCollidingHorizontallyTick() < 6 || e.getClimbTick() < 4 ) {
            e.getSalmonPlayer().speedFBuffer.clearTick();
            e.getSalmonPlayer().speedFSampleList.clear();
            e.getSalmonPlayer().flag1 = false;
            e.getSalmonPlayer().flag2 = false;
            return;
        }

        float[] movementInput = PlayerUtil.movementInput(e.getDeltaX(), e.getDeltaZ(), e.getDeltaXZ(), e.getPlayer().getLocation().getYaw(), 0.069f);
        float forward = movementInput[0] / 0.98f;
        float strafe = movementInput[1] / 0.98f;

//        e.getPlayer().sendMessage(forward+"/"+strafe+"/"+e.isLastServerGround()+"/"+MathUtil.getInfoFromFloat3(Math.abs(e.getDeltaYaw()) % 360));

        if( !e.isLastServerGround() && e.isJumping() && Math.abs(e.getDeltaYaw() % 360) < 30.0f && Math.abs(e.getLastDeltaYaw() % 360) < 30.0f ) {
            e.getSalmonPlayer().speedFSampleList.add((long) strafe);
            if( strafe == 1.0f ) e.getSalmonPlayer().flag1 = true;
            if( strafe == -1.0f ) e.getSalmonPlayer().flag2 = true;
            if( e.getSalmonPlayer().flag1 && e.getSalmonPlayer().flag2 ) {
                if( e.getSalmonPlayer().speedFSampleList.count(Long.valueOf(0)) < 5 ) {
                    e.getSalmonPlayer().speedFBuffer.onTick();
                    if ( e.getSalmonPlayer().speedFBuffer.getTick() > 1 ) {
                        flag(e.getPlayer(), "StrafeLeft = " + e.getSalmonPlayer().flag1 +
                                "\nStrafeRight = " + e.getSalmonPlayer().flag2 +
                                "\nCount = " + e.getSalmonPlayer().speedFSampleList.count(Long.valueOf(0)) +
                                "\nDeltaYaw = " + MathUtil.getInfoFromFloat3(Math.abs(e.getDeltaYaw())) +
                                "\nBuffer = " + e.getSalmonPlayer().speedFBuffer.getTick() );
                        e.getSalmonPlayer().speedFSampleList.clear();
                    }
                }else {
                    e.getSalmonPlayer().speedFBuffer.reduceTick();
                    e.getSalmonPlayer().speedFSampleList.clear();
                    e.getSalmonPlayer().flag1 = false;
                    e.getSalmonPlayer().flag2 = false;
                }
            }
        }
    }
}
