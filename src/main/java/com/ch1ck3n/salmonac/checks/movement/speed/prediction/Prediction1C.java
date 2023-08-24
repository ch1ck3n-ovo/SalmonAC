package com.ch1ck3n.salmonac.checks.movement.speed.prediction;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.MathUtil;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;

public class Prediction1C extends Check {
    public Prediction1C(String name, Category category, Punishment punishment, String description) {
        super(name, category, punishment, description);
        this.setType("Prediction.Lava");
        this.setSubCategory("Speed");
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        if( e.getPlayer().getGameMode() == GameMode.CREATIVE ) return;

        if( e.getRespawnTick() < 20 ) return;
        if( e.getLavaTick() < 5 ) return;

        double prediction = e.getLastDeltaXZ() * 0.5d + 0.02d;

        debug(e.getPlayer(), MathUtil.getInfoFromDouble10(e.getDeltaXZ())+"/"+MathUtil.getInfoFromDouble10(prediction));

        if ( e.getDeltaXZ() - prediction > 0.001 && e.isTouchingLava() &&
                (e.getPlayer().getLocation().add(0.0d, 0.4d, 0.0d).getBlock().getType() == Material.LAVA ||
                        e.getPlayer().getLocation().add(0.0d, 0.4d, 0.0d).getBlock().getType() == Material.STATIONARY_LAVA)) {
            e.getSalmonPlayer().prediction1CBuffer.onTick();
            if ( e.getSalmonPlayer().prediction1CBuffer.getTick() > 2 ) {
                this.setVlPerFail(MathUtil.getVlFromDouble(e.getDeltaXZ() - prediction) * 5);
                flag(e.getPlayer(), "DeltaXZ = " + MathUtil.getInfoFromDouble10(e.getDeltaXZ()) +
                        "\nPrediction = " + MathUtil.getInfoFromDouble10(prediction));
            }
        }else { e.getSalmonPlayer().prediction1BBuffer.reduceTick(); }
    }
}
