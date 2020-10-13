@echo off
echo =-------------------
echo git add -A
echo -------------------=
git add -A
echo =-------------------
echo git commit -m "committed by lida"
echo -------------------=
git commit -m "committed by lida"
echo =-------------------
echo git pull
echo -------------------=
git pull
echo =-------------------
echo git push
echo -------------------=
git push

set sec=3
echo =-------------------
echo Window will be closed in %sec% seconds
echo -------------------=
:symbol
set /a sec=sec-1
ping -n 2 -w 100 127.1>nul
if %sec%==0 (exit) else (goto symbol)