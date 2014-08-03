package com.disney.customcraft.handlers;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.disney.customcraft.ModInfo;

import cpw.mods.fml.common.FMLLog;

public class LogHandler {
	
	private static Logger logger = LogManager.getLogger(ModInfo.NAME);
	
	public static void init(Logger log) {
		logger = log;
	}
	
	public static void log(String message) {
		log(Level.INFO, message);
	}
	
	public static void log(Level logLevel, String message) {
		logger.log(logLevel, "CustomCraft : " + message);
	}

}
