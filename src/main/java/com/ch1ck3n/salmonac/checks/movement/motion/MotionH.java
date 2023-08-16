package com.ch1ck3n.salmonac.checks.movement.motion;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.MathUtil;
import com.ch1ck3n.salmonac.utils.PlayerUtil;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.potion.PotionEffectType;

public class MotionH extends Check {
    public MotionH(String name, Response response, Punishment punishment, String description) {
        super(name, response, punishment, description);
        this.setType("Collide");
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        if( e.getPlayer().getGameMode() == GameMode.CREATIVE ) return;

        // Type H (Spider - Collide)
        if( e.getRespawnTick() < 20 ) return;
        if( e.getClimbTick() < 2 ) return;
        if( e.getDamageTick() < 2 ) return;
        if( e.isOnLadder() ) return;
        if( (e.getServerAirTick() < 6 + PlayerUtil.getAmplifier(e.getPlayer(), PotionEffectType.JUMP)) ) return;
        if( e.isTouchingClimable() ) return;
        if( e.isTouchingLiquid() ) return;

        // Check
        if (e.isCollidingHorizontally() && !e.isLastServerGround() && e.getDeltaY() > 0) {
            this.setVlPerFail(MathUtil.getVlFromDouble(e.getDeltaY()) * 10);
            flag(e.getPlayer(), "CollidingHorizontally = " + e.isCollidingHorizontally() +
                    "\nServerGround = " + e.isServerGround() +
                    "\nDeltaY = " + String.format("%.10f", e.getDeltaY()));
        }
    }
}
