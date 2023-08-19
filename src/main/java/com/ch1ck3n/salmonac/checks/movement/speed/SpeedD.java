package com.ch1ck3n.salmonac.checks.movement.speed;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.MathUtil;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;

public class SpeedD extends Check {
    public SpeedD(String name, Category category, Punishment punishment, String description) {
        super(name, category, punishment, description);
        this.setType("Invalid(A)");
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        if( e.getPlayer().getGameMode() == GameMode.CREATIVE ) return;

        // Type D (Invalid)
        if( e.getRespawnTick() < 60 ) return;
        if( e.getTeleportTick() < 20 ) return;
        if( e.getDamageTick() < 20 ) return;

        if ( e.getDeltaXZ() > 10 ) {
            this.setVlPerFail(Float.parseFloat(String.format("%.1f", 1.0f * Math.round(e.getDeltaXZ()) / 5)));
            flag( e.getPlayer(), "DeltaXZ = " + MathUtil.getInfoFromDouble10(e.getDeltaXZ()) );
        }
    }
}
