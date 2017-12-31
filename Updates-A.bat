@echo off
Title Almighty's updates
echo -------------------------------------------------------
echo -Isn't this cool Ben? You can steal it if you want. :)-
echo -------------------------------------------------------
:choice
set /P c=Would you like to browse the updates or go to the credits[U/C]?
if /I "%c%" EQU "U" goto :Updates.
if /I "%c%" EQU "C" goto :Runeserver.
goto :choice
pause

:Updates.
echo ------------------------------
echo ------------------------------
echo -Updates - Almighty        ---
echo ------------------------------
echo -Updates for Client here   ---
echo ------------------------------
echo -10/19/2017:                --
echo -Added Recoloring Method(RDC)-
echo ------------------------------
echo ------------------------------
echo -10/20/2017:                --
echo -Renamed ints in ItemDef    --
echo ------------------------------
echo ------------------------------
echo ------------------------------
echo -Updates for Source here    --
echo ------------------------------
echo ------------------------------
echo ------------------------------
pause

:Runeserver.
start https://www.rune-server.ee/members/isaiah_/
ECHO Don't forget to thank, and rep.
pause 


