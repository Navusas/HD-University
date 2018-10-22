#! /bin/bash
# del – a version of rm which only copies files to a special “.waste” directory. 

if [ ! -d $HOME/bin/.waste ]; then # if directory doesn not exist create it
		mkdir $HOME/bin/.waste
fi

for file in "$@"; do # loop while arguments finish
	if cp -r $file $HOME/bin/.waste &>> $HOME/bin/.del_Errors.log; then  # copy file recursively to .waste directory , if error occurs put it in hidden del_Errors file
		if [ $? -eq 0 ]; then # if last command executed fine (True)
		  printf "del: file\directory \"$file\" deleted\n"
		fi
	else 
		2>$HOME/bin/.del_Errors.log # puts error into file
		rm $PWD/.del_Errors.log # delete that file
		echo "del: file not found"
	fi	
done
