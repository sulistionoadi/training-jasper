/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.virtualaccount.helper;

/**
 *
 * @author adi
 */
public class EpsonCommand {
    public static final char ESC = (char) 27;
    public static final char[] RESET = {ESC, '@'};
    
    public static final char[] PAGE_LENGTH_27 = {ESC, 'C', 22};
    public static final char[] VERTICAL_PRINT_POSITION = {ESC, 'J', '1'};
    
    public static final char[] EJECT = {(char) 12};

    // print mode
    public static final char[] MODE_DRAFT = {ESC, 'x', 0};

    // font setting
    public static final char[] FONT_SANS_SERIF = {ESC, 'k', 1};
    public static final char[] SIZE_10_CPI = {ESC, 'P'};
    public static final char[] SIZE_12_CPI = {ESC, 'M'};
    public static final char[] SIZE_15_CPI = {ESC, 'g'};
    public static final char[] CONDENSED_ON = {(char) 15};
    public static final char[] CONDENSED_OFF = {(char) 18};
    public static final char[] BOLD_ON = {ESC, 'E'};
    public static final char[] BOLD_OFF = {ESC, 'F'};
    public static final char[] DOUBLE_WIDTH_ON = {ESC, 87, 49};
    public static final char[] DOUBLE_WIDTH_OFF = {ESC, 87, 48};
    public static final char[] DOUBLE_HEIGHT_ON = {ESC, 119, 49};
    public static final char[] DOUBLE_HEIGHT_OFF = {ESC, 119, 48};

    // line spacing
    public static final char[] SPACING_9_72 = {ESC, '0'};
    public static final char[] SPACING_7_72 = {ESC, '1'};
    public static final char[] SPACING_12_72 = {ESC, '2'};

    // metric
    public static final float CM_PER_INCH = 2.54f;
    public static final int MAX_UNIT_9PIN = 216;
    public static final int MAX_UNITS = 127;
    
    public static int MAX_ADVANCE_9PIN = 216; //for 24/48 pin esc/p2 printers this should be 180
    public static int MAX_ADVANCE_24PIN = 180;
}
