package com.ch1ck3n.salmonac.checks.movement.speed;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.MathUtil;
import com.ch1ck3n.salmonac.utils.PlayerUtil;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;

public class SpeedH extends Check {
    public SpeedH(String name, Category category, Punishment punishment, String description) {
        super(name, category, punishment, description);
        this.setType("GroundStrafe");
        this.setVlPerFail(2.0f);
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        if( e.getPlayer().getGameMode() == GameMode.CREATIVE ) return;

        if( e.getRespawnTick() < 20 ) return;
        if( !(e.getServerAirTick() < 3) ||  e.getCollidingHorizontallyTick() < 6 || e.getClimbTick() < 4 ) {
            e.getSalmonPlayer().speedHSampleList.clear();
            e.getSalmonPlayer().flag3 = false;
            e.getSalmonPlayer().flag4 = false;
            return;
        }

        float[] movementInput = PlayerUtil.movementInput(e.getDeltaX(), e.getDeltaZ(), e.getDeltaXZ(), e.getPlayer().getLocation().getYaw(), 0.069f);
        float strafe = movementInput[1] / 0.98f;

//        e.getPlayer().sendMessage(strafe+"/"+e.getServerAirTick()+"/"+e.getSalmonPlayer().flag3+"/"+e.getSalmonPlayer().flag4);

        if( e.getServerAirTick() < 2 && Math.abs(e.getDeltaYaw() % 360) < 30.0f && Math.abs(e.getLastDeltaYaw() % 360) < 30.0f ) {
            if( strafe == 1.0f ) e.getSalmonPlayer().flag3 = true;
            if( strafe == -1.0f ) e.getSalmonPlayer().flag4 = true;

            if( e.getSalmonPlayer().flag3 || e.getSalmonPlayer().flag4 && !(e.getSalmonPlayer().flag3 && e.getSalmonPlayer().flag4) ) {
                e.getSalmonPlayer().speedHSampleList.add((long) strafe);
            }

            if( e.getSalmonPlayer().flag3 && e.getSalmonPlayer().flag4 && !e.isServerGround() ) {
                if( e.getSalmonPlayer().speedHSampleList.count(0L) < 1 ) {
                    flag(e.getPlayer(), "StrafeLeft = " + e.getSalmonPlayer().flag3 +
                            "\nStrafeRight = " + e.getSalmonPlayer().flag4 +
                            "\nCount = " + e.getSalmonPlayer().speedHSampleList.count(0L) +
                            "\nDeltaYaw = " + MathUtil.getInfoFromFloat3(Math.abs(e.getDeltaYaw())) );
                    e.getSalmonPlayer().speedHSampleList.clear();
                    e.getSalmonPlayer().flag3 = false;
                    e.getSalmonPlayer().flag4 = false;
                }
            }
        }
    }
}
