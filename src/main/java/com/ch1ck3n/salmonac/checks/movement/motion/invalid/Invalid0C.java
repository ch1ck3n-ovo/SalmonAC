package com.ch1ck3n.salmonac.checks.movement.motion.invalid;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.MathUtil;
import com.ch1ck3n.salmonac.utils.PlayerUtil;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.potion.PotionEffectType;

public class Invalid0C extends Check {
    public Invalid0C(String name, Category category, Punishment punishment, String description) {
        super(name, category, punishment, description);
        this.setType("Invalid.Collide");
        this.setSubCategory("Motion");
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        if( e.getPlayer().getGameMode() == GameMode.CREATIVE ) return;

        if( e.getRespawnTick() < 20 ) return;
        if( e.getClimbTick() < 2 ) return;
        if( e.getDamageTick() < 2 ) return;
        if( e.getPlaceBlockTick() < 6 ) return;
        if( (e.getServerAirTick() < 6 + PlayerUtil.getAmplifier(e.getPlayer(), PotionEffectType.JUMP)) ) return;

        if( e.getLilyAround() != 0 ) return;
        if( e.isTouchingClimable() ) return;
        if( e.isTouchingLiquid() ) return;

        if( e.isOnLadder() ) return;

        // Check
        if (e.isCollidingHorizontally() && !e.isLastServerGround() && e.getDeltaY() > 0) {
            e.getSalmonPlayer().invalid0CBuffer.onTick();
            if (e.getSalmonPlayer().invalid0CBuffer.getTick() > 1) {
                this.setVlPerFail(MathUtil.getVlFromDouble(e.getDeltaY()) * 10);
                flag(e.getPlayer(), "CollidingHorizontally = " + e.isCollidingHorizontally() +
                        "\nServerGround = " + e.isServerGround() +
                        "\nDeltaY = " + MathUtil.getInfoFromDouble10(e.getDeltaY()));
            }
        }
    }
}
