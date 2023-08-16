package com.ch1ck3n.salmonac.checks.player.groundspoof;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.MathUtil;
import com.ch1ck3n.salmonac.utils.PlayerUtil;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.potion.PotionEffectType;

public class GroundSpoofC extends Check {
    public GroundSpoofC(String name, Response response, Punishment punishment, String description) {
        super(name, response, punishment, description);
        this.setType("(C)");
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        if( e.getPlayer().getGameMode() == GameMode.CREATIVE ) return;

        // Type C
        // ServerGround but not ClientGround, DeltaY = 0
        if( e.getRespawnTick() < 40 ) return;
        if( e.getDamageTick() < 4 ) return;
        if( !e.isFuzzyServerGround() ) return;
        if( e.isPistonAround() ) return;
        if( e.getSlimeTick() < 20 ) return;
        if( e.getTeleportTick() < 20 ) return;
        if( e.isTouchingStair() ) return;

        // Check
        if ( e.getDeltaY() == 0 && e.getLastDeltaY() == 0 && e.isServerGround() && !e.isClientGround() ) {
            this.setVlPerFail(2.0f);
            flag(e.getPlayer(), "ClientGround = " + e.isClientGround() +
                    "\nServerGround = " + e.isServerGround() +
                    "\nDeltaY = " + String.format("%.10f", e.getDeltaY()) +
                    "\nLastDeltaY = " + String.format("%.10f", e.getLastDeltaY()) +
                    (this.getResponse() == Response.FIX ? "\n\nFix FallDistance to " + String.format("%.10f", e.getFallDistance()) : ""));
            if ( this.getResponse() == Response.FIX ) {
                if (e.getPlayer().getFallDistance() - 3 > 0) e.getPlayer().damage(e.getPlayer().getFallDistance() - 3);
                e.getPlayer().setFallDistance((float) e.getFallDistance());
            }
        }
    }
}
