package com.ch1ck3n.salmonac.checks.movement.speed;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;

public class SpeedD extends Check {
    public SpeedD(String name, Response response, Punishment punishment, String description) {
        super(name, response, punishment, description);
        this.setType("Invalid");
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        if( e.getPlayer().getGameMode() == GameMode.CREATIVE ) return;

        // Type D (Invalid)
        if( e.getRespawnTick() < 20 ) return;
        if( e.getTeleportTick() < 20  ) return;
        if( e.getDamageTick() < 20 ) return;

        if ( e.getDeltaXZ() > 10 ) {
            this.setVlPerFail(Float.parseFloat(String.format("%.1f", 1.0f * Math.round(e.getDeltaXZ()) / 5)));
            flag( e.getPlayer(), "DeltaXZ = " + e.getDeltaXZ() );
        }
    }
}
