@echo off

if not "%JAVA_HOME%" == "" goto gotJavaHome
echo The JAVA_HOME environment variable is not defined
echo This environment variable is needed to run this program
goto end

:gotJavaHome
if not exist "%JAVA_HOME%\bin\java.exe" goto noJavaHome
goto okJavaHome

:noJavaHome
echo The JAVA_HOME environment variable is not defined correctly
echo This environment variable is needed to run this program
echo NB: JAVA_HOME should point to a JDK/JRE
goto end

:okJavaHome

if NOT DEFINED OCTITBIT_HOME set OCTITBIT_HOME=%~dp0

if NOT DEFINED OCTITBIT_LIB set OCTITBIT_LIB="%OCTITBIT_HOME%lib"

if NOT DEFINED OCTITBIT_TP set OCTITBIT_TP=com.ipc.oce.TestPlatform

REM echo OCTITBIT_HOME: %OCTITBIT_HOME%
REM echo OCTITBIT_LIB: %OCTITBIT_LIB%
REM echo OCTITBIT_TP: %OCTITBIT_TP%

set LOCALCLASSPATH="%CLASSPATH%";%OCTITBIT_LIB%\!ANT_COREJAR_RPL!;%OCTITBIT_LIB%\optional\jcifs-1.2.19.jar;%OCTITBIT_LIB%\optional\j-interop.jar;%OCTITBIT_LIB%\optional\j-interopdeps.jar;%OCTITBIT_LIB%\optional\commons-logging.jar

"%JAVA_HOME%\bin\java" -cp %LOCALCLASSPATH% -Dorg.apache.commons.logging.simplelog.defaultlog=trace "%OCTITBIT_TP%" %*
:end
pause
