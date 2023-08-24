package com.ch1ck3n.salmonac.checks.movement.invmove;

import com.ch1ck3n.salmonac.SalmonAC;
import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.utils.MathUtil;
import com.ch1ck3n.salmonac.utils.SalmonPlayer;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InvMoveD extends Check {
    public InvMoveD(String name, Category category, Punishment punishment, String description) {
        super(name, category, punishment, description);
        this.setType("Rotate");
        this.setSubCategory("InvMove");
        this.setVlPerFail(2.0f);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        SalmonPlayer salmonPlayer = SalmonAC.getInstance().getPlayerManager().getPlayer( (Player)e.getWhoClicked() );

        // Type D
        // Click gui while rotating
        if( salmonPlayer.getPlayer().getGameMode() == GameMode.CREATIVE ) return;

        // Check
        if (salmonPlayer.getDeltaYaw() != 0 || salmonPlayer.getDeltaPitch() != 0) {
            salmonPlayer.invMoveDBuffer.onTick();
            if (salmonPlayer.invMoveDBuffer.getTick() > 1) {
                flag(salmonPlayer.getPlayer(), "DeltaYaw = " + MathUtil.getInfoFromFloat10(salmonPlayer.getDeltaYaw()) +
                        "\nDeltaPitch = " + MathUtil.getInfoFromFloat10(salmonPlayer.getDeltaPitch()) );
            }
        }
    }
}
