package com.ch1ck3n.salmonac.checks.combat;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import org.bukkit.event.EventHandler;

public class Velocity extends Check {
    public Velocity(String name, Response response, Punishment punishment, String description) {
        super(name, response, punishment, description);
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {

        // Type A
        if( e.getSalmonPlayer().getLastVelocityY() <= 0 || ( e.getDeltaY() / Math.abs(e.getDeltaY()) == (e.getSalmonPlayer().getLastVelocityY() / Math.abs(e.getSalmonPlayer().getLastVelocityY())) * -1) ) {} else {
            if ( e.getDeltaY() / e.getSalmonPlayer().getLastVelocityY() < 0.99 ) {
                this.setType("(A)");
                this.setVlPerFail(2.0f);
                flag(e.getPlayer(), "Percentage = " + String.format("%.10f", Math.abs(e.getDeltaY() / e.getSalmonPlayer().getLastVelocityY())) +
                        "\nDeltaY = " + String.format("%.10f", e.getDeltaY()) +
                        "\nVelocityY = " + String.format("%.10f", e.getSalmonPlayer().getLastVelocityY()));
            }
        }

//        // Type Baguar
//        if( e.getCustomPlayer().getLastVelocityY() <= 0 ) {} else {
//            if ( e.getServerAirTick() > 10 ) {
//                double y = Math.abs(e.getDeltaY() - e.getCustomPlayer().getLastVelocityY());
//                double data = e.getDeltaY() / y;
//                double diff = Math.abs(data-0.5);
//                if(diff >= 0.3 && diff < 0.8) {
//                    this.setType("(Baguar)");
//                    this.setVlPerFail(0.0f);
//                    flag(e.getPlayer(), "Bad Velocity Y Detection");
//                }
//            }
//        }
    }
}
