package com.ch1ck3n.salmonac.events;

import com.ch1ck3n.salmonac.SalmonAC;
import com.ch1ck3n.salmonac.utils.SalmonPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

public class PlayerEventListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        SalmonAC.getInstance().getPlayerManager().registerPlayer( e.getPlayer() );
        SalmonPlayer salmonPlayer = getCustomPlayer(e.getPlayer());
        salmonPlayer.respawnTick = 0;
    }

    @EventHandler
    public void onJoin(PlayerQuitEvent e){
        SalmonAC.getInstance().getPlayerManager().unregisterPlayer( e.getPlayer() );
    }

    @EventHandler
    public void onSwing(PlayerAnimationEvent e) {
        if( e.getAnimationType() == PlayerAnimationType.ARM_SWING ){
            SalmonPlayer salmonPlayer = getCustomPlayer(e.getPlayer());
            if (salmonPlayer == null) return;

            salmonPlayer.swingTick = 0;
//            salmonPlayer.lastSwingDelay = salmonPlayer.getSwingDelay();
//            salmonPlayer.swingDelay = System.currentTimeMillis() - salmonPlayer.getLastSwingTime();
//            salmonPlayer.lastSwingTime = salmonPlayer.getSwingTime();
//            salmonPlayer.swingTime = System.currentTimeMillis();
        }
    }

    @EventHandler
    public void onItemChange(PlayerItemHeldEvent e) {
        SalmonPlayer salmonPlayer = getCustomPlayer(e.getPlayer());
        if (salmonPlayer == null) return;

        salmonPlayer.itemChangeTick = 0;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        SalmonPlayer salmonPlayer = getCustomPlayer(e.getPlayer());
        if (salmonPlayer == null) return;

        if ( salmonPlayer.doSetBack() ) {
            e.setTo(e.getFrom());
            salmonPlayer.setBackTick = 0;
            salmonPlayer.setBack = false;
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        SalmonPlayer salmonPlayer = getCustomPlayer(e.getPlayer());
        if (salmonPlayer == null) return;

        salmonPlayer.respawnTick = 0;
    }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent e) {
        SalmonPlayer salmonPlayer = getCustomPlayer(e.getPlayer());
        if ( salmonPlayer == null || salmonPlayer.doSetBack() ) return;

        salmonPlayer.teleportTick = 0;
    }

    @EventHandler
    public void onVelocity(PlayerVelocityEvent e) {
        SalmonPlayer salmonPlayer = SalmonAC.getInstance().getPlayerManager().getPlayer( e.getPlayer() );
        if( salmonPlayer == null ) return;

        salmonPlayer.velocityX = e.getVelocity().getX();
        salmonPlayer.velocityY = e.getVelocity().getY();
        salmonPlayer.velocityZ = e.getVelocity().getZ();
        salmonPlayer.velocityH = Math.hypot(salmonPlayer.velocityX, salmonPlayer.velocityZ);
        salmonPlayer.velocityTick = 0;
    }

    public SalmonPlayer getCustomPlayer(Player player){
        return SalmonAC.getInstance().getPlayerManager().getPlayer( player );
    }
}
