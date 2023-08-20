package com.ch1ck3n.salmonac.checks.player.groundspoof;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.MathUtil;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;

public class GroundSpoofE extends Check {
    public GroundSpoofE(String name, Category category, Punishment punishment, String description) {
        super(name, category, punishment, description);
        this.setType("(E)");
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        if( e.getPlayer().getGameMode() == GameMode.CREATIVE ) return;

        // Type E
        // My Flux NoFall ==
        if( e.getRespawnTick() < 40 ) return;
        if( e.getJumpTick() != 0 ) return;
        if( e.getSlimeTick() < 20 ) return;
        if( (e.isFuzzyServerGround() && !e.isFuzzyCollidingHorizontally()) ) return;
        if( e.isTouchingLiquid() ) return;
        if( e.isTouchingSlab() ) return;
        if( e.isTouchingStair() ) return;

        // Check
        if ( e.getFallDistance() > 1 && e.getPlayer().getFallDistance() == 0 && !e.isServerGround() ) {
            this.setVlPerFail(MathUtil.getVlFromDoubleOrDefault(e.getFallDistance() - e.getPlayer().getFallDistance(), 2.0f));
            flag( e.getPlayer(), "ServerFallDistance = " + MathUtil.getInfoFromDouble10(e.getFallDistance()) +
                    "\nClientFallDistance = " + MathUtil.getInfoFromDouble10(e.getPlayer().getFallDistance()) +
                    "\nServerGround = " + e.isServerGround() );
        }

//        // Type F
//        // Baguar NoFall codes
//        if( e.getRespawnTick() < 40 || e.getPlaceBlockTick() < 6 || e.getSlimeTick() == 0 ||
//                e.isSuperFuzzyServerGround() || e.isTouchingLiquid() ) {} else {
//            if ( e.isClientGround() != e.isServerGround() || e.isClientGround() != (e.getDeltaY() > 0) ) {
//                this.setType("(F)");
//                this.setVlPerFail(5.0f);
//                e.getCustomPlayer().groundSpoofFBuffer.onTick();
//                if( e.getCustomPlayer().groundSpoofFBuffer.getTick() > 5 ) {
//                    flag(e.getPlayer(), "ClientGround = " + e.isClientGround() +
//                            "\nServerGround = " + e.isServerGround() +
//                            "\nDeltaY > 0 = " + (e.getDeltaY() > 0) +
//                            "\n\n<Baguar> LEMAFO");
//                }
//            }else {
//                e.getCustomPlayer().groundSpoofFBuffer.reduceTick();
//            }
//        }

//        // Type G
//        if( e.getRespawnTick() < 40 || e.getSalmonPlayer().getVelocityY() != 0 ||
//                e.getSlimeTick() < 20 || (e.isSuperFuzzyServerGround() && e.isCollidingHorizontally()) || e.isTouchingLiquid() ) {} else {
//            if ( e.getFallDistance() != 0 && e.getPlayer().getFallDistance() == 0 && !e.isServerGround() ) {
//                this.setType("(G)");
//                this.setVlPerFail(5.0f);
//                flag( e.getPlayer(), "ServerFallDistance = " + String.format("%.10f", e.getFallDistance()) +
//                        "\nClientFallDistance = " + String.format("%.10f", e.getPlayer().getFallDistance()) +
//                        "\nServerGround = " + e.isServerGround() );
//            }
//        }
    }
}
