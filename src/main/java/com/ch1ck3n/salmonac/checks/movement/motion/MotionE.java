package com.ch1ck3n.salmonac.checks.movement.motion;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.MathUtil;
import com.ch1ck3n.salmonac.utils.PlayerUtil;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.potion.PotionEffectType;

public class MotionE extends Check {
    public MotionE(String name, Response response, Punishment punishment, String description) {
        super(name, response, punishment, description);
        this.setType("Prediction");
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        if( e.getPlayer().getGameMode() == GameMode.CREATIVE ) return;

        // Type E (Prediction)
        if( e.getRespawnTick() < 20 ) return;
        if( e.getDamageTick() < 2 ) return;
        if( e.isOnLadder() ) return;
        if( e.getPlaceBlockTick() < 6 ) return;
        if( e.getSetBackTick() < 2 ) return;
        if( e.getSlimeTick() == 0 ) return;
        if( e.isTouchingLiquid() ) return;
        if( e.getWebTick() == 0 ) return;
        if( e.isFuzzyServerGround() ) return;

        // Check
        if ( !e.isCollidingVerticallyUp() && !e.isServerGround() && e.getServerAirTick() > 6 ) {
            double prediction = (e.getLastDeltaY() - 0.08D) * 0.9800000190734863D;
            if (Math.abs(e.getDeltaY() - prediction) > 0.001) {
                e.getSalmonPlayer().motionEBuffer.onTick();
                if (e.getSalmonPlayer().motionEBuffer.getTick() > 2) {
                    this.setVlPerFail(MathUtil.getVlFromDouble(Math.abs(e.getDeltaY() - prediction)) * 10);
                    flag( e.getPlayer(), "DeltaY = " + String.format("%.10f", e.getDeltaY()) +
                            "\nPrediction = " + String.format("%.10f", prediction) );
                }
            }
        }
    }
}
