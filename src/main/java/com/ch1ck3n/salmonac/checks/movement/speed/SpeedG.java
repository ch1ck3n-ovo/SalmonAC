package com.ch1ck3n.salmonac.checks.movement.speed;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.MathUtil;
import com.ch1ck3n.salmonac.utils.PlayerUtil;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;

public class SpeedG extends Check {
    public SpeedG(String name, Category category, Punishment punishment, String description) {
        super(name, category, punishment, description);
        this.setType("AirStrafe(B)");
        this.setVlPerFail(2.0f);
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        if( e.getPlayer().getGameMode() == GameMode.CREATIVE ) return;

        if( e.getRespawnTick() < 20 ) return;
//        if( e.isLastServerGround() ) return;
//        if( e.getCollidingHorizontallyTick() < 6 ) return;
//        if( e.getClimbTick() < 4 ) return;
        if( e.isLastServerGround() ||  e.getCollidingHorizontallyTick() < 6 || e.getClimbTick() < 4 ) {
            e.getSalmonPlayer().speedGBuffer.clearTick();
            return;
        }

        float[] movementInput = PlayerUtil.movementInput(e.getDeltaX(), e.getDeltaZ(), e.getDeltaXZ(), e.getPlayer().getLocation().getYaw(), 0.069f);
        float forward = movementInput[0] / 0.98f;
        float strafe = movementInput[1] / 0.98f;

        if( !e.isLastServerGround() && e.isJumping() && Math.abs(e.getDeltaYaw()) % 360 > 6.9f && forward == 1.0f && strafe == 0.0f ) {
            e.getSalmonPlayer().speedGBuffer.onTick();
            if ( e.getSalmonPlayer().speedGBuffer.getTick() > 5 ) {
                flag(e.getPlayer(), "Forward = " + forward +
                        "\nStrafe = " + strafe +
                        "\nDeltaYaw = " + MathUtil.getInfoFromFloat3(Math.abs(e.getDeltaYaw())) +
                        "\nBuffer = " + e.getSalmonPlayer().speedGBuffer.getTick() );
            }
        }
    }
}
