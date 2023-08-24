package com.ch1ck3n.salmonac.checks.movement.motion.prediction;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.MathUtil;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;

public class Prediction0A extends Check {
    public Prediction0A(String name, Category category, Punishment punishment, String description) {
        super(name, category, punishment, description);
        this.setType("Prediction.General");
        this.setSubCategory("Motion");
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        if( e.getPlayer().getGameMode() == GameMode.CREATIVE ) return;

        if( e.getRespawnTick() < 20 ) return;
        if( e.getDamageTick() < 2 ) return;
        if( e.getPlaceBlockTick() < 6 ) return;
        if( e.getSetBackTick() < 2 ) return;
        if( e.getSlimeTick() == 0 ) return;
        if( e.getWebTick() == 0 ) return;

        if( e.isFuzzyServerGround() ) return;
        if( e.isTouchingLiquid() ) return;

        if( e.isOnLadder() ) return;

        // Check
        if ( !e.isCollidingVerticallyUp() && !e.isServerGround() && e.getServerAirTick() > 6 ) {
            double prediction = (e.getLastDeltaY() - 0.08D) * 0.9800000190734863D;
            if (Math.abs(e.getDeltaY() - prediction) > 0.001) {
                e.getSalmonPlayer().prediction0ABuffer.onTick();
                if (e.getSalmonPlayer().prediction0ABuffer.getTick() > 2) {
                    this.setVlPerFail(MathUtil.getVlFromDouble(Math.abs(e.getDeltaY() - prediction)) * 10);
                    flag( e.getPlayer(), "DeltaY = " + MathUtil.getInfoFromDouble10(e.getDeltaY()) +
                            "\nPrediction = " + MathUtil.getInfoFromDouble10(prediction) );
                }
            }
        }
    }
}
