package com.ch1ck3n.salmonac;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.checks.combat.blocking.BlockingA;
import com.ch1ck3n.salmonac.checks.combat.critical.CriticalA;
import com.ch1ck3n.salmonac.checks.combat.critical.CriticalB;
import com.ch1ck3n.salmonac.checks.combat.critical.CriticalC;
import com.ch1ck3n.salmonac.checks.combat.reach.ReachA;
import com.ch1ck3n.salmonac.checks.combat.swing.SwingA;
import com.ch1ck3n.salmonac.checks.movement.invmove.*;
import com.ch1ck3n.salmonac.checks.movement.jesus.*;
import com.ch1ck3n.salmonac.checks.movement.motion.*;
import com.ch1ck3n.salmonac.checks.movement.speed.*;
import com.ch1ck3n.salmonac.checks.movement.sprinting.*;
import com.ch1ck3n.salmonac.checks.movement.step.*;
import com.ch1ck3n.salmonac.checks.player.groundspoof.*;
import com.ch1ck3n.salmonac.checks.world.timer.*;
import com.ch1ck3n.salmonac.commands.SalmonACCommand;
import com.ch1ck3n.salmonac.events.*;
import com.ch1ck3n.salmonac.managers.CheckManager;
import com.ch1ck3n.salmonac.managers.PlayerManager;
import com.ch1ck3n.salmonac.utils.PlayerUtil;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public final class SalmonAC extends JavaPlugin implements Listener {

    public Executor packetThread;
    private String prefix = "";
    public String getPrefix(){
        return prefix;
    }
    private boolean broadcast = true;
    public boolean shouldBroadcast() {
        return broadcast;
    }
    public void setBroadcast(boolean b) {
        broadcast = b;
    }
    private boolean debugMode = false;
    public boolean isDebugMode() {
        return debugMode;
    }
    public void setDebugMode(boolean b) {
        debugMode = b;
    }
    private static SalmonAC instance;
    public static SalmonAC getInstance(){
        return instance;
    }
    private CheckManager checkManager;
    public CheckManager getCheckManager(){
        return checkManager;
    }
    private PlayerManager playerManager;
    public PlayerManager getPlayerManager(){
        return playerManager;
    }

    @Override
    public void onEnable() {
        /* ----- SalmonAC Setup ----- */
        instance = this;
        prefix = "§8[§cSalmon§8]§r";

        getServer().getPluginManager().registerEvents(this,this);
        getServer().getPluginManager().registerEvents(new BlockEventListener(),this);
        getServer().getPluginManager().registerEvents(new EntityEventListener(),this);
        getServer().getPluginManager().registerEvents(new InventoryEventListener(),this);
        getServer().getPluginManager().registerEvents(new PlayerEventListener(),this);

        packetThread = Executors.newSingleThreadExecutor();
        checkManager = new CheckManager();
        playerManager = new PlayerManager();

        PlayerUtil.weaponDamageListSetup();

        /* ----- Check Setup ----- */

        // Combat
//        checkManager.addCheck(new Aim("Aim", Check.Category.COMBAT, Check.Punishment.KICK, "Invalid rotations"));

        // Combat.Blocking
        checkManager.addCheck( new BlockingA("Blocking(A)", Check.Category.COMBAT, Check.Punishment.KICK, "Attack while blocking") );

        // Combat.Critical
        checkManager.addCheck( new CriticalA("Critical(A)", Check.Category.COMBAT, Check.Punishment.KICK, "Modify packet to do critical" +
                "\n(Crits with 0 FallDistance)") );
        checkManager.addCheck( new CriticalB("Critical(B)", Check.Category.COMBAT, Check.Punishment.KICK, "Modify packet to do critical" +
                "\n(Crits with FallDistance but Different GroundState)") );
        checkManager.addCheck( new CriticalC("Critical(C)", Check.Category.COMBAT, Check.Punishment.KICK, "Modify packet to do critical" +
                "\n(Crits with bad GroundState)") );

        // Combat.Reach
        checkManager.addCheck( new ReachA("Reach(A)", Check.Category.COMBAT, Check.Punishment.KICK, "Modify reach distance to hit further") );

        // Combat.Swing
        checkManager.addCheck( new SwingA("Swing(A)", Check.Category.COMBAT, Check.Punishment.KICK, "Cancel packet to not to swing") );

//        checkManager.addCheck( new Velocity("Velocity", Check.Category.COMBAT, Check.Punishment.KICK, "Cancel packet to get no knockback") );

        // Movement.InvMove
        checkManager.addCheck( new InvMoveA("InvMove(A)", Check.Category.MOVEMENT, Check.Punishment.KICK, "Click gui while sprinting") );
        checkManager.addCheck( new InvMoveB("InvMove(B)", Check.Category.MOVEMENT, Check.Punishment.KICK, "Click gui while sneaking") );
        checkManager.addCheck( new InvMoveC("InvMove(C)", Check.Category.MOVEMENT, Check.Punishment.KICK, "Click gui while attacking entity") );
        checkManager.addCheck( new InvMoveD("InvMove(D)", Check.Category.MOVEMENT, Check.Punishment.KICK, "Click gui while rotating") );
        checkManager.addCheck( new InvMoveE("InvMove(E)", Check.Category.MOVEMENT, Check.Punishment.KICK, "Click gui while placing block") );

        // Movement.Jesus
        checkManager.addCheck( new JesusA("Jesus(A)", Check.Category.MOVEMENT, Check.Punishment.KICK, "Walk on liquid") );
        checkManager.addCheck( new JesusB("Jesus(B)", Check.Category.MOVEMENT, Check.Punishment.KICK, "Walk on liquid") );
        checkManager.addCheck( new JesusC("Jesus(C)", Check.Category.MOVEMENT, Check.Punishment.KICK, "Walk on liquid") );

        // Movement.Motion
        checkManager.addCheck( new MotionA("Motion(A)", Check.Category.MOVEMENT, Check.Punishment.KICK, "Modify motion Y to do impossible movement") );
        checkManager.addCheck( new MotionB("Motion(B)", Check.Category.MOVEMENT, Check.Punishment.KICK, "Modify motion Y to do impossible movement") );
        checkManager.addCheck( new MotionC("Motion(C)", Check.Category.MOVEMENT, Check.Punishment.KICK, "Modify motion Y to do impossible movement") );
        checkManager.addCheck( new MotionD("Motion(D)", Check.Category.MOVEMENT, Check.Punishment.KICK, "Modify motion Y to do impossible movement") );
        checkManager.addCheck( new MotionE("Motion(E)", Check.Category.MOVEMENT, Check.Punishment.KICK, "Modify motion Y to do impossible movement") );
        checkManager.addCheck( new MotionF("Motion(F)", Check.Category.MOVEMENT, Check.Punishment.KICK, "Modify motion Y to do impossible movement") );
        checkManager.addCheck( new MotionG("Motion(G)", Check.Category.MOVEMENT, Check.Punishment.KICK, "Modify motion Y to do impossible movement") );
        checkManager.addCheck( new MotionH("Motion(H)", Check.Category.MOVEMENT, Check.Punishment.KICK, "Modify motion Y to do impossible movement") );
        checkManager.addCheck( new MotionI("Motion(I)", Check.Category.MOVEMENT, Check.Punishment.KICK, "Modify motion Y to do impossible movement") );

        // Movement.Speed
        checkManager.addCheck( new SpeedA("Speed(A)", Check.Category.MOVEMENT, Check.Punishment.KICK, "Modify motion XZ to move faster") );
        checkManager.addCheck( new SpeedB("Speed(B)", Check.Category.MOVEMENT, Check.Punishment.KICK, "Modify motion XZ to move faster") );
        checkManager.addCheck( new SpeedC("Speed(C)", Check.Category.MOVEMENT, Check.Punishment.KICK, "Modify motion XZ to move faster") );
        checkManager.addCheck( new SpeedD("Speed(D)", Check.Category.MOVEMENT, Check.Punishment.KICK, "Modify motion XZ to move faster" +
                "\n(Move to fast without teleporting)") );
        checkManager.addCheck( new SpeedE("Speed(E)", Check.Category.MOVEMENT, Check.Punishment.KICK, "Modify motion XZ to move faster" +
                "\n(Experimental)") );
        checkManager.addCheck( new SpeedF("Speed(F)", Check.Category.MOVEMENT, Check.Punishment.KICK, "Modify motion XZ to move faster") );
        checkManager.addCheck( new SpeedG("Speed(G)", Check.Category.MOVEMENT, Check.Punishment.KICK, "Modify motion XZ to move faster") );
//        checkManager.addCheck( new SpeedH("Speed(H)", Check.Category.MOVEMENT, Check.Punishment.KICK, "Modify motion XZ to move faster") );

        // Movement.Sprinting
        checkManager.addCheck( new SprintingA("Sprinting(A)", Check.Category.MOVEMENT,Check.Punishment.KICK, "BlockingA sprinting") );
        checkManager.addCheck( new SprintingB("Sprinting(B)", Check.Category.MOVEMENT,Check.Punishment.KICK, "Hungry sprinting") );
        checkManager.addCheck( new SprintingC("Sprinting(C)", Check.Category.MOVEMENT,Check.Punishment.KICK, "Sneaking sprinting") );
        checkManager.addCheck( new SprintingD("Sprinting(D)", Check.Category.MOVEMENT,Check.Punishment.KICK, "Blind sprinting") );
        checkManager.addCheck( new SprintingE("Sprinting(E)", Check.Category.MOVEMENT,Check.Punishment.KICK, "No move sprinting") );
        checkManager.addCheck( new SprintingF("Sprinting(F)", Check.Category.MOVEMENT,Check.Punishment.KICK, "Omni sprinting") );

        // Movement.Step
        checkManager.addCheck( new StepA("Step(A)", Check.Category.MOVEMENT, Check.Punishment.KICK, "Modify step height to step higher" +
                "\n(Vanilla or etc)") );
        checkManager.addCheck( new StepB("Step(B)", Check.Category.MOVEMENT, Check.Punishment.KICK, "Modify step height to step higher" +
                "\n(Bad ground states)") );

        // Player.GroundSpoof
        checkManager.addCheck( new GroundSpoofA("GroundSpoof(A)", Check.Category.PLAYER, Check.Punishment.KICK, "Modify packet to spoof ground" +
                "\n(Spoof ground while it's not, motionY != 0)") );
        checkManager.addCheck( new GroundSpoofB("GroundSpoof(B)", Check.Category.PLAYER, Check.Punishment.KICK, "Modify packet to spoof ground" +
                "\n(Spoof ground while it's not, motionY = 0)") );
        checkManager.addCheck( new GroundSpoofC("GroundSpoof(C)", Check.Category.PLAYER, Check.Punishment.KICK, "Modify packet to spoof ground" +
                "\n(Spoof not ground while it it, motionY = 0)") );
        checkManager.addCheck( new GroundSpoofD("GroundSpoof(D)", Check.Category.PLAYER, Check.Punishment.KICK, "Modify packet to spoof ground" +
                "\n(Spoof ground with C03 mid air)") );
        checkManager.addCheck( new GroundSpoofE("GroundSpoof(E)", Check.Category.PLAYER, Check.Punishment.KICK, "Modify packet to spoof ground" +
                "\n(Patch for the bypass made by me)") );

        // World.Timer
        checkManager.addCheck( new TimerA("Timer(A)", Check.Category.WORLD, Check.Punishment.KICK, "Modify timer speed to move faster" +
                "\n(Max speed 1.21)") );
        checkManager.addCheck( new TimerB("Timer(B)", Check.Category.WORLD, Check.Punishment.KICK, "Modify timer speed to move slower" +
                "\n(Min speed 0.9)") );
        checkManager.addCheck( new TimerC("Timer(C)", Check.Category.WORLD, Check.Punishment.KICK, "Modify timer speed to move faster or slower" +
                "\n(More Precisely)") );

        checkManager.setupChecks();

        /* ----- Player Register ----- */
        for( Player p : Bukkit.getOnlinePlayers() ){
            playerManager.registerPlayer( p );
        }


        /* ----- Command Register ----- */
        getCommand("salmon").setExecutor( new SalmonACCommand() );

        /* ----- ProtocolLib Setup ----- */
        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(this,
                ListenerPriority.NORMAL,
                PacketType.Play.Client.POSITION,
                PacketType.Play.Client.POSITION_LOOK) {
            @Override
            public void onPacketReceiving(PacketEvent event) {
                packetThread.execute(() ->{
                    if (event.getPacketType() == PacketType.Play.Client.POSITION || event.getPacketType() == PacketType.Play.Client.POSITION_LOOK) {
                        Player player = event.getPlayer();
                        double x = event.getPacket().getDoubles().read(0);
                        double y = event.getPacket().getDoubles().read(1);
                        double z = event.getPacket().getDoubles().read(2);
                        float yaw = event.getPacket().getFloat().read(0);
                        float pitch = event.getPacket().getFloat().read(1);
                        SalmonMoveEvent customEvent = new SalmonMoveEvent(player,new Location(player.getWorld(),x,y,z,yaw,pitch));
                        getServer().getPluginManager().callEvent( customEvent );
                    }
                });
            }
            @Override
            public void onPacketSending(PacketEvent event) {

            }
        });
    }
}
