@echo off
cd /d "%~dp0"
java -jar "build\jar\Escape_The_Lost_World_V2.jar"
if errorlevel 1 pause
