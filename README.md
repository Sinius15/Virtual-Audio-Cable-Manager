Virtual Audio Cable Manager
================================

You can use this application to manage multable Audio Repeaters. It lest you automatic start multable Repeaters at once and close them all with one button press. 

If you want to know how to use this application, visit the wiki!

Also, while creating this application, i automaticly committed every 30 seconds the code changes. This is why this project has so much commits.

From now on:
VAC = Virtual Audio Cable
VACM = Virtual Audio Cable Manager


How to install?
----------------
To use this VACM you first need VAC. You can download VAC at [the official website](http://software.muzychenko.net/eng/vac.htm#download). If you have VAC installed, you have to download VACM. Down here you can find the direct links to all the versions:

| [Version 1.1](https://github.com/Sinius15/Virtual-Audio-Cable-Manager/raw/master/release/Virtual%20Audio%20Cable%20Manager%20v1.1.jar) |
|-------------|
| [Version 1.0](https://github.com/Sinius15/Virtual-Audio-Cable-Manager/raw/master/release/Virtual%20Audio%20Cable%20Manager%20v1.0.jar) |

When you are done downloading, double click on the downloaded file and VACM should start up.

Arguments?!
----------------
If you want to start VACM via the command line, you can use a few arguments. The first argument must always be a absolute path to a config file. If this argument is detected, VACM will automaticly load the config file. Here is an example:
````
java -jar "Virtual Audio Cable Manager v1.1.jar" "U:\configFile.yml"
````
This will start Virtual Audio cable and automaticly load configuration from the file.

If you want to load the config and automaticly start all the repeaters, you can use the argument 'autoStart':
````
java -jar "Virtual Audio Cable Manager v1.1.jar" "U:\configFile.yml" autoStart
````

But wait, there is more: If you want to start VACM hidden in the iconbar, you can use the argument 'min':
````
java -jar "Virtual Audio Cable Manager v1.1.jar" "U:\configFile.yml" autoStart min
````
Here are some more arguments that do not need an example:

|Argument|What does it do?|
|-------|-------|
|autostart| start all the loaded cables.|
|autostop | stop all the loaded cables.|
|quit | Quit VACM inmediately|
|min | Minimize VACM to the Icon Tray|

The cool thing about these arguments is that you can change the order of them. What do you think this will do?
````
java -jar "Virtual Audio Cable Manager v1.1.jar" "U:\configFile.yml" min autostop autostart quit
````
This will restart the running audiorepeaters and when done quit the application.



License
----------------
Dont touch my stuff without asking unless i explicity said you could touch it. If you want to contribute to this project, you can. Just ask and i will 99% of the time say yes.

Bugs
----------------
If you find any, please create a bug-report at github and i will do the best i can to fix it.
