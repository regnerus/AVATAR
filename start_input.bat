@echo off
set /p lang="Language: (EN/NL) "
set /p input="Input: (WOZ/SPEECH) "

start java input -%input% -%lang%
