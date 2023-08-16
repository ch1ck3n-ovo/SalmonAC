package com.ch1ck3n.salmonac.checks.movement.motion;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.MathUtil;
import com.ch1ck3n.salmonac.utils.PlayerUtil;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.potion.PotionEffectType;

public class MotionC extends Check {
    public MotionC(String name, Response response, Punishment punishment, String description) {
        super(name, response, punishment, description);
        this.setType("(C)");
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        if( e.getPlayer().getGameMode() == GameMode.CREATIVE ) return;

        // Type C (FastFall)
        if( e.getRespawnTick() < 20 ) return;
        if( e.getDamageTick() < 20 ) return;

        // Check
        if (e.getDeltaY() < -3.920005) {
            this.setVlPerFail(MathUtil.getVlFromDouble(Math.abs(e.getDeltaY())));
            flag( e.getPlayer(), "DeltaY = " + String.format("%.10f", e.getDeltaY()) );
        }
    }
}
