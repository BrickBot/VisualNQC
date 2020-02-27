# VisualNQC
 An iconic language to for the Lego MindStorms RCX, which generates NQC code.  Support is included to facilitate integrating the NQC compilation and deployment processes.  (This project was formerly known as fUNSoftWare.)

        ----------------
-----  fUNSoftWare  -----
        ----------------

Version: 1.0
Author: Thomas Legowo and Eric Martin
Copyright of UNSW School of Computer Science and Engineering

---------------------------------
|  Table of Contents         |
---------------------------------

1 Requirements
2 Introduction
3 Basic features
    3.1 Icon types 
    3.2 Icon insertion
    3.3 Icon deletion
    3.4 Icon replacement
    3.5 Icon relocation
    3.6 Copying an icon
    3.7 Editing icon attributes
    3.8 Displaying icon properties and help
    3.9 Undo/Redo
    3.10 Execution to RCX
4 Advanced Features
    4.1 Tasks
    4.2 Functions
    4.3 Events
    4.4 Variables and arithmetic operations
    4.5 NQC code
5 Acknowledgements

----------------------------------


>>>>> 1 Requirements <<<<<
----------------------------------------

fUNSoftWare needs the following set-up:

1. Java 1.5 or above.
The latest Java environment can be downloaded from 
http://java.sun.com/j2se/1.5.0/download.jsp
2. NQC 3.1 r4 or above
The latest version of NQC can be downloaded from
http://bricxcc.sourceforge.net/nqc/welcome.html

fUNSoftWare can be run on any Operating System, with Windows and MacOS 
as the recommended operating systems.


>>>>> 2 Introduction <<<<<
--------------------------------------

fUNSoftWare is an icon based software program used to program the LEGO Mindstorms robots.
It is able to utilise the robots' motors, lamps, sensors (touch sensors, light sensors, 
rotational sensors, temperature sensors), timers, and speakers. Equipped with powerful
features, fUNSoftWare also teaches the basics of programming concepts: functions, variables, 
branches, loops, and arithmetic operations. It even encourages modular programming.

In order to program a robot, you will have to do the following steps:
1. Create a program that involves stringing together icons from left to right and editing 
them where appropriate. fUNSoftWare might generate some error messages throughout the 
process if you attempt to execute an illegal operation. One of the great features of fUNSoftWare
is that all the wiring is done automatically. Whether you want to insert or delete icons, the necessary
wires that link the icon you are working on with the surrounding icons will be created or 
deleted automatically. 
2. After you are satisfied with the program, you will need to compile it. The result 
of the compilation is an NQC (Not Quite C) text code that is guaranteed to run (though
maybe not as you want it to run!) on the LEGO robot.
3. The robot might not perform the task you wanted because you inserted icons that do not perform
their intended operations. You will then need to edit the program and repeat processes 2 
and 3 until everything works as you originally had in mind.

Now understanding the basics of creating a program in fUNSoftWare.

You will start off with a main graph consisting of two traffic light icons, the left one of being set 
to green and the right one to red. Any icon that you wish to use should be placed between the 
green and red traffic lights, and the program will read them sequentially from left to right. It is 
possible to create another graph with different sets of icons and a new set of traffic lights in one of 
three kinds of structures, called tasks, functions or events.


>>>>> 3 Basic features <<<<<
----------------------------------------

-->> 3.1 Icon types
==============

There are three types of icons:
1. Singles
   An icon of this type accomplishes one single operation. Upon execution, this icon
   does not affect the rest of the program.

Following is a schematic representation of two "single" icons strung next to each other:

----       ----
|  |------|  |
----       ----


2. Branches
   An icon of this type determines one out of two possible paths that the program takes 
   based on a check on whether a specified condition (can be using sensor readings or an 
   arithmetic expression) is met.

Following is a schematic representation of a "branch" icon:
     
     |---
     |
----|
|  |
----|
     |
     |---

3. Loops
   An icon of this type decides whether or not to repeat a designated sequence of icons 
   based on a check on whether a specified condition (can be using sensor readings or an 
   arithmetic expression) is met.

Following is a schematic representation of a "loop" icon:

      |                      |
      |         ----        |
      |-------|  |-------|
                ----


-->> 3.2 Icon insertion
================

To insert a "single" typed icon, simply click on the "single" icon that you want, then click
once more on the programming palette to place it. 

To insert a "branch" typed icon, simply click on the "branch" icon that you want, then click 
once more on the programming palette to place it.
 
To insert a "loop" typed icon, simply click on the "loop" icon that you want, then you will 
need to click twice on the programming palette to determine the beginning and the end of 
the loop, respectively.

The program will let you know if the icon insertion process was unsuccessful due to illegal 
steps taken by you.

If after you have selected an icon, you decide not to insert it, then simply select the 
"Cancel" button on the top right hand side of the window.


-->> 3.3 Icon deletion
===============

To delete a "single" typed icon, simply right click on the "single" icon that you want to 
delete, then select "Delete icon".

To delete a "branch" typed icon, simply right click on the "branch" icon that you want to 
delete, then select "Delete". You will then need to select whether to delete the branch 
with all of the icons inside both of its paths or just one of the paths (in which case you will have
to indicate which path you want to delete and which one you want to keep).

To delete a "loop" typed icon, simply right click on the "loop" icon that you want to 
delete, then select "Delete". You will then need to indicate whether to delete the loop icon 
with all of the icons inside it or just the icon itself.


-->> 3.4 Icon replacement
==================

You can replace an icon by simply right clicking the icon, then select "Replace icon", then 
select the desired icon as the replacement.


-->> 3.5 Icon relocation
=================

For a "single" typed icon, simply right click on the icon, then select "Relocate icon". You will 
then need to click on the programming palette to determine the new position of the icon.

For a "branch" typed icon, simply right click on the icon then select "Relocate icon". You will 
then need to click on the programming palette to determine the new position of the icon. All icons
in its paths will also be relocated to positions relative to the branch icon.

For a "loop" typed icon, simply right click on the icon then select "Relocate icon". You will 
then need to click on the programming palette to determine the new position of the icon. All
icons enclosed will also be relocated to positions relative to the loop icon.


-->> 3.6 Copying an icon
=================

Simply right click on the desired icon, then left click on the programming palette to place 
the copied icon. The copied icon will have the same attribute values as the original icon.

For a "loop" icon, after "Copy icon" is selected, you will need to click twice on the programming 
palette just as if you were inserting a new "loop" icon.


-->> 3.7 Editing icon attributes
======================

All kinds of icons in fUNSoftWare have their attributes ready to be edited without the need to add 
more icons. Some attributes are displayed and can be edited by clicking on them. Other attributes 
can be edited through a small pop up window.

For certain attributes, you will need to have the following information before continuing:
1. Light sensor value:
    ranges between 0 (bright) to 100 (dark)
2. Temperature sensor value:
    1 = 1/10 Celcius
3. Rotational sensor value:
    1 = 1/16 of one motor rotation
4. Timer value:
    1 = 1/10 seconds
5. Note duration:
    1 = 1/100 seconds


-->> 3.8 Displaying icon properties and help
===============================


By right clicking on any icon and selecting "Properties", the corresponding icon's attributes and 
their respective values will be displayed.

By right clicking on any icon and selecting "Help", the corresponding icon's context help will be
displayed. This will explain each of the icon's attributes.

-->> 3.9 Undo/Redo
===============

On the top right corner of the program's window, there are two buttons for undo and redo. "Undo" 
is used to restore the previous state of the program and "Redo" is used to restore the state of 
the program before an undo operation was carried out.


-->> 3.10 Execution to RCX
====================

If you select "Compile and Download" or "Compile and Run" in the menu, an NQC code will be 
generated and executed on the robot. "Compile and Download" will download your program into
the LEGO robot and then you will need to press the green button "run" on the robot to get it to start
on running your program. "Compile and Run" will download and automatically run your program 
on the LEGO robot.

You should make sure that the robot is within the infrared tower's range, and is properly 
equipped to accomplish your task.


>>>>> 4 Advanced features <<<<<
-----------------------------------------------

-->> 4.1 Tasks
===========

A task can be executed in parallel to other tasks and can be started or stopped anytime. A task called 
"main" is set by default and cannot be renamed nor deleted. It is the starting point whenever a 
program of your creation is to be executed.


-->> 4.2 Functions
=============

A function is different to a task. It can be re-used anywhere. It is used to make programs more 
modular and readable.

A "level" attribute for function is used to determine "who calls whom". A function can only call 
another function if and only if its level is higher than the level of the called functions.

A function can have arguments. Arguments are local variables that are passed onto the function to 
be used within the function only.


-->> 4.3 Events
===========

There are two elements for this feature:
1. Event Definition

An event is a process where the LEGO robot checks a selected sensor or timer. It consists of:
1. Type of sensor or timer
2. Input port of the sensor or the timer number
3. Comparator. (E.g. for a touch sensor, detect whether the sensor is being touched or released
whereas for a light sensor, detect whether its reading is greater than or less than a specified value). 

A maximum of 16 different events can be defined in a program.

2. Event Monitor

An event monitor is a process where the LEGO robot executes a set of icons while waiting for one or
more events to be satisfied. 

An event monitor consists of:

1. Type (Local or Global)
2. Events to be monitored.
3. Associated code to be executed as long as no event has detected the associated reading.
If and when an event that is being monitored "happens", then execution of the code associated 
with the event monitor stops and execution of the program jumps to right after the icon that has 
started monitoring the event.

A "Local" event monitor implies that it can only be started from a specified task or function that has
a higher level than the event monitor. An arithmetic operation in such event monitor can use the 
specified task or function's local variables.

A "Global" event monitor implies that it can be started from any task or function, but then cannot use
any of the local variables of the task or function.

No event monitor can start another event monitor. 

A "level" attribute for event monitor is used to determine "which function call it". A function can only
call an event monitor if and only if its level is higher than the level of the called event monitor.

-->> 4.4 Variables and arithmetic operations
================================

Variable take integer values. These values change according to arithmetic operations applied to 
the variable.

An arithmetic operation is of the form
 
Variable = Operand

where "Operand" is one of
1. Constant
2. Variable
3. Operand Operator Operand

and "Operator" is one of
1. +
2. -
3. *
4. /

The variables SENSOR_1, SENSOR_2 and SENSOR_3 are special: they hold the values of the sensors 
connected to ports 1, 2 and 3, respectively, if any. So their values will change over time, and as 
such they are variables. But contrary to other kinds of (global or local) variables, their values cannot 
be set or changed by the user.

A variable that you have defined can be used in some icon attributes. You need to click on the
desired attribute in the usual way, then you will need to type in "v". A small pop up window will appear
and ask you to select which variable should be associated with the attribute.

Icons that allow the use of variable together with the attribute:
1. Motor Icon for its power level
2. Lamp Icon for its power level
3. Play Any Note Icon for its frequency
4. Wait Icon for the amount of time to wait
5. Wait Light Sensor Icon for the value threshold
6. Wait Temperature Sensor Icon for the value threshold
7. Wait Rotational Sensor Icon for the value threshold
8. Wait Timer Sensor Icon for the value threshold
9. Branch Light Sensor Icon for the value threshold
10. Branch Temperature Sensor Icon for the value threshold
11. Branch Rotational Sensor Icon for the value threshold
12. Branch Timer Sensor Icon for the value threshold
13. Loop Light Sensor Icon for the value threshold
14. Loop Temperature Sensor Icon for the value threshold
15. Loop Rotational Sensor Icon for the value threshold
16. Loop Timer Sensor Icon for the value threshold
17. Repeat Icon for the number of repeat


-->> 4.5 NQC code
=============

NQC is Not Quite C and it is a text based program used to program the LEGO robot. Unlike fUNSoftWare, 
you will need to input purely text without the use of any icons. 
To create a corresponding NQC code from your current program, you will need to save your program,
then select the menu "Build", then "Create NQC Code". Alternatively, you can just press "F-5". An NQC
file will be created afterwards and that file will use the same name as your save file but with "nqc" as the
extension of the file. The location of the NQC file will also be in the same directory as your save file.

Example: If you saved your program with under a name "runfast", then the NQC file generated 
will be named "runfast.nqc".


>>>>> 5 Acknowledgements <<<<<
------------------------------------------------

Mindstorms and LEGO are trademarks of the LEGO Group
NQC is a free software released under the Mozilla Public License (MPL).

fUNSoftWare development team:
Eric Martin --> Supervisor
Thomas Legowo --> Programmer
Geoffrey Roberts --> Graphic Designer

fUNSoftWare is released under the University of New South Wales School of Computer Science 
and Engineering.

