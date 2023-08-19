package com.ch1ck3n.salmonac.checks.movement.motion;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.MathUtil;
import com.ch1ck3n.salmonac.utils.PlayerUtil;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.potion.PotionEffectType;

public class MotionI extends Check {
    public MotionI(String name, Category category, Punishment punishment, String description) {
        super(name, category, punishment, description);
        this.setType("Invalid(B)/LowHop");
        this.setVlPerFail(3.0f);
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {

        // Type I (Invalid)
        if( e.getRespawnTick() < 20 ) return;
        if( e.getClimbTick() < 4 ) return;
        if( e.isCollidingVerticallyUp() ) return;
        if( e.getDamageTick() < 20 ) return;
        if( e.isTouchingLiquid() ) return;
        if( e.getWebTick() == 0 ) return;

        // Check
        if ( e.isJumping() && e.getJumpTick() > 5 + PlayerUtil.getAmplifier(e.getPlayer(), PotionEffectType.JUMP) && e.getDeltaY() < 0 ) {
            e.getSalmonPlayer().motionIBuffer.onTick();
            if (e.getSalmonPlayer().motionIBuffer.getTick() > 1) {
                flag(e.getPlayer(), "Jumping = " + e.isJumping() +
                        "\nJumpTick = " + e.getJumpTick() +
                        "\nDeltaY = " + MathUtil.getInfoFromDouble10(e.getDeltaY()));
            }
        }
    }
}
