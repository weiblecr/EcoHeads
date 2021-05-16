package plugins.cardcraft.inc.ecoheads.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Logger {

    private static String name = "&cECOHEADS";

    public static void info(String message) {
        log(LogLevel.INFO, message);
    }

    public static void warning(String message) {
        log(LogLevel.WARNING, message);
    }

    public static void error(String message) {
        log(LogLevel.ERROR, message);
    }

    public static void error(Throwable ex) {
        log(ex);
    }

    public static void debug(String message) {
        log(LogLevel.DEBUG, message);
    }

    public static void log(LogLevel level, String message) {
    	/*
        if (level == LogLevel.DEBUG && !HeadDB.getInstance().getCfg().getBoolean("debug")) {
            return;
        }
        */
        Bukkit.getConsoleSender().sendMessage(Utils.colorize("&7[&9&l" + name + "&7] " + level.getColor() + "[" + level.name() + "]: " + message));
    }

    public static void log(Throwable ex) {
        Bukkit.getConsoleSender().sendMessage(Utils.colorize("&7[" + name + "&7] " + "&4&l[EXCEPTION]: " + ex.getMessage()));
        Bukkit.getConsoleSender().sendMessage(Utils.colorize("&4&l[StackTrace]: " + getStackTrace(ex)));
    }

    public static void setName(String logName) {
        name = logName;
    }

    public static String getName() {
        return name;
    }

    public static String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }

    public enum LogLevel {

        INFO,
        WARNING,
        ERROR,
        DEBUG;

        private ChatColor getColor() {
            switch (this) {
                case INFO:
                    return ChatColor.GREEN;
                case WARNING:
                    return ChatColor.YELLOW;
                case ERROR:
                    return ChatColor.DARK_RED;
                case DEBUG:
                    return ChatColor.DARK_AQUA;
                default:
                    return ChatColor.WHITE;
            }
        }

    }

}