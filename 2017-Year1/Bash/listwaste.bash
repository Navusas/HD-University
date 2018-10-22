
#! /bin/bash

# Program which list all files in .waste directory

if [ ! -d $HOME/bin/.waste  ]; then # checks if directory exists
	echo "listwaste: directory doesn't exists"
	exit
fi

cd $HOME/bin/.waste # go to .waste directory
if [[ $(ls "$@" | wc -l ) == 0 ]]; then
	echo -e "listwaste: waste directory is empty"
else
	du -abh --apparent-size --max-depth=1
fi
