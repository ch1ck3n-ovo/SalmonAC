package com.ch1ck3n.salmonac.checks.movement.speed.strafe;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.MathUtil;
import com.ch1ck3n.salmonac.utils.PlayerUtil;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;

public class StrafeB extends Check {
    public StrafeB(String name, Category category, Punishment punishment, String description) {
        super(name, category, punishment, description);
        this.setType("Strafe.AirB");
        this.setSubCategory("Speed");
        this.setVlPerFail(3.0f);
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        if( e.getPlayer().getGameMode() == GameMode.CREATIVE ) return;

        if( e.getRespawnTick() < 20 ) return;

        float[] movementInput = PlayerUtil.movementInput(e.getDeltaX(), e.getDeltaZ(), e.getDeltaXZ(), e.getPlayer().getLocation().getYaw(), 0.02f);


        if( e.getCollidingHorizontallyTick() < 6 || e.getClimbTick() < 4 ||
                e.isLastLastServerGround() || e.isLastServerGround() ) {
            e.getSalmonPlayer().f1 = movementInput[0] / 0.98f;
            e.getSalmonPlayer().f2 = movementInput[1] / 0.98f;
            e.getSalmonPlayer().strafeBBuffer.clearTick();
            return;
        }

        float forward = movementInput[0] / 0.98f;
        float strafe = movementInput[1] / 0.98f;

        if( !e.isLastLastServerGround() && !e.isLastServerGround() && e.isJumping() && Math.abs(e.getDeltaYaw() % 360) > 6.9f &&
                forward == e.getSalmonPlayer().f1 && strafe == e.getSalmonPlayer().f2 && forward + strafe != 0.0f ) {
            e.getSalmonPlayer().strafeBBuffer.onTick();
            if ( e.getSalmonPlayer().strafeBBuffer.getTick() > 4 ) {
                flag(e.getPlayer(), "Forward = " + forward + " (" + e.getSalmonPlayer().f1 + ")" +
                        "\nStrafe = " + strafe + " (" + e.getSalmonPlayer().f2 + ")" +
                        "\nDeltaYaw = " + MathUtil.getInfoFromFloat3(Math.abs(e.getDeltaYaw() % 360)) +
                        "\nBuffer = " + e.getSalmonPlayer().strafeBBuffer.getTick() );
            }
        }
    }
}
