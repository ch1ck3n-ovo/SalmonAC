package com.ch1ck3n.salmonac.managers;

import com.ch1ck3n.salmonac.SalmonAC;
import com.ch1ck3n.salmonac.checks.Check;
import org.bukkit.plugin.PluginManager;

import java.util.ArrayList;

public class CheckManager {

    ArrayList<Check> checks;
    SalmonAC instance;
    PluginManager pluginManager;

    public CheckManager(){
        checks = new ArrayList<>();
        instance = SalmonAC.getInstance();
        pluginManager = instance.getServer().getPluginManager();
    }

    public void addCheck(Check check){
        checks.add( check );
    }

    public Check getCheck(String checkName) {
        for( Check c: checks ) {
            if( c.getCheckName() == checkName ) {
                return c;
            }
        }

        return null;
    }

    public ArrayList<Check> getChecks() {
        return checks;
    }

    public void setupChecks(){
        for( Check c : checks ){
            pluginManager.registerEvents( c , instance );
        }
    }

}
