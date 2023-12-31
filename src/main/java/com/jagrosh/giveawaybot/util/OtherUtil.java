/*
 * Copyright 2018 John Grosh (john.a.grosh@gmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jagrosh.giveawaybot.util;

import java.awt.Color;

/**
 *
 * @author John Grosh (john.a.grosh@gmail.com)
 */
public class OtherUtil
{
    public static Color parseColor(String color)
    {
        try 
        {
            switch(color.replace(" ", "_").toLowerCase()) 
            {
                case "red":         return Color.RED;
                case "orange":      return Color.ORANGE;
                case "yellow":      return Color.YELLOW;
                case "green":       return Color.GREEN;
                case "cyan":        return Color.CYAN;
                case "blue":        return Color.BLUE;
                case "magenta":     return Color.MAGENTA;
                case "pink":        return Color.PINK;
                case "black":       return Color.decode("#000001");
                case "dark_gray": 
                case "dark_grey":   return Color.DARK_GRAY;
                case "gray":
                case "grey":        return Color.GRAY;
                case "light_gray":
                case "light_grey":  return Color.LIGHT_GRAY;
                case "white":       return Color.WHITE;
                //discord 
                case "blurple":     return Color.decode("#5865F2");
                case "old_blurple": return Color.decode("#7289DA");
                case "greyple":     return Color.decode("#99AAB5");
                case "darktheme":   return Color.decode("#2C2F33");
                default:            return Color.decode(color.startsWith("#") ? color : "#" + color);
            }
        }
        catch(NumberFormatException e) 
        {
            return null;
        }
    }
    
    @SuppressWarnings("fallthrough")
    public static int parseShortTime(String timestr)
    {
        timestr = timestr.toLowerCase();
        if(!timestr.matches("\\d{1,8}[smhd]?"))
            return -1;
        int multiplier = 1;
        switch(timestr.charAt(timestr.length()-1))
        {
            case 'd':
                multiplier *= 24;
            case 'h':
                multiplier *= 60;
            case 'm':
                multiplier *= 60;
            case 's':
                timestr = timestr.substring(0, timestr.length()-1);
            default:
        }
        return multiplier * Integer.parseInt(timestr);
    }
    
    public static int parseTime(String timestr)
    {
        timestr = timestr.replaceAll("(?i)(\\s|,|and)","")
                .replaceAll("(?is)(-?\\d+|[a-z]+)", "$1 ")
                .trim();
        String[] vals = timestr.split("\\s+");
        int timeinseconds = 0;
        try
        {
            for(int j=0; j<vals.length; j+=2)
            {
                int num = Integer.parseInt(vals[j]);
                if(vals[j+1].toLowerCase().startsWith("m"))
                    num*=60;
                else if(vals[j+1].toLowerCase().startsWith("h"))
                    num*=60*60;
                else if(vals[j+1].toLowerCase().startsWith("d"))
                    num*=60*60*24;
                timeinseconds+=num;
            }
        }
        catch(Exception ex)
        {
            return 0;
        }
        return timeinseconds;
    }
    
    public static int parseWinners(String winstr)
    {
        if(!winstr.toLowerCase().matches("\\d{1,3}w"))
            return -1;
        return Integer.parseInt(winstr.substring(0, winstr.length()-1));
    }
    
    public static boolean strEquals(String a, String b)
    {
        return a == null ? b == null : a.equals(b);
    }
}
