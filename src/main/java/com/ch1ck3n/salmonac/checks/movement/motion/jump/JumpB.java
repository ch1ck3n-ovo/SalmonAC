package com.ch1ck3n.salmonac.checks.movement.motion.jump;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.MathUtil;
import com.ch1ck3n.salmonac.utils.PlayerUtil;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.potion.PotionEffectType;

public class JumpB extends Check {
    public JumpB(String name, Category category, Punishment punishment, String description) {
        super(name, category, punishment, description);
        this.setType("Jump.General");
        this.setSubCategory("Motion");
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        if( e.getPlayer().getGameMode() == GameMode.CREATIVE ) return;

        if( e.getRespawnTick() < 20 ) return;
        if( e.getCollidingVerticallyUpTick() < 2 ) return;
        if( e.getDamageTick() < 2 ) return;
        if( e.getServerAirTick() != 1 ) return;
        if( e.getSetBackTick() < 2 ) return;
        if( e.getSlimeTick() < 20 ) return;
        if( e.getWebTick() == 0 ) return;

        if( e.isCollidingVerticallyUp() ) return;
        if( e.isTouchingLiquid() ) return;

        if( e.getDeltaY() <= 0 ) return;
        if( e.isOnLadder() ) return;

        // Check
        if ( !e.isServerGround() ) {
            double prediction = 0.41999998688697815;
            if ( e.getPlayer().hasPotionEffect(PotionEffectType.JUMP) )
                prediction += PlayerUtil.getAmplifier(e.getPlayer(), PotionEffectType.JUMP) * 0.1f;
            if (Math.abs(e.getDeltaY() - prediction) > 0.001) {
                if(e.getDeltaY() > prediction) this.setType("Jump.HighJump");
                else if(e.getDeltaY() < prediction) this.setType("Jump.LowHop");
                this.setVlPerFail(MathUtil.getVlFromDouble(Math.abs(e.getDeltaY() - prediction)) * 10);
                flag( e.getPlayer(), "DeltaY = " + MathUtil.getInfoFromDouble10(e.getDeltaY()) +
                        "\nPrediction = " + MathUtil.getInfoFromDouble10(prediction) );
            }else { this.setType("Jump.General"); }
        }
    }
}
