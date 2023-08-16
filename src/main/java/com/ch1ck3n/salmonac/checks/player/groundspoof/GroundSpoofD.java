package com.ch1ck3n.salmonac.checks.player.groundspoof;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.MathUtil;
import com.ch1ck3n.salmonac.utils.PlayerUtil;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.potion.PotionEffectType;

public class GroundSpoofD extends Check {
    public GroundSpoofD(String name, Response response, Punishment punishment, String description) {
        super(name, response, punishment, description);
        this.setType("(D)");
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        if( e.getPlayer().getGameMode() == GameMode.CREATIVE ) return;

        // Type D
        // Spoof Ground in air with C03
        if( e.getRespawnTick() < 40 ) return;
        if( e.getDeltaY() >= 0 ) return;
        if( (e.isFuzzyServerGround() && !e.isCollidingHorizontally()) ) return;
        if( e.getPlaceBlockTick() < 6 ) return;
        if( e.getSlimeTick() < 6 ) return;
        if( e.isTouchingLiquid() ) return;

        // Check
        if ( Math.abs(e.getFallDistance() - e.getPlayer().getFallDistance()) > 1 &&
                e.getFallDistance() / 2 > e.getPlayer().getFallDistance() ) {
            if ( e.getFallDistance() - e.getLastFallDamage() > 0 ) {
                this.setVlPerFail(MathUtil.getVlFromDouble(e.getFallDistance() - e.getPlayer().getFallDistance()));
                flag(e.getPlayer(), "ServerFallDistance = " + String.format("%.10f", e.getFallDistance()) +
                        "\nClientFallDistance = " + String.format("%.10f", e.getPlayer().getFallDistance()) +
                        (this.getResponse() == Response.FIX ? "\n\nFix FallDistance to " + String.format("%.10f", e.getFallDistance() - e.getLastFallDamage()) : ""));
                if ( this.getResponse() == Response.FIX ) {
                    e.getPlayer().setFallDistance((float) (e.getFallDistance()));
                    e.setLastFallDamage(0);
                }
            } else {
                if ( e.getFallDistance() != 0 ) {
                    this.setType("(D)");
                    this.setVlPerFail(4.0f);
                    flag(e.getPlayer(), "ServerFallDistance = " + String.format("%.10f", e.getFallDistance()) +
                            "\nClientFallDistance = " + String.format("%.10f", e.getPlayer().getFallDistance()));
                }
            }
        }
    }
}
