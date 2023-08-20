package com.ch1ck3n.salmonac.checks.player.groundspoof;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.MathUtil;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;

public class GroundSpoofD extends Check {
    public GroundSpoofD(String name, Category category, Punishment punishment, String description) {
        super(name, category, punishment, description);
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
                this.setVlPerFail(MathUtil.getVlFromDoubleOrDefault(e.getFallDistance() - e.getPlayer().getFallDistance(), 2.0f));
                flag(e.getPlayer(), "ServerFallDistance = " + MathUtil.getInfoFromDouble10(e.getFallDistance()) +
                        "\nClientFallDistance = " + MathUtil.getInfoFromDouble10(e.getPlayer().getFallDistance()) );
            }
//            else {
//                if ( e.getFallDistance() != 0 ) {
//                    this.setType("(D)");
//                    this.setVlPerFail(4.0f);
//                    flag(e.getPlayer(), "ServerFallDistance = " + MathUtil.getInfoFromDouble10(e.getFallDistance()) +
//                            "\nClientFallDistance = " + MathUtil.getInfoFromDouble10(e.getPlayer().getFallDistance()));
//                }
//            }
        }
    }
}
