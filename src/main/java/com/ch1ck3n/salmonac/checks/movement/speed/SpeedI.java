package com.ch1ck3n.salmonac.checks.movement.speed;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.MathUtil;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;

public class SpeedI extends Check {
    public SpeedI(String name, Category category, Punishment punishment, String description) {
        super(name, category, punishment, description);
        this.setType("LadderSpeed");
        this.setVlPerFail(1.0f);
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        if( e.getPlayer().getGameMode() == GameMode.CREATIVE ) return;

        if( e.getRespawnTick() < 20 ) return;

        if( e.isOnLadder() && Float.parseFloat(String.format("%.2f", e.getDeltaXZ())) > 0.151d &&
                Float.parseFloat(String.format("%.2f", e.getLastDeltaXZ())) > 0.151d) {
            e.getSalmonPlayer().speedIBuffer.onTick();
            if ( e.getSalmonPlayer().speedIBuffer.getTick() > 8 ) {
                flag(e.getPlayer(), "DeltaXZ = " + MathUtil.getInfoFromDouble10(e.getDeltaXZ()) +
                        "\nMax = " + MathUtil.getInfoFromDouble10(0.151d));
            }
        }
    }
}
