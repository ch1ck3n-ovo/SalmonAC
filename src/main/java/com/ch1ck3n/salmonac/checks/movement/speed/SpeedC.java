package com.ch1ck3n.salmonac.checks.movement.speed;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.MathUtil;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;

public class SpeedC extends Check {
    public SpeedC(String name, Response response, Punishment punishment, String description) {
        super(name, response, punishment, description);
        this.setType("Prediction");
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        if( e.getPlayer().getGameMode() == GameMode.CREATIVE ) return;

        // Type C (Prediction)
        if( e.getRespawnTick() < 20 ) return;
        if( e.isFuzzyCollidingHorizontally() ) return;
        if( e.getCollidingHorizontallyTick() < 2 ) return;
        if( e.getDamageTick() < 2 ) return;
        if( e.isOnLadder() ) return;
        if( e.getSetBackTick() < 3 ) return;
        if( e.isFuzzyServerGround() ) return;
        if( e.getServerAirTick() < 2 ) return;
        if( e.isTouchingLiquid() ) return;
        if( e.getWebTick() == 0 ) return;

        float f4 = 0.91f;
        if (e.isServerGround()) {
            if (e.getIceTick() == 0) f4 *= 0.98f;
            else if (e.getSlimeTick() == 0) f4 *= 0.8f;
            else f4 *= 0.6f;
        }
        double prediction = e.getLastDeltaXZ() * f4 + 0.026D;
        if (e.getDeltaXZ() - prediction > 0.001) {
            e.getSalmonPlayer().speedBBuffer.onTick();
            if ( e.getSalmonPlayer().speedBBuffer.getTick() > 1 ) {
                this.setVlPerFail(MathUtil.getVlFromDouble(e.getDeltaXZ() - prediction) * 5);
                flag(e.getPlayer(), "DeltaXZ = " + e.getDeltaXZ() +
                        "\nPrediction = " + prediction);
            }
        }
    }
}
