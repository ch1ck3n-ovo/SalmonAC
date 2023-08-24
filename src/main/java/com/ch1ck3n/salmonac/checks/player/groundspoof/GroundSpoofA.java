package com.ch1ck3n.salmonac.checks.player.groundspoof;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.MathUtil;
import com.ch1ck3n.salmonac.utils.PlayerUtil;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.potion.PotionEffectType;

public class GroundSpoofA extends Check {
    public GroundSpoofA(String name, Category category, Punishment punishment, String description) {
        super(name, category, punishment, description);
        this.setType("AlwaysSpoof.A");
        this.setSubCategory("GroundSpoof");
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        if( e.getPlayer().getGameMode() == GameMode.CREATIVE ) return;

        // Type A
        // ClientGround but not ServerGround, DeltaY != 0
        if( e.getRespawnTick() < 40 ) return;
        if( e.getPlaceBlockTick() < 6 ) return;
        if( e.getServerAirTick() < 6 + PlayerUtil.getAmplifier(e.getPlayer(), PotionEffectType.JUMP) ) return;
        if( e.getSetBackTick() < 2 ) return;
        if( e.getSlimeTick() < 20 ) return;

        if( e.isBoatAround() ) return;
        if( (e.isFuzzyServerGround() && !e.isFuzzyCollidingHorizontally()) ) return;


        // Check
        if ( e.getDeltaY() != 0.0D && e.getLastDeltaY() != 0.0D && !e.isInLiquid() &&
            e.isClientGround() && e.isLastClientGround() && !e.isLastServerGround() && !e.isServerGround() ) {
            this.setVlPerFail(MathUtil.getVlFromDoubleOrDefault(e.getFallDistance() - e.getPlayer().getFallDistance(), 1.0f));
            flag(e.getPlayer(), "DeltaY = " + MathUtil.getInfoFromDouble10(e.getDeltaY()) +
                    "\nLastDeltaY = " + MathUtil.getInfoFromDouble10(e.getLastDeltaY()) +
                    "\nClientGround = " + e.isClientGround() +
                    "\nLastClientGround = " + e.isLastClientGround() +
                    "\nServerGround = " + e.isServerGround() +
                    "\nLastServerGround = " + e.isLastServerGround() );
        }
    }
}
