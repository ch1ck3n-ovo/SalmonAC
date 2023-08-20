package com.ch1ck3n.salmonac.checks.player.groundspoof;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.MathUtil;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;

public class GroundSpoofB extends Check {
    public GroundSpoofB(String name, Category category, Punishment punishment, String description) {
        super(name, category, punishment, description);
        this.setType("(B)");
        this.setVlPerFail(2.0f);
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        if( e.getPlayer().getGameMode() == GameMode.CREATIVE ) return;

        // Type B
        // ClientGround but not ServerGround, DeltaY = 0
        if( e.getRespawnTick() < 40 ) return;
        if( e.isBoatAround() ) return;
        if( e.getLilyAround() > 0 ) return;
        if( e.getPlaceBlockTick() < 6 ) return;
        if( e.getSetBackTick() < 2) return;
        if( e.getSlimeTick() < 20 ) return;

        // Check
        if ( e.getDeltaY() == 0.0D && e.getLastDeltaY() == 0.0D &&
                e.isClientGround() && e.isLastClientGround() && !e.isLastServerGround() && !e.isServerGround() ) {
            flag(e.getPlayer(), "DeltaY = " + MathUtil.getInfoFromDouble10(e.getDeltaY()) +
                    "\nLastDeltaY = " + MathUtil.getInfoFromDouble10(e.getLastDeltaY()) +
                    "\nClientGround = " + e.isClientGround() +
                    "\nLastClientGround = " + e.isLastClientGround() +
                    "\nServerGround = " + e.isServerGround() +
                    "\nLastServerGround = " + e.isLastServerGround() );
        }
    }
}
