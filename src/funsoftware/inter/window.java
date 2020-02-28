/*
 * window.java
 *
 * Created on 9 June 2005, 01:57
 */

package funsoftware.inter;

import com.sun.glass.ui.Cursor;
import java.io.*;
import java.net.*;

import funsoftware.ic.*;
import funsoftware.ic.obj.*;
import funsoftware.ic.bran.*;
import funsoftware.ic.loo.*;
import funsoftware.ic.func.*;
import funsoftware.ic.others.*;
import funsoftware.ic.func.*;
import funsoftware.ic.tsk.*;
import funsoftware.ic.var.*;
import funsoftware.ic.ev.*;
import funsoftware.st.*;
import funsoftware.wr.*;
import funsoftware.gri.*;
import funsoftware.pallette.*;
import funsoftware.comp.*;
import funsoftware.consts.*;
import funsoftware.functs.*;
import funsoftware.tasks.*;
import funsoftware.struct.*;
import funsoftware.var.*;
import funsoftware.events.*;
import funsoftware.properties.*;
import java.awt.Color;
import javax.swing.ScrollPaneConstants;

/**
 * This is the class that cater for the user interface (i.e. all graphics related)
 * fUNSoftWare starts from this class. This class contains the main function. 
 * All of the original buttons, icons, palette, panels and menu are created here as well as their respective event handlers
 * @author Thomas Legowo
 * 
 */
public class window extends javax.swing.JFrame {
    
    /** Creates new form window */
    public window() {

        initComponents();
        
        // initialise the icons to be cloned
        initIconClones();
        
        // initialise the lookup table for Translator
        Translator.init_lookup_table();
        
        // initialise the palette sections
        initEditingIconsPalette();
        initIconHelpPalette();
        initObjectIconsPalette();
        initBranchIconsPalette();
        initLoopIconsPalette();
        initFunctionsReusePalette();
        

//        // initialise the manually added variables
//        initManVariables();

        initOtherClasses();
        // initialise the programming window
        initScrollable();
        //reset all variables
        resetVars();
        // initialise the variable window
        initVariablesPallette();        
        // initialize the event monitor window
        initEventMonitor();
        // initialise the task window
        initTask();              
        // initialise the function window
        initFunction();
        // initialise the event window
        initEvent();
        
        // bind the hot keys
        bindHotKeys();

        // reset
        reset();
        
        // initialise the two main icons, start and ending icon and the first piece of wire
        initIconsWires();  

        // resize the programming window appropriately
//        jProgrammingWindow.resizeProgWindow(jScrollPane1); 
        UndoRedo.update_state();
        
        // Set the target device
        updateTargetDevice();
    }
    
    // method to bind the frame with the hot keys
    private void bindHotKeys()
    {
        // new file
        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
        java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        // open file
        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
        java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        // save file
        jMenuItem21.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
        java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        // exit
        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
        java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        // undo
        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
        java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        // redo
        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
        java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        // cancel last operation
        jMenuItem14.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
        java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        // set or hide grid
        jMenuItem13.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
        java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.SHIFT_MASK));
        // edit grid
        jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
        java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        // create function
        jMenuItem11.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
        java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        // just to compile the file
        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
                java.awt.event.KeyEvent.VK_F5, 0));
        // compile and download
        jMenuItem12.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
                java.awt.event.KeyEvent.VK_F6, 0));
        // compile and run
        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
                java.awt.event.KeyEvent.VK_F7, 0));
        // create a task
        jMenuItem16.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
        java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        // declare a variable
        jMenuItem15.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
        java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        // to create an arithmetic operation
        jMenuItem17.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
        java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        // to create an event
        jMenuItem18.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
        java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        // to create an event monitor
        jMenuItem20.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
        java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
    }
    
    // for the prototype design pattern
    private void initIconClones()
    {
        _motor = new motorIcon();
        _stopmotor = new stopMotorIcon();
        _beep = new beepIcon();
        _lamp = new lampIcon();
        _dirmotor = new directionalMotorIcon();
        _timer = new timerIcon();
        _clearsensor = new clearSensorIcon();
        _cleartimer = new clearTimerIcon();
        _playanynote = new playAnyNoteIcon();
        _float = new floatMotorIcon();
        _specificnote = new playSpecificNote();
        _returnicon = new returnIcon();  
        _waittouch = new waitTouchIcon();   
        _waitlight = new waitLightIcon();  
        _waitcelcius = new waitCelciusIcon();
        _waitrotation = new waitRotationalIcon();
        _waittimer = new waitTimerIcon();
        
        // the branches

        _Btouch = new BtouchSensorIcon();
        _Blight = new BlightSensorIcon();
        _Bcelcius = new BcelciusSensorIcon();
        _Brotation = new BrotationSensorIcon();
        _Btimer = new BtimerIcon();
        _Brandom = new BrandomIcon();
        _Barithmetic = new BarithmeticIcon();        

        // the loops

        _Lrep = new repeatIcon();
        _Llight = new lightSensorLoopIcon();
        _Linfinite = new infiniteLoopIcon();
        _Ltimer = new timerLoopIcon();
        _Lrotation = new rotationSensorLoopIcon();
        _Lcelcius = new celciusSensorLoopIcon();
        _Ltouch = new touchSensorLoopIcon();
        _Larithmetic = new arithmeticLoopIcon();        

        // the auxiliary

        _Jicon = new joinIcon();
        _Jloop = new joinLoopIcon();
        _JEBIcon = new joinEndBranchIcon();

        // the task icons

        _Start = new startTaskIcon();
        _End = new endTaskIcon();
        _BeginTask = new beginTaskIcon();
        _StopTask = new stopTaskIcon();
        
        // the function icon and the function skeleton
        
        _Funcby = new functionIcon();
        _Funct = new function();
        _Task = new task();
        _Var = new variable();
        _Event = new Event();
        _mEvent = new monitorEvent();
        _AOIcon = new ArithOpIcon();
        
        // the event icons
        _SEvent = new startEvent();
    }
    
    private void reset()
    {
        jPanelTask.reset();
        jPanelFunction.reset();
        jPanelEvent.reset();
        jPanelEventMonitor.reset();
        jPanelVar.reset();
        
        TabbedWindowManager.Instance(CenterCardPanel, jPanelVar, jPanelEventMonitor).reset();
    }
    
    // this method reset all variables
    // 1. icon_list
    // 2. wire_list
    // 3. jProgrammingWindow's components
    // 4. auxiliary list
    // 5. variable list
    private void resetVars()
    {
        do_state = states.Instance();
        
        // reset the programming window
        if (jProgrammingWindow != null)
        {
            jProgrammingWindow.removeAll();
            jProgrammingWindow.resetWires();
            jProgrammingWindow.resetIcons();
        }
        
        // reset the function window
        fcombo.reset();
        
        // reset the button group
        radiobuttons.Instance().reset();
        
        // reset the icons        
        icons_list.Instance().resetIconList();
        
        // reset the wiring        
        wires.Instance().resetWireList();        
        
        // reset function list
        aux_list.Instance().resetAuxList();
        
        // reset the variable list
        var_list.Instance().resetVarList();
        
        // reset the event list
        event_number_tracker.Instance().resetEvList();
    }

    private void initEditingIconsPalette()
    {
        javax.swing.JPanel jPanelEditing = new javax.swing.JPanel();
        jPanelEditing.setLayout(new java.awt.GridBagLayout());
        jPanelEditing.setBackground(new java.awt.Color(247, 247, 247));
        jPanelEditing.setBorder(null);
        
        // insert the object window inside the scroller
        javax.swing.JViewport vw = new javax.swing.JViewport();
        vw.setView(jPanelEditing);
        EditingPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        EditingPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        EditingPane.setViewport(vw);

        java.awt.GridBagConstraints gridBagConstraints;

        // Grid-Mode button
        jButtonGridMode = new javax.swing.JButton();  
        jButtonGridMode.setForeground(new java.awt.Color(212, 208, 200));
        jButtonGridMode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/other/gridmode.gif")));
        jButtonGridMode.setToolTipText("To display or hide grids");
        jButtonGridMode.setBorder(null);
        jButtonGridMode.setMaximumSize(new java.awt.Dimension(32, 32));
        jButtonGridMode.setMinimumSize(new java.awt.Dimension(32, 32));
        jButtonGridMode.setPreferredSize(new java.awt.Dimension(32, 32));
        jButtonGridMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanelEditing.add(jButtonGridMode, gridBagConstraints);

        // Undo button
        jButtonUndo = new javax.swing.JButton();
        jButtonUndo.setForeground(new java.awt.Color(180, 180, 180));
        jButtonUndo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/other/undo.gif")));
        jButtonUndo.setToolTipText("To undo previous action");
        jButtonUndo.setBorder(null);
        jButtonUndo.setMaximumSize(new java.awt.Dimension(32, 32));
        jButtonUndo.setMinimumSize(new java.awt.Dimension(32, 32));
        jButtonUndo.setPreferredSize(new java.awt.Dimension(32, 32));
        jButtonUndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanelEditing.add(jButtonUndo, gridBagConstraints);

        // Redo button
        jButtonRedo = new javax.swing.JButton();        
        jButtonRedo.setForeground(new java.awt.Color(180, 180, 180));
        jButtonRedo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/other/redo.gif")));
        jButtonRedo.setToolTipText("To redo previous action");
        jButtonRedo.setBorder(null);
        jButtonRedo.setMaximumSize(new java.awt.Dimension(32, 32));
        jButtonRedo.setMinimumSize(new java.awt.Dimension(32, 32));
        jButtonRedo.setPreferredSize(new java.awt.Dimension(32, 32));
        jButtonRedo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanelEditing.add(jButtonRedo, gridBagConstraints);

        // Cancel button
        jButtonCancel = new javax.swing.JButton();
        jButtonCancel.setText("Cancel");
        jButtonCancel.setToolTipText("To cancel last operation");
        jButtonCancel.setBorder(null);
        jButtonCancel.setMaximumSize(new java.awt.Dimension(70, 32));
        jButtonCancel.setMinimumSize(new java.awt.Dimension(70, 32));
        jButtonCancel.setPreferredSize(new java.awt.Dimension(70, 32));
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanelEditing.add(jButtonCancel, gridBagConstraints);
    }
    
    private void initIconHelpPalette()
    {
        javax.swing.JPanel jPanelHelp = new javax.swing.JPanel();
        jPanelHelp.setLayout(new java.awt.GridBagLayout());
        jPanelHelp.setBackground(new java.awt.Color(247, 247, 247));
        jPanelHelp.setBorder(null);
        jPanelHelp.setRequestFocusEnabled(false);
        
        jLabelHelp = new javax.swing.JLabel();
        jLabelHelp.setText("Select an icon");
        jLabelHelp.setForeground(new java.awt.Color(51, 108, 255));
        jLabelHelp.setFont(new java.awt.Font("Verdana", 0, 12));
        jPanelHelp.add(jLabelHelp, new java.awt.GridBagConstraints());

        // insert the object window inside the scroller
        javax.swing.JViewport vw = new javax.swing.JViewport();
        vw.setView(jPanelHelp);
        IconHelpPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        IconHelpPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        IconHelpPane.setViewport(vw);
    }
    
    // this method initialises all the object icons pallettes
    private void initObjectIconsPalette()
    {
        // set up the object window where the icons will reside
        javax.swing.JPanel objwin = new javax.swing.JPanel();
        objwin.setLayout(new java.awt.GridBagLayout());
        objwin.setBackground(new java.awt.Color(247, 247, 247));
        objwin.setBorder(null);
                
        // insert the object window inside the scroller
        javax.swing.JViewport vw = new javax.swing.JViewport();
        vw.setView(objwin);
        SinglesPane.setViewport(vw);
        
        // --- adding the buttons
                
        createIconicButton(40, 40, "/icons/motors/movemotor/icon_wheel2.gif", 0, 0, objwin, "Motor Icon", _motor);
        
        createIconicButton(40, 40, "/icons/motors/directional/dir.gif", 1, 0, objwin, "Directional Motor Icon", _dirmotor);

        createIconicButton(40, 40, "/icons/motors/stopmotor/icon_wheel_stop.gif", 2, 0, objwin, "Stop Icon", _stopmotor);
        
        createIconicButton(40, 40, "/icons/lamps/lamp.gif", 3, 0, objwin, "Lamp Icon", _lamp);        
        
        createIconicButton(40, 40, "/icons/motors/floatmotor/floatmotor.gif", 0, 1, objwin, "Float Motor Icon", _float);
        
        createIconicButton(40, 40, "/icons/beeps/beeps.gif", 1, 1, objwin, "Beep Icon", _beep);
        
        createIconicButton(40, 40, "/icons/notes/free/freenote.gif", 2, 1, objwin, "Play Any Note Icon", _playanynote);
        
        createIconicButton(40, 40, "/icons/notes/specific/specific.gif", 3, 1, objwin, "Play A Note Icon", _specificnote);  
        
        createIconicButton(40, 40, "/icons/timers/wait/icon_time.gif", 0, 2, objwin, "Wait Icon", _timer);    

        createIconicButton(40, 40, "/icons/wait/touch/wait_touch.gif", 1, 2, objwin, "Wait Touch Sensor Icon", _waittouch);    
       
        createIconicButton(40, 40, "/icons/wait/light/wait_light.gif", 2, 2, objwin, "Wait Light Sensor Icon", _waitlight);    
        
        createIconicButton(40, 40, "/icons/wait/celcius/wait_celcius.gif", 3, 2, objwin, "Wait Celcius Sensor Icon", _waitcelcius);    
        
        createIconicButton(40, 40, "/icons/wait/rotate/wait_rotate.gif", 0, 3, objwin, "Wait Rotational Sensor Icon", _waitrotation);    
        
        createIconicButton(40, 40, "/icons/wait/timer/wait_timer.gif", 1, 3, objwin, "Wait Timer Sensor Icon", _waittimer);    
        
        createIconicButton(40, 40, "/icons/structure/return/return.gif", 2, 3, objwin, "Return Icon", _returnicon);       
        
        createIconicButton(40, 40, "/icons/sensors/clearsensor.gif", 3, 3, objwin, "Clear Sensors Icon", _clearsensor);
        
        createIconicButton(40, 40, "/icons/timers/cleart/cleartimer.gif", 0, 4, objwin, "Clear Timer Icon", _cleartimer);
        
        createIconicButton(40, 40, "/icons/task/start/startTask.gif", 1, 4, objwin, "Start Task Icon", _BeginTask);  

        createIconicButton(40, 40, "/icons/task/stop/stopTask.gif", 2, 4, objwin, "Stop Task Icon", _StopTask);

        createIconicButton(40, 40, "/icons/events/start/start.gif", 3, 4, objwin, "Start Event Monitor Icon", _SEvent);       
    }
    
    // this method initialises the branch icons window
    private void initBranchIconsPalette()
    {
        // set up the branch window where the icons will reside
        javax.swing.JPanel brawin = new javax.swing.JPanel();
        brawin.setLayout(new java.awt.GridBagLayout());
        brawin.setBackground(new java.awt.Color(247, 247, 247));
        brawin.setBorder(null);
        
        // insert the branch window inside the scroller
        javax.swing.JViewport vw = new javax.swing.JViewport();
        vw.setView(brawin);
        BranchesPane.setViewport(vw);
                
        createIconicButton(40, 40, "/icons/branches/touch/icon_if.gif", 0, 0, brawin, "Branch Touch Sensor Icon", _Btouch);  
        
        createIconicButton(40, 40, "/icons/branches/light/light.gif", 1, 0, brawin, "Branch Light Sensor Icon", _Blight);
        
        createIconicButton(40, 40, "/icons/branches/celcius/celcius.gif", 2, 0, brawin, "Branch Temperature Sensor Icon", _Bcelcius);  
        
        createIconicButton(40, 40, "/icons/branches/rotation/rotation.gif", 3, 0, brawin, "Branch Rotational Sensor Icon", _Brotation);
        
        createIconicButton(40, 40, "/icons/branches/timer/timer.gif", 0, 1, brawin, "Branch Timer Icon", _Btimer); 

        createIconicButton(40, 40, "/icons/branches/random/random.gif", 1, 1, brawin, "Branch Random Icon", _Brandom);   
        
        createIconicButton(40, 40, "/icons/branches/arithmetic/arithmetic.gif", 2, 1, brawin, "Branch Arithmetic Icon", _Barithmetic);   
    }
    
    // this method initialises the loop icons window
    private void initLoopIconsPalette()
    {
         // set up the loop window where the icons will reside
        javax.swing.JPanel loowin = new javax.swing.JPanel();
        loowin.setLayout(new java.awt.GridBagLayout());
        loowin.setBackground(new java.awt.Color(247, 247, 247));
        loowin.setBorder(null);            

        // insert the loop window inside the scroller
        javax.swing.JViewport vw = new javax.swing.JViewport();
        vw.setView(loowin);
        LoopsPane.setViewport(vw);
        
        createIconicButton(40, 40, "/icons/loops/repeat/icon_rep.gif", 0, 0, loowin, "Repeat Icon", _Lrep); 
        
        createIconicButton(40, 40, "/icons/loops/infiniteloop/infinite_rep.gif", 1, 0, loowin, "Infinite Repeat Icon", _Linfinite); 

        createIconicButton(40, 40, "/icons/loops/lightrep/light_rep.gif", 2, 0, loowin, "Light Sensor Repeat Icon", _Llight);   
        
        createIconicButton(40, 40, "/icons/loops/timer/timer.gif", 3, 0, loowin, "Timer Repeat Icon", _Ltimer);         
        
        createIconicButton(40, 40, "/icons/loops/celcius/celcius.gif", 0, 1, loowin, "Temperature Sensor Repeat Icon", _Lcelcius); 
        
        createIconicButton(40, 40, "/icons/loops/rotation/rotational.gif", 1, 1, loowin, "Rotational Sensor Repeat Icon", _Lrotation); 

        createIconicButton(40, 40, "/icons/loops/touch/touch.gif", 2, 1, loowin, "Touch Sensor Repeat Icon", _Ltouch);    
        
        createIconicButton(40, 40, "/icons/loops/arithmetic/arithmetic.gif", 3, 1, loowin, "Arithmetic Repeat Icon", _Larithmetic);    
    }
    
    private void initFunctionsReusePalette()
    {
        javax.swing.JPanel jPanelFuncReuse = new javax.swing.JPanel();
        jPanelFuncReuse.setLayout(new java.awt.GridBagLayout());
        jPanelFuncReuse.setBackground(new java.awt.Color(247, 247, 247));
        jPanelFuncReuse.setBorder(null);            

        // insert the loop window inside the scroller
        javax.swing.JViewport vw = new javax.swing.JViewport();
        vw.setView(jPanelFuncReuse);
        FunctionsReusePane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        FunctionsReusePane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        FunctionsReusePane.setViewport(vw);

        fcombo = FunctionCombo.Instance();   // the function combo box
        fcombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FuncComboBoxActionPerformed(evt);   // to duplicate a function
            }
        });

        jPanelFuncReuse.add(fcombo, new java.awt.GridBagConstraints());
    }
    
    
    // this method initialises the programming window
    private void initScrollable()
    {       
//        jProgrammingWindow = new ProgWindow(DirectionsLabel);
//        jProgrammingWindow.setBackground(new java.awt.Color(255, 255, 255));                
//        
//        jScrollPane1 = new javax.swing.JScrollPane();
//        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
//        jScrollPane1.setBorder(null);
//        
//        javax.swing.JViewport viewport = new javax.swing.JViewport();
//        viewport.setView(jProgrammingWindow);
//        jScrollPane1.setViewport(viewport);
//        
//        javax.swing.JPanel jPanel18 = new javax.swing.JPanel();
//        jPanel18.setLayout(new java.awt.GridBagLayout());
//        jPanel18.setLayout(new javax.swing.BoxLayout(jPanel18, javax.swing.BoxLayout.PAGE_AXIS));
//        jPanel18.add(jScrollPane1);
//        
//        CenterCardPanel.add(jPanel18, "0");  // the initial main programming window is coded 1

        TitlePanelLabel panelCardZero = new TitlePanelLabel();
        panelCardZero.setText("Welcome to " + AppProperties.appName + "!");
        CenterCardPanel.add(panelCardZero, "0");

        // initialise the list of wires and icons first 
        wire_list = wires.Instance();
        icon_list = icons_list.Instance();
    }
    
    // the function to initialise the bottom left pallette
    // the pallette will display both global and local variables
    private void initVariablesPallette()
    {
        jPanelVar = new VarWindow(this);
        javax.swing.JViewport vp = new javax.swing.JViewport();
        vp.setView(jPanelVar);
        VariablesPane.setViewport(vp);
    }
    
    // to initialise the task window
    private void initTask()
    {        
//        jPanelTask = new TaskWindow(this, jPanel13, jProgrammingWindow, jScrollPane1, jPanel18, DirectionsLabel, jPanelVar, jPanelEventMonitor);
        jPanelTask = new TaskWindow(this, CenterCardPanel, DirectionsLabel, jPanelVar, jPanelEventMonitor);
        javax.swing.JViewport vp = new javax.swing.JViewport();
        vp.setView(jPanelTask);
        TasksPane.setViewport(vp);
    }

    // to initialise the function window
    private void initFunction()
    {
//        jPanelFunction = new FunctionWindow(this, jPanel13, jProgrammingWindow, jScrollPane1, jPanel18, DirectionsLabel, jPanelVar, jPanelEventMonitor);        
        jPanelFunction = new FunctionWindow(this, CenterCardPanel, DirectionsLabel, jPanelVar, jPanelEventMonitor);        
        javax.swing.JViewport vp = new javax.swing.JViewport();
        vp.setView(jPanelFunction);
        FunctionsPane.setViewport(vp);
    }
    
    // to initialise the event window
    private void initEvent()
    {
        jPanelEvent = new eventWindow(this);
        javax.swing.JViewport vp = new javax.swing.JViewport();
        vp.setView(jPanelEvent);
        EventsPane.setViewport(vp);
    }
    
    // to initialize the event monitor window
    private void initEventMonitor()
    {
//        jPanelEventMonitor = new monitorEventWindow(this, jPanel13, jProgrammingWindow, jScrollPane1, jPanel18, DirectionsLabel, jPanelVar);
        jPanelEventMonitor = new monitorEventWindow(this, CenterCardPanel, DirectionsLabel, jPanelVar);
        javax.swing.JViewport vp = new javax.swing.JViewport();
        vp.setView(jPanelEventMonitor);
        EventMonitorsPane.setViewport(vp);
    }    
    
    
    // This method initialise the programming window by putting a piece of wire between the start and end icons 
    // This method will only be called under two circumstances
    //   1. When the user clicks on the "New" menu item to initialise the programming window ready to be used.
    //   2. If the user loads a program from a saved file
    private void initIconsWires()
    {                         
        task tsk = _Task.Clone();
        aux_list.Instance().addAuxiliary(tsk);
        tsk.setName("main");                
        jPanelTask.insertTask(tsk); 
        
        task tmp = (task)(aux_list.Instance().getAuxiliaries().get(1));
        setProgrammingWindow(tmp.getPanel(), tmp.getScroller(), tmp.getProgWindow());
        tmp.getTaskWindow().getTabbedWinMan().displayPanel("1");
        
        radiobuttons rb = radiobuttons.Instance();
        javax.swing.ButtonGroup group = rb.getButtonGroup();
        group.getElements().nextElement().setSelected(true);
        
        // now resize the programming window appropriately
//        jProgrammingWindow.resizeProgWindow(jScrollPane1); 
//        current_aux = aux_list.Instance().getAuxiliaries().get(1);

        repaint();
        validate();
    }
    
    
//    // This method is called upon the initialisation of the interface
//    // Used to initialise all manually added variables as well as the sensor variables
//    private void initManVariables()
//    {
//        distanceX = 50;   // the horizontal and vertical distances between grid intersections
//        distanceY = 55;
//        Constants.setGridDistanceX(50);
//        Constants.setGridDistanceY(55);
//        Constants.setMaxHorizontalProgWindow(640);
//        Constants.setMaxVerticalProgWindow(530);
//        Constants.setHorizontalWindowSize(this.getWidth());
//        Constants.setVerticalWindowSize(this.getHeight());
//        Constants.setScrollbarWidth(((Integer)javax.swing.UIManager.get("ScrollBar.width")).intValue());
//        current_aux = new auxiliary();
//        maxX = gridDistanceX * 3;
//        maxY = gridDistanceY * 2;
//        _Current_Save_Path = new String();
//        ConfigFile_Name = getUserAppPath() + AppProperties.appName + "Config.txt";
//    }
    
    // This method is called upon the initialisation of the interface
    // Used to initialise other classes
    private void initOtherClasses()
    {
        // initialise the icon of this program
        setIconImage((new javax.swing.ImageIcon(getClass().getResource("/icons/logos/logo.gif"))).getImage());    
        
        // initialise the undo redo buttons involved in the state tracking processing
        UndoRedo.setUndoButton(jButtonUndo);
        UndoRedo.setRedoButton(jButtonRedo);
        UndoRedo.setUndoMenu(jMenuItem5);
        UndoRedo.setRedoMenu(jMenuItem8);
    }
    
    
    // to restart --> for undo, redo, open and new file
    // type == 0 for new file, 1 for otherwise
    private void restartProgWindow(int type)
    {       
        // initialise the two main icons, start and ending icon and the first piece of wire
        // reset all variables
        jPanelFunction.getTabbedWinMan().panels_reset();

        do_state.reset();      // for undo operation
        
        reset();
        resetVars();
        
        if(type == 0)
        {
            initIconsWires();  
            ((javax.swing.JRadioButton)jPanelTask.getComponent(0)).setSelected(true);
            
            // resize the programming window appropriately
//            jProgrammingWindow.resizeProgWindow(jScrollPane1);  
        }

        repaint();
        validate();
    }
    
    /** 
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jFileChooser1 = new javax.swing.JFileChooser();
        buttonGroupTarget = new javax.swing.ButtonGroup();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanelMainLeft = new javax.swing.JPanel();
        TasksLabel = new funsoftware.pallette.TitlePanelLabel();
        TasksPane = new funsoftware.pallette.RoundedScrollPane();
        FunctionsLabel = new funsoftware.pallette.TitlePanelLabel();
        FunctionsPane = new funsoftware.pallette.RoundedScrollPane();
        EventsLabel = new funsoftware.pallette.TitlePanelLabel();
        EventsPane = new funsoftware.pallette.RoundedScrollPane();
        EventMonitorsLabel = new funsoftware.pallette.TitlePanelLabel();
        EventMonitorsPane = new funsoftware.pallette.RoundedScrollPane();
        VariablesLabel = new funsoftware.pallette.TitlePanelLabel();
        VariablesPane = new funsoftware.pallette.RoundedScrollPane();
        jSplitPane2 = new javax.swing.JSplitPane();
        jPanelMainCenter = new javax.swing.JPanel();
        CenterCardPanel = new funsoftware.pallette.CardPanel();
        DirectionsLabel = new funsoftware.pallette.TitlePanelLabel();
        jPanelMainRight = new javax.swing.JPanel();
        EditingLabel = new funsoftware.pallette.TitlePanelLabel();
        EditingPane = new funsoftware.pallette.RoundedScrollPane();
        IconHelpLabel = new funsoftware.pallette.TitlePanelLabel();
        IconHelpPane = new funsoftware.pallette.RoundedScrollPane();
        SinglesLabel = new funsoftware.pallette.TitlePanelLabel();
        SinglesPane = new funsoftware.pallette.RoundedScrollPane();
        BranchesLabel = new funsoftware.pallette.TitlePanelLabel();
        BranchesPane = new funsoftware.pallette.RoundedScrollPane();
        LoopsLabel = new funsoftware.pallette.TitlePanelLabel();
        LoopsPane = new funsoftware.pallette.RoundedScrollPane();
        FunctionsReuseLabel = new funsoftware.pallette.TitlePanelLabel();
        FunctionsReusePane = new funsoftware.pallette.RoundedScrollPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JSeparator();
        jMenuItem14 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JSeparator();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JSeparator();
        jMenuItem11 = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JSeparator();
        jMenuItem16 = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JSeparator();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jRadioButtonMenuItemRCX1 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItemRCX2 = new javax.swing.JRadioButtonMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JSeparator();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();

        jDialog1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jDialog1.getContentPane().setLayout(new java.awt.GridBagLayout());
        jDialog1.getContentPane().add(jFileChooser1, new java.awt.GridBagConstraints());

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("VisualNQC 1.0  - Lego Mindstorms - New File");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        setMinimumSize(new java.awt.Dimension(1018, 725));
        setName("appFrame"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1018, 725));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jSplitPane1.setDividerLocation(131);
        jSplitPane1.setLastDividerLocation(131);

        jPanelMainLeft.setBackground(new java.awt.Color(234, 234, 234));
        jPanelMainLeft.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanelMainLeft.setMinimumSize(new java.awt.Dimension(130, 680));
        jPanelMainLeft.setOpaque(false);
        jPanelMainLeft.setPreferredSize(new java.awt.Dimension(130, 680));
        jPanelMainLeft.setLayout(new javax.swing.BoxLayout(jPanelMainLeft, javax.swing.BoxLayout.PAGE_AXIS));

        TasksLabel.setText("Tasks");
        jPanelMainLeft.add(TasksLabel);
        jPanelMainLeft.add(TasksPane);

        FunctionsLabel.setText("Functions");
        jPanelMainLeft.add(FunctionsLabel);
        jPanelMainLeft.add(FunctionsPane);

        EventsLabel.setText("Events");
        jPanelMainLeft.add(EventsLabel);
        jPanelMainLeft.add(EventsPane);

        EventMonitorsLabel.setText("Event Monitors");
        jPanelMainLeft.add(EventMonitorsLabel);
        jPanelMainLeft.add(EventMonitorsPane);

        VariablesLabel.setText("Variables");
        jPanelMainLeft.add(VariablesLabel);
        jPanelMainLeft.add(VariablesPane);

        jSplitPane1.setLeftComponent(jPanelMainLeft);

        jSplitPane2.setDividerLocation(-211);
        jSplitPane2.setResizeWeight(1.0);
        jSplitPane2.setLastDividerLocation(-211);

        jPanelMainCenter.setBackground(new java.awt.Color(234, 234, 234));
        jPanelMainCenter.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanelMainCenter.setMinimumSize(new java.awt.Dimension(640, 680));
        jPanelMainCenter.setName(""); // NOI18N
        jPanelMainCenter.setPreferredSize(new java.awt.Dimension(640, 680));
        jPanelMainCenter.setLayout(new javax.swing.BoxLayout(jPanelMainCenter, javax.swing.BoxLayout.PAGE_AXIS));
        jPanelMainCenter.add(CenterCardPanel);

        DirectionsLabel.setForeground(new java.awt.Color(51, 102, 255));
        DirectionsLabel.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        DirectionsLabel.setText("Welcome");
        jPanelMainCenter.add(DirectionsLabel);

        jSplitPane2.setLeftComponent(jPanelMainCenter);

        jPanelMainRight.setBackground(new java.awt.Color(234, 234, 234));
        jPanelMainRight.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanelMainRight.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        jPanelMainRight.setMinimumSize(new java.awt.Dimension(210, 680));
        jPanelMainRight.setOpaque(false);
        jPanelMainRight.setPreferredSize(new java.awt.Dimension(210, 680));
        jPanelMainRight.setLayout(new javax.swing.BoxLayout(jPanelMainRight, javax.swing.BoxLayout.PAGE_AXIS));

        EditingLabel.setText("Editing");
        jPanelMainRight.add(EditingLabel);

        EditingPane.setMinimumSize(new java.awt.Dimension(210, 46));
        EditingPane.setPreferredSize(new java.awt.Dimension(210, 46));
        jPanelMainRight.add(EditingPane);

        IconHelpLabel.setText("Icon Help");
        jPanelMainRight.add(IconHelpLabel);

        IconHelpPane.setMinimumSize(new java.awt.Dimension(210, 30));
        IconHelpPane.setPreferredSize(new java.awt.Dimension(210, 30));
        jPanelMainRight.add(IconHelpPane);

        SinglesLabel.setText("Singles");
        jPanelMainRight.add(SinglesLabel);

        SinglesPane.setMinimumSize(new java.awt.Dimension(210, 110));
        SinglesPane.setPreferredSize(new java.awt.Dimension(210, 250));
        jPanelMainRight.add(SinglesPane);

        BranchesLabel.setText("Branches");
        jPanelMainRight.add(BranchesLabel);

        BranchesPane.setMinimumSize(new java.awt.Dimension(210, 100));
        BranchesPane.setPreferredSize(new java.awt.Dimension(210, 100));
        jPanelMainRight.add(BranchesPane);

        LoopsLabel.setText("Loops");
        jPanelMainRight.add(LoopsLabel);

        LoopsPane.setMinimumSize(new java.awt.Dimension(210, 100));
        LoopsPane.setPreferredSize(new java.awt.Dimension(210, 100));
        jPanelMainRight.add(LoopsPane);

        FunctionsReuseLabel.setText("Functions Reuse");
        jPanelMainRight.add(FunctionsReuseLabel);

        FunctionsReusePane.setMinimumSize(new java.awt.Dimension(210, 35));
        FunctionsReusePane.setPreferredSize(new java.awt.Dimension(210, 35));
        jPanelMainRight.add(FunctionsReusePane);

        jSplitPane2.setRightComponent(jPanelMainRight);

        jSplitPane1.setRightComponent(jSplitPane2);

        getContentPane().add(jSplitPane1);

        jMenuBar1.setBorder(null);

        jMenu1.setBorder(null);
        jMenu1.setMnemonic('f');
        jMenu1.setText("File");

        jMenuItem1.setText("New");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem7.setText("Open");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem7);

        jMenuItem21.setText("Save");
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem21);

        jMenuItem2.setText("Save As");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jMenu1.add(jSeparator1);

        jMenuItem3.setText("Exit");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setBorder(null);
        jMenu2.setMnemonic('e');
        jMenu2.setText("Edit");

        jMenuItem5.setText("Undo");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuItem8.setText("Redo");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem8);
        jMenu2.add(jSeparator2);

        jMenuItem14.setText("Cancel Operation");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem14);
        jMenu2.add(jSeparator4);

        jMenuItem13.setText("Set/Hide Grid");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem13);

        jMenuItem10.setText("Modify Grid");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem10);

        jMenuBar1.add(jMenu2);

        jMenu5.setMnemonic('s');
        jMenu5.setText("Structure");

        jMenuItem15.setText("Declare A Variable");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem15);

        jMenuItem17.setText("Create Arithmetic Operation");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem17);
        jMenu5.add(jSeparator3);

        jMenuItem11.setText("Create A Function");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem11);
        jMenu5.add(jSeparator6);

        jMenuItem16.setText("Create A Task");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem16);
        jMenu5.add(jSeparator7);

        jMenuItem18.setText("Create An Event");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem18);

        jMenuItem20.setText("Create An Event Monitor");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem20);

        jMenuBar1.add(jMenu5);

        jMenu6.setMnemonic('d');
        jMenu6.setText("Device");

        buttonGroupTarget.add(jRadioButtonMenuItemRCX1);
        jRadioButtonMenuItemRCX1.setText("RCX 1.0");
        jRadioButtonMenuItemRCX1.setEnabled(false);
        jRadioButtonMenuItemRCX1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItemRCX1ActionPerformed(evt);
            }
        });
        jMenu6.add(jRadioButtonMenuItemRCX1);

        buttonGroupTarget.add(jRadioButtonMenuItemRCX2);
        jRadioButtonMenuItemRCX2.setSelected(true);
        jRadioButtonMenuItemRCX2.setText("RCX 2.0");
        jRadioButtonMenuItemRCX2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItemRCX2ActionPerformed(evt);
            }
        });
        jMenu6.add(jRadioButtonMenuItemRCX2);

        jMenuBar1.add(jMenu6);

        jMenu3.setBorder(null);
        jMenu3.setMnemonic('b');
        jMenu3.setText("Build");

        jMenuItem4.setText("Create NQC Code");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);
        jMenu3.add(jSeparator5);

        jMenuItem12.setText("Compile and Download");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem12);

        jMenuItem9.setText("Compile and Run");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem9);

        jMenuBar1.add(jMenu3);

        jMenu4.setBorder(null);
        jMenu4.setMnemonic('h');
        jMenu4.setText("Help");

        jMenuItem19.setText("Introduction");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem19);

        jMenuItem22.setText("Show Video");
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem22);

        jMenuItem6.setText("About");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem6);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        setSize(new java.awt.Dimension(1020, 738));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // what to do in the event of the program being initialised
    // THE FILE fUNSoftWareConfig.txt is a config file needed to determine whether the user had accessed
    // the programmed before.
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        // Disable first-time user check (for now)
//        try
//        {
//            java.io.FileReader finput = new java.io.FileReader(ConfigFile_Name);
//            java.io.BufferedReader inputBuffer = new java.io.BufferedReader(finput);
//            String output = inputBuffer.readLine(); 
//            String tmp_string = new String();
//            if(output.compareTo("New=1") == 0)  // only continue if it is valid, check the file header
//            {
//                javax.swing.JOptionPane.showMessageDialog(this,
//                                    "You are new to " + AppProperties.appName + ", watch the video tutorial by selecting Help -> Show Video",
//                                    AppProperties.appName + " video",
//                                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
//            }                                  
//        }
//        catch(Exception e)
//        {
//            javax.swing.JOptionPane.showMessageDialog(this,
//                                    "You are new to " + AppProperties.appName + ", watch the video tutorial by selecting Help -> Show Video",
//                                    AppProperties.appName + " video",
//                                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
//            try 
//            {
//                java.io.FileWriter filewriter = new java.io.FileWriter(ConfigFile_Name);	    
//                filewriter.write("New=0", 0, 5);
//                filewriter.close();
//            } 
//            catch (Exception er) 
//            {
//                System.out.println("Error: " + e.getMessage());
//            } 
//        }
    }//GEN-LAST:event_formWindowOpened

    // to display a default browser containing the flash video presentation of the program
    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
        String url = getAppPath() + "flash_presentation/funTutorial.html";
        
        // format the file path string for use in a browser
        //url = url.replace(' ', '+');
        
        openURL(url);
    }//GEN-LAST:event_jMenuItem22ActionPerformed

    /**
     * Determines the current path of the application and returns it as a string
     * @return The current application path, including the trailing slash, as a string
     */
    public String getAppPath(){
        String url = (getClass().getResource("")).getPath();
        int endPos = 0;
        
        System.out.println("URL1 " + url);
        
        try{
            url = URLDecoder.decode(url, "UTF-8");
        } catch (UnsupportedEncodingException uee) {
            System.out.println("ERROR: UnsupportedEncodingException");
        }        
        
        System.out.println("URL2 " + url);
        
        
        // Get the directory of the jar file or class file
        if (url.lastIndexOf("!") >= 0){
            endPos = url.lastIndexOf("/", url.lastIndexOf("!")) + 1;
        } else if (url.lastIndexOf("/") > 1) {
            endPos = url.lastIndexOf("/") + 1;
        } else {
            endPos = url.length() - 1;
        }
        url = url.substring(0, endPos);
        
        System.out.println("URL3 " + url);
        
        // NOTE: File "protocol" varies by platform and implementation.
        //    For examle, the "file:" portion is not included when
        //    debugging in the NetBeans IDE but is when running java
        //    Thus, remove one leading character at a time
        //    and test if the directory exists
        while (url.length() > 1 && ! (new File(url)).exists()){
            url = url.substring(1);
        }
        
        // Determine if the resulting directory string exists
        if (!(new File(url)).exists()){
            // Default to current directory
            url = "";
        } else if (System.getProperty("os.name").startsWith("Windows")
                && url.length() > 1 && url.startsWith("/")
                && url.charAt(2) == ':'){
            url = url.substring(1);
        }
        
        System.out.println("URL4 " + url);
        
        return url;
    }

    /**
     * Determines the current user application path
     *  - creates the application-specific directory if it does not exist
     * @return The current user application path, including the trailing slash, as a string
     */
    public String getUserAppPath(){
        String path = System.getProperty("user.home");
        path = path + File.separator + "." + AppProperties.appName + File.separator;
        
        File userAppPath = new File(path);
        if (!userAppPath.exists()){
            userAppPath.mkdirs();
        }
        
        return path;
    }

    // code adapted from www.centerkey.com
    public void openURL(String url) {
        String errMsg = "Error attempting to launch web browser";
        String osName = System.getProperty("os.name");
        try 
        {
            if (osName.startsWith("Mac OS")) 
            {
               /* Class fileMgr = Class.forName("com.apple.eio.FileManager");
                java.lang.reflect.Method openURL = fileMgr.getDeclaredMethod("openURL",
                   new Class[] {String.class});
                openURL.invoke(null, new Object[] {"file:///"+url});*/
                Runtime.getRuntime().exec("open file:///"+url);
            }
            else if (osName.startsWith("Windows"))
            {
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler \"" + url + "\"");
            }
            else // assume Unix or Linux
            { 
                String[] browsers = {
                   "firefox", "opera", "konqueror", "epiphany", "mozilla", "netscape", "midori" };
                String browser = null;
                for (int count = 0; count < browsers.length && browser == null; count++)
                {
                    if (Runtime.getRuntime().exec(
                            new String[] {"which", browsers[count]}).waitFor() == 0)
                    {
                        browser = browsers[count];
                    }
                }
                if (browser == null)
                {
                    throw new Exception("Could not find web browser");
                }
                else
                {
                    Runtime.getRuntime().exec(new String[] {browser, url});
                }
            }
        }
        catch (Exception e) 
        {
         javax.swing.JOptionPane.showMessageDialog(null, errMsg + ":\n" + e.getLocalizedMessage());
        }
    }

    
    
    // for save file, NOT SAVE-AS
    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
        if(_Current_Save_Path.compareTo("") != 0)
        {
            jProgrammingWindow.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
            String code = Translator.getAlgorithm(icon_list.getIcons().get(1));

            java.io.File file = new java.io.File(_Current_Save_Path);

            try 
            {
                java.io.FileWriter filewriter = new java.io.FileWriter(file);	    
                filewriter.write(code, 0, code.length());
                filewriter.close();
                do_state.getCurrentState().setSaveStatus(1);
                do_state.getCurrentState().setSaveFilePath(file.getAbsolutePath());
                do_state.getCurrentState().setSaveFileName(file.getName());
                updateFileName(file.getName());
            } 
            catch (Exception e) 
            {
                System.out.println("Error: " + e.getMessage());
            }      
            jProgrammingWindow.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));     
        }
        else
        {
            execute_save();
        }
    }//GEN-LAST:event_jMenuItem21ActionPerformed

    
    // to handle for an event monitor creation 
    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
        
        if(event_number_tracker.Instance().isEmpty() == true)
        {
            javax.swing.JOptionPane.showMessageDialog(this,
                "There has to be at least 1 event defined.\nPlease define one.",
                "Error - No Event",
            javax.swing.JOptionPane.WARNING_MESSAGE);    
        }
        else
        {
            // set up the dialog first
            monitorEventDialog me_dialog = new monitorEventDialog(this, null, 0);
            me_dialog.pack();
            me_dialog.setLocationRelativeTo(this);
            me_dialog.setVisible(true);  
            
            String eventName = me_dialog.getEventMonitorName();
            if(eventName.compareTo("") != 0)  // user didnt press cancel
            {
                monitorEvent mE = _mEvent.Clone();
                mE.setName(eventName);
                mE.setLevel(me_dialog.getLevel());
                String aux_name = me_dialog.getAuxiliary();
                if(aux_name != null)  // local not global
                {
                    mE.setEventElements(me_dialog.getEventType(), aux_list.Instance().getAuxiliary(aux_name));    
                }
                else
                {
                    mE.setEventElements(me_dialog.getEventType(), null);
                }
                
                
                mE.setMonitoredEvents(me_dialog.getEventsMonitored());
                
                aux_list.Instance().addAuxiliary(mE);
                
                jPanelEventMonitor.insertEvent(mE);                    

                UndoRedo.update_state();
                DirectionsLabel.setText("Event Monitor successfully created");
            }
        }
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    // To display an introductory help document
    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed

        HelpDialog help_dialog = new HelpDialog(this);
        help_dialog.pack();
        help_dialog.setLocationRelativeTo(this);
        help_dialog.setVisible(true);
        
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    
    // for undo
    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {
        executeUndo();
    }

    // for redo
    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {
        executeRedo();
    }    
    
    // to handle for an event creation
    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
        // set up the dialog first
        EventNameDialog ev_dialog = new EventNameDialog(this, null, 0);
        ev_dialog.pack();
        ev_dialog.setLocationRelativeTo(this);
        ev_dialog.setVisible(true);
        
        String eventName = ev_dialog.getEventName();
        if(eventName.compareTo("") != 0)
        {
            Event ev = _Event.Clone();

            int can_add = event_number_tracker.Instance().addEvent(ev);
            if(can_add == 0)
            {
                ev.setName(eventName);
                
                Operand threshold_to_pass = ev_dialog.getThreshold();
                ev.setEventElements(ev_dialog.getSource(), ev_dialog.getEventSource(), 
                                    ev_dialog.getEventType(), threshold_to_pass);
                
                jPanelEvent.insertEvent(ev);                    

                UndoRedo.update_state();
                DirectionsLabel.setText("Event successfully created");                   
            }
            else
            {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "Event cannot be created, limit reached (16 events).",
                    "Error - Event Limit Reached",
                javax.swing.JOptionPane.WARNING_MESSAGE);       
            }
        }
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    
    // to handle for closing the program by clicking the cross button located on the top right hand side of the window
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        jMenuItem3ActionPerformed(null);
    }//GEN-LAST:event_formWindowClosing

    
    // to get the current auxiliary (either a function or a task)
    private auxiliary getCurrentAuxiliary()
    {
        int nid=0;
        javax.swing.ButtonGroup bg = radiobuttons.Instance().getButtonGroup();
        java.util.Enumeration<javax.swing.AbstractButton> enum_ab = bg.getElements();
        javax.swing.JRadioButton jrb = new javax.swing.JRadioButton();
        while(enum_ab.hasMoreElements())
        {
            jrb = (javax.swing.JRadioButton)enum_ab.nextElement();
            if(jrb.isSelected())
            {
                nid = Integer.parseInt(jrb.getActionCommand());
                break;
            }            
        }
        return aux_list.Instance().getAuxiliaries().get(nid);
    }
    
    // for initialising an arithmetic operation
    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        // first check that there at least 1 declared variable aside from the input sensors values which are not to be altered but read
        // the declared variable can either be a local or global
          
        local_var_list lvl = new local_var_list();
        if(getCurrentAuxiliary() instanceof function)
        {
            function f =  (function)getCurrentAuxiliary();
            lvl = f.getLocalVarList();
        }
        else if(getCurrentAuxiliary() instanceof task)
        {
            task t =  (task)getCurrentAuxiliary();
            lvl = t.getLocalVarList();      
        }
        else if(getCurrentAuxiliary() instanceof monitorEvent)
        {
            monitorEvent e =  (monitorEvent)getCurrentAuxiliary();
            if(e.getType().compareTo("Local") == 0)
            {
                lvl = e.getAuxPoint().getLocalVarList();
            }
        }
                
        if(var_list.Instance().isDefinedVariables() == false && lvl.isEmpty() == true)
        {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "There has to be at least 1 non input sensor local or global variable.\nPlease declare one.",
                    "Error - No Variable",
                javax.swing.JOptionPane.WARNING_MESSAGE);
        }
        else
        {
            VarOperationDialog dv_dialog = new VarOperationDialog(this, getCurrentAuxiliary()); 
            dv_dialog.pack();
            dv_dialog.setLocationRelativeTo(this);
            dv_dialog.setVisible(true);
                    
            // start creating the arithmetic operation class and its multiple operands
            ArithmeticOperation ret_val = dv_dialog.getUserInput();
            if(ret_val != null)  // an indication that user did not press cancel
            {
                // create the ArithOpIcon class, then fill in the ArithmeticOperation class that it will point at   
                ArithOpIcon icon = _AOIcon.Clone();
                icon.setAO(ret_val);
                prepIconHandlers(icon, 1);
                UndoRedo.update_state();
            }
        }
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    // handles a variable's declaration
    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        DeclareVariableDialog dv_dialog = new DeclareVariableDialog(this, 0, null);
        dv_dialog.pack();
        dv_dialog.setLocationRelativeTo(this);
        dv_dialog.setVisible(true);
        String[] ret_val = dv_dialog.getUserInput();
        
        if(ret_val[0].compareTo("") != 0)  // user didn't press the cancel button
        {
            variable var = _Var.Clone();
            
            var.setName(ret_val[0]);
            var.setValue(Integer.parseInt(ret_val[3]));
            if(ret_val[1].compareTo("Global") == 0)
            {
                var.setType(0);
                var_list.Instance().addVariable(var);
            }
            else if(ret_val[1].compareTo("Local") == 0)
            {
                var.setType(1);
                auxiliary a = aux_list.Instance().getAuxiliary(ret_val[2]);
                var.setAux(a);
                a.addVariable(var);              
            }
            jPanelVar.insertVar(var);
            UndoRedo.update_state();
            DirectionsLabel.setText("Variable successfully declared");      
        }
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    // to handle the menu items to create a task including its radio buttons on the task pallette.
    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        // set up the dialog first
        TaskNameDialog tsk_dialog = new TaskNameDialog(this, "", 0);
        tsk_dialog.pack();
        tsk_dialog.setLocationRelativeTo(this);
        tsk_dialog.setVisible(true);
        
        String taskName = tsk_dialog.getUserInput();
        if(taskName.compareTo("") != 0)
        {
            createTask(taskName);            
        }
    }//GEN-LAST:event_jMenuItem16ActionPerformed
    
    // to create a task
    private void createTask(String taskName)
    {
        task tsk = _Task.Clone();

        aux_list.Instance().addAuxiliary(tsk);
                                
        if(taskName.compareTo("") == 0)
        {
            tsk.setName("task"+tsk.getNumId());
        }
        else
        {
            tsk.setName(taskName);
        }
                
        jPanelTask.insertTask(tsk);                    

        UndoRedo.update_state();
        DirectionsLabel.setText("Task successfully created");
    }
        
        
    // ------------------------------------------------------------------------------------
    // ------------------------------------------------------------------------------------
    // >>>  THE MENU ITEM HANDLERS
    // ------------------------------------------------------------------------------------
    // ------------------------------------------------------------------------------------
    
    // This menu is auxiliary, it is only to open a pop up window outlining the "About and Licenses"
    // of this program
    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        javax.swing.JOptionPane.showMessageDialog(jPanelMainCenter,
                                (new javax.swing.ImageIcon(getClass().getResource("/icons/logos/about.png"))),
                                AppProperties.appNameVersion + " -- About", 
                                javax.swing.JOptionPane.PLAIN_MESSAGE);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    // to handle for running the previously created NQC file which in turn would run the LEGO Mindstorms
    // NQC was developed by Dave Baum
    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        buildAndDownload(true);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    // NQC compiler created by Dave Baum
    // this action method simply download the program into the RCX without running it
    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        buildAndDownload(false);
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    // helper method that builds NQC Code and downloads it
    private void buildAndDownload(boolean autoRun)
    {
        int check = compileProgram();
        if(check == 0)
        {
            try{
                String[] cmd = new String[3];
                String NQCCommand = "nqc -T" + AppProperties.getDeviceID() + " -d " + AppProperties.appCodeFileName;
                if (autoRun)
                {
                    NQCCommand += " -run";
                }
                
                String osName = System.getProperty("os.name");
                System.out.println(osName);
                // to enable platform independence
                if(osName.compareTo("Windows 95")  == 0 || osName.compareTo("Windows 98") == 0)
                {
                    cmd[0] = "command.com" ;
                    cmd[1] = "/C" ;
                    cmd[2] = NQCCommand;
                }
                // Other, non-legacy Windows platforms (e.g. CE, NT, 2000, XP, Vista) use "cmd.exe"
                else if(osName.startsWith("Windows"))
                {
                    cmd[0] = "cmd.exe" ;
                    cmd[1] = "/C" ;
                    cmd[2] = NQCCommand;
                }
                else if(osName.compareTo("Linux")  == 0)
                {
                    cmd[0] = "/bin/sh" ;
                    cmd[1] = "-c" ;
                    cmd[2] = NQCCommand;
                }                
                else if(osName.compareTo("Mac OS X")  == 0)
                {
                    cmd[0] = "/bin/sh" ;
                    cmd[1] = "-c";
                    cmd[2] = NQCCommand;
                }
                Runtime rt = Runtime.getRuntime();
            
                Process proc = rt.exec(cmd, null, new File(getUserAppPath()));
                int exitVal = proc.waitFor();
                if(exitVal != 0)
                {
                    DirectionsLabel.setText("Error encountered in the downloading process");
                }
                else
                {
                    DirectionsLabel.setText("Your program has been downloaded successfully");
                }
            }
            catch(Throwable t)
            {
                t.printStackTrace();
            } 
        }
    }
    
    // helper method that creates NQC Code
    private void createNQCFile()
    {
        int err = 0;
        String NQCcode = Translator.getRCXNQCCode(1);  // the string representation of NQC
        String url_save = do_state.getCurrentState().returnSaveFilePath();
        String filename = do_state.getCurrentState().returnSaveFileName();

        url_save = url_save.substring(0, url_save.lastIndexOf(filename));
        url_save += filename + ".nqc";
        java.io.File file = new java.io.File(url_save);
        try 
        {
            java.io.FileWriter filewriter = new java.io.FileWriter(file);	    
            filewriter.write(NQCcode, 0, NQCcode.length());
            filewriter.close();
        } 
        catch (Exception e) 
        {
            System.out.println("Error: " + e.getMessage());
            DirectionsLabel.setText("NQC Error Encountered in the Compilation Process");
            err = 1;
        }
        if(err == 0)
        {
            DirectionsLabel.setText("NQC code created successfully.");
        }             
    }
    
    // method that handles the compiling or translating the program into pseudocode then into NQC which in turn will 
    // be executed using the NQC compiler
    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed

        if(do_state.getCurrentState().returnSaveStatus() == 0)
        {
            int user_choice;
            user_choice=javax.swing.JOptionPane.showConfirmDialog(jPanelMainCenter,
                "Current work not yet saved, you need to save your work\nbefore creating NQC code out of it. Save file?",
                "Current work not saved",
                javax.swing.JOptionPane.OK_CANCEL_OPTION);
            if(user_choice == javax.swing.JOptionPane.CANCEL_OPTION)
            {
                // return to programming window
            }
            else if(user_choice == javax.swing.JOptionPane.OK_OPTION)
            {
                // save the file first
                int r = 1;
                if(_Current_Save_Path.compareTo("") != 0)
                {
                    jProgrammingWindow.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
                    String code = Translator.getAlgorithm(icon_list.getIcons().get(1));

                    java.io.File file = new java.io.File(_Current_Save_Path);

                    try 
                    {
                        java.io.FileWriter filewriter = new java.io.FileWriter(file);	    
                        filewriter.write(code, 0, code.length());
                        filewriter.close();
                        do_state.getCurrentState().setSaveStatus(1);
                        do_state.getCurrentState().setSaveFilePath(file.getAbsolutePath());
                        do_state.getCurrentState().setSaveFileName(file.getName());
                        updateFileName(file.getName());
                    } 
                    catch (Exception e) 
                    {
                        System.out.println("Error: " + e.getMessage());
                        r = 0;
                    }      
                    jProgrammingWindow.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));     
                }
                else
                {
                    r = execute_save();
                }
                if(r == 1)
                {
                    createNQCFile();
                }
            }
        }
        else
        {
            createNQCFile();        
        }        
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    // to edit the size of the grid (distance between grid intersections)
    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        grid_dialog = new GridDialog(this, Constants.gridDistanceX, Constants.gridDistanceY);
        grid_dialog.pack();
        grid_dialog.setLocationRelativeTo(this);
        grid_dialog.setVisible(true);
        int[] grid_sizes = grid_dialog.getUserInput();
        
        // preserve the old value for shifting values
        int old_dx = Constants.gridDistanceX;
        int old_dy = Constants.gridDistanceY;
        
//        distanceX = grid_sizes[0];
        Constants.setGridDistanceX(grid_sizes[0]);
      
//        distanceY = grid_sizes[1];
        Constants.setGridDistanceY(grid_sizes[1]);
        
        java.util.Vector<Icon> ics = icon_list.getIcons();
        int shiftX = 0;
        int shiftY = 0;
        if(old_dx != Constants.gridDistanceX || old_dy != Constants.gridDistanceY)
        {
            for(int i = 1; i < ics.size(); i++)
            {
                Icon tmp = ics.get(i); 
                if(tmp != null)
                {
                    // shift its coordinate
                    int hor_seg = (tmp.getCoordinate().getX()+(tmp.getWidth()/2)) / old_dx;  // # segments horizontally
                    int cur_x = hor_seg * Constants.gridDistanceX;
                    int old_x = hor_seg * old_dx;
                    shiftX = cur_x - old_x; 
                    int new_x = tmp.getCoordinate().getX() + shiftX;

                    // now do the Y coord
                    int ver_seg = (tmp.getCoordinate().getY()+(tmp.getHeight()/2)) / old_dy;  // # segments horizontally
                    int cur_y = ver_seg * Constants.gridDistanceY;
                    int old_y = ver_seg * old_dy;
                    shiftY = cur_y - old_y;
                    int new_y = tmp.getCoordinate().getY() + shiftY;
                    
                    coord co = new coord(new_x, new_y);
                    tmp.setCoordinate(co);
                    tmp.setBounds(co.getX(), co.getY(), tmp.getWidth(), tmp.getHeight());
                }
            }
        }
        // update all tabbed programming windows
//        int lim = jPanel13.getComponentCount();
        int lim = CenterCardPanel.getComponentCount();
        for(int i = 1; i < lim; i++)  // skip the empty one
        {
//            javax.swing.JPanel jp = (javax.swing.JPanel)jPanel13.getComponent(i);
            javax.swing.JPanel jp = (javax.swing.JPanel)CenterCardPanel.getComponent(i);
            javax.swing.JScrollPane jsp = (javax.swing.JScrollPane)jp.getComponent(0);           
            ProgWindow pw = (ProgWindow)(jsp.getViewport().getView());
            pw.resizeProgWindow(jsp);
            jsp.repaint();
            jsp.revalidate();
        }        
        repaint();
        validate();
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    // to handle redo
    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        executeRedo();
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    // to handle the undo operation    
    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        executeUndo();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    // This method simply quits the system, but ensuring user is aware whether the current work has been saved
    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        int user_choice;
        if(do_state.getCurrentState() != null && do_state.getCurrentState().returnSaveStatus() == 0
                && icon_list.getIcons().size() > 3)  // not yet saved, then ask user to save first
        {
            user_choice=javax.swing.JOptionPane.showConfirmDialog(jPanelMainCenter,
                                    "Current work not yet saved, save file before quitting?",
                                    "Current work not saved",
                                    javax.swing.JOptionPane.YES_NO_CANCEL_OPTION);
            if(user_choice == javax.swing.JOptionPane.CANCEL_OPTION)
            {
                // return to programming window
            }
            else if(user_choice == javax.swing.JOptionPane.YES_OPTION)
            {
                // save the file first
                int r = execute_save();
                if(r == 1)
                {
                    System.exit(0);
                }                
            }
            else if(user_choice == javax.swing.JOptionPane.NO_OPTION)
            {
                System.exit(0);
            }
        }
        else
        {
            System.exit(0);
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    // This method handles for when "Save As" menu has been executed
    // This should pop up a dialog window asking the user to name and select where to save the file
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        execute_save();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    // The following method open up a file chooser for the user to load a specific file
    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // ask the user first whether to save the existing unsaved work
        int user_choice;
        int allowed = 0; // initially not allowed to proceed
        if(do_state.getCurrentState().returnSaveStatus() == 0)  // not yet saved, then ask user to save first
        {
            user_choice=javax.swing.JOptionPane.showConfirmDialog(jPanelMainCenter,
                                    "Current work not yet saved, save file before continuing?",
                                    "Current work not saved",
                                    javax.swing.JOptionPane.YES_NO_CANCEL_OPTION);
            if(user_choice == javax.swing.JOptionPane.YES_OPTION)
            {
                // save the file first
                int r = execute_save();    
                if(r == 1)
                {
                    allowed = 1;
                }                
            }
            else if(user_choice == javax.swing.JOptionPane.NO_OPTION)
            {  
                allowed = 1;
            }
        }
        else
        {
            allowed = 1;
        }
        if(allowed == 1)
        {
            int ret_val = jFileChooser1.showOpenDialog(jDialog1); 
            String output = new String();
            if (ret_val == javax.swing.JFileChooser.APPROVE_OPTION) {
                jProgrammingWindow.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
                java.io.File file = jFileChooser1.getSelectedFile();            
                try
                {
                    java.io.FileReader finput = new java.io.FileReader(file);
                    java.io.BufferedReader inputBuffer = new java.io.BufferedReader(finput);
                    output += inputBuffer.readLine(); 
                    String tmp_string = new String();
                    // only continue if it is valid, check the file header
                    if(output.compareTo(AppProperties.getProgramTypeID()) == 0)
                    {
                        while ((tmp_string = inputBuffer.readLine()) != null)
                        {  
                            output += "\n";
                            output += tmp_string;                    
                        }
                        finput.close();
                        executeLoad(output); 
                        updateFileName(file.getName());
                        UndoRedo.update_state();
                        do_state.getCurrentState().setSaveStatus(1);
                        do_state.getCurrentState().setSaveFilePath(file.getAbsolutePath());
                        do_state.getCurrentState().setSaveFileName(file.getName());
                        _Current_Save_Path = file.getAbsolutePath();
                        jPanel7onListeners();
                    }
                    else
                    {
                        finput.close();
                        javax.swing.JOptionPane.showMessageDialog(jPanelMainCenter,
                                        "File invalid or incompatible, please reselect the file",
                                        "File Invalid",
                                        javax.swing.JOptionPane.WARNING_MESSAGE);
                    }                                   
                }
                catch(Exception e)
                {
                    System.out.println("Error: " + e.getMessage());
                    javax.swing.JOptionPane.showMessageDialog(jPanelMainCenter,
                                        "File invalid or incompatible, please reselect the file",
                                        "File Invalid",
                                        javax.swing.JOptionPane.WARNING_MESSAGE);
                    // reset ALL
//                    restartProgWindow(1);
                    restartProgWindow(0);
                }           
            }
            jProgrammingWindow.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        }
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    // to restart a program entirely        
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // Have to ensure that the current work is saved or unsaved under the consent of the user    
        int user_choice;
        int allowed = 0; // initially not allowed to proceed
        if(do_state.getCurrentState() != null && do_state.getCurrentState().returnSaveStatus() == 0)  // not yet saved, then ask user to save first
        {
            user_choice=javax.swing.JOptionPane.showConfirmDialog(jPanelMainCenter,
                                    "Current work not yet saved, save file before continuing?",
                                    "Current work not saved",
                                    javax.swing.JOptionPane.YES_NO_CANCEL_OPTION);
            if(user_choice == javax.swing.JOptionPane.YES_OPTION)
            {
                // save the file first
                int r = execute_save();    
                if(r == 1)
                {
                    allowed = 1;
                }                
            }
            else if(user_choice == javax.swing.JOptionPane.NO_OPTION)
            {  
                allowed = 1;
            }
        }
        else
        {
            allowed = 1;
        }
        
        if(allowed == 1)
        {
            updateFileName("New File");
            restartProgWindow(0);     
            _Current_Save_Path = new String();
            UndoRedo.update_state();
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    
    // this method will ask the user to click on the programming window for the beginning of the click,
    // afterwards the user would need to click on the second position for the ending of the function
    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        // creating functions
      
       jPanel7offListeners();
        
       DirectionsLabel.setText("Click on the programming window for the beginning of the function");
        
       jProgrammingWindow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt2) {                
                FunctionMouseClicked1(evt2);
            }
        });
    }//GEN-LAST:event_jMenuItem11ActionPerformed
        
    // ------------------------------------------------------------------------------------
    // >>>  END OF MENU ITEM HANDLERS
    // ------------------------------------------------------------------------------------
    // ------------------------------------------------------------------------------------
    
    // this the method that will add another listener to accept the end of the function
    private void FunctionMouseClicked1(java.awt.event.MouseEvent evt)
    {
        Icon tic = jProgrammingWindow.findIcon(evt.getX(), evt.getY(), 1);   // right of icon
        Icon tic2 = jProgrammingWindow.findIcon(evt.getX(), evt.getY(), 0);  // left of icon
        
        // instead has to find a mid point between tic and tic2
        
        int xpos = 0;
        if(tic2 != null && tic != null)
        {
            xpos = gridcalculation.midPoint(tic2.getCoordinate(), tic.getCoordinate());
        }
        
        int ypos = gridcalculation.calculateY(evt.getY());
        coord co = new coord(xpos, ypos);
        
        if(co.getX() < (jProgrammingWindow.getStartIcon().getCoordinate().getX()+jProgrammingWindow.getStartIcon().getWidth()/2) ||
           co.getX() > (jProgrammingWindow.getEndIcon().getCoordinate().getX()+jProgrammingWindow.getEndIcon().getWidth()/2) ||
           tic == null || tic2 == null || tic instanceof joinEndBranchIcon)
        {
            // if the desired place for the new icon is taken by another icon, make sure
            // the new icon could not be to the left of the starting icon or to the right of the
            // ending icon
            
            javax.swing.JOptionPane.showMessageDialog(jPanelMainCenter,
                                    "Selection invalid, please reselect the position",
                                    "Selection Invalid",
                                    javax.swing.JOptionPane.WARNING_MESSAGE);
            DirectionsLabel.setText(readyPrompt);
            jPanel7onListeners();
        }
        else   
        {        
           DirectionsLabel.setText("Click on the programming window to position the end of the function");
           final int xx = evt.getX();
           final int yy = evt.getY();

           jPanel7offListeners();

           jProgrammingWindow.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt2) {

                    // the handler for this repeat icon
                    createFunction(xx, yy, evt2);

                    // reset the listeners
                    jPanel7onListeners();
                }
            });
        }
    }
    
    // now that both beginning and end of the function has been defined, create the function
    private void createFunction(int xpos, int ypos, java.awt.event.MouseEvent evt2)
    {        
        coord tmp_co = new coord(evt2.getX(), evt2.getY());        

        // for beginning icon
        Icon tic = jProgrammingWindow.findIcon(xpos, ypos, 1);   // right of icon
        Icon tic2 = jProgrammingWindow.findIcon(xpos, ypos, 0);  // left of icon

        xpos = gridcalculation.calculateXright(xpos);
        ypos = gridcalculation.calculateY(ypos);

        // for ending icon
        Icon tic3 = jProgrammingWindow.findIcon(evt2.getX(), evt2.getY(), 1);   // right of icon
        Icon tic4 = jProgrammingWindow.findIcon(evt2.getX(), evt2.getY(), 0);   // left of icon

        int xpos2 = gridcalculation.calculateXright(evt2.getX());  
        int ypos2 = gridcalculation.calculateY(evt2.getY());

        // for viewport
        int v_x = xpos;
        int v_y = ypos;


        // for checking that all icons to be put within this new repeat should have the same parent icon
        int allowed = 1;  // initially zero for allowed (not allowed = 0)
        if(ypos == ypos2 && xpos2 >= xpos)
        {
            // check for the enclosed icons
            Icon tm_p = null;
            int x = xpos;
            int x_limit = xpos2;
            while(x < x_limit)
            {
                java.awt.Component c = jProgrammingWindow.getComponentAt(x, ypos);
                if(c instanceof Icon)
                {
                    Icon ico = (Icon) c;
                    if(ico instanceof joinLoopIcon)
                    {
                        ico = ico.getBottomNeighbour();
                        x = gridcalculation.calculateXright(ico.getEndIcon().getCoordinate().getX());
                        if(x >= xpos2 && xpos <= gridcalculation.calculateXright(ico.getBeginIcon().getCoordinate().getX()))
                        {
                            allowed = 0;
                            break;
                        }
                    }
                    else if(ico instanceof branchIcon)
                    {
                        x = gridcalculation.calculateXright(ico.getEndBranch().getCoordinate().getX());
                    }
                        if(tm_p == null)
                        {
                            tm_p = ico.getParentIcon();
                        }
                        else
                        {
                            if(ico.getParentIcon().getId() != tm_p.getId())
                            {
                                allowed = 0;
                                break;
                            }
                        }
                }
                x += Constants.gridDistanceX;
            }                    
        }
        else
        {
            allowed = 0;
        }
        if(allowed == 1)
        {
            if(tic instanceof joinLoopIcon && tic.getBottomNeighbour().getEndIcon().getId() == tic.getId() &&
               tic4 instanceof joinLoopIcon && tic4.getBottomNeighbour().getBeginIcon().getId() == tic4.getId())
            {
                allowed = 1;
            }
            else
            {
               if(tic instanceof joinLoopIcon && tic.getBottomNeighbour().getEndIcon().getId() == tic.getId())
                {
                    allowed = 0;
                }
                if(tic4 instanceof joinLoopIcon && tic4.getBottomNeighbour().getBeginIcon().getId() == tic4.getId())
                {
                    allowed = 0;
                }
            }
        }

        if(xpos == xpos2)  // special override, if this repeat meant to start empty, it will be allowed no matter where it is
        {
            allowed = 1;
        }
            
        if(tmp_co.getX() < (jProgrammingWindow.getStartIcon().getCoordinate().getX()+jProgrammingWindow.getStartIcon().getWidth()/2) ||
           tmp_co.getX() > (jProgrammingWindow.getEndIcon().getCoordinate().getX()+jProgrammingWindow.getEndIcon().getWidth()/2) ||
           tic == null || tic2 == null || tic3 == null || tic4 == null || allowed == 0 || tic3 instanceof joinEndBranchIcon) 
        {
            // if the desired place for the new icon is taken by another icon, make sure
            // the new icon could not be to the left of the starting icon or to the right of the
            // ending icon
            
            javax.swing.JOptionPane.showMessageDialog(jPanelMainCenter,
                                    "Selection invalid, please reselect the position",
                                    "Selection Invalid",
                                    javax.swing.JOptionPane.WARNING_MESSAGE);
            DirectionsLabel.setText(readyPrompt); 
        }
        else
        {
            // set up the dialog first
            FunctionNameDialog func_dialog = new FunctionNameDialog(this, "", 0);
            func_dialog.pack();
            func_dialog.setLocationRelativeTo(this);
            func_dialog.setVisible(true);
                    
            String[] functionName = func_dialog.getUserInput();
            local_var_list lvl = func_dialog.getArgumentsInput();
              
            if(functionName != null && functionName[0].compareTo("") != 0)  // user didn't press cancel, then go ahead
            {
                // establish the new links between the old members, new members, old parent and new parent
                java.util.Vector<Icon> ics = new java.util.Vector<Icon>();
                Icon left_icon; // = new Icon();
                Icon right_icon; // = new Icon();
                left_icon = tic2;
                right_icon = tic3;
                Icon ic = new Icon();
                icon_list.addIcon(ic);
                jProgrammingWindow.addIcon(ic);
                ic.setCoordinate(tic3.getCoordinate());
                jProgrammingWindow.setUpIconParent(ic, tic2, tic3);
                if(tic2 instanceof joinLoopIcon)
                {
                    if(tic2.getParentIcon().getId() != ic.getParentIcon().getId())
                    {
                       left_icon = tic2.getBottomNeighbour(); 
                    }
                }
                else if(tic2 instanceof joinEndBranchIcon)
                {
                    left_icon = tic2.getBranchIcon();
                }
                if(tic3 instanceof joinLoopIcon && tic3.getParentIcon().getId() != ic.getParentIcon().getId())
                {

                    right_icon = tic3.getBottomNeighbour();
                }

                ic.getParentIcon().delMember(ic);
                jProgrammingWindow.deleteIcon(ic);
                icon_list.delIcon(ic);

                ics = jProgrammingWindow.getIconsToChange(left_icon, right_icon);

                functionIcon Ifunc = _Funcby.Clone();            

                function fu = _Funct.Clone();
                fu.setMembers(ics);  
                // to set the arguments
                fu.setLocalVarList(lvl);
                java.util.Vector<variable> vect_var = lvl.getVariables();
                for(int i=1; i<vect_var.size(); i++)
                {
                    if(vect_var.get(i) != null)
                    {
                        vect_var.get(i).setType(2);
                        fu.addArgument(vect_var.get(i));
                        vect_var.get(i).setAux(fu);
                    }
                }

                // delete icons within the selected area
                delSelectedIcons(ics);
                // add the function icon
                xpos2 = gridcalculation.calculateXright(tic3.getCoordinate().getX());
                ypos2 = gridcalculation.calculateY(tic3.getCoordinate().getY()+(tic.getHeight()/2));
                jProgrammingWindow.setUpIcon(Ifunc, xpos2, ypos2);
                
                aux_list.Instance().addAuxiliary(fu);
                                
                if(functionName[0].compareTo("") == 0)
                {
                    fu.setName("func"+fu.getNumId());
                }
                else
                {
                    fu.setName(functionName[0]);
                }
                
                fu.setLevel(Integer.parseInt(functionName[1]));
                
                jPanelFunction.insertFunction(fu);                    
                
                // now the function has an id, so set it so that the function icon points at it.
                Ifunc.setFunction(fu);
                
                FunctionCombo FunctionComboBox = FunctionCombo.Instance(); 
                javax.swing.JMenuItem jmi = new javax.swing.JMenuItem(Ifunc.getName());
                jmi.setActionCommand(Integer.toString(Ifunc.getNumId()));
                FunctionComboBox.addItem(jmi);
                
                //insertObjectIcon(Ifunc, tic3, tic2, 0);      // need to insert function completely (type == 0)            
                UndoRedo.update_state();
                DirectionsLabel.setText("Function successfully created");    
            }
        }
    }
    
    // ---------------
    // 
    /**
     * A special method to set the panels
     * @param jp Panel on top of programming window
     * @param jsp Scroll pane of the programming window
     * @param pw The programming window
     */
    public static void setProgrammingWindow(javax.swing.JPanel jp, javax.swing.JScrollPane jsp, ProgWindow pw)
    {
//        jPanel18 = jp;
        jScrollPane1 = jsp;
        jProgrammingWindow = pw; 
    }
    // ---------------
    
    // a function that deletes a set of icons as specified, argument is a set of icons to be inserted into the function
    private void delSelectedIcons(java.util.Vector<Icon> ics)
    {
        for(int i=0; i<ics.size(); i++)
        {
            Icon tmp = ics.get(i);
            if(tmp instanceof loopIcon)
            {
                deleteRepeatAll(tmp, tmp.getCoordinate().getX(), tmp.getCoordinate().getX());
            }
            else
            {
                deleteIcon(tmp, tmp.getCoordinate().getX(), tmp.getCoordinate().getX());
            }
        }
    }
            
        
    // to handle real time icon's name indicator for this icon (Exit)
    private void iconMouseExited()
    {
        jLabelHelp.setText("Select an icon");
    }
    
    // to handle real time icon's name indicator for this icon (Enter)
    private void iconMouseEntered(String Label)
    {
        jLabelHelp.setText(Label);
    }
    
    // to handle redo
    
    // this method does what it takes to execute the redo operation
    private void executeRedo()
    {
        State to_change_to = do_state.commitRedo();
        completingUndoRedo(to_change_to); 
    }
    
    // to handle undo
    // this method does what it takes to execute the undo operation
    private void executeUndo()
    {
        State to_change_to = do_state.commitUndo();
        completingUndoRedo(to_change_to);
    }
    
    
    // the common steps after committing and undo or redo in this program
    private void completingUndoRedo(State to_change_to)
    {
        if(to_change_to != null)
        {
            // to determine which radio button to select after the whole thing is reset to previous state
            String radioButtonCommand = new String();
            String name = new String();  //REMEMBER a function's a task's and an event monitor's name are unique to each other
            javax.swing.ButtonGroup bg = radiobuttons.Instance().getButtonGroup();
            java.util.Enumeration<javax.swing.AbstractButton> enum_ab = bg.getElements();
            javax.swing.JRadioButton jrb;
            while(enum_ab.hasMoreElements() == true)
            {
                jrb = (javax.swing.JRadioButton)enum_ab.nextElement();
                if(jrb.isSelected() == true)
                {
                    name = jrb.getText(); 
                    radioButtonCommand = jrb.getActionCommand();
                    break;
                }
            } 
            
            // reset all variables
            jPanelFunction.getTabbedWinMan().panels_reset();
            
            reset();
            resetVars();
            
            String algo = to_change_to.returnState();
            algo = algo.substring(algo.indexOf("start"));
            algo = algo.substring(algo.indexOf("\n")+1);     
            addOntoProgWindow(algo);
            UndoRedo.updateRedoUndoButtons();
            
            auxiliary tmp = aux_list.Instance().getAuxiliary(name);
            if(tmp == null)
            {
                radioButtonCommand = "0";
            }
            else
            {
                radioButtonCommand = Integer.toString(tmp.getNumId());    
            }            
            
            // to maintain the current's panel view if possible
            bg = radiobuttons.Instance().getButtonGroup();
            enum_ab = bg.getElements();
            
            if(tmp != null)
            {
                setProgrammingWindow(tmp.getPanel(), tmp.getScroller(), tmp.getProgWindow());

                while(enum_ab.hasMoreElements() == true)
                {
                    jrb = (javax.swing.JRadioButton)enum_ab.nextElement();

                    if(jrb.getText().compareTo(name) == 0)
                    {
                        jPanelFunction.getTabbedWinMan().displayPanel(radioButtonCommand);
                        jrb.setSelected(true); 
                        break;
                    }
                }                
            }  
            else
            {
                jPanelTask.getTabbedWinMan().displayPanel("1");
                ((javax.swing.JRadioButton)jPanelTask.getComponent(0)).setSelected(true);
            }
        }         
        repaint();
        validate();
    }
    
    // the ultimate small method that does big thing !! :P
    // this method pretty much creates an NQC file which then will be run to move the RCX robot
    private int compileProgram()
    {       
        // Check that the program's current state has been saved
        String NQCcode = Translator.getRCXNQCCode(1);  // the string representation of NQC
        java.io.File file = new java.io.File(getUserAppPath() + AppProperties.appCodeFileName);
        try 
        {
            java.io.FileWriter filewriter = new java.io.FileWriter(file);	    
            filewriter.write(NQCcode, 0, NQCcode.length());
            filewriter.close();
            return 0;
        } 
        catch (Exception e) 
        {
            System.out.println("Error: " + e.getMessage());
            DirectionsLabel.setText("NQC Error Encountered in the Compilation Process");
        }

        return 0; // default is no error
    }
    
    
    // this method handles the click on the function combo box to then duplicate the specified function
    private void FuncComboBoxActionPerformed(java.awt.event.ActionEvent evt)
    {        
        // set up its attributes first
        javax.swing.JComboBox jcb = (javax.swing.JComboBox)evt.getSource();
        
        if(jcb.getSelectedItem() instanceof javax.swing.JMenuItem)
        {            
            final functionIcon func = _Funcby.Clone();
            javax.swing.JMenuItem selected = (javax.swing.JMenuItem)jcb.getSelectedItem();
            String ss = selected.getActionCommand();
            int func_pos = Integer.parseInt(ss);   // the num id of the function
            
            function tmp_func = (function)(aux_list.Instance().getAuxiliaries().get(func_pos));
            
            auxiliary current_auxiliary = getCurrentAuxiliary();
            
            if(func_pos == current_auxiliary.getNumId())
            {
                javax.swing.JOptionPane.showMessageDialog(jPanelMainCenter,
                    "A function cannot be called within itself.\nReselect other tasks or functions.",
                    "Function Call Invalid",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
                DirectionsLabel.setText(readyPrompt);
            }
            else if(current_auxiliary instanceof function && ((function)current_auxiliary).getLevel() <= tmp_func.getLevel())
            {
                javax.swing.JOptionPane.showMessageDialog(jPanelMainCenter,
                    "A function cannot be called from a lower or equal level function.\nReselect other tasks or functions.",
                    "Function Call Invalid",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
                DirectionsLabel.setText(readyPrompt);    
            }
            else if(current_auxiliary instanceof monitorEvent && ((monitorEvent)current_auxiliary).getLevel() <= tmp_func.getLevel())
            {
                javax.swing.JOptionPane.showMessageDialog(jPanelMainCenter,
                        "A function cannot be called from a lower or equal level event monitor.\n",
                        "Function Call Invalid",
                        javax.swing.JOptionPane.WARNING_MESSAGE);
                DirectionsLabel.setText(readyPrompt);
            }
            else
            {                
                func.setNumId(tmp_func.getNumId());
                func.setName(tmp_func.getName());
                func.setFunction(tmp_func);

                func.setImage();

                // prompt the user to input the value for arguments in the function call

                java.util.Vector<Operand> ret_val;
                if(tmp_func.getArguments().size() > 0)
                {
                    FunctionArgumentDialog fad = new FunctionArgumentDialog(this, tmp_func, getCurrentAuxiliary(), null);
                    fad.pack();
                    fad.setLocationRelativeTo(this);
                    fad.setVisible(true);

                    ret_val = fad.getUserInput();
                }
                else
                {
                    ret_val = new java.util.Vector<Operand>();
                }


                if(ret_val != null)  // user did not press cancel
                {
                    // set the arguments in the functionIcon first
                    func.setArguments(ret_val);

                    DirectionsLabel.setText("Click on the programming window to position the icon");
                    jPanel7offListeners();

                    jProgrammingWindow.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt2) {

                            jPanel7MouseClicked2(evt2, func, 1);

                            // reset the listeners
                            jPanel7onListeners();
                        }
                    });
                }    
            }
        }                
    }
    
    
    // the method handles for the insertion of an object or branch icon
    private void iconMouseClicked(java.awt.event.MouseEvent evt, Icon icon, int type) {  
        if(evt.getButton() != 3)  // not a right click, right click is for help pop up menu
        {        
            auxiliary current_auxiliary = getCurrentAuxiliary();
            if(current_auxiliary instanceof monitorEvent && icon instanceof startEvent)
            {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "An event cannot call another event.\nOnly insert an event call inside a task or a function.",
                    "Error - Nested Events",
                    javax.swing.JOptionPane.WARNING_MESSAGE);                    
            }
            else if(icon instanceof BarithmeticIcon)
            {
                if(type == 1)
                {
                    ArithComparatorDialog ACD = new ArithComparatorDialog(this, getCurrentAuxiliary(), null);
                    ACD.pack();
                    ACD.setLocationRelativeTo(this);
                    ACD.setVisible(true);

                    NestedOperation res = ACD.getExpression();
                    if(res != null)  // user didn't press cancel
                    {
                        BarithmeticIcon tmp_icon = (BarithmeticIcon) icon;
                        tmp_icon.setExpression(res);
                        prepIconHandlers(icon, type);
                    }    
                }
                else
                {
                    prepIconHandlers(icon, type);
                }
            }
            else
            {
                prepIconHandlers(icon, type);
            }
        }
    }
    
    // the method handles for the insertion of a loop icon
    private void iconLoopMouseClicked(java.awt.event.MouseEvent evt, Icon icon, int type) {  
        if(evt.getButton() != 3)  // not a right click, right click is for help pop up menu
        { 
            if(icon instanceof arithmeticLoopIcon)
            {
                if(type == 1)
                {
                    ArithComparatorDialog ACD = new ArithComparatorDialog(this, getCurrentAuxiliary(), null);
                    ACD.pack();
                    ACD.setLocationRelativeTo(this);
                    ACD.setVisible(true);

                    NestedOperation res = ACD.getExpression();
                    if(res != null)  // user didn't press cancel
                    {
                        arithmeticLoopIcon tmp_icon = (arithmeticLoopIcon) icon;
                        tmp_icon.setExpression(res);
                        prepLoopIconHandlers(icon, type);
                    }    
                }
                else
                {
                    prepLoopIconHandlers(icon, type);
                }                        
            }
            else
            {
                prepLoopIconHandlers(icon, type);     
            }     
        }
    }
            
    // The following method open up a file chooser for the user to load a specific file    
    // THE METHOD to finish up a load operation by adding icons onto programming window as indicated by a string 
    // representation of an fUNSoftWare save file
    // The initial checking of the file header has been done in the function that called this function
    private void executeLoad(String code)
    {        
      //  java.util.StringTokenizer st = new java.util.StringTokenizer(code);
       // String line  = new String();
        int error = 0;  // no error encountered in reading the file if = 0, if error = 1, there is a syntax error in the save file                        
        
        if(code.indexOf("start") == -1 || code.indexOf("endprogram") == -1) // if test is passed then introduce begin and end icon
        {
            javax.swing.JOptionPane.showMessageDialog(jPanelMainCenter,
                                        "Error encountered while reading the file,\nplease reselect the file",
                                        "Error in file reading",
                                        javax.swing.JOptionPane.WARNING_MESSAGE);
        }
        else
        {
            // restart
            restartProgWindow(1);

            // now skip until the first member of this start icon
            code = code.substring(code.indexOf("start"));
            code = code.substring(code.indexOf("\n")+1);

            error = addOntoProgWindow(code);
         
            task tmp = (task)(aux_list.Instance().getAuxiliaries().get(1));
            setProgrammingWindow(tmp.getPanel(), tmp.getScroller(), tmp.getProgWindow());

            radiobuttons rb = radiobuttons.Instance();
            javax.swing.ButtonGroup group = rb.getButtonGroup();
            tmp.getTaskWindow().getTabbedWinMan().displayPanel("1");
            group.getElements().nextElement().setSelected(true);
            ((javax.swing.JRadioButton)jPanelTask.getComponent(0)).setSelected(true);
            repaint();
            validate();

            if(error == 1)
            {
                javax.swing.JOptionPane.showMessageDialog(jPanelMainCenter,
                    "Error encountered while reading the file,\nplease reselect the file",
                    "Error in file reading",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
                // reset all variables
                restartProgWindow(1);
            }
        }
    }
    
    
    // the method that automatically adds the icons and wires onto the programming window
    // in accordance to a save file, the second argument is the icon to be used as the right neighbour of the
    // latest inserted icon in the reverse translation process
    private int addOntoProgWindow(String code)
    {
        int error = 0;
        String line = new String();  // to read per line
        String argument = new String();  // to be passed along to read a chunk of code (for branches and loops)      
        
        // now code will contain only the elements enclosed by the GREEN icon
        if(code.indexOf("endprogram") == 0) // no elements enclosed by these two RED and GREEN icons
        {
            return 0;
        }        
        // get the tasks and functions
        java.util.Vector<String> ss = Translator.getTFTags(code.substring(0, code.indexOf("endprogram")-1));
        
        if(ss == null)
        {
            return 1;  // error encountered
        }

        for(int i=0; i<ss.size(); i++)
        {
            line = ss.get(i); 
            int check = Translator.checkTFTag(line);
            if(check == 0)
            {
                return 1;
            }
            else if(check == 1)
            {
                error = loadFunctionDefinition(line);
            }
            else if(check == 2)
            {
                error = loadTaskDefinition(line); 
            }
            else if(check == 3)
            {
                error = loadGlobalVarDefinition(line);
            }
            else if(check == 4)
            {
                error = loadEventDefinition(line);
            }        
            else if(check == 5)
            {
                error = loadEventMonitorDefinition(line);
            }  
        }

        // then go back and link every task and function icons back to its parents programming window as well as
        // setting their vital attributes
        
        java.util.Vector<Icon> icl = icons_list.Instance().getIcons();
        for(int i=1; i<icl.size(); i++)
        {
            Icon ico = icl.get(i);
            if(ico != null && ico instanceof functionIcon)
            {
                function f = (function)aux_list.Instance().getAuxiliary(ico.getName());
                ico.setFunction(f);
            }
            else if(ico != null && (ico instanceof beginTaskIcon || ico instanceof stopTaskIcon))
            {
                task t = (task)aux_list.Instance().getAuxiliary(ico.getName());
                ico.setTask(t);                
            }
            else if(ico != null && (ico instanceof startEvent))
            {
                monitorEvent t = (monitorEvent)aux_list.Instance().getAuxiliary(ico.getName());
                ico.setEventMonitor(t);                
            }            
        }        
        
        // done restoring
        return error;
    }
    
    // to add an icon based a description on a save file, also passes an auxiliary that this icon belongs to
    private int loadIcon(String line, Icon tic, auxiliary a)
    {
        int error = 0;
        
        Icon icon = Translator.getIcon(line, a); 
        if(icon == null)
        {      
            return 1;   // error encountered
        }
        else
        {           
            Icon tic2 = tic.getLeftNeighbour();  // left of icon

            // instead has to find a mid point between tic and tic2

            int xpos = 0;
            if(tic2 != null && tic != null)
            {
                xpos = gridcalculation.midPoint(tic2.getCoordinate(), tic.getCoordinate());
            }

            int ypos = gridcalculation.calculateY(tic.getCoordinate().getY()+(tic.getHeight()/2));
            coord tmp_co = new coord(xpos, ypos);
            coord co = gridcalculation.computeCoord(icon, tmp_co);
            icon.setCoordinate(co);

            insertObjectIcon(icon, tic, tic2);         
        }
                
        repaint();
        validate();
        return error;
    }
    
    
    /**
     * A method that loads a function icon without its definition
     */
    private int loadFunctionIcon(String line, Icon tic, auxiliary a)
    {
        int error = 0;
        
        Icon icon = Translator.getIcon(line, a); 
        if(icon == null)
        {      
            return 1;   // error encountered
        }
        else
        {           
            Icon tic2 = tic.getLeftNeighbour();  // left of icon

            // instead has to find a mid point between tic and tic2

            int xpos = 0;
            if(tic2 != null && tic != null)
            {
                xpos = gridcalculation.midPoint(tic2.getCoordinate(), tic.getCoordinate());
            }

            int ypos = gridcalculation.calculateY(tic.getCoordinate().getY()+(tic.getHeight()/2));
            coord tmp_co = new coord(xpos, ypos);
            coord co = gridcalculation.computeCoord(icon, tmp_co);
            icon.setCoordinate(co);

            insertObjectIcon(icon, tic, tic2);
        }
                
        repaint();
        validate();
        return error;
    }   
    
    /**
     * A method that defines a global varible with its name and its initial value
     * @param code the piece of algorithm string
     * @return error If equals zero then no error was encountered
     */
    private int loadGlobalVarDefinition(String code)
    {
        int error = 0;            
        variable var = _Var.Clone();
        Translator.setVarAttributes(var, code);

        var_list.Instance().addVariable(var);
        jPanelVar.insertVar(var);            
        return error;
    }   
    
    /**
     * A method that defines a local varible with its name and its initial value
     * @param code the piece of algorithm string
     * @return error If equals zero then no error was encountered
     */
    private int loadLocalVarDefinition(String code, auxiliary a)
    {
        int error = 0;
        variable var = _Var.Clone();
        Translator.setVarAttributes(var, code);

        // decide whether to insert into the panel
        if(a.getNumId() == 1)  // only the first panel is allowed because initially the first panel is displayed
        {
            jPanelVar.insertVar(var);
        }
        var.setAux(a);
        a.addVariable(var);        
        
        if(var.getType() == 2 && a instanceof function)
        {
            function f = (function) a;
            f.addArgument(var);
        }
        
        return error;
    }    
        
    private int loadEventMonitorDefinition(String code)
    {        
        int error = 0;
        
        String line = new String();  // to read per line
        String argument = new String();  // to be passed along to read a chunk of code (for branches and loops)       
        
        String beginTag = Translator.getEventMonitorTag(code);
        String endTag = Translator.getEndTag(code);
                
        java.util.Vector<String> eventAtt = Translator.getEventMonitorAtt(code.substring(0, code.indexOf("\n")));
        String event_name = Translator.getEventMonitorName(code);
        code = code.substring(code.indexOf(beginTag)+beginTag.length()+1);
        
        // before continuing, gotta initialise the event monitor's attributes first
        monitorEvent evt = _mEvent.Clone();
        aux_list.Instance().addAuxiliary(evt);
        
        evt.setName(event_name);
        evt.setLevel(Integer.parseInt(eventAtt.get(2)));
        auxiliary to_be_passed;
        if(eventAtt.get(3).compareTo(".") == 0)
        {
            to_be_passed = null;
        }
        else
        {
            to_be_passed = aux_list.Instance().getAuxiliary(eventAtt.get(3));
        }       
        
        evt.setEventElements(eventAtt.get(1), to_be_passed);       
        
        java.util.Vector<Event> monitored = new java.util.Vector<Event>();
        for(int i=4; i<eventAtt.size(); i++)
        {
            monitored.addElement(event_number_tracker.Instance().getEvent(eventAtt.get(i)));  
        }
        evt.setMonitoredEvents(monitored);        
        
        
        jPanelEventMonitor.insertEvent(evt);  
        aux_list al = aux_list.Instance();
        monitorEvent tmp_evt = (monitorEvent)al.getAuxiliary(event_name);
        Icon tic = tmp_evt.getEndIcon();
            
        if(code.indexOf(endTag) > 0)
        {
            code = code.substring(0, code.indexOf(endTag)-1);

            java.util.Vector<String> ss = Translator.getOBLTags(code);

            jProgrammingWindow = evt.getProgWindow();
            jScrollPane1 = evt.getScroller();
//            jPanel18 = evt.getPanel();        

            if(ss == null)
            {
                return 1;  // error encountered
            }
            for(int i=0; i<ss.size(); i++)
            {
                line = ss.get(i); 
                int check = Translator.checkOBLTag(line);
                if(check == 0)
                {
                    return 1;
                }
                else if(check == 1)
                {
                    error = loadIcon(line, tic, evt); 
                }
                else if(check == 2)
                {
                    error = recursiveLoadBranch(line, tic, evt);
                }
                else if(check == 3)
                {
                    error = recursiveLoadLoop(line, tic, evt);
                }
                else if(check == 4)
                {
                    error = loadFunctionIcon(line, tic, evt);
                }
                else if(check == 5)
                {
                    error = loadLocalVarDefinition(line, evt);
                }
            }           
        }        
        return error;
    }
    
    /**
     * A method that recursively defines a event
     * @param code the piece of algorithm string
     * @return error If equals zero then no error was encountered
     */
    private int loadEventDefinition(String code)
    {
        int error = 0;
        
        String line = new String();  // to read per line
        String argument = new String();  // to be passed along to read a chunk of code (for branches and loops)       

        String[] eventAtt = Translator.getEventAtt(code);

        // before continuing, gotta initialise the event's attributes first
        Event evt = _Event.Clone();
        event_number_tracker.Instance().addEvent(evt);
        evt.setName(eventAtt[0]);
        
        Operand threshold_to_pass = new Operand();
        
        if(eventAtt[4].compareTo(".") == 0)
        {
            threshold_to_pass = null;
        }
        else
        {
            String first_char = eventAtt[4].substring(0,1);
            String op_att = eventAtt[4].substring(1); 
            if(first_char.compareTo("c") == 0)   // constant
            {
                constant c = new constant();
                c.setValue(Integer.parseInt(eventAtt[5]));
                threshold_to_pass = c;                
            }
        }        
        
        evt.setEventElements(eventAtt[1], eventAtt[2], eventAtt[3], threshold_to_pass);       
        
                
        jPanelEvent.insertEvent(evt);          
        return error;
    } 
    
    
    /**
     * A method that recursively defines a task
     * @param code the piece of algorithm string
     * @return error If equals zero then no error was encountered
     */
    private int loadTaskDefinition(String code)
    {
        int error = 0;
        String line = new String();  // to read per line     

        // get the tasks and functions
                
        String beginTag = Translator.getTaskTag(code);
        String endTag = Translator.getEndTag(code);
                
        String task_name = Translator.getTaskName(code);
        code = code.substring(code.indexOf(beginTag)+beginTag.length()+1);

        // before continuing, gotta initialise the task's attributes first
        task tsk = _Task.Clone();
        aux_list.Instance().addAuxiliary(tsk);
        tsk.setName(task_name);
                
        jPanelTask.insertTask(tsk);  
        aux_list al = aux_list.Instance();
        task tmp_tsk = (task)al.getAuxiliary(task_name);
        Icon tic = tmp_tsk.getEndIcon();
            
        if(code.indexOf(endTag) > 0)
        {
            code = code.substring(0, code.indexOf(endTag)-1);

            java.util.Vector<String> ss = Translator.getOBLTags(code);

            jProgrammingWindow = tmp_tsk.getProgWindow();
            jScrollPane1 = tmp_tsk.getScroller();
//            jPanel18 = tmp_tsk.getPanel();        

            if(ss == null)
            {
                return 1;  // error encountered
            }
            for(int i=0; i<ss.size(); i++)
            {
                line = ss.get(i); 
                int check = Translator.checkOBLTag(line);
                if(check == 0)
                {
                    return 1;
                }
                else if(check == 1)
                {
                    error = loadIcon(line, tic, tsk); 
                }
                else if(check == 2)
                {
                    error = recursiveLoadBranch(line, tic, tsk);
                }
                else if(check == 3)
                {
                    error = recursiveLoadLoop(line, tic, tsk);
                }
                else if(check == 4)
                {
                    error = loadFunctionIcon(line, tic, tsk);
                }
                else if(check == 5)
                {
                    error = loadLocalVarDefinition(line, tsk);
                }
            }            
        }
        return error;
    }
    
    
    /**
     * A method that recursively defines a function
     * @param code The piece of algorithm string
     * @return error If equals zero then no error was encountered
     */
    private int loadFunctionDefinition(String code)
    {
        int error = 0;
        String line = new String();  // to read per line     

        // get the tasks and functions
                
        String beginTag = Translator.getFunctionTag(code);
        String endTag = Translator.getEndTag(code);
                
        String[] function_att = Translator.getFunctionAtt(code);
        code = code.substring(code.indexOf(beginTag)+beginTag.length()+1);
    
        function fu = _Funct.Clone();
        aux_list al = aux_list.Instance();
        al.addAuxiliary(fu);

        fu.setName(function_att[0]);
        fu.setLevel(Integer.parseInt(function_att[1]));

        jPanelFunction.insertFunction(fu);                    

        FunctionCombo FunctionComboBox = FunctionCombo.Instance(); 
        javax.swing.JMenuItem jmi = new javax.swing.JMenuItem(fu.getName());
        jmi.setActionCommand(Integer.toString(fu.getNumId()));
        FunctionComboBox.addItem(jmi);
            
        if(code.indexOf(endTag) > 0)
        {
            code = code.substring(0, code.indexOf(endTag)-1);

            java.util.Vector<String> ss = Translator.getOBLTags(code);

            // before continuing, gotta initialise the function's attributes first

            function tmp_func = (function)al.getAuxiliary(function_att[0]);
            Icon tic = tmp_func.getEndIcon();
            jProgrammingWindow = tmp_func.getProgWindow();
            jScrollPane1 = tmp_func.getScroller();
//            jPanel18 = tmp_func.getPanel();  

            if(ss == null)
            {
                return 1;  // error encountered
            }
            for(int i=0; i<ss.size(); i++)
            {
                line = ss.get(i); 
                int check = Translator.checkOBLTag(line);
                if(check == 0)
                {
                    return 1;
                }
                else if(check == 1)
                {
                    error = loadIcon(line, tic, fu); 
                }
                else if(check == 2)
                {
                    error = recursiveLoadBranch(line, tic, fu);
                }
                else if(check == 3)
                {
                    error = recursiveLoadLoop(line, tic, fu);
                }
                else if(check == 4)
                {
                    error = loadFunctionIcon(line, tic, fu);
                }
                else if(check == 5)
                {
                    error = loadLocalVarDefinition(line, fu);
                }
            }
        }
        return error;
    }
    
    // a method that recursively inserts a branch and its members in load operation
    // by this time, the branch has already been inserted
    private int recursiveLoadBranch(String code, Icon tic, auxiliary a)
    {
        int error = 0;   // initially no error, if error --> error = 1
        String if_tag = Translator.getIfTag(code);
        String endif_tag = Translator.getEndIfTag(code);
        String else_tag = Translator.getElseTag(code);
        String endelse_tag = Translator.getEndElseTag(code);
        String if_line = new String();
        String else_line = new String();
        if(if_tag == null || endif_tag == null || else_tag == null || endelse_tag == null)
        {
            return 1;  // error encountered
        }
        
        // insert the branch icon
        Icon icon = Translator.getIcon(code, a);
        Icon tic2 = tic.getLeftNeighbour();  // left of icon

        // instead has to find a mid point between tic and tic2

        int xpos = 0;
        if(tic2 != null && tic != null)
        {
            xpos = gridcalculation.midPoint(tic2.getCoordinate(), tic.getCoordinate());
        }

        int ypos = gridcalculation.calculateY(tic.getCoordinate().getY()+(tic.getHeight()/2));
        coord tmp_co = new coord(xpos, ypos);
        coord co = gridcalculation.computeCoord(icon, tmp_co);
        icon.setCoordinate(co);
        insertBranchIcon(icon, tic, tic2);
        
        java.util.Vector<String> ss = new java.util.Vector<String>();
        String line = new String(); 
        
        // do the if
                
        if_line = code.substring(code.indexOf(if_tag)); 
        code = code.substring(code.indexOf(if_tag));  
        
        if(if_tag.length()+1 < code.indexOf(endif_tag)-1)
        {
            if_line = code.substring(if_tag.length()+1, code.indexOf(endif_tag)-1);
            ss = Translator.getOBLTags(if_line);
            if(ss == null)
            {
                return 1;  // error encountered
            }

            for(int i=0; i<ss.size(); i++)
            {
                line = ss.get(i);
                int check = Translator.checkOBLTag(line);
                if(check == 0)
                {
                    return 1;
                }
                else if(check == 1)
                {
                    error = loadIcon(line, icon.getEndBranch().getLeftNeighbourTop(), a);
                }
                else if(check == 2)
                {
                    error = recursiveLoadBranch(line, icon.getEndBranch().getLeftNeighbourTop(), a);
                }
                else if(check == 3)
                {
                    error = recursiveLoadLoop(line, icon.getEndBranch().getLeftNeighbourTop(), a);
                }
                else if(check == 4)
                {
                    error = loadFunctionIcon(line, icon.getEndBranch().getLeftNeighbourTop(), a);
                }
            }
        }
                
        // do the else
        
        else_line = code.substring(code.indexOf(else_tag)); 
        code = code.substring(code.indexOf(else_tag));
        
        if(else_tag.length()+1 < code.indexOf(endelse_tag)-1)
        {
            else_line = code.substring(else_tag.length()+1, code.indexOf(endelse_tag)-1);
            ss = Translator.getOBLTags(else_line);
            if(ss == null)
            {
                return 1;  // error encountered
            }
            for(int i=0; i<ss.size(); i++)
            {
                line = ss.get(i);
                int check = Translator.checkOBLTag(line);
                if(check == 0)
                {
                    return 1;
                }
                else if(check == 1)
                {
                    error = loadIcon(line, icon.getEndBranch().getLeftNeighbourBottom(), a);
                }
                else if(check == 2)
                {
                    error = recursiveLoadBranch(line, icon.getEndBranch().getLeftNeighbourBottom(), a);
                }
                else if(check == 3)
                {
                    error = recursiveLoadLoop(line, icon.getEndBranch().getLeftNeighbourBottom(), a);
                }
                else if(check == 4)
                {
                    error = loadFunctionIcon(line, icon.getEndBranch().getLeftNeighbourBottom(), a);
                }                
            }
        }                
        return error;
    }
    
    // the method to recursively inserts a loop and its members in load operation
    // by this time, the loop has already been inserted
    private int recursiveLoadLoop(String code, Icon tic, auxiliary a)
    {
        int error = 0;  // initially no error
        String begin_tag = Translator.getBeginLoopTag(code); 
        String end_tag = Translator.getEndTag(code);
        String loop_line = new String();
        if(end_tag == null)
        {
            return 1;  // error encountered
        }
        
        // insert the branch icon
        Icon icon = Translator.getIcon(code, a);
        Icon tic2 = tic.getLeftNeighbour();  // left of icon

        // instead has to find a mid point between tic and tic2

        int xpos = 0;
        if(tic2 != null && tic != null)
        {
            xpos = gridcalculation.midPoint(tic2.getCoordinate(), tic.getCoordinate());
        }

        int ypos = gridcalculation.calculateY(tic.getCoordinate().getY()+(tic.getHeight()/2));
        coord tmp_co = new coord(xpos, ypos);
        coord co = gridcalculation.computeCoord(icon, tmp_co);
        icon.setCoordinate(co);
        insertLoopIcon(icon, tic, tic2, tic, tic2);
        
        java.util.Vector<String> ss = new java.util.Vector<String>();
        String line = new String(); 
        
        // do the if
                
        loop_line = code.substring(code.indexOf(begin_tag)); 
        code = code.substring(code.indexOf(begin_tag));  
        
        if(begin_tag.length()+1 < code.indexOf(end_tag)-1)
        {
            loop_line = code.substring(begin_tag.length()+1, code.indexOf(end_tag)-1);
            ss = Translator.getOBLTags(loop_line);
            if(ss == null)
            {
                return 1;  // error encountered
            }
            
            for(int i=0; i<ss.size(); i++)
            {
                line = ss.get(i);
                int check = Translator.checkOBLTag(line);
                if(check == 0)
                {
                    return 1;
                }
                else if(check == 1)
                {
                    error = loadIcon(line, icon.getEndIcon(), a);
                }
                else if(check == 2)
                {
                    error = recursiveLoadBranch(line, icon.getEndIcon(), a);
                }
                else if(check == 3)
                {
                    error = recursiveLoadLoop(line, icon.getEndIcon(), a);
                }
                else if(check == 4)
                {
                    error = loadFunctionIcon(line, icon.getEndIcon(), a);
                }
            }
        }
        return error;
    }
    
    // This method cancels an inserting operation of the icon onto the programming window
    // Keeping in mind that inserting an icon onto the programming window
    // requires the user to click on the icon, then click again on the programming window on the location to place the icon
    // The user can cancel this after clicking on the icon, causing the next click on the programming window to be
    // ignored by the program

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        javax.swing.JOptionPane.showMessageDialog(jPanelMainCenter,
                                    "Operation cancelled",
                                    "Operation Cancelled",
                                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
        DirectionsLabel.setText(readyPrompt);
        jPanel7onListeners();
    }//GEN-LAST:event_jButton5ActionPerformed
     
    
    // should the button1 - wheel button is clicked, then the mouse listeners of jProgrammingWindow would be changed
    // until the user select where to place the wheel icon on the programming panel, the panel can only
    // listen to one kind of mouse event, that is mouse clicked to position this new icon
    // once the new icon has been placed, only then the mouse listeners of jProgrammingWindow would be restored to only
    // accepting click on itself without the need of preceeding click on any of the icon buttons
    // this is handler for backward rotating motor    
    // this method paints the grid for the programming window
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if(Constants.getGridStatus() == 0)
        {
            Constants.setGridStatus(1);   // 1 represent grid is set
            jButtonGridMode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/other/freeplace.gif")));
        }
        else if(Constants.getGridStatus() == 1)
        {
            Constants.setGridStatus(0);   // 0 represent grid is not set
            jButtonGridMode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/other/gridmode.gif")));
        }
        
        jProgrammingWindow.repaint();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jRadioButtonMenuItemRCX2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItemRCX2ActionPerformed
        updateTargetDevice();
    }//GEN-LAST:event_jRadioButtonMenuItemRCX2ActionPerformed

    private void jRadioButtonMenuItemRCX1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItemRCX1ActionPerformed
        updateTargetDevice();
    }//GEN-LAST:event_jRadioButtonMenuItemRCX1ActionPerformed
       

    // method to save file
    private int execute_save()
    {
        int ret_val = jFileChooser1.showSaveDialog(jDialog1); 
        if (ret_val == javax.swing.JFileChooser.APPROVE_OPTION) {
            // the saving process
            // firstly get the text representation of these icons and wires and structures
            jProgrammingWindow.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
            String code = Translator.getAlgorithm(icon_list.getIcons().get(1));
            
            java.io.File file = jFileChooser1.getSelectedFile();

            try 
	    {
                java.io.FileWriter filewriter = new java.io.FileWriter(file);	    
                filewriter.write(code, 0, code.length());
	    	filewriter.close();
                do_state.getCurrentState().setSaveStatus(1);
                do_state.getCurrentState().setSaveFilePath(file.getAbsolutePath());
                do_state.getCurrentState().setSaveFileName(file.getName());
                _Current_Save_Path = file.getAbsolutePath();
                updateFileName(file.getName());
                
            } 
            catch (Exception e) 
            {
                System.out.println("Error: " + e.getMessage());
            }      
            ret_val = 1; // file saved
        }
        else
        {
            ret_val = 0;
        }
        jProgrammingWindow.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        return ret_val;
    }
    
    
    /**
     * The main method, SHOULD NOT BE MODIFIED
     * @param args The command line arguments
     */
    public static void main(String args[]) {
        //java.awt.EventQueue.invokeLater(new Splash("bobo"));
      //  java.awt.EventQueue.invokeLater(new Runnable() {
        try{
            javax.swing.SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    new window().setVisible(true);
                }
            });
        } catch (InterruptedException e) {
            // Ignore: If this exception occurs, we return too early, which
            // makes the splash window go away too early.
            // Nothing to worry about. Maybe we should write a log message.
        } catch (java.lang.reflect.InvocationTargetException e) {
            // Error: Startup has failed badly. 
            // We can not continue running our application.
            InternalError error = new InternalError();
            error.initCause(e);
            throw error;
        }
    }
    

    // this is to handle a special case to click and drop an icon onto the programming window
    // this will be the second click of the mouse to decide where on the programming window the icon will be
    // placed
    // AS WELL AS CHECKING WHICH TYPE OF ICON BEING INSERTED, ALSO NEED TO CHECK WHERE THE ICON IS BEING INSERTED
    // 1. Is it just between a pair of icons?
    // 2. Is it inside a branching structure?
    // 3. Is it inside a looping structure?
    // if type == 1 then prompt for task, type == 0 means copy
    private void jPanel7MouseClicked2(java.awt.event.MouseEvent evt, Icon icon, int type) {   
        
        Icon tic = jProgrammingWindow.findIcon(evt.getX(), evt.getY(), 1);   // right of icon
        Icon tic2 = jProgrammingWindow.findIcon(evt.getX(), evt.getY(), 0);  // left of icon
        
        // instead has to find a mid point between tic and tic2
        
        int xpos = 0;
        if(tic2 != null && tic != null)
        {
            xpos = gridcalculation.midPoint(tic2.getCoordinate(), tic.getCoordinate());
        }
        
        int ypos = gridcalculation.calculateY(evt.getY());
        coord tmp_co = new coord(xpos, ypos);
        coord co = gridcalculation.computeCoord(icon, tmp_co);
        icon.setCoordinate(co);
        
        if(co.getX() < (jProgrammingWindow.getStartIcon().getCoordinate().getX()+jProgrammingWindow.getStartIcon().getWidth()/2) ||
           co.getX() > (jProgrammingWindow.getEndIcon().getCoordinate().getX()+jProgrammingWindow.getEndIcon().getWidth()/2) ||
           tic == null || tic2 == null || tic instanceof joinEndBranchIcon)
        {
            // if the desired place for the new icon is taken by another icon, make sure
            // the new icon could not be to the left of the starting icon or to the right of the
            // ending icon
            
            javax.swing.JOptionPane.showMessageDialog(jPanelMainCenter,
                                    "Insertion invalid, please reselect a position",
                                    "Insertion Invalid",
                                    javax.swing.JOptionPane.WARNING_MESSAGE);
            DirectionsLabel.setText(readyPrompt);
            if(icon instanceof loopIcon)
            {
                jPanel7onListeners();
            }
        }
        else   
        {   
            // three cases of icon insertion
            // 1. if it is a branching icon
            // 2. if it is a loop icon
            // 3. if it is everything else
            if(icon instanceof branchIcon)
            {
                insertBranchIcon(icon, tic, tic2);
                UndoRedo.update_state();
            }
            else if(icon instanceof loopIcon)
            {        
                   DirectionsLabel.setText("Click on the programming window to position the end of the loop");
                   final int xx = evt.getX();
                   final int yy = evt.getY();
                   final Icon lic = icon;
                   
                   java.awt.event.MouseListener[] ml = jProgrammingWindow.getMouseListeners();
            
                   jProgrammingWindow.removeMouseListener(ml[0]);
                    
                   jProgrammingWindow.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt2) {
                            
                            // the handler for this repeat icon
                            jPanel7MouseClicked4(xx, yy, evt2, lic);

                            // reset the listeners
                            jPanel7onListeners();
                            }
                    });                    
            }
            else
            {
                if(icon instanceof beginTaskIcon || icon instanceof stopTaskIcon)
                {
                    if(type == 1)
                    {
                        int ret_val = promptTask(icon);
                        if(ret_val == 1)
                        {
                            insertObjectIcon(icon, tic, tic2);
                            UndoRedo.update_state();
                        }    
                    }   
                    else
                    {
                        insertObjectIcon(icon, tic, tic2);
                        UndoRedo.update_state();
                    }
                                        
                }
                else if(icon instanceof startEvent)
                {
                    if(type == 1)
                    {
                        int ret_val = promptEvent(icon);
                        if(ret_val == 1)
                        {
                            insertObjectIcon(icon, tic, tic2);
                            UndoRedo.update_state();
                        }    
                    }   
                    else
                    {
                        insertObjectIcon(icon, tic, tic2);
                        UndoRedo.update_state();
                    }                                         
                }
                else
                {
                    insertObjectIcon(icon, tic, tic2);
                    UndoRedo.update_state();
                }                
            }                    
            repaint();
            validate();
        }  
    } 
        
    // handler for object icon replacements
    private void objectIconReplaceClicked(java.awt.event.MouseEvent evt, Icon icon, Icon parent)
    {
        parent.getComponentPopupMenu().setVisible(false);

        if(icon instanceof beginTaskIcon || icon instanceof stopTaskIcon)
        {
            int ret_val = promptTask(icon);
            if(ret_val == 1) 
            {
                icon.setImage();
                put_object_icon(icon, parent);
                object_init_replace_listener(icon);
                jPanel7onListeners();  
            }                    
        }
        else if(icon instanceof startEvent)
        {
            if(getCurrentAuxiliary() instanceof monitorEvent)
            {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "An event cannot call another event.\nOnly insert an event call inside a task or a function.",
                    "Error - Nested Events",
                    javax.swing.JOptionPane.WARNING_MESSAGE);                    
            }
            else
            {
                int ret_val = promptEvent(icon);
                if(ret_val == 1) 
                {
                    icon.setImage();
                    put_object_icon(icon, parent);
                    object_init_replace_listener(icon);
                    jPanel7onListeners();  
                }                      
            }                  
        }
        else
        {
            put_object_icon(icon, parent);
            object_init_replace_listener(icon);
            jPanel7onListeners();                    
        } 
    }      
        
        
    // handler for icon relocation, this method would then ask for a click on the programming window
    // to determine where to put the old icon
    private void relocateIconPerformed(java.awt.event.ActionEvent evt, Icon parent)
    {
        parent.getComponentPopupMenu().setVisible(false);
        jPanel7offListeners();

        DirectionsLabel.setText("Now click where you want to relocate the icon");
        final Icon icon = parent;
        jProgrammingWindow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt2) {

                // the handler for this repeat icon
                jPanel7MouseClicked5(evt2, icon);

                // reset the listeners
                jPanel7onListeners();
            }
        });     
    }
        
        
    // handler for icon deletion, this method would delete the specified icon
    // the third argument is for a special case, if type == 1 --> delete a repeat icon with all of its members
    // type == 2 --> delete a branch icon and keep the top (IF) part
    // type == 3 --> delete a branch icon and keep the bottom (ELSE) part
    private void deleteIconPerformed(java.awt.event.ActionEvent evt, Icon parent, int type)
    {
        parent.getComponentPopupMenu().setVisible(false);
        if(type == 0)
        {
            deleteIcon(parent, parent.getLeftNeighbour().getCoordinate().getX(), parent.getLeftNeighbour().getCoordinate().getY());
        }
        else if(type == 1)
        {
            deleteRepeatAll(parent, parent.getEndIcon().getLeftNeighbour().getCoordinate().getX(), 
                            parent.getEndIcon().getLeftNeighbour().getCoordinate().getY());
        }      
        else if(type == 2)
        {
            deleteBranchKeepIf(parent, parent.getLeftNeighbour().getCoordinate().getX(), parent.getLeftNeighbour().getCoordinate().getY());
        }
        else if(type == 3)
        {
            deleteBranchKeepElse(parent, parent.getLeftNeighbour().getCoordinate().getX(), parent.getLeftNeighbour().getCoordinate().getY());
        }
        UndoRedo.update_state();       
    }
        
        
    // to copy an icon with all of its attributes set to the previous icons
    private void copyIconPerformed(Icon parent)
    {
        Icon new_icon = parent.Copy();
        if(new_icon instanceof loopIcon)
        {
            prepLoopIconHandlers(new_icon, 0);    
        }
        else
        {
            prepIconHandlers(new_icon, 0);    
        }
    }
        
    // to edit an existing arithmetic operation
    private void arithOpEditPerformed(Icon icon)
    {
        ArithOpIcon AOI = (ArithOpIcon) icon;
        ArithmeticOperation AO = AOI.getAO();
        java.util.Vector<variable> vr = new java.util.Vector<variable>();
        if(getCurrentAuxiliary() instanceof function)
        {
            function f =  (function)getCurrentAuxiliary();
            vr = f.getVariables();
        }
        else if(getCurrentAuxiliary() instanceof task)
        {
            task t =  (task)getCurrentAuxiliary();
            vr = t.getVariables();      
        }
        else if(getCurrentAuxiliary() instanceof monitorEvent)
        {
            monitorEvent et =  (monitorEvent)getCurrentAuxiliary();
            if(et.getAuxPoint() != null)
            {
                vr = et.getAuxPoint().getVariables();      
            }                    
        }            

        if(var_list.Instance().getVariables().size() == 4 && vr.size() == 0)
        {
                javax.swing.JOptionPane.showMessageDialog(this,
                                        "There has to be at least 1 non input sensor local or global variable.\nPlease declare one.",
                                        "Error - No Variable",
                                        javax.swing.JOptionPane.WARNING_MESSAGE);
        }
        else
        {
            VarOperationDialog dv_dialog = new VarOperationDialog(this, AO, getCurrentAuxiliary()); 
            dv_dialog.pack();
            dv_dialog.setLocationRelativeTo(this);
            dv_dialog.setVisible(true);

            ArithmeticOperation ret_val = dv_dialog.getUserInput();
            if(ret_val != null)  // an indication that user did not press cancel
            {
                // create the ArithOpIcon class, then fill in the ArithmeticOperation class that it will point at   
                AOI.setAO(ret_val);
                UndoRedo.update_state();
            }                
        }            
    }        
        
        
    // to edit the input into a function's arguments
    private void editArgPerformed(Icon icon)
    {
        java.util.Vector<Operand> ret_val;
        functionIcon fic = (functionIcon) icon;
        function tmp_func = fic.getFunction();
        if(tmp_func.getArguments().size() > 0)
        {
            FunctionArgumentDialog fad = new FunctionArgumentDialog(this, tmp_func, getCurrentAuxiliary(), fic);
            fad.pack();
            fad.setLocationRelativeTo(this);
            fad.setVisible(true);

            ret_val = fad.getUserInput();
            if(ret_val != null)  // user did not press cancel
            {
                // set the arguments in the functionIcon first
                fic.setArguments(ret_val);
                UndoRedo.update_state();
            }
        }
        else
        {
            ret_val = new java.util.Vector<Operand>();
            javax.swing.JOptionPane.showMessageDialog(this,
                "No arguments can be edited.\nThis function does not accept arguments.",
                "Zero argument",
                javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
    }
        
    // to show the programming window as indicated by the users
    private void showProgWindowPerformed(Icon icon)
    {
        int nid = 1;
//        current_aux = aux_list.Instance().getAuxiliaries().get(nid);
        if(icon instanceof beginTaskIcon)
        {
            nid = ((beginTaskIcon)icon).getNumId();
        }
        else if(icon instanceof stopTaskIcon)
        {
            nid = ((stopTaskIcon)icon).getNumId();
        }
        else if(icon instanceof functionIcon)
        {
            nid = ((functionIcon)icon).getNumId();
        }
        else if(icon instanceof startEvent)
        {
            nid = ((startEvent)icon).getNumId();
        }            

        jPanelFunction.getTabbedWinMan().displayPanel(Integer.toString(nid));
        
        // then have the radio button selected !
        javax.swing.ButtonGroup bg = radiobuttons.Instance().getButtonGroup();
        java.util.Enumeration<javax.swing.AbstractButton> enum_ab = bg.getElements();
        javax.swing.JRadioButton jrb = new javax.swing.JRadioButton();
        while(enum_ab.hasMoreElements())
        {
            jrb = (javax.swing.JRadioButton)enum_ab.nextElement();
            if(jrb.getActionCommand().compareTo(Integer.toString(nid)) == 0)
            {
                jrb.setSelected(true);
             //   break;
            }
        }  
    }       
        
    // to edit an expression of a branch arithmetic or a loop arithmetic icons
    private void expressionEditPerformed(Icon icon)
    {
        NestedOperation res = new NestedOperation();
        if(icon instanceof BarithmeticIcon)
        {
            BarithmeticIcon bai = (BarithmeticIcon)icon;
            res = bai.getExpression();
        }
        else if(icon instanceof arithmeticLoopIcon)
        {
            arithmeticLoopIcon bai = (arithmeticLoopIcon)icon;
            res = bai.getExpression();                
        }

        ArithComparatorDialog ACD = new ArithComparatorDialog(this, getCurrentAuxiliary(), res);
        ACD.pack();
        ACD.setLocationRelativeTo(this);
        ACD.setVisible(true);

        res = ACD.getExpression();
        if(res != null)  // user didn't press cancel
        {
            if(icon instanceof BarithmeticIcon)
            {
                BarithmeticIcon bai = (BarithmeticIcon)icon;
                bai.setExpression(res);
            }
            else if(icon instanceof arithmeticLoopIcon)
            {
                arithmeticLoopIcon bai = (arithmeticLoopIcon)icon;
                bai.setExpression(res);               
            }    
            UndoRedo.update_state();
        }
    }
        
    // for task icons, to remap the task icons related to a task
    private void relinkTask(Icon icon)
    {
        int ret_val = promptTask(icon);
        if(ret_val == 1)
        {
            icon.setImage();
            repaint();
            validate();
            UndoRedo.update_state();
        }
    }
 
    // for event icons, to remap the event icons related to a event
    private void relinkEvent(Icon icon)
    {
        int ret_val = promptEvent(icon);
        if(ret_val == 1)
        {
            icon.setImage();
            repaint();
            validate();
            UndoRedo.update_state();
        }
    }        
        
    // for function icons, to remap the function icons related to a function
    private void relinkFunction(Icon icon)
    {
        int ret_val = promptFunction(icon);
        if(ret_val == 1)
        {
            icon.setImage();
            repaint();
            validate();
            UndoRedo.update_state();
        }
    }        
        
        
    // method called by the object icon replacement handler
    private void put_object_icon(Icon icon, Icon parent)
    {
        // initialise the icon            
        int new_x = parent.getCoordinate().getX() + ((parent.getWidth()/2)-(icon.getWidth()/2));
        int new_y = parent.getCoordinate().getY() + ((parent.getHeight()/2)-(icon.getHeight()/2));
        icon.setCoordinate(new coord(new_x, new_y));
        icon.setBounds(icon.getCoordinate().getX(), icon.getCoordinate().getY(), icon.getPreferredSize().width, icon.getPreferredSize().height);

        // set the parent member relationship and delete the old one
        parent.getParentIcon().delMember(parent);
        jProgrammingWindow.setUpIconParent(icon, parent.getLeftNeighbour(), parent.getRightNeighbour());                  

        // set the wiring
        icon.setLeftWire(parent.getLeftWire());
        icon.setRightWire(parent.getRightWire());
        icon.getLeftWire().setRightIcon(icon);
        icon.getRightWire().setLeftIcon(icon);

        icon.setRightNeighbour(parent.getRightNeighbour());
        icon.getRightNeighbour().setLeftNeighbour(icon);

        icon.setLeftNeighbour(parent.getLeftNeighbour());
        icon.getLeftNeighbour().setRightNeighbour(icon);   
        icon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));  // to change the cursor
        icon.turnonListeners();

        jProgrammingWindow.deleteIcon(parent);
        icon_list.delIcon(parent);
        icon_list.addIcon(icon);
        jProgrammingWindow.addIcon(icon);

        jProgrammingWindow.add(icon);
        jProgrammingWindow.remove(parent);
        jProgrammingWindow.updateAllRepeatNeighbours();
        repaint();
        validate();
        DirectionsLabel.setText("Object icon successfully replaced");
        UndoRedo.update_state();
    }
        
    // the method to initialise a pop up menu of an icon
    private void object_init_replace_listener(Icon icon)
    {
        javax.swing.JPopupMenu jpm = new javax.swing.JPopupMenu();
        javax.swing.JMenuItem jm = new javax.swing.JMenuItem();
        javax.swing.JPanel jp = new javax.swing.JPanel();
        jp.setLayout(new java.awt.GridBagLayout());
        javax.swing.JMenu jm2 = new javax.swing.JMenu();
        javax.swing.JButton jmi = new javax.swing.JButton();

        java.awt.GridBagConstraints gridBagConstraints;
        final Icon parent = icon;

        jm2 = new javax.swing.JMenu("Replace Icon");
        jp.setLayout(new java.awt.GridBagLayout());

        replaceIconicButton(40, 40, "/icons/motors/movemotor/icon_wheel2.gif", 0, 0, jp, "Motor Icon", _motor, parent);
//        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                objectIconReplaceClicked(evt, _motor.Clone(), parent);
//            }
//        });      

        replaceIconicButton(40, 40, "/icons/motors/directional/dir.gif", 1, 0, jp, "Directional Motor Icon", _dirmotor, parent);
//        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                objectIconReplaceClicked(evt, _dirmotor.Clone(), parent);
//            }
//        });  

        replaceIconicButton(40, 40, "/icons/motors/stopmotor/icon_wheel_stop.gif", 2, 0, jp, "Stop Icon", _stopmotor, parent);
//        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                objectIconReplaceClicked(evt, _stopmotor.Clone(), parent);
//            }
//        });                      

        replaceIconicButton(40, 40, "/icons/lamps/lamp.gif", 3, 0, jp, "Lamp Icon", _lamp, parent);
//        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                objectIconReplaceClicked(evt, _lamp.Clone(), parent);
//            }
//        });                    

        replaceIconicButton(40, 40, "/icons/motors/floatmotor/floatmotor.gif", 0, 1, jp, "Float Motor Icon", _float, parent);
//        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                objectIconReplaceClicked(evt, _float.Clone(), parent);
//            }
//        }); 

        replaceIconicButton(40, 40, "/icons/beeps/beeps.gif", 1, 1, jp, "Beep Icon", _beep, parent);
//        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                objectIconReplaceClicked(evt, _beep.Clone(), parent);
//            }
//        }); 

        replaceIconicButton(40, 40, "/icons/notes/free/freenote.gif", 2, 1, jp, "Play Any Note Icon", _playanynote, parent);
//        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                objectIconReplaceClicked(evt, _playanynote.Clone(), parent);
//            }
//        }); 

        replaceIconicButton(40, 40, "/icons/notes/specific/specific.gif", 3, 1, jp, "Play A Note Icon", _specificnote, parent);
//        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                objectIconReplaceClicked(evt, _specificnote.Clone(), parent);
//            }
//        });                     

        replaceIconicButton(40, 40, "/icons/timers/wait/icon_time.gif", 0, 2, jp, "Wait Icon", _timer, parent);
//        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                objectIconReplaceClicked(evt, _timer.Clone(), parent);
//            }
//        });           

        replaceIconicButton(40, 40, "/icons/wait/touch/wait_touch.gif", 1, 2, jp, "Wait Touch Sensor Icon", _waittouch, parent);    
//        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                objectIconReplaceClicked(evt, _waittouch.Clone(), parent);
//            }
//        });  

        replaceIconicButton(40, 40, "/icons/wait/light/wait_light.gif", 2, 2, jp, "Wait Light Sensor Icon", _waitlight, parent);    
//        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                objectIconReplaceClicked(evt, _waitlight.Clone(), parent);
//            }
//        });                    

        replaceIconicButton(40, 40, "/icons/wait/celcius/wait_celcius.gif", 3, 2, jp, "Wait Celcius Sensor Icon", _waitcelcius, parent);    
//        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                objectIconReplaceClicked(evt, _waitcelcius.Clone(), parent);
//            }
//        });      

        replaceIconicButton(40, 40, "/icons/wait/rotate/wait_rotate.gif", 0, 3, jp, "Wait Rotational Sensor Icon", _waitrotation, parent);    
//        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                objectIconReplaceClicked(evt, _waitrotation.Clone(), parent);
//            }
//        });  

        replaceIconicButton(40, 40, "/icons/wait/timer/wait_timer.gif", 1, 3, jp, "Wait Timer Sensor Icon", _waittimer, parent);    
//        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                objectIconReplaceClicked(evt, _waittimer.Clone(), parent);
//            }
//        });                       

        replaceIconicButton(40, 40, "/icons/structure/return/return.gif", 2, 3, jp, "Return Icon", _returnicon, parent);       
//        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                objectIconReplaceClicked(evt, _returnicon.Clone(), parent);
//            }
//        });                     

        replaceIconicButton(40, 40, "/icons/sensors/clearsensor.gif", 3, 3, jp, "Clear Sensors Icon", _clearsensor, parent);
//        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                objectIconReplaceClicked(evt, _clearsensor.Clone(), parent);
//            }
//        });                    

        replaceIconicButton(40, 40, "/icons/timers/cleart/cleartimer.gif", 0, 4, jp, "Clear Timer Icon", _cleartimer, parent);
//        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                objectIconReplaceClicked(evt, _cleartimer.Clone(), parent);
//            }
//        });  

        replaceIconicButton(40, 40, "/icons/task/start/startTask.gif", 1, 4, jp, "Start Task Icon", _BeginTask, parent);
//        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                objectIconReplaceClicked(evt, _BeginTask.Clone(), parent);
//            }
//        });                     

        replaceIconicButton(40, 40, "/icons/task/stop/stopTask.gif", 2, 4, jp, "Stop Task Icon", _StopTask, parent);
//        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                objectIconReplaceClicked(evt, _StopTask.Clone(), parent);
//            }
//        });                     

        replaceIconicButton(40, 40, "/icons/events/start/start.gif", 3, 4, jp, "Start Event Monitor Icon", _SEvent, parent);
//        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                objectIconReplaceClicked(evt, _SEvent.Clone(), parent);
//            }
//        });                      

        jm2.add(jp);

        jpm.add(jm2);       

        jm = new javax.swing.JMenuItem("Relocate Icon");
        jm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relocateIconPerformed(evt, parent);
            }
        });

        jpm.add(jm);
        jpm.addSeparator();
        jm = new javax.swing.JMenuItem("Delete Icon");
        jpm.add(jm);
        jm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteIconPerformed(evt, parent, 0);
            }
        });

        jm = new javax.swing.JMenuItem("Copy Icon");
        jpm.add(jm);
        jm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyIconPerformed(parent);
            }
        });                


        if(icon instanceof functionIcon)
        {
            jpm.addSeparator();
            jm = new javax.swing.JMenuItem("View Function");
            jpm.add(jm);
            jm.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    showProgWindowPerformed(parent);
                }
            });

            jpm.addSeparator();
            jm = new javax.swing.JMenuItem("Re-Link Function");
            jpm.add(jm);
            jm.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    relinkFunction(parent);
                }
            });

            jm = new javax.swing.JMenuItem("Edit Argument");
            jpm.add(jm);
            jm.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    editArgPerformed(parent);
                }
            });                    
        } 
        else if(icon instanceof ArithOpIcon)
        {
            jpm.addSeparator();
            jm = new javax.swing.JMenuItem("Edit Operation");
            jpm.add(jm);
            jm.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    arithOpEditPerformed(parent);
                }
            });
        }                
        else if(icon instanceof beginTaskIcon || icon instanceof stopTaskIcon)
        {
            jpm.addSeparator();
            jm = new javax.swing.JMenuItem("View Task");
            jpm.add(jm);
            jm.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    showProgWindowPerformed(parent);
                }
            });
            jpm.addSeparator();
            jm = new javax.swing.JMenuItem("Re-Link Task");
            jpm.add(jm);
            jm.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    relinkTask(parent);
                }
            });
        }    
        else if(icon instanceof startEvent)
        {
            jpm.addSeparator();
            jm = new javax.swing.JMenuItem("View Event");
            jpm.add(jm);
            jm.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    showProgWindowPerformed(parent);
                }
            });
            jpm.addSeparator();
            jm = new javax.swing.JMenuItem("Re-Link Event");
            jpm.add(jm);
            jm.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    relinkEvent(parent);
                }
            });
        }                


        jpm.addSeparator();
        jm = new javax.swing.JMenuItem("Properties");
        jpm.add(jm);
        jm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                        javax.swing.JOptionPane.showMessageDialog(jPanelMainCenter,
                                parent.getHelpMsg(),
                                parent.getHelpTitle(),
                                javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        jm = new javax.swing.JMenuItem("Help");
        jpm.add(jm);
        jm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                        javax.swing.JOptionPane.showMessageDialog(jPanelMainCenter,
                                parent.getHelpDesc(),
                                "Context Help -- "+parent.getHelpTitle(),
                                javax.swing.JOptionPane.INFORMATION_MESSAGE,
                                parent.getHelpIcon());
            }
        });        

        icon.setComponentPopupMenu(jpm);  
    }
        
    // handler for branch icon replacement process
    private void branchIconReplaceClicked(java.awt.event.MouseEvent evt, Icon icon, Icon parent)
    {
        parent.getComponentPopupMenu().setVisible(false);
            
        if(icon instanceof BarithmeticIcon)
        {
            ArithComparatorDialog ACD = new ArithComparatorDialog(this, getCurrentAuxiliary(), null);
            ACD.pack();
            ACD.setLocationRelativeTo(this);
            ACD.setVisible(true);

            NestedOperation res = ACD.getExpression();
            if(res != null)  // user didn't press cancel
            {
                BarithmeticIcon tmp_icon = (BarithmeticIcon) icon;
                tmp_icon.setExpression(res);
                icon.setImage();
                put_branch_icon(icon, parent);
                branch_init_replace_listener(icon);
                jPanel7onListeners();  
            }                    
        }
        else
        {
            put_branch_icon(icon, parent);
            branch_init_replace_listener(icon);
            jPanel7onListeners();       
        }              
    }
    
    
    // the method that put the new replacement branch icons
    private void put_branch_icon(Icon icon, Icon parent)
    {
        // initialise the icon            
        int new_x = parent.getCoordinate().getX() + ((parent.getWidth()/2)-(icon.getWidth()/2));
        int new_y = parent.getCoordinate().getY() + ((parent.getHeight()/2)-(icon.getHeight()/2));
        icon.setCoordinate(new coord(new_x, new_y));
        icon.setBounds(icon.getCoordinate().getX(), icon.getCoordinate().getY(), icon.getPreferredSize().width, icon.getPreferredSize().height);

        // set the parent member relationship and delete the old one
        parent.getParentIcon().delMember(parent);
        jProgrammingWindow.setUpIconParent(icon, parent.getLeftNeighbour(), parent.getEndBranch().getRightNeighbour());                  

        // set the join Icons and the end branch icons
        icon.setRightNeighbour(parent.getRightNeighbour());

        icon.setLeftNeighbour(parent.getLeftNeighbour());
        icon.getLeftNeighbour().setRightNeighbour(icon);

        icon.setRightNeighbourTop(parent.getRightNeighbourTop());
        icon.setRightNeighbourBottom(parent.getRightNeighbourBottom());
        icon.getRightNeighbourTop().setLeftNeighbour(icon);
        icon.getRightNeighbourBottom().setLeftNeighbour(icon);

        icon.setEndBranch(parent.getEndBranch());  // end branch will stay the same

        // set the wiring connections for this branch
        icon.setRightWireTop(parent.getRightWireTop());
        icon.getRightWireTop().setLeftIcon(icon);
        icon.setRightWireBottom(parent.getRightWireBottom());
        icon.getRightWireBottom().setLeftIcon(icon);
        icon.setLeftWire(parent.getLeftWire());
        icon.getLeftWire().setRightIcon(icon);

        // set the vertical sizes
        icon.setTopTopVerticalSize(parent.getTopTopVerticalSize());
        icon.setTopBottomVerticalSize(parent.getTopBottomVerticalSize());
        icon.setBottomTopVerticalSize(parent.getBottomTopVerticalSize());
        icon.setBottomBottomVerticalSize(parent.getBottomBottomVerticalSize());
        icon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));  // to change the cursor
        icon.turnonListeners();

        // set the kid or member icons within !!

        java.util.Vector<Icon> if_members = parent.getIfMembers();   // the icons enclosed within this branch
        java.util.Vector<Icon> else_members = parent.getElseMembers();   // the icons enclosed within this branch

        for(int i=0; i<if_members.size(); i++)
        {
            icon.addMember(if_members.get(i), icon.getEndBranch().getLeftNeighbourTop());
            if_members.get(i).setParentIcon(icon);
        }

        for(int i=0; i<else_members.size(); i++)
        {
            icon.addMember(else_members.get(i), icon.getEndBranch().getLeftNeighbourBottom());
            else_members.get(i).setParentIcon(icon);
        }

        jProgrammingWindow.deleteIcon(parent);
        icon_list.delIcon(parent);
        icon_list.addIcon(icon);
        jProgrammingWindow.addIcon(icon);

        jProgrammingWindow.add(icon);
        jProgrammingWindow.remove(parent);
        jProgrammingWindow.updateAllRepeatNeighbours();
        repaint();
        validate();
        DirectionsLabel.setText("Branch icon successfully replaced");
        UndoRedo.update_state();
    }
    
    
    // the method that initialise the mouse listener for branch icons
    private void branch_init_replace_listener(Icon icon)
    {
        javax.swing.JPopupMenu jpm = new javax.swing.JPopupMenu();
        javax.swing.JMenuItem jm = new javax.swing.JMenuItem();
        javax.swing.JPanel jp = new javax.swing.JPanel();
        jp.setLayout(new java.awt.GridBagLayout());
        javax.swing.JMenu jm2 = new javax.swing.JMenu();
        javax.swing.JButton jmi = new javax.swing.JButton();
                
        java.awt.GridBagConstraints gridBagConstraints;
        final Icon parent = icon;
                
        jm2 = new javax.swing.JMenu("Replace Icon");
        jp.setLayout(new java.awt.GridBagLayout());                                
                
        replaceIconicButton(40, 40, "/icons/branches/touch/icon_if.gif", 0, 0, jp, "Branch Touch Sensor Icon", _Btouch, parent);
//        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                branchIconReplaceClicked(_Btouch.Clone(), parent);
//            }
//        }); 
                    
        replaceIconicButton(40, 40, "/icons/branches/light/light.gif", 1, 0, jp, "Branch Light Sensor Icon", _Blight, parent);
//        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                branchIconReplaceClicked(_Blight.Clone(), parent);
//            }
//        });                                 
                
        replaceIconicButton(40, 40, "/icons/branches/celcius/celcius.gif", 2, 0, jp, "Branch Temperature Sensor Icon", _Bcelcius, parent);
//        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                branchIconReplaceClicked(_Bcelcius.Clone(), parent);
//            }
//        });                
                
        replaceIconicButton(40, 40, "/icons/branches/rotation/rotation.gif", 3, 0, jp, "Branch Rotational Sensor Icon", _Brotation, parent);
//        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                branchIconReplaceClicked(_Brotation.Clone(), parent);
//            }
//        });                
                
        replaceIconicButton(40, 40, "/icons/branches/timer/timer.gif", 0, 1, jp, "Branch Timer Icon", _Btimer, parent);
//        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                branchIconReplaceClicked(_Btimer.Clone(), parent);
//            }
//        });                 
                
        replaceIconicButton(40, 40, "/icons/branches/random/random.gif", 1, 1, jp, "Branch Random Icon", _Brandom, parent);
//        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                branchIconReplaceClicked(_Brandom.Clone(), parent);
//            }
//        });       
        
        replaceIconicButton(40, 40, "/icons/branches/arithmetic/arithmetic.gif", 2, 1, jp, "Branch Arithmetic Icon", _Barithmetic, parent);   
//        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                branchIconReplaceClicked(_Barithmetic.Clone(), parent);
//            }
//        });          
                
        jm2.add(jp);
              
        jpm.add(jm2);

        jm = new javax.swing.JMenuItem("Relocate Icon");
        jm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relocateIconPerformed(evt, parent);
            }
        });                

        jpm.add(jm);
        jpm.addSeparator();
        jm2 = new javax.swing.JMenu("Delete");

        jm = new javax.swing.JMenuItem("Delete branch icon and all members");
        jm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteIconPerformed(evt, parent, 0);
            }
        });
        jm2.add(jm);

        jm = new javax.swing.JMenuItem("Delete branch icon and keep top part");
        jm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteIconPerformed(evt, parent, 2);
            }
        });
        jm2.add(jm);

        jm = new javax.swing.JMenuItem("Delete branch icon and keep bottom part");
        jm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteIconPerformed(evt, parent, 3);
            }
        });
        jm2.add(jm);

        jpm.add(jm2);

        jm = new javax.swing.JMenuItem("Copy Icon");
        jpm.add(jm);
        jm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyIconPerformed(parent);
            }
        });     

        jpm.addSeparator();
        if(icon instanceof BarithmeticIcon)
        {
            jm = new javax.swing.JMenuItem("Edit Expression");
            jpm.add(jm);
            jm.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    expressionEditPerformed(parent);
                }
            });
            jpm.addSeparator();                   
        }    

        jm = new javax.swing.JMenuItem("Properties");
        jpm.add(jm);
        jm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                        javax.swing.JOptionPane.showMessageDialog(jPanelMainCenter,
                                parent.getHelpMsg(),
                                parent.getHelpTitle(),
                                javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
        });                

        jm = new javax.swing.JMenuItem("Help");
        jpm.add(jm);
        jm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                        javax.swing.JOptionPane.showMessageDialog(jPanelMainCenter,
                                parent.getHelpDesc(),
                                "Context Help -- "+parent.getHelpTitle(),
                                javax.swing.JOptionPane.INFORMATION_MESSAGE,
                                parent.getHelpIcon());
            }
        });           
        
        icon.setComponentPopupMenu(jpm);
    }
    
    
    // handler for loop icon replacement process
    private void loopIconReplaceClicked(java.awt.event.MouseEvent evt, Icon icon, Icon parent)
    {
        parent.getComponentPopupMenu().setVisible(false);

        if(icon instanceof arithmeticLoopIcon)
        {
            ArithComparatorDialog ACD = new ArithComparatorDialog(this, getCurrentAuxiliary(), null);
            ACD.pack();
            ACD.setLocationRelativeTo(this);
            ACD.setVisible(true);

            NestedOperation res = ACD.getExpression();
            if(res != null)  // user didn't press cancel
            {
                arithmeticLoopIcon tmp_icon = (arithmeticLoopIcon) icon;
                tmp_icon.setExpression(res);
                icon.setImage();
                put_loop_icon(icon, parent);
                loop_init_replace_listener(icon);
                jPanel7onListeners();    
            }                    
        }
        else
        {
            put_loop_icon(icon, parent);
            loop_init_replace_listener(icon);
            jPanel7onListeners();      
        }               
    }
    
    // the method that put the new replacement loop icons
    private void put_loop_icon(Icon icon, Icon parent)
    {
        // initialise the icon            
        int new_x = parent.getCoordinate().getX() + ((parent.getWidth()/2)-(icon.getWidth()/2));
        int new_y = parent.getCoordinate().getY() + ((parent.getHeight()/2)-(icon.getHeight()/2));
        icon.setCoordinate(new coord(new_x, new_y));
        icon.setBounds(icon.getCoordinate().getX(), icon.getCoordinate().getY(), icon.getPreferredSize().width, icon.getPreferredSize().height);

        // set the parent member relationship and delete the old one
        parent.getParentIcon().delMember(parent);
        jProgrammingWindow.setUpIconParent(icon, parent.getLeftNeighbour(), parent.getEndIcon().getRightNeighbour());                  

        // set the neighbouring icons

        icon.setLeftNeighbour(parent.getLeftNeighbour());

        icon.setRightNeighbour(parent.getRightNeighbour());

        // set the join Icons 
        icon.setBeginIcon(parent.getBeginIcon());
        icon.getBeginIcon().setBottomNeighbour(icon);
        icon.setEndIcon(parent.getEndIcon());
        icon.getEndIcon().setBottomNeighbour(icon);

        // set the wiring connections for this branch
        icon.setRightWire(parent.getRightWire());
        icon.getRightWire().setLeftIcon(icon);
        icon.setLeftWire(parent.getLeftWire());
        icon.getLeftWire().setRightIcon(icon);


        // set the vertical sizes
        icon.setTopVerticalSize(parent.getTopVerticalSize());
        icon.setBottomVerticalSize(parent.getBottomVerticalSize());

        icon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));  // to change the cursor
        icon.turnonListeners();
        // set the kid or member icons within !!

        java.util.Vector<Icon> members = parent.getMembers();   

        for(int i=0; i<members.size(); i++)
        {
            icon.addMember(members.get(i), icon.getEndIcon());
            members.get(i).setParentIcon(icon);
        }

        jProgrammingWindow.deleteIcon(parent);
        icon_list.delIcon(parent);
        icon_list.addIcon(icon);
        jProgrammingWindow.addIcon(icon);

        jProgrammingWindow.add(icon);
        jProgrammingWindow.remove(parent);
        jProgrammingWindow.updateAllRepeatNeighbours();
        repaint();
        validate();
        DirectionsLabel.setText("Loop icon successfully replaced");
        UndoRedo.update_state();
    }
    
    
    // the method that initialise the mouse listener for loop icons
    private void loop_init_replace_listener(Icon icon)
    {
        javax.swing.JPopupMenu jpm = new javax.swing.JPopupMenu();
        javax.swing.JMenuItem jm = new javax.swing.JMenuItem();
        javax.swing.JPanel jp = new javax.swing.JPanel();
        jp.setLayout(new java.awt.GridBagLayout());
        javax.swing.JMenu jm2 = new javax.swing.JMenu();
        javax.swing.JButton jmi = new javax.swing.JButton();
                
        java.awt.GridBagConstraints gridBagConstraints;
        final Icon parent = icon;
                
        jm2 = new javax.swing.JMenu("Replace Icon");
                
        jp.setLayout(new java.awt.GridBagLayout());
        
        replaceIconicButton(40, 40, "/icons/loops/repeat/icon_rep.gif", 0, 0, jp, "Repeat Icon", _Lrep, parent);
//        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                loopIconReplaceClicked(_Lrep.Clone(), parent);
//            }
//        });        
                                
        replaceIconicButton(40, 40, "/icons/loops/infiniteloop/infinite_rep.gif", 1, 0, jp, "Infinite Repeat Icon", _Linfinite, parent);
//        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                loopIconReplaceClicked(_Linfinite.Clone(), parent);
//            }
//        });                             
                                                          
        replaceIconicButton(40, 40, "/icons/loops/lightrep/light_rep.gif", 2, 0, jp, "Light Sensor Repeat Icon", _Llight, parent);
//        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                loopIconReplaceClicked(_Llight.Clone(), parent);
//            }
//        });                 
                
        replaceIconicButton(40, 40, "/icons/loops/timer/timer.gif", 3, 0, jp, "Timer Repeat Icon", _Ltimer, parent);
//        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                loopIconReplaceClicked(_Ltimer.Clone(), parent);
//            }
//        });                  
                
        replaceIconicButton(40, 40, "/icons/loops/celcius/celcius.gif", 0, 1, jp, "Temperature Sensor Repeat Icon", _Lcelcius, parent);
//        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                loopIconReplaceClicked(_Lcelcius.Clone(), parent);
//            }
//        });                 
                
        replaceIconicButton(40, 40, "/icons/loops/rotation/rotational.gif", 1, 1, jp, "Rotational Sensor Repeat Icon", _Lrotation, parent);
//        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                loopIconReplaceClicked(_Lrotation.Clone(), parent);
//            }
//        });                 
                
        replaceIconicButton(40, 40, "/icons/loops/touch/touch.gif", 2, 1, jp, "Touch Sensor Repeat Icon", _Ltouch, parent);
//        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                loopIconReplaceClicked(_Ltouch.Clone(), parent);
//            }
//        });  
        
        replaceIconicButton(40, 40, "/icons/loops/arithmetic/arithmetic.gif", 3, 1, jp, "Arithmetic Repeat Icon", _Larithmetic, parent);    
//        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                loopIconReplaceClicked(_Larithmetic.Clone(), parent);
//            }
//        });         

        jm2.add(jp);
              
        jpm.add(jm2);

        jm = new javax.swing.JMenuItem("Relocate Icon");
        jm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relocateIconPerformed(evt, parent);
            }
        });

        jpm.add(jm);
        jpm.addSeparator();
        jm2 = new javax.swing.JMenu("Delete");

        jm = new javax.swing.JMenuItem("Delete loop icon");
        jm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteIconPerformed(evt, parent, 0);
            }
        });

        jm2.add(jm);
        jm = new javax.swing.JMenuItem("Delete all icons within loop");
        jm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteIconPerformed(evt, parent, 1);
            }
        });
        jm2.add(jm);

        jpm.add(jm2);

        jm = new javax.swing.JMenuItem("Copy Icon");
        jpm.add(jm);
        jm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyIconPerformed(parent);
            }
        });  

        jpm.addSeparator();

        if(icon instanceof arithmeticLoopIcon)
        {
            jm = new javax.swing.JMenuItem("Edit Expression");
            jpm.add(jm);
            jm.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    expressionEditPerformed(parent);
                }
            });
            jpm.addSeparator();                   
        }                 

        jm = new javax.swing.JMenuItem("Properties");
        jpm.add(jm);
        jm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                        javax.swing.JOptionPane.showMessageDialog(jPanelMainCenter,
                                parent.getHelpMsg(),
                                parent.getHelpTitle(),
                                javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        jm = new javax.swing.JMenuItem("Help");
        jpm.add(jm);
        jm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                        javax.swing.JOptionPane.showMessageDialog(jPanelMainCenter,
                                parent.getHelpDesc(),
                                "Context Help -- "+parent.getHelpTitle(),
                                javax.swing.JOptionPane.INFORMATION_MESSAGE,
                                parent.getHelpIcon());
            }
        });          

        icon.setComponentPopupMenu(jpm);
    }
    
    
    /**
     * special method that deletes a branchIcon but keep the if part of the branch
     * @param icon 
     * @param px 
     * @param py 
     */
    private void deleteBranchKeepIf(Icon icon, int px, int py)
    {
        java.util.Vector<Icon> ics = icon.getIfMembers();
        Icon tic = icon.getEndBranch().getRightNeighbour();
        Icon tic2 = icon.getLeftNeighbour();
        // delete all, reinsert the if_members
        deleteIcon(icon, px, py);
        
        // reinsert the if members, except the join icons
        for(int i=1; i<(ics.size()-1); i++)
        {
            tic2 = tic.getLeftNeighbour();
            Icon tmp_icon = ics.get(i);
            tmp_icon.turnoffListeners();
            if(tmp_icon instanceof branchIcon || tmp_icon instanceof loopIcon)
            {                
                insertRecursiveBranchLoopIcon(tmp_icon, tic, tic2);
            }
            else
            {
                int xpos = 0;
                if(tic2 != null && tic != null)
                {
                    xpos = gridcalculation.midPoint(tic2.getCoordinate(), tic.getCoordinate());
                }

                int ypos = gridcalculation.calculateY(tic2.getCoordinate().getY()+((tic2.getHeight())/2));  
                coord tmp_co = new coord(xpos, ypos);
                coord co = gridcalculation.computeCoord(icon, tmp_co);
                tmp_icon.setCoordinate(co);
                insertObjectIcon(tmp_icon, tic, tic2);
            }
        }        
    }
    
    
    // special method that deletes a branchIcon but keep the else part of the branch
    private void deleteBranchKeepElse(Icon icon, int px, int py)
    {
        java.util.Vector<Icon> ics = icon.getElseMembers();
        Icon tic = icon.getEndBranch().getRightNeighbour();
        Icon tic2 = icon.getLeftNeighbour();
        // delete all, reinsert the else_members
        deleteIcon(icon, px, py);
        
        // reinsert the else members, except the join icons
        for(int i=1; i<(ics.size()-1); i++)
        {
            tic2 = tic.getLeftNeighbour();
            Icon tmp_icon = ics.get(i);
            tmp_icon.turnoffListeners();
            if(tmp_icon instanceof branchIcon || tmp_icon instanceof loopIcon)
            {
                insertRecursiveBranchLoopIcon(tmp_icon, tic, tic2);
            }
            else
            {
                int xpos = 0;
                if(tic2 != null && tic != null)
                {
                    xpos = gridcalculation.midPoint(tic2.getCoordinate(), tic.getCoordinate());
                }

                int ypos = gridcalculation.calculateY(tic2.getCoordinate().getY()+((tic2.getHeight())/2));  
                coord tmp_co = new coord(xpos, ypos);
                coord co = gridcalculation.computeCoord(icon, tmp_co);
                tmp_icon.setCoordinate(co);
                insertObjectIcon(tmp_icon, tic, tic2);
            }
        }
    }
    
    // special method that deletes a repeatIcon and all of its members
    private void deleteRepeatAll(Icon icon, int px, int py)
    {
        jProgrammingWindow.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        // shift all icons as necessary
        int n_shift = (gridcalculation.calculateXright(icon.getEndIcon().getRightNeighbour().getCoordinate().getX()) 
                     - gridcalculation.calculateXright(icon.getBeginIcon().getCoordinate().getX())) / Constants.gridDistanceX;
        jProgrammingWindow.recursiveShiftIconHorizontalLeft(icon.getBeginIcon(), n_shift);
        
        // the following line will delete all icons and wires involved
        jProgrammingWindow.delAllIcons(icon);    
        
        // set the neighbour relationships
        icon.getBeginIcon().getLeftNeighbour().setRightNeighbour(icon.getEndIcon().getRightNeighbour());
                
        icon.getEndIcon().getRightNeighbour().setLeftNeighbour(icon.getBeginIcon().getLeftNeighbour());
                
        // reconnect wiring
        icon.getBeginIcon().getLeftNeighbour().getRightWire().setRightIcon(icon.getEndIcon().getRightNeighbour());
        icon.getEndIcon().getRightNeighbour().setLeftWire(icon.getBeginIcon().getLeftNeighbour().getRightWire());
        
        int member_pos=-1;
        if(icon.getParentIcon() instanceof branchIcon)
        {
            member_pos = icon.getParentIcon().determineMember(icon);
        }
        icon.getParentIcon().delMember(icon);
        jProgrammingWindow.recursiveShiftIconVerticalUp(icon, 1, member_pos);

        jProgrammingWindow.repositionAllBranches();
        jProgrammingWindow.repositionAllRepeat();
        jProgrammingWindow.updateAllRepeatNeighbours();
        DirectionsLabel.setText("Icon successfully deleted");
        
        // resize the programming window
        jProgrammingWindow.resizeProgWindow(jScrollPane1); 

//        viewport.setViewPosition(new java.awt.Point(px - (Constants.gridDistanceX * 2), py - (Constants.gridDistanceY * 2)));
        jProgrammingWindow.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }
    
    
    
    // this method does the actual deletion
    private void deleteIcon(Icon icon, int px, int py)
    {
        jProgrammingWindow.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        if(icon instanceof loopIcon)
        {
            Icon jlicon_left = icon.getBeginIcon();
            Icon jlicon_right = icon.getEndIcon(); 

            // reconnect the enclosing icons of this loop
            if(icon.getMembers().size() > 2)  // there are members in this icon 
            {
                // now delete the wires
                jProgrammingWindow.recursiveShiftIconHorizontalLeft(jlicon_left, 1);
                jProgrammingWindow.collapseWire(jlicon_left.getLeftNeighbour(), jlicon_left.getRightNeighbour());

                jProgrammingWindow.recursiveShiftIconHorizontalLeft(jlicon_right, 1);

                jProgrammingWindow.collapseWire(jlicon_right.getLeftNeighbour(), jlicon_right.getRightNeighbour());

                jlicon_left.getLeftNeighbour().setRightNeighbour(jlicon_left.getRightNeighbour());
                jlicon_left.getRightNeighbour().setLeftNeighbour(jlicon_left.getLeftNeighbour());
                jlicon_right.getRightNeighbour().setLeftNeighbour(jlicon_right.getLeftNeighbour());
                jlicon_right.getLeftNeighbour().setRightNeighbour(jlicon_right.getRightNeighbour());
            }
            else
            {
                jProgrammingWindow.recursiveShiftIconHorizontalLeft(jlicon_left, 1);

                jProgrammingWindow.deleteWire(jlicon_left.getRightWire());
                wire_list.delWire(jlicon_left.getRightWire());


                jProgrammingWindow.recursiveShiftIconHorizontalLeft(jlicon_right, 1);

                jProgrammingWindow.deleteWire(jlicon_right.getRightWire());
                wire_list.delWire(jlicon_right.getRightWire());

                jlicon_right.getRightNeighbour().setLeftWire(jlicon_left.getLeftNeighbour().getRightWire());
                jlicon_left.getLeftNeighbour().getRightWire().setRightIcon(jlicon_right.getRightNeighbour());

                jlicon_left.getLeftNeighbour().setRightNeighbour(jlicon_right.getRightNeighbour());
                jlicon_right.getRightNeighbour().setLeftNeighbour(jlicon_left.getLeftNeighbour());
            }

            // reconfigure the member with its new parents now that this repeat icon is gone

            java.util.Vector<Icon> ics = icon.getMembers();
            for(int i = 1; i < (ics.size() - 1); i++)  // don't forget to exclude the beginning and end icon of this repeat icon
            {
                ics.get(i).setParentIcon(icon.getParentIcon()); 
                if(icon.getRightNeighbour() instanceof joinLoopIcon)
                {
                    if(icon.getRightNeighbour().getBottomNeighbour().getId() == icon.getParentIcon().getId())
                    {
                        icon.getParentIcon().addMember(ics.get(i), icon.getRightNeighbour()); 
                    }
                    else
                    {
                        icon.getParentIcon().addMember(ics.get(i), icon.getRightNeighbour().getBottomNeighbour());
                    }
                }
                else
                {
                    icon.getParentIcon().addMember(ics.get(i), icon.getRightNeighbour()); 
                }                        
            }                    

            int wire_type=0;  // for the colour type
            if(icon.getParentIcon() instanceof branchIcon)
            {
                wire_type = 1;
            }
            else if(icon.getParentIcon() instanceof loopIcon)
            {
                wire_type = 2;
            }
            // get rid of the members of this repeat
            for(int i = 1; i < (ics.size() - 1); i++)
            {
                icon.delMember(ics.get(i));
                // update the colour of the wiring

                if(ics.get(i) instanceof branchIcon)
                {
                    ics.get(i).getLeftWire().setType(wire_type);
                    ics.get(i).getEndBranch().getRightWire().setType(wire_type);
                }
                else if(ics.get(i) instanceof loopIcon)
                {
                    ics.get(i).getBeginIcon().getLeftWire().setType(wire_type);
                    ics.get(i).getEndIcon().getRightWire().setType(wire_type);
                }
                else
                {
                    ics.get(i).getLeftWire().setType(wire_type);
                    ics.get(i).getRightWire().setType(wire_type);
                }
            }

            // delete the vertical wiring
            jProgrammingWindow.deleteWire(icon.getLeftWire());
            wire_list.delWire(icon.getLeftWire());
            jProgrammingWindow.deleteWire(icon.getRightWire());
            wire_list.delWire(icon.getRightWire());

            // finally delete the icons   
            jProgrammingWindow.deleteIcon(icon);
            icon_list.delIcon(icon);
            jProgrammingWindow.remove(icon);
            jProgrammingWindow.deleteIcon(jlicon_right);
            icon_list.delIcon(jlicon_right);
            jProgrammingWindow.remove(jlicon_right);
            jProgrammingWindow.deleteIcon(jlicon_left);
            icon_list.delIcon(jlicon_left);
            jProgrammingWindow.remove(jlicon_left);                     

            int member_pos = -1;
            if(icon.getParentIcon() instanceof branchIcon)
            {
                member_pos = icon.getParentIcon().determineMember(icon);
            }
            icon.getParentIcon().delMember(icon);
            jProgrammingWindow.recursiveShiftIconVerticalUp(icon, 1, member_pos);
        }
        else if(icon instanceof branchIcon)
        {
            // shift all icons as necessary
            int n_shift = (gridcalculation.calculateXright(icon.getEndBranch().getRightNeighbour().getCoordinate().getX()) 
                          - gridcalculation.calculateXright(icon.getCoordinate().getX())) / Constants.gridDistanceX;

            jProgrammingWindow.recursiveShiftIconHorizontalLeft(icon, n_shift);

            // the following line will delete all icons and wires involved
            jProgrammingWindow.delAllIcons(icon);       

            // reconnect the neighbouring icons
            icon.getLeftNeighbour().setRightNeighbour(icon.getEndBranch().getRightNeighbour());
            icon.getEndBranch().getRightNeighbour().setLeftNeighbour(icon.getLeftNeighbour());

            icon.getLeftNeighbour().getRightWire().setRightIcon(icon.getEndBranch().getRightNeighbour());
            icon.getEndBranch().getRightNeighbour().setLeftWire(icon.getLeftNeighbour().getRightWire());

            int member_pos = -1;
            if(icon.getParentIcon() instanceof branchIcon)
            {
                member_pos = icon.getParentIcon().determineMember(icon);
            }
            icon.getParentIcon().delMember(icon);
            jProgrammingWindow.recursiveShiftIconVerticalUp(icon, 1, member_pos);
        }
        else
        {
            // reconnect the neighbouring icons
            icon.getLeftNeighbour().setRightNeighbour(icon.getRightNeighbour());

            icon.getRightNeighbour().setLeftNeighbour(icon.getLeftNeighbour());

            // recursively shift the icons
            jProgrammingWindow.recursiveShiftIconHorizontalLeft(icon, 1);
            // collapse the wires
            jProgrammingWindow.collapseWire(icon.getLeftNeighbour(), icon.getRightNeighbour());

            // remove this icon from its parent's vector of member icons                    

            jProgrammingWindow.deleteIcon(icon);

            icon.getParentIcon().delMember(icon);
            icon_list.delIcon(icon);
            jProgrammingWindow.remove(icon);
        }

        jProgrammingWindow.repositionAllBranches();
        jProgrammingWindow.repositionAllRepeat();
        DirectionsLabel.setText("Icon successfully deleted");
        
        // resize the programming window
        jProgrammingWindow.resizeProgWindow(jScrollPane1); 

//        viewport.setViewPosition(new java.awt.Point(px - (Constants.gridDistanceX * 2), py - (Constants.gridDistanceY * 2)));
        
        jProgrammingWindow.updateAllRepeatNeighbours();
        
        repaint();
        validate();
        
        jProgrammingWindow.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }
    
    // this method handles for the second click of a repeat icon placement
    // the second click determines the end of the repeat segment
    // the lefty and righty arguments are needed for setting up parent-member relationship of the repeat
    private void jPanel7MouseClicked4(int xpos, int ypos, java.awt.event.MouseEvent evt2, Icon lic)
    {            
        coord tmp_co = new coord(evt2.getX(), evt2.getY());        

        // for beginning icon
        Icon tic = jProgrammingWindow.findIcon(xpos, ypos, 1);   // right of icon
        Icon tic2 = jProgrammingWindow.findIcon(xpos, ypos, 0);  // left of icon

        xpos = gridcalculation.calculateXright(xpos);
        ypos = gridcalculation.calculateY(ypos);

        // for ending icon
        Icon tic3 = jProgrammingWindow.findIcon(evt2.getX(), evt2.getY(), 1);   // right of icon
        Icon tic4 = jProgrammingWindow.findIcon(evt2.getX(), evt2.getY(), 0);   // left of icon

        int xpos2 = gridcalculation.calculateXright(evt2.getX());  
        int ypos2 = gridcalculation.calculateY(evt2.getY());

        // for viewport
        int v_x = xpos;
        int v_y = ypos;


        // for checking that all icons to be put within this new repeat should have the same parent icon
        int allowed = 1;  // initially zero for allowed (not allowed = 0)
        if(ypos == ypos2 && xpos2 >= xpos)
        {
            // check for the enclosed icons
            Icon tm_p = null;
            int x = xpos;
            int x_limit = xpos2;
            while(x < x_limit)
            {
                java.awt.Component c = jProgrammingWindow.getComponentAt(x, ypos);
                if(c instanceof Icon)
                {
                    Icon ico = (Icon) c;
                    if(ico instanceof joinLoopIcon)
                    {
                        ico = ico.getBottomNeighbour();
                        x = gridcalculation.calculateXright(ico.getEndIcon().getCoordinate().getX());
                        if(x >= xpos2 && xpos <= gridcalculation.calculateXright(ico.getBeginIcon().getCoordinate().getX()))
                        {
                            allowed = 0;
                            break;
                        }
                    }
                    else if(ico instanceof branchIcon)
                    {
                        x = gridcalculation.calculateXright(ico.getEndBranch().getCoordinate().getX());
                    }
                        if(tm_p == null)
                        {
                            tm_p = ico.getParentIcon();
                        }
                        else
                        {
                            if(ico.getParentIcon().getId() != tm_p.getId())
                            {
                                allowed = 0;
                                break;
                            }
                        }
                }
                x += Constants.gridDistanceX;
            }                    
        }
        else
        {
            allowed = 0;
        }
        if(allowed == 1)
        {
            if(tic instanceof joinLoopIcon && tic.getBottomNeighbour().getEndIcon().getId() == tic.getId() &&
               tic4 instanceof joinLoopIcon && tic4.getBottomNeighbour().getBeginIcon().getId() == tic4.getId())
            {
                allowed = 1;
            }
            else
            {
                if(tic instanceof joinLoopIcon && tic.getBottomNeighbour().getEndIcon().getId() == tic.getId())
                {
                    allowed = 0;
                }
                if(tic4 instanceof joinLoopIcon && tic4.getBottomNeighbour().getBeginIcon().getId() == tic4.getId())
                {
                    allowed = 0;
                }
            }
        }

        if(xpos == xpos2)  // special override, if this repeat meant to start empty, it will be allowed no matter where it is
        {
            allowed = 1;
        }

            
        if(tmp_co.getX() < (jProgrammingWindow.getStartIcon().getCoordinate().getX()+jProgrammingWindow.getStartIcon().getWidth()/2) ||
           tmp_co.getX() > (jProgrammingWindow.getEndIcon().getCoordinate().getX()+jProgrammingWindow.getEndIcon().getWidth()/2) ||
           tic == null || tic2 == null || tic3 == null || tic4 == null || allowed == 0 || tic3 instanceof joinEndBranchIcon) 
        {
            // if the desired place for the new icon is taken by another icon, make sure
            // the new icon could not be to the left of the starting icon or to the right of the
            // ending icon
            
            javax.swing.JOptionPane.showMessageDialog(jPanelMainCenter,
                                    "Insertion of repeat was invalid, please reselect a position",
                                    "Insertion Invalid",
                                    javax.swing.JOptionPane.WARNING_MESSAGE);
            DirectionsLabel.setText(readyPrompt); 
        }
        else
        {
            insertLoopIcon(lic, tic, tic2, tic3, tic4);
            UndoRedo.update_state();
        }
    }
    
    
    // the method that relocates an icon to its new position
    private void jPanel7MouseClicked5(java.awt.event.MouseEvent evt, Icon icon)
    {
        // get the location of the click
        
        int new_x = (int) evt.getPoint().getX();
        int new_y = (int) evt.getPoint().getY();

        Icon tic = jProgrammingWindow.findIcon(new_x, new_y, 1);   // right of icon
        Icon tic2 = jProgrammingWindow.findIcon(new_x, new_y, 0);  // left of icon
           
        // ensure it is valid first

        if(new_x < (jProgrammingWindow.getStartIcon().getCoordinate().getX()+jProgrammingWindow.getStartIcon().getWidth()/2) ||
           new_x > (jProgrammingWindow.getEndIcon().getCoordinate().getX()+jProgrammingWindow.getEndIcon().getWidth()/2) ||
           tic == null || tic2 == null || tic instanceof joinEndBranchIcon) 
        {
            javax.swing.JOptionPane.showMessageDialog(null,
                    "Position invalid, please reselect a position",
                    "Position Invalid",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
            DirectionsLabel.setText(readyPrompt);
        }
        else
        {
            // 1. get rid of the icon from its parent's membership lists
            // 2. get rid of the icon visually
            // 3. shift all icons to the left or top whatever necessary
            
            if(icon instanceof branchIcon)
            {
                // this is a special case, the way this feature is implemented differs as to what has been done
                // to a normal object icons. This is due to branch icons containing nested icons within
                // and deleting and reinserting them will be very hard to do and bug ridden,
                // so simply, move and reconnect the branch icons (The icons within will not know that the parent icon
                // has change connection to a new parent icon)
                
                // has to ensure that the new location parent icon is not itself
                if(jProgrammingWindow.checkParent(icon, tic, tic2) == false)
                {
                    javax.swing.JOptionPane.showMessageDialog(null,
                                            "Position invalid, please reselect a position",
                                            "Position Invalid",
                                            javax.swing.JOptionPane.WARNING_MESSAGE);
                                    DirectionsLabel.setText(readyPrompt);
                }
                else
                {   
                    // only reposition if tic2 and tic are not the icon's original neighbours

                    if((tic.getId() == icon.getEndBranch().getRightNeighbour().getId() && tic2.getId() == icon.getEndBranch().getId())
                    || (tic2.getId() == icon.getLeftNeighbour().getId() && tic.getId() == icon.getId()))
                    {
                        
                    }
                    else
                    {
                        deleteIcon(icon, icon.getCoordinate().getX(), icon.getCoordinate().getX());
                        icon.turnoffListeners();
                        insertRecursiveBranchLoopIcon(icon, tic, tic2);      
                    }
                    
                    // DONE
                    DirectionsLabel.setText("Icon successfully relocated");
                    UndoRedo.update_state();
                }
            }
            else if(icon instanceof loopIcon)
            {                
                if(jProgrammingWindow.checkParent(icon, tic, tic2) == false)
                {
                    javax.swing.JOptionPane.showMessageDialog(null,
                                            "Position invalid, please reselect a position",
                                            "Position Invalid",
                                            javax.swing.JOptionPane.WARNING_MESSAGE);
                                    DirectionsLabel.setText(readyPrompt);
                }
                else
                {   
                    // only reposition if tic2 and tic are not the icon's original neighbours

                    if((tic.getId() == icon.getEndIcon().getRightNeighbour().getId() && tic2.getId() == icon.getEndIcon().getId())
                    || (tic2.getId() == icon.getBeginIcon().getLeftNeighbour().getId() && tic.getId() == icon.getBeginIcon().getId()))
                    {
                        
                    }
                    else
                    {
                        deleteRepeatAll(icon, icon.getCoordinate().getX(), icon.getCoordinate().getX());
                        icon.turnoffListeners();
                        insertRecursiveBranchLoopIcon(icon, tic, tic2);      
                    }                    
                    // DONE
                    DirectionsLabel.setText("Icon successfully relocated");
                    UndoRedo.update_state();
                }
            }
            else
            {
                deleteIcon(icon, icon.getCoordinate().getX(), icon.getCoordinate().getX());
                icon.turnoffListeners();
                // reconfigure the icons
                if(tic.getId() == icon.getId())
                {
                    tic = icon.getRightNeighbour();
                }
                else if(tic2.getId() == icon.getId())
                {
                    tic2 = icon.getLeftNeighbour();
                }

                // 4. introduce the icons to its new parents
                // 5. introduce the icons visually (new coordinates and new bounds)
                // 6. shift other icons appropriately

                // instead has to find a mid point between tic and tic2

                int xpos = 0;
                if(tic2 != null && tic != null)
                {
                    xpos = gridcalculation.midPoint(tic2.getCoordinate(), tic.getCoordinate());
                }

                int ypos = gridcalculation.calculateY(evt.getY());   // ypos wouldn't change since an object icon would not alter any vertical sizes
                coord tmp_co = new coord(xpos, ypos);
                coord co = gridcalculation.computeCoord(icon, tmp_co);
                icon.setCoordinate(co); 
                
                insertObjectIcon(icon, tic, tic2);
                // 7. DONE
                DirectionsLabel.setText("Icon successfully relocated");
                UndoRedo.update_state();
            }            
        }
    }    
    
    // method thats insert an object icon
    // the fourth argument type is to decide whether to insert a whole new function
    private void insertObjectIcon(Icon icon, Icon tic, Icon tic2)
    {
//        jProgrammingWindow.insertObjectIcon(icon, tic, tic2, jScrollPane1);
        jProgrammingWindow.insertObjectIcon(icon, tic, tic2, jScrollPane1);
        icon.turnonListeners();
        icon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));  // to change the cursor
        object_init_replace_listener(icon);
        
        repaint();
        validate();
    }
    
    
    // a method that recursively inserts a branch or a loop and its members
    // WARNING : THIS METHOD THAT SHOULD ONLY BE USED WHEN THE ICONS WITHIN THE BRANCH AND THE BRANCH ITSELF HAS BEEN INSERTED ONTO
    // THE PROGRAMMING WINDOW PREVIOUSLY
    private void insertRecursiveBranchLoopIcon(Icon icon, Icon tic, Icon tic2)
    {
        // temporary variables for the member vectors
        // tmp_members1 for member in loop and if_member in branch
        // tmp_members2 for else_member in branch
                    
        java.util.Vector<Icon> tmp_members1;              
        java.util.Vector<Icon> tmp_members2;            
        
        // calculate the length of the shifting of this icon, vertically and horizontally, and all of its members
        // will follow
            
        int xpos = 0;
        if(tic2 != null && tic != null)
        {
            xpos = gridcalculation.midPoint(tic2.getCoordinate(), tic.getCoordinate());
        }
        int ypos = tic2.getCoordinate().getY()+(tic2.getHeight()/2);  // tic2 and tic should have the same coordinate                        
        
        coord tmp_co = new coord(xpos, ypos);
        coord co = gridcalculation.computeCoord(icon, tmp_co);
        icon.setCoordinate(co);
        
        if(icon instanceof branchIcon)
        {
            // before we continue the icon has to start fresh, i.e. its members vector has to be cleaned and restarted
            // now remove all of this icon's members
            // also restart the vertical sizes
            icon.setTopTopVerticalSize(0);
            icon.setTopBottomVerticalSize(0);
            icon.setBottomTopVerticalSize(0);
            icon.setBottomBottomVerticalSize(0);
            tmp_members1 = icon.getIfMembers();
            // turns off the icon's listeners first
            for(int i=0; i<tmp_members1.size(); i++)
            {
                Icon tmpic = tmp_members1.get(i);
                tmpic.turnoffListeners();
            }
            tmp_members2 = icon.getElseMembers();
            // turns off the icon's listeners first
            for(int i=0; i<tmp_members2.size(); i++)
            {
                Icon tmpic = tmp_members2.get(i);
                tmpic.turnoffListeners();
            }
            icon.delAllMembers();
            insertBranchIcon(icon, tic, tic2);           
            
            // now insert all of the existing members of this branch
            
            // do the if_part
            for(int i=1; i<(tmp_members1.size()-1); i++)
            {
                Icon tmp_icon = tmp_members1.get(i);
                if(tmp_icon instanceof branchIcon || tmp_icon instanceof loopIcon)
                {                    
                    insertRecursiveBranchLoopIcon(tmp_icon, icon.getEndBranch().getLeftNeighbourTop(), icon.getEndBranch().getLeftNeighbourTop().getLeftNeighbour());
                }
                else
                {
                    int xpos2 = gridcalculation.calculateXright(icon.getEndBranch().getLeftNeighbourTop().getCoordinate().getX());
                    int ypos2 = gridcalculation.calculateY(icon.getEndBranch().getLeftNeighbourTop().getCoordinate().getY());
                    jProgrammingWindow.setUpIcon(tmp_icon, xpos2, ypos2);
                    
                    insertObjectIcon(tmp_icon, icon.getEndBranch().getLeftNeighbourTop(), icon.getEndBranch().getLeftNeighbourTop().getLeftNeighbour());
                }
            }
            // then do the else_part
            for(int i=1; i<(tmp_members2.size()-1); i++)
            {
                Icon tmp_icon = tmp_members2.get(i);
                if(tmp_icon instanceof branchIcon || tmp_icon instanceof loopIcon)
                {                    
                    insertRecursiveBranchLoopIcon(tmp_icon, icon.getEndBranch().getLeftNeighbourBottom(), icon.getEndBranch().getLeftNeighbourBottom().getLeftNeighbour());
                }
                else
                {
                    int xpos2 = gridcalculation.calculateXright(icon.getEndBranch().getLeftNeighbourBottom().getCoordinate().getX());
                    int ypos2 = gridcalculation.calculateY(icon.getEndBranch().getLeftNeighbourBottom().getCoordinate().getY());
                    jProgrammingWindow.setUpIcon(tmp_icon, xpos2, ypos2);
                    insertObjectIcon(tmp_icon, icon.getEndBranch().getLeftNeighbourBottom(), icon.getEndBranch().getLeftNeighbourBottom().getLeftNeighbour());
                }    
            }
        }
        else if(icon instanceof loopIcon)
        {
            tmp_members1 = icon.getMembers();
            
            // turns off the icon's listeners first
            for(int i=0; i<tmp_members1.size(); i++)
            {
                Icon tmpic = tmp_members1.get(i);
                tmpic.turnoffListeners();
            }
            
            icon.setTopVerticalSize(0);
            icon.setBottomVerticalSize(0);
            icon.delAllMembers();
            insertLoopIcon(icon, tic, tic2, tic, tic2);    // re-insert the loop as if it was empty
            
            // now insert all of the existing members of this loop
            
            for(int i=1; i<(tmp_members1.size()-1); i++)
            {
                Icon tmp_icon = tmp_members1.get(i);
                if(tmp_icon instanceof branchIcon || tmp_icon instanceof loopIcon)
                {                    
                    insertRecursiveBranchLoopIcon(tmp_icon, icon.getEndIcon(), icon.getEndIcon().getLeftNeighbour());
                }
                else
                {
                    int xpos2 = gridcalculation.calculateXright(icon.getEndIcon().getCoordinate().getX());
                    int ypos2 = gridcalculation.calculateY(icon.getEndIcon().getCoordinate().getY());
                    jProgrammingWindow.setUpIcon(tmp_icon, xpos2, ypos2);
                    
                    insertObjectIcon(tmp_icon, icon.getEndIcon(), icon.getEndIcon().getLeftNeighbour());
                }
            }
        }    
        repaint();
        validate();
    }
    
    // method that inserts an branch icon and loop icons
    private void insertBranchIcon(Icon icon, Icon tic, Icon tic2)
    {
        // introduce the join icon to reconnect split wires at the end of the branching segment of the code
        joinEndBranchIcon jic = _JEBIcon.Clone();
        joinIcon jic1 = _Jicon.Clone();
        joinIcon jic2 = _Jicon.Clone();
        joinIcon jic3 = _Jicon.Clone();
        joinIcon jic4 = _Jicon.Clone();                
        jProgrammingWindow.insertBranchIcon(icon, tic, tic2, jScrollPane1, jic, jic1, jic2, jic3, jic4);
        icon.turnonListeners();
        icon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));  // to change the cursor
        branch_init_replace_listener(icon);
        repaint();
        validate();
    }
    
    // method that inserts a loop icons
    private void insertLoopIcon(Icon lic, Icon tic, Icon tic2, Icon tic3, Icon tic4)
    {        
        // introduce the join icon at the beginning of the loop
        joinLoopIcon jlc = _Jloop.Clone();
        joinLoopIcon jlc2 = _Jloop.Clone();
        jProgrammingWindow.insertLoopIcon(lic, tic, tic2, tic3, tic4, jScrollPane1, jlc, jlc2);  
        lic.turnonListeners();
        lic.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));  // to change the cursor
        loop_init_replace_listener(lic);
        repaint();
        validate();
    }
    
    // to reset the mouse listeners for jProgrammingWindow and turn off listeners for its icons
    private void jPanel7offListeners()
    {
        java.awt.event.MouseListener[] ml = jProgrammingWindow.getMouseListeners();
        jProgrammingWindow.removeMouseListener(ml[0]);
    }
    
    
    // to reset the mouse listeners for jProgrammingWindow and turn on listeners for its icons
    private void jPanel7onListeners()
    {  
        java.awt.event.MouseListener[] ml = jProgrammingWindow.getMouseListeners();
        for(int i=0; i<ml.length; i++)
        {
            jProgrammingWindow.removeMouseListener(ml[i]);
        }
        jProgrammingWindow.init_listener();
    }
    
    
    // MAYBE to prep a button
    private javax.swing.JButton prepButton(int horizontal_size, int vertical_size, String filepath)
    {
        javax.swing.JButton jb = new javax.swing.JButton();
        jb.setIcon(new javax.swing.ImageIcon(getClass().getResource(filepath)));
        jb.setBorder(null);
        jb.setMaximumSize(new java.awt.Dimension(horizontal_size, vertical_size));
        jb.setMinimumSize(new java.awt.Dimension(horizontal_size, vertical_size));
        jb.setPreferredSize(new java.awt.Dimension(horizontal_size, vertical_size));
        return jb;
    }
    
    // to prep a handler process for a branch or object icon
    private void prepIconHandlers(Icon m, int type)
    {
        final Icon mic = m;
        final int tt = type;
        jPanel7offListeners();        
        DirectionsLabel.setText("Click on the programming window to position the icon");
        
        jProgrammingWindow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt2) {                
                jPanel7MouseClicked2(evt2, mic, tt);
                
                // reset the panel listeners                
                jPanel7onListeners();
            }
        });
    }
    
    // to prep a handler process for a loop icon
    private void prepLoopIconHandlers(Icon m, int type)
    {
        final Icon mic = m;
        final int tt = type;
        jPanel7offListeners();        
        DirectionsLabel.setText("Click on the programming window to position the beginning of the loop");
        
        jProgrammingWindow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt2) {
                
                jPanel7MouseClicked2(evt2, mic, tt);
                }
        });
    }
    
    
    // to create an iconic button
    private <TIcon extends Icon> void createIconicButton(int width, int height, String filepath, int gridx, int gridy, javax.swing.JPanel panel, String title, TIcon iconPrototype)
    {
        javax.swing.JButton jButtonIcon = setupIconicButton(width, height, filepath, gridx, gridy, panel, title, iconPrototype);
        
        if (iconPrototype instanceof loopIcon)
        {
            jButtonIcon.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    iconLoopMouseClicked(evt, iconPrototype.Clone(), 1);
                }
            });
        } else {
            jButtonIcon.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    iconMouseClicked(evt, iconPrototype.Clone(), 1);
                }
            });
        }
    }
    
    // to replace an iconic button
    private <TIcon extends Icon> void replaceIconicButton(int width, int height, String filepath, int gridx, int gridy, javax.swing.JPanel panel, String title, TIcon iconReplacementPrototype, TIcon iconParent)
    {
        javax.swing.JButton jButtonIcon = setupIconicButton(width, height, filepath, gridx, gridy, panel, title, iconReplacementPrototype);
        
        if (iconReplacementPrototype instanceof objectIcon)
        {
            jButtonIcon.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    objectIconReplaceClicked(evt, iconReplacementPrototype.Clone(), iconParent);
                }
            });
        }
        else if (iconReplacementPrototype instanceof branchIcon)
        {
            jButtonIcon.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    branchIconReplaceClicked(evt, iconReplacementPrototype.Clone(), iconParent);
                }
            }); 
        }
        else if (iconReplacementPrototype instanceof loopIcon)
        {
            jButtonIcon.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    loopIconReplaceClicked(evt, iconReplacementPrototype.Clone(), iconParent);
                }
            });        
        }
    }
    
    private <TIcon extends Icon> javax.swing.JButton setupIconicButton(int width, int height, String filepath, int gridx, int gridy, javax.swing.JPanel panel, String title, TIcon iconPrototype)
    {
        javax.swing.JButton jButtonIcon;
        jButtonIcon = prepButton(width, height, filepath);
        
        javax.swing.JPopupMenu jpm = new javax.swing.JPopupMenu();
        javax.swing.JMenuItem jm = new javax.swing.JMenuItem();
        jm = new javax.swing.JMenuItem("Help");
        jpm.add(jm);
        final TIcon parent = (TIcon)(iconPrototype).Clone();
        jm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                    javax.swing.JOptionPane.showMessageDialog(jPanelMainCenter,
                            parent.getHelpDesc(),
                            "Context Help -- " + parent.getHelpTitle(),
                            javax.swing.JOptionPane.INFORMATION_MESSAGE,
                            parent.getHelpIcon());
            }
        });  
        
        jButtonIcon.setComponentPopupMenu(jpm);
        
        jButtonIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                iconMouseEntered(title);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                iconMouseExited();
            }
        });
        
        java.awt.GridBagConstraints gridBagConstraints= new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        panel.add(jButtonIcon, gridBagConstraints);
        
        return jButtonIcon;
    }
    
    // for task icons 
    private int promptTask(Icon icon)
    {
        java.util.Vector<auxiliary> al = aux_list.Instance().getAuxiliaries();
        int total_task=0;
        for(int i=1; i<al.size(); i++)
        {
            if(al.get(i) != null && al.get(i) instanceof task)
            {
                total_task++;
            }
        }
        if(total_task > 1)
        {
            ChooseTaskDialog tsk_dialog = new ChooseTaskDialog(this);
            tsk_dialog.pack();
            tsk_dialog.setLocationRelativeTo(this);
            tsk_dialog.setVisible(true);
            int num_id = tsk_dialog.getUserInput();
            if(num_id == -1)
            {
                return 0;  // user pressed cancel
            }
            icon.setTask((task)aux_list.Instance().getAuxiliaries().get(num_id));
            return 1; // everything is fine
        }
        else
        {
            javax.swing.JOptionPane.showMessageDialog(null,
                                    "There should be at least one task apart from 'main' created.\nTask 'main' cannot be started within itself",
                                    "Task setting invalid",
                                    javax.swing.JOptionPane.WARNING_MESSAGE);
            DirectionsLabel.setText(readyPrompt);            
        }
        return 0;
    }
    
    
    // for event icons    
    // for task icons 
    private int promptEvent(Icon icon)
    {
        java.util.Vector<auxiliary> al = aux_list.Instance().getAuxiliaries();
        int total_event=0;
        for(int i=1; i<al.size(); i++)
        {
            if(al.get(i) != null && al.get(i) instanceof monitorEvent)
            {
                total_event++;
            }
        }
        if(total_event > 0)
        {
                ChooseEventDialog evt_dialog = new ChooseEventDialog(this, getCurrentAuxiliary());
                evt_dialog.pack();
                evt_dialog.setLocationRelativeTo(this);
                evt_dialog.setVisible(true);
                monitorEvent to_set = evt_dialog.getUserInput();
                if(to_set == null)
                {
                    return 0;  // user pressed cancel
                }
                icon.setEventMonitor(to_set);
                return 1; // everything is fine
        }
        else
        {
            javax.swing.JOptionPane.showMessageDialog(null,
                "There should be at least one event monitor defined.",
                "Event setting invalid",
                javax.swing.JOptionPane.WARNING_MESSAGE);
            DirectionsLabel.setText(readyPrompt);            
        }
        return 0;
    }    
    
    // for function icons 
    private int promptFunction(Icon icon)
    {
        java.util.Vector<auxiliary> al = aux_list.Instance().getAuxiliaries();
        int total_function=0;
        for(int i=1; i<al.size(); i++)
        {
            if(al.get(i) != null && al.get(i) instanceof function)
            {
                total_function++;
            }
        }
        if(total_function > 0)
        {
            function f;
            if(getCurrentAuxiliary() instanceof function)
            {
                f = (function)getCurrentAuxiliary();
            }
            else
            {
                f = null;
            }
                ChooseFunctionDialog func_dialog = new ChooseFunctionDialog(this, f);
                func_dialog.pack();
                func_dialog.setLocationRelativeTo(this);
                func_dialog.setVisible(true);
                int num_id = func_dialog.getUserInput();
                            
                if(num_id == -1)
                {
                    return 0;  // user pressed cancel
                }   
                
                function new_func = (function)aux_list.Instance().getAuxiliaries().get(num_id);
                functionIcon FI = (functionIcon)icon;
                if(new_func.getArguments().size() > 0)
                {                    
                    FunctionArgumentDialog fad = new FunctionArgumentDialog(this, new_func, getCurrentAuxiliary(), null);
                    fad.pack();
                    fad.setLocationRelativeTo(this);
                    fad.setVisible(true);

                    java.util.Vector<Operand> ret_val = fad.getUserInput();
                    
                    if(ret_val != null)
                    {                        
                        FI.setFunction(new_func);  
                        FI.setArguments(ret_val);                        
                    }
                }      
                else
                {                    
                        FI.setArguments(new java.util.Vector<Operand>());
                        FI.setFunction(new_func);
                }
                
                return 1; // everything is fine
        }
        else
        {
            javax.swing.JOptionPane.showMessageDialog(null,
                "There should be at least one function.",
                "Function setting invalid",
                javax.swing.JOptionPane.WARNING_MESSAGE);
            DirectionsLabel.setText(readyPrompt);            
        }
        return 0;
    }    

    private void updateWindowTitle() {
        setTitle(AppProperties.appNameVersion + " - " + AppProperties.getDeviceName() + " - " + _Current_Save_File);
    }
    
    private void updateFileName(String fileName) {
        _Current_Save_File = fileName;
        updateWindowTitle();
    }
    
    private void updateTargetDevice() {
        if (jRadioButtonMenuItemRCX1.isSelected()) {
            AppProperties.setDeviceID("RCX");
            AppProperties.setDeviceName("RCX 1.0");
        } else if (jRadioButtonMenuItemRCX2.isSelected()) {
            AppProperties.setDeviceID("RCX2");
            AppProperties.setDeviceName("RCX 2.0");
        } else {
            AppProperties.setDeviceID("null");
            AppProperties.setDeviceName("undefined");
        }
        
        updateWindowTitle();
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private funsoftware.pallette.TitlePanelLabel BranchesLabel;
    private funsoftware.pallette.RoundedScrollPane BranchesPane;
    private funsoftware.pallette.CardPanel CenterCardPanel;
    private funsoftware.pallette.TitlePanelLabel DirectionsLabel;
    private funsoftware.pallette.TitlePanelLabel EditingLabel;
    private funsoftware.pallette.RoundedScrollPane EditingPane;
    private funsoftware.pallette.TitlePanelLabel EventMonitorsLabel;
    private funsoftware.pallette.RoundedScrollPane EventMonitorsPane;
    private funsoftware.pallette.TitlePanelLabel EventsLabel;
    private funsoftware.pallette.RoundedScrollPane EventsPane;
    private funsoftware.pallette.TitlePanelLabel FunctionsLabel;
    private funsoftware.pallette.RoundedScrollPane FunctionsPane;
    private funsoftware.pallette.TitlePanelLabel FunctionsReuseLabel;
    private funsoftware.pallette.RoundedScrollPane FunctionsReusePane;
    private funsoftware.pallette.TitlePanelLabel IconHelpLabel;
    private funsoftware.pallette.RoundedScrollPane IconHelpPane;
    private funsoftware.pallette.TitlePanelLabel LoopsLabel;
    private funsoftware.pallette.RoundedScrollPane LoopsPane;
    private funsoftware.pallette.TitlePanelLabel SinglesLabel;
    private funsoftware.pallette.RoundedScrollPane SinglesPane;
    private funsoftware.pallette.TitlePanelLabel TasksLabel;
    private funsoftware.pallette.RoundedScrollPane TasksPane;
    private funsoftware.pallette.TitlePanelLabel VariablesLabel;
    private funsoftware.pallette.RoundedScrollPane VariablesPane;
    private javax.swing.ButtonGroup buttonGroupTarget;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanelMainCenter;
    private javax.swing.JPanel jPanelMainLeft;
    private javax.swing.JPanel jPanelMainRight;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItemRCX1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItemRCX2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    // End of variables declaration//GEN-END:variables
    
    // manually added variables declaration by me -----
    
    // the pallettes
    private static ProgWindow jProgrammingWindow;   // the programming window
//    private objectWindow jPanel2;  // the object icons window, can be reused for other similarly coloured pallettes
//    private javax.swing.JScrollPane objectScroller;
    
//    private loopWindow jPanel3;  // the loop icons window
//    private javax.swing.JScrollPane branchScroller;
    
//    private branchWindow jPanel5;  // the branch icons window
//    private javax.swing.JScrollPane loopScroller;    
    
    // -- ALL of the left hand side pallettes
    // (all have been moved to designer)
    
    // for the centralProgrammingWindow
//    private CardPanel jPanel13;
    
//    private bottomRightWindow jPanel11;  // the bottom right window
//    private directionWindow jPanel10;
    private FunctionWindow jPanelFunction;  // the function window
    private TaskWindow jPanelTask;    // the task window
    private eventWindow jPanelEvent;    // the event window
    private monitorEventWindow jPanelEventMonitor;  // the event monitor window
    private static javax.swing.JScrollPane jScrollPane1;
//    private static javax.swing.JPanel jPanel18;   // the panel on top of the programming window
    private VarWindow jPanelVar;
    
//    private javax.swing.JPanel jPanel19;    // the title panel for object icons
//    private javax.swing.JPanel jPanel20;    // the title panel for branch icons
//    private javax.swing.JPanel jPanel21;    // the title panel for loop icons
    
    // the icon buttons
    private javax.swing.JButton jButtonGridMode;   // for grid mode button
    private javax.swing.JButton jButtonUndo;   // for undo button
    private javax.swing.JButton jButtonRedo;   // for redo button
    private javax.swing.JButton jButtonCancel; // for cancel button
    
    // for icon name help
    private javax.swing.JLabel jLabelHelp;
    
    // the grid dialog and syntax error dialog
    private GridDialog grid_dialog;
    
    // the icons ready to be cloned
    // the objects
    
    private motorIcon _motor;
    private stopMotorIcon _stopmotor;
    private beepIcon _beep;
    private lampIcon _lamp;
    private directionalMotorIcon _dirmotor;
    private timerIcon _timer;
    private clearSensorIcon _clearsensor;
    private clearTimerIcon _cleartimer;
    private playAnyNoteIcon _playanynote;
    private floatMotorIcon _float;
    private playSpecificNote _specificnote;
    private returnIcon _returnicon;    
    private waitTouchIcon _waittouch;      
    private waitLightIcon _waitlight; 
    private waitCelciusIcon _waitcelcius;
    private waitRotationalIcon _waitrotation;
    private waitTimerIcon _waittimer;
    
    // the branches
    
    private BtouchSensorIcon _Btouch;
    private BlightSensorIcon _Blight;
    private BcelciusSensorIcon _Bcelcius;
    private BrotationSensorIcon _Brotation;
    private BtimerIcon _Btimer;
    private BrandomIcon _Brandom;
    private BarithmeticIcon _Barithmetic;      
    
    // the loops
    
    private repeatIcon _Lrep;
    private lightSensorLoopIcon _Llight;
    private infiniteLoopIcon _Linfinite;
    private timerLoopIcon _Ltimer;
    private rotationSensorLoopIcon _Lrotation;
    private celciusSensorLoopIcon _Lcelcius;
    private touchSensorLoopIcon _Ltouch;    
    private arithmeticLoopIcon _Larithmetic;       
            
    // the auxiliary
    
    private joinIcon _Jicon;
    private joinLoopIcon _Jloop;
    private joinEndBranchIcon _JEBIcon;
    
    // the task icons
    
    private startTaskIcon _Start;
    private endTaskIcon _End;
    private beginTaskIcon _BeginTask;
    private stopTaskIcon _StopTask;
    
    // the function icon and the function skeleton
    
    private functionIcon _Funcby;
    private function _Funct;
    private task _Task;
    private Event _Event;
    private monitorEvent _mEvent;
    
    // for events
    private startEvent _SEvent;
    
    // the variable and arithmetic operation icon
    private variable _Var;
    private ArithOpIcon _AOIcon;
    
//    private int distanceX;  // horizontal uniform distance between grid intersection
//    private int distanceY;  // vertical uniform distance between grid intersection
    
    private wires wire_list;  // the list containing the wires
    private icons_list icon_list;  // the list containing the icons
    
//    private int maxX;    // maximum X value of the programming window
//    private int maxY;    // maximum Y value of the programming window
//    private javax.swing.JViewport viewport;  // the viewport of the scrollpane1
    
    private states do_state = states.Instance();    // for undo and redo (memento design pattern) and to check whether a state has been saved in a file
    
    private FunctionCombo fcombo;  // function combo list
    
//    private auxiliary current_aux;  // currently displayed function or task
    
    private String _Current_Save_Path = new String();
//    private String ConfigFile_Name;
    
    // Track the current file name
    private String _Current_Save_File = "New File";
    
    // The "ready" prompt text
    private String readyPrompt = AppProperties.appName + " ready for inputs";
}