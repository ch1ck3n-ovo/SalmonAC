package com.ch1ck3n.salmonac.checks.movement.speed.invalid;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.MathUtil;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;

public class Invalid1A extends Check {
    public Invalid1A(String name, Category category, Punishment punishment, String description) {
        super(name, category, punishment, description);
        this.setType("Invalid.A");
        this.setSubCategory("Speed");
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        if( e.getPlayer().getGameMode() == GameMode.CREATIVE ) return;

        if( e.getRespawnTick() < 60 ) return;
        if( e.getDamageTick() < 20 ) return;
        if( e.getTeleportTick() < 20 ) return;

        if ( e.getDeltaXZ() > 8d ) {
            this.setVlPerFail(MathUtil.getVlFromDouble(1.0f * Math.round(e.getDeltaXZ()) / 5));
            flag( e.getPlayer(), "DeltaXZ = " + MathUtil.getInfoFromDouble10(e.getDeltaXZ()) );
        }
    }
}
