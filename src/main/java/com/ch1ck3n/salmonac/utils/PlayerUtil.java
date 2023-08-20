package com.ch1ck3n.salmonac.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;

public class PlayerUtil {
    public static float[] getAngle(Entity entity, Player damager) {
        float yaw, pitch;
        Location damagerPos = damager.getEyeLocation();
        Location entityPos = entity.getLocation();
        double d0 = entityPos.getX() - damagerPos.getX();
        double d1 = entityPos.getY() - damagerPos.getY();
        double d2 = entityPos.getZ() - damagerPos.getZ();
        double d3 = Math.sqrt(d0 * d0 + d2 * d2);
        float f = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
        float f1 = (float)(-(Math.atan2(d1, d3) * 180.0D / Math.PI));
        yaw = f;
        pitch = f1;
        return new float[] {yaw, pitch};
    }
    public static float[] getYawMinAndMax(Entity entity, Player damager) {
        float minYaw = 360, maxYaw = 0;
        Location damagerPos = damager.getEyeLocation();
        Location entityPos = entity.getLocation();
        float[] offset = {-0.5f, 0.5f};
        float[] yoffset = {0.0f, 1.8f};
        for(float x : offset) {
            for(float z : offset) {
                double d0 = entityPos.getX() - damagerPos.getX() + x;
                double d2 = entityPos.getZ() - damagerPos.getZ() + z;
                double d3 = (Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
                double d4 = d3 % 360 + (d3 < 0 ? 360 : 0);
                minYaw = Math.min((float) d4, minYaw);
                maxYaw = Math.max((float) d4, maxYaw);
            }
        }
        return new float[] {minYaw, maxYaw};
    }
    public static float[] getPitchMinAndMax(Entity entity, Player damager) {
        float minPitch = 360, maxPitch = 0;
        Location damagerPos = damager.getEyeLocation();
        Location entityPos = entity.getLocation();
        float[] offset = {-0.5f, 0.5f};
        float[] yoffset = {-0.125f, 1.95f};
        for(float x : offset) {
            for(float y : yoffset) {
                double d0 = entityPos.getX() - damagerPos.getX() + x;
                double d1 = entityPos.getY() - damagerPos.getY() + y;
                double d2 = entityPos.getZ() - damagerPos.getZ();
                double d3 = Math.sqrt(d0 * d0 + d2 * d2);
                double d4 = (float) (-(Math.atan2(d1, d3) * 180.0D / Math.PI));
                minPitch = Math.min((float) d4, minPitch);
                maxPitch = Math.max((float) d4, maxPitch);
            }
        }
        for(float z : offset) {
            for(float y : yoffset) {
                double d0 = entityPos.getX() - damagerPos.getX();
                double d1 = entityPos.getY() - damagerPos.getY() + y;
                double d2 = entityPos.getZ() - damagerPos.getZ() + z;
                double d3 = Math.sqrt(d0 * d0 + d2 * d2);
                double d4 = (float) (-(Math.atan2(d1, d3) * 180.0D / Math.PI));
                minPitch = Math.min((float) d4, minPitch);
                maxPitch = Math.max((float) d4, maxPitch);
            }
        }
        return new float[] {minPitch, maxPitch};
    }
    public static int getAmplifier(Player player, PotionEffectType effectType) {
        if(!player.hasPotionEffect(effectType)) {
            return 0;
        }
        for(PotionEffect effect : player.getActivePotionEffects()) {
            if(effect.getType().getName().equals(effectType.getName())) {
                return effect.getAmplifier() + 1;
            }
        }
        return 0;
    }

    public static int getEnchantmentLevel(Player player, Enchantment enchantment) {
        if( !player.getItemInHand().getEnchantments().containsKey(enchantment) ) {
            return 0;
        }
        return player.getItemInHand().getEnchantmentLevel(enchantment);
    }

    public static int getBootsEnchantmentLevel(Player player, Enchantment enchantment) {
        if( player.getInventory().getBoots() != null ) {
            if (!player.getInventory().getBoots().getEnchantments().containsKey(enchantment)) {
                return 0;
            }
        }
        return player.getItemInHand().getEnchantmentLevel(enchantment);
    }

    public static HashMap<Material, Integer> weaponDamageList = new HashMap<>();

    public static int getWeaponDamage(Material material) {
        if( !weaponDamageList.containsKey(material) ) return 1;
        return weaponDamageList.get(material) + 1;
    }

    public static void weaponDamageListSetup() {
        weaponDamageList.put(Material.WOOD_SWORD, 4);
        weaponDamageList.put(Material.STONE_SWORD, 5);
        weaponDamageList.put(Material.IRON_SWORD, 6);
        weaponDamageList.put(Material.GOLD_SWORD, 4);
        weaponDamageList.put(Material.DIAMOND_SWORD, 7);

        weaponDamageList.put(Material.WOOD_AXE, 3);
        weaponDamageList.put(Material.STONE_AXE, 4);
        weaponDamageList.put(Material.IRON_AXE, 5);
        weaponDamageList.put(Material.GOLD_AXE, 3);
        weaponDamageList.put(Material.DIAMOND_AXE, 6);

        weaponDamageList.put(Material.WOOD_PICKAXE, 2);
        weaponDamageList.put(Material.STONE_PICKAXE, 3);
        weaponDamageList.put(Material.IRON_PICKAXE, 4);
        weaponDamageList.put(Material.GOLD_PICKAXE, 2);
        weaponDamageList.put(Material.DIAMOND_PICKAXE, 5);

        weaponDamageList.put(Material.WOOD_SPADE, 1);
        weaponDamageList.put(Material.STONE_SPADE, 2);
        weaponDamageList.put(Material.IRON_SPADE, 3);
        weaponDamageList.put(Material.GOLD_SPADE, 1);
        weaponDamageList.put(Material.DIAMOND_SPADE, 4);
    }

    public static float[] movementInput(double x, double z, double xz, float yaw, float d) {
        double x1 = -Math.sin(Math.toRadians(yaw + 45)) * xz;
        double z1 = Math.cos(Math.toRadians(yaw + 45)) * xz;
        double x2 = -Math.sin(Math.toRadians(yaw - 45)) * xz;
        double z2 = Math.cos(Math.toRadians(yaw - 45)) * xz;
        if(Math.abs(x-x1) < d &&Math.abs(z-z1) < d) {
            return new float[] {0.98f, -0.98f};
        }else if(Math.abs(x-x2) < d &&Math.abs(z-z2) < d) {
            return new float[] {0.98f, 0.98f};
        }
        double x3 = -Math.sin(Math.toRadians(yaw + 225)) * xz;
        double z3 = Math.cos(Math.toRadians(yaw + 225)) * xz;
        double x4 = -Math.sin(Math.toRadians(yaw + 135)) * xz;
        double z4 = Math.cos(Math.toRadians(yaw + 135)) * xz;
        if(Math.abs(x-x3) < d &&Math.abs(z-z3) < d) {
            return new float[] {-0.98f, 0.98f};
        }else if(Math.abs(x-x4) < d &&Math.abs(z-z4) < d) {
            return new float[] {-0.98f, -0.98f};
        }
        double x5 = -Math.sin(Math.toRadians(yaw)) * xz;
        double z5 = Math.cos(Math.toRadians(yaw)) * xz;
        double x6 = -Math.sin(Math.toRadians(yaw + 180)) * xz;
        double z6 = Math.cos(Math.toRadians(yaw + 180)) * xz;
        if(Math.abs(x-x5) < d &&Math.abs(z-z5) < d) {
            return new float[] {0.98f, 0.0f};
        }else if(Math.abs(x-x6) < d &&Math.abs(z-z6) < d) {
            return new float[] {-0.98f, 0.0f};
        }
        double x7 = -Math.sin(Math.toRadians(yaw + 90)) * xz;
        double z7 = Math.cos(Math.toRadians(yaw + 90)) * xz;
        double x8 = -Math.sin(Math.toRadians(yaw - 90)) * xz;
        double z8 = Math.cos(Math.toRadians(yaw - 90)) * xz;
        if(Math.abs(x-x7) < d && Math.abs(z-z7) < d) {
            return new float[] {0.0f, -0.98f};
        }else if(Math.abs(x-x8) < d && Math.abs(z-z8) < d) {
            return new float[] {0.0f, 0.98f};
        }
        return new float[] {0.0f, 0.0f};
    }
}
