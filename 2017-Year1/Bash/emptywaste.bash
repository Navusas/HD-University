#! /bin/bash

#emptywaste - delete all files from .waste directory

if [[ ! $HOME/bin/.waste ]]; then # check if directory exists
		echo "emptywaste: waste directory doesn't exists"
else
	if [[ wastesize == 0 ]] ; then
		echo "emptywaste: waste directory is empty! "
	else
		find $HOME/bin/.waste -mindepth 1 -delete
		echo -e "emptywaste: waste directory is now empty" 
	fi
fi
