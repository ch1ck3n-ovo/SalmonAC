package com.ch1ck3n.salmonac.checks.player.groundspoof;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.MathUtil;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;

public class GroundSpoofC extends Check {
    public GroundSpoofC(String name, Category category, Punishment punishment, String description) {
        super(name, category, punishment, description);
        this.setType("NoGround");
        this.setSubCategory("GroundSpoof");
        this.setVlPerFail(2.0f);
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        if( e.getPlayer().getGameMode() == GameMode.CREATIVE ) return;

        // Type C
        // ServerGround but not ClientGround, DeltaY = 0
        if( e.getRespawnTick() < 40 ) return;
        if( e.getDamageTick() < 4 ) return;
        if( e.getSlimeTick() < 20 ) return;
        if( e.getTeleportTick() < 20 ) return;

        if( !e.isFuzzyServerGround() ) return;
        if( e.isPistonAround() ) return;
        if( e.isTouchingStair() ) return;

        // Check
        if ( e.getDeltaY() == 0 && e.getLastDeltaY() == 0 && e.isServerGround() && !e.isClientGround() ) {
            e.getSalmonPlayer().groundSpoofCBuffer.onTick();
            if (e.getSalmonPlayer().groundSpoofCBuffer.getTick() > 3) {
                flag(e.getPlayer(), "ClientGround = " + e.isClientGround() +
                        "\nServerGround = " + e.isServerGround() +
                        "\nDeltaY = " + MathUtil.getInfoFromDouble10(e.getDeltaY()) +
                        "\nLastDeltaY = " + MathUtil.getInfoFromDouble10(e.getLastDeltaY()) );
            }
        }
    }
}
