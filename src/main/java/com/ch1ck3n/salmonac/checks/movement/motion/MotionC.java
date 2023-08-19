package com.ch1ck3n.salmonac.checks.movement.motion;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.MathUtil;
import com.ch1ck3n.salmonac.utils.PlayerUtil;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.potion.PotionEffectType;

public class MotionC extends Check {
    public MotionC(String name, Category category, Punishment punishment, String description) {
        super(name, category, punishment, description);
        this.setType("FastFall");
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        if( e.getPlayer().getGameMode() == GameMode.CREATIVE ) return;

        // Type C (FastFall)
        if( e.getRespawnTick() < 60 ) return;
        if( e.getDamageTick() < 20 ) return;

        // Check
        if (e.getDeltaY() < -3.920005) {
            this.setVlPerFail(MathUtil.getVlFromDouble(Math.abs(e.getDeltaY())));
            flag( e.getPlayer(), "DeltaY = " + MathUtil.getInfoFromDouble10(e.getDeltaY()) );
        }
    }
}
