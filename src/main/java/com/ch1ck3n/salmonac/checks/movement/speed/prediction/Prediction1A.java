package com.ch1ck3n.salmonac.checks.movement.speed.prediction;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.MathUtil;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;

public class Prediction1A extends Check {
    public Prediction1A(String name, Category category, Punishment punishment, String description) {
        super(name, category, punishment, description);
        this.setType("Prediction.General");
        this.setSubCategory("Speed");
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        if( e.getPlayer().getGameMode() == GameMode.CREATIVE ) return;

        if( e.getRespawnTick() < 20 ) return;
        if( e.getCollidingHorizontallyTick() < 3 ) return;
        if( e.getDamageTick() < 1 ) return;
        if( e.getMoveTick() < 4 ) return;
        if( e.getTeleportTick() < 4 ) return;
        if( e.getWebTick() == 0 ) return;

        if( e.isFuzzyCollidingHorizontally() ) return;
        if( e.isTouchingLiquid() ) return;

        if( e.isOnLadder() ) return;

        float f1 = 0.91f;
        if (e.isLastLastServerGround()) {
            if (e.getIceTick() == 0) f1 *= 0.98f;
            else if (e.getSlimeTick() == 0) f1 *= 0.8f;
            else f1 *= 0.6f;
        }
        float f = 0.16277136F / (f1 * f1 * f1);
        float f2;
        if (e.isLastServerGround()) f2 = e.getPlayer().isSprinting() ? 0.13f : 0.1f * f;
        else f2 = e.getPlayer().isSprinting() ? 0.026f : 0.02f;
        double prediction = e.getLastDeltaXZ() * f1 + f2;

        if (e.getPlayer().isSprinting() && e.isJumpUpwards()) {
            prediction += 0.2d;
        }

        if (e.getDeltaXZ() - prediction > 0.001D) {
            e.getSalmonPlayer().prediction1ABuffer.onTick();
            if ( e.getSalmonPlayer().prediction1ABuffer.getTick() > 2 ) {
                this.setVlPerFail(MathUtil.getVlFromDouble(e.getDeltaXZ() - prediction) * 5);
                flag(e.getPlayer(), "DeltaXZ = " + MathUtil.getInfoFromDouble10(e.getDeltaXZ()) +
                        "\nPrediction = " + MathUtil.getInfoFromDouble10(prediction));
            }
        }else { e.getSalmonPlayer().prediction1ABuffer.clearTick(); }
    }
}
