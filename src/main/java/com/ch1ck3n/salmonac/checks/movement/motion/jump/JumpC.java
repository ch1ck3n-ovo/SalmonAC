package com.ch1ck3n.salmonac.checks.movement.motion.jump;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.MathUtil;
import com.ch1ck3n.salmonac.utils.PlayerUtil;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.potion.PotionEffectType;

public class JumpC extends Check {
    public JumpC(String name, Category category, Punishment punishment, String description) {
        super(name, category, punishment, description);
        this.setType("Jump.LowHop");
        this.setSubCategory("Motion");
        this.setVlPerFail(3.0f);
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        if( e.getPlayer().getGameMode() == GameMode.CREATIVE ) return;

        if( e.getRespawnTick() < 20 ) return;
        if( e.getClimbTick() < 4 ) return;
        if( e.getDamageTick() < 20 ) return;
        if( e.getWebTick() == 0 ) return;

        if( e.isCollidingVerticallyUp() ) return;
        if( e.isTouchingLiquid() ) return;

        // Check
        if ( e.isJumping() && e.getJumpTick() > 5 + PlayerUtil.getAmplifier(e.getPlayer(), PotionEffectType.JUMP) && e.getDeltaY() < 0 ) {
            e.getSalmonPlayer().jumpCBuffer.onTick();
            if (e.getSalmonPlayer().jumpCBuffer.getTick() > 1) {
                flag(e.getPlayer(), "Jumping = " + e.isJumping() +
                        "\nJumpTick = " + e.getJumpTick() +
                        "\nDeltaY = " + MathUtil.getInfoFromDouble10(e.getDeltaY()));
            }
        }
    }
}
