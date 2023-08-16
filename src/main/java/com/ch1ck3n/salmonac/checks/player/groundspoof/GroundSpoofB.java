package com.ch1ck3n.salmonac.checks.player.groundspoof;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.PlayerUtil;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.potion.PotionEffectType;

public class GroundSpoofB extends Check {
    public GroundSpoofB(String name, Response response, Punishment punishment, String description) {
        super(name, response, punishment, description);
        this.setType("(B)");
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        if( e.getPlayer().getGameMode() == GameMode.CREATIVE ) return;

        // Type B
        // ClientGround but not ServerGround, DeltaY = 0
        if( e.getRespawnTick() < 40 ) return;
        if( e.isBoatAround() ) return;
        if( e.getLilyAround() > 0 ) return;
        if( e.getSetBackTick() < 2) return;
        if( e.getSlimeTick() < 20 ) return;

        // Check
        if ( e.getDeltaY() == 0.0D && e.getLastDeltaY() == 0.0D &&
                e.isClientGround() && e.isLastClientGround() && !e.isLastServerGround() && !e.isServerGround() ) {
            this.setVlPerFail(2.0f);
            flag(e.getPlayer(), "DeltaY = " + String.format("%.10f", e.getDeltaY()) +
                    "\nLastDeltaY = " + String.format("%.10f", e.getLastDeltaY()) +
                    "\nClientGround = " + e.isClientGround() +
                    "\nLastClientGround = " + e.isLastClientGround() +
                    "\nServerGround = " + e.isServerGround() +
                    "\nLastServerGround = " + e.isLastServerGround() );
        }
    }
}
