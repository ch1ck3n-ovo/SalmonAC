package com.ch1ck3n.salmonac.checks.movement.motion;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.MathUtil;
import com.ch1ck3n.salmonac.utils.PlayerUtil;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.potion.PotionEffectType;

public class MotionF extends Check {
    public MotionF(String name, Response response, Punishment punishment, String description) {
        super(name, response, punishment, description);
        this.setType("Invalid");
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        if( e.getPlayer().getGameMode() == GameMode.CREATIVE ) return;

        // Type F (Invalid)
        if( e.getRespawnTick() < 20 ) return;
        if( e.getDamageTick() < 2 ) return;
        if( e.isOnLadder() ) return;
        if( e.getPlaceBlockTick() < 6 ) return;
        if( e.getServerAirTick() < 6 + PlayerUtil.getAmplifier(e.getPlayer(), PotionEffectType.JUMP) ) return;
        if( e.getSetBackTick() < 2 ) return;
        if( e.isTouchingLiquid() ) return;
        if( e.getWebTick() == 0 ) return;
        if( e.isFuzzyServerGround() ) return;

        // Check
        if ( e.getFallDistance() != 0 && e.getLastDeltaY() < 0 && e.getDeltaY() > 0 && e.getSalmonPlayer().getLastVelocityY() == 0 ) {
            this.setVlPerFail(MathUtil.getVlFromDouble(e.getDeltaY()) * 15);
            flag( e.getPlayer(), "DeltaY = " + String.format("%.10f", e.getDeltaY()) +
                    "\nVelocityY = " + String.format("%.10f", e.getSalmonPlayer().getLastVelocityY()) );
        }
    }
}
