/*
 * Splasher.java  2.0  January 31, 2004
 *
 * Copyright (c) 2003-2005 Werner Randelshofer
 * Staldenmattweg 2, Immensee, CH-6405, Switzerland.
 * This software is in the public domain.
 */

package funsoftware.inter;

/**
 * As part of fUNSoftWare, this code is adapted from the above copyrighted named.
 * @author  Thomas Legowo
 */
public class Splasher {
    /**
     * Shows the splash screen, launches the application and then disposes
     * the splash screen.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SplashWindow.splash(Splasher.class.getResource("/icons/logos/splash_logo.png"));
        SplashWindow.invokeMain("funsoftware.inter.window", args);
        SplashWindow.disposeSplash();
    }
    
}


