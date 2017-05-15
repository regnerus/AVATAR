@echo off
set /p lang="Language: (EN/NL) "
set /p diag="Dialog: (QA) "

start java asapstarter -nogui -%lan%
start java dialog manager -%diag%

