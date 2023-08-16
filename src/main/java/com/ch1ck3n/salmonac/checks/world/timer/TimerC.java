package com.ch1ck3n.salmonac.checks.world.timer;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.MathUtil;
import org.bukkit.event.EventHandler;

public class TimerC extends Check {
    public TimerC(String name, Response response, Punishment punishment, String description) {
        super(name, response, punishment, description);
        this.setType("Average");
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {

        if( e.getRespawnTick() < 40 ) return;
        if( e.getTeleportTick() < 20 ) return;

        // Check
        if ( e.getDeltaXZ() == 0 || e.getLastDeltaXZ() == 0  || !e.getPlayer().isSprinting() ) {
            e.getSalmonPlayer().timerCSampleList.clear();
            e.getSalmonPlayer().timerCBuffer.reduceTick();
        }
        e.getSalmonPlayer().timerCSampleList.add((e.getPacketTime() - e.getLastPacketTime()));
        if ( e.getSalmonPlayer().timerCSampleList.isFull() ) {
            if ( Math.abs(Math.round(e.getSalmonPlayer().timerCSampleList.getAverage()) - 50) > 2 ) {
                e.getSalmonPlayer().timerCBuffer.onTick();
                if ( e.getSalmonPlayer().timerCBuffer.getTick() > 7 ) {
                    this.setVlPerFail(MathUtil.getVlFromFloat(Math.abs(1 - 50f / e.getSalmonPlayer().timerCSampleList.getAverage()) * 2) * 4);
                    flag(e.getPlayer(), "Speed = " + (50f / e.getSalmonPlayer().timerCSampleList.getAverage()) +
                            "\nAverage = " + e.getSalmonPlayer().timerCSampleList.getAverage() +
                            "\nʕ•ᴥ•ʔ");
                }
            } else { e.getSalmonPlayer().timerCBuffer.reduceTick(); }
        }
    }
}
