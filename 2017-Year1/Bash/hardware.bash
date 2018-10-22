#! /bin/bash
RANGE=255 # set to default value.
LOOP=10
testTaken=false
setDifficulty() {
        ## sets RANGE by choosen difficulty
        echo -e "Now choose tasks difficulty:"
        echo -e "1. Easy"
        echo -e "2. Medium"
        echo -e "3. Hard"
        echo -e "4. intermediate"
        echo -e "5. Advanced"
        echo -e "6. Quit"
        read chosenDifficulty
        case "$chosenDifficulty" in
                1) RANGE=255 ;; # easy
                2) RANGE=511 ;; # medium
                3) RANGE=1023 ;; # hard
                4) RANGE=2045 ;; # intermediate
                5) RANGE=4097 ;; # advanced
                6) exitState ;;
                *) inputError "setDifficulty" ;;
        esac
}

printFirstOptions(){
        #Function prints first options on the screen
        #:parameter: user inputs
        #:return: user input
        echo -e "Choose one of the following options:"
 		echo -e "1. Convert from decimal"
        echo -e "2. Convert to decimal"
        echo -e "3. Test (includes everything)"
        echo -e "4. Quit"
        read firstOption
        printSecondOptions
}
printSecondOptions() {
        ### prints Second option menu
        clear
        case "$firstOption" in
        1)
            echo -e "Choose from the following options:"
            echo -e "1. Decimal to Binary"
            echo -e "2. Decimal to Octal"
            echo -e "3. Decimal to Hexademical"
            echo -e "4. Random"
            echo -e "5. Quit"
            read userFromDecimal
            clear
                case "$userFromDecimal" in
                        1) setDifficulty ## asks for difficulty
                                fromDecimal "2" "binary" "to";; ## calls other function and passes :
                                #       "Base" "string" "string for case statement"
                        2) setDifficulty
                                fromDecimal "8" "octal" "to";;
                        3) setDifficulty
                                fromDecimal "16" "hexademical" "to";;
                        4) setDifficulty
								 fromDecimal "-1" "random" "to";;
                        5) exitState ;;
                        *) inputError "printSecondOptions";;
                esac
        ;;
        2)
            echo -e "Choose from the following options:"
            echo -e "1. Binary to Decimal"
            echo -e "2. Octal to Deciaml"

        ##! /bin/bash
            echo -e "3. Hexademical to Decimal"     echo -e "4. Random"
            echo -e "5. Quit"
            read userToDecimal
                clear
                case "$userToDecimal" in
                        1) setDifficulty
                                fromDecimal "2" "Binary" "from";;
                        2) setDifficulty
                                fromDecimal "8" "Octal" "from";;
                        3) setDifficulty
                                fromDecimal "16" "Hexademical" "from";;
                        4) setDifficulty
                                fromDecimal "-1" "Random" "from";;
                        5) exitState ;;
                        *) inputError "printSecondOptions";;
                esac
        ;;
        3) testStarts ;; ## calls test function
 		4) exitState ;; ## exits
        *) inputError "printFirstOptions" ;; # to handle anything else
        esac

}
inputError() {
        clear
        echo -e "Incorrect input\nPlease enter correct number"
        $1 ## calls function (which is an argument)
}

exitState() {
        ## exits by echoing some text
        echo -e "\n\n\tHope our service was helpful for you!"
        echo -e "\tCan't wait to see you again. Bye!\n\n\n\n"
        exit
}

ifQuit() {
  if [[ "$1" == "q" ]] ; then
          break;
  fi
}
fromDecimal() {
        ## Main function covering all FROM DECIMAL tasks.
        ## Initialize variables
        answersTaken=0
        correctAnswers=0
        base=$1 # set to first argument
        oldBase=$base
        convertTo=$2 # set to second argument
        correctAnswer=0
        ## loop until the default value (20) is reached
        for ((index=0;index<$LOOP;index++));
         do
                if [[ $index == 0 ]]; then # if for the first time
                        clear
                        echo -e "By default there will be 10 questions"
                        echo -e "Press ENTER to start! (or [q] to quit)"
                        read uasd
                        ifQuit "$uasd"

                else
                        echo -e "Press ENTER to continue (or [q] to quit)"
                        read uasd
                        ifQuit "$uasd"
                fi
                clear
                # generate random number
                number=$RANDOM
                let "number%=$RANGE" # set that random number would be between Range
                holdOldNumber=$number # store this number in memory
                case "$oldBase" in
                        2) toBinary ;;
                        8) toOctal ;;
                        16) toHexademical;;
                        -1) random=$(( ( RANDOM % 3 )  + 1 )) # if players chooses "test" generate one more random number
                                                                # to randomly pick up task
				case $random in
                                        1)      base=2
                                                toBinary
                                                convertTo="binary" ;;
                                        2)      base=8
                                                toOctal
                                                convertTo="octal" ;;
                                        3)      base=16
                                                toHexademical
                                                convertTo="hexademical" ;;
                                esac
                esac
                # echo $answer $holdOldNumber # shows correct answers
                # passes 3rd argument which is "to" or "from"
                case "$3" in
                        "to")
                                echo -e "Decimal $holdOldNumber to $convertTo is..."
                                read userAnswer;
                                ifQuit "$userAnswer"

                                if [[ "$userAnswer" == $answer ]]; then
                                                let correctAnswer=$correctAnswer+1
                                                echo -e "Correct!"
                                else
                                                echo -e "Wrong. Correct answer was : $answer"
                                fi
                                let answersTaken=answersTaken+1
                        ;;
                        "from")
								 case $convertTo in
                                "binary") convertTo="Binary";;
                                "octal") convertTo="Octal";;
                                "hexademical") convertTo="Hexademical";;
                                esac
                                echo -e "$convertTo $answer to decimal is..."
                                read userAnswer;
                                ifQuit "$userAnswer"
                                if [[ "$userAnswer" == $holdOldNumber ]]; then
                                                let correctAnswer=$correctAnswer+1
                                                echo -e "Correct!"
                                else
                                                echo -e "Wrong. Correct answer was : $holdOldNumber"
                                fi
                                let answersTaken=answersTaken+1
                        ;;
                        *) "Unexpected error occured\nPlease take a screenshot and email to navuscoorp@gmail" ;; # handles anything else
                esac
                echo -e "\nCurrent result: $correctAnswer / $((index+1))\n"
        done

}

#CONVERT DECIMAL TO BINARY
toBinary() {
        answer=""
        zero="0"
        one="1"
        powerOf=0
        # check what is the maximum power of passed base and number
        # for example:
        # random number = 65
        # base = 2
        # powerOf = 6
        while [[ $number -gt $currentNumber ]]; do
                if [[ $((base**$((powerOf+1)))) -gt $number ]]; then
                        break
                fi
                let powerOf=powerOf+1
                let currentNumber=base**powerOf # stores the maximum possible digit (which is less then random number) into variable
        done

        # check size in bytes. If its lower then 8, set to default
        if [[ $powerOf -lt 8 ]]; then # if the powerof is less then 8 ( to keep minimum of 8 bytes number in binary)
                sizeOfAnswer=8
                powerOf=7
                let currentNumber=base**powerOf
        else
                let sizeOfAnswer=powerOf+1
        fi
        for ((j=0; j<$sizeOfAnswer; j++)); do # loop

                if [[ $number -ge $currentNumber ]] ; then
                        let number=number-currentNumber # substract from number the maximum possible number
                        answer=$answer$one; # attach 1 to answer (as a string)
                else
                		answer=$answer$zero # attach 0 to answer ( as a string)
                fi
                if [[ $powerOf -gt 0 ]]; then
                        let powerOf=powerOf-1
                        let currentNumber=base**powerOf
                fi
        done
}


#CONVERT DECIMAL TO OCTAL
toOctal() {
        answer=""
        powerOf=0
        zero="0"
        # check what is the maximum power and number
        while [[ $number -gt $currentNumber ]]; do
                if [[ $((base**$((powerOf+1)))) -gt $number ]]; then
                        break
                fi
                let powerOf=powerOf+1
                let currentNumber=base**powerOf
        done

        if [[ $currentNumber -eq 0 ]]; then
                currentNumber=0
                break
        fi
        let sizeOfAnswer=powerOf+1
        for ((j=0; j<$sizeOfAnswer; j++)); do
                 if [[ $number -ge $currentNumber ]] ; then
                        let division=number/currentNumber
                        answer=$answer$division
                        let number=number-$((division*currentNumber))
                elif [[ $number -eq 0 ]]; then
                        answer=$answer$zero
                else
                        answer=$answer$number
                fi
                if [[ $powerOf -gt 0 ]]; then
                        let powerOf=powerOf-1
                        let currentNumber=base**powerOf
                fi
        done
}

# CONVERT DECIMAL TO HEXADEMICAL
toHexademical() {
        answer=""
        powerOf=0
        zero="0"
        # check what is the maximum power and number
        while [[ $number -gt $currentNumber ]]; do
                if [[ $((base**$((powerOf+1)))) -gt $number ]]; then
                        break
                fi
                let powerOf=powerOf+1
                let currentNumber=base**powerOf
        done
        let sizeOfAnswer=powerOf+1
        if [[ $currentNumber -eq 0 ]]; then
                answer=$answer$number
                break
        fi
        for ((j=0; j<$sizeOfAnswer; j++)); do
                 if [[ $number -ge $currentNumber ]] ; then
                        let division=number/currentNumber
                        let number=number-$((division*currentNumber))
                        case $division in
                        0) division=0 ;;
                        1) division=1 ;;
                        2) division=2 ;;
                        3) division=3 ;;
                        4) division=4 ;;
                        5) division=5 ;;
                        6) division=6 ;;
                        7) division=7 ;;
                        8) division=8 ;;
                        9) division=9 ;;
                        10) division=A ;;
                        11) division=B ;;
                        12) division=C ;;
                        13) division=D ;;
                        14) division=E ;;
                        15) division=F ;;
                        *) echo -e "Unexpected error occured\n Please take screenshot and email to navuscoorp@gmail.com"
                                exit ;;
                    esac
                        answer=$answer$division
                else
                        answer=$answer$zero
                fi
                if [[ $powerOf -gt 0 ]]; then
                        let powerOf=powerOf-1
                        let currentNumber=base**powerOf
                fi
        done
}

outputAnswer() {
        clear
        if [[ $correctAnswer -ne 0 ]]; then
                percentage=$(echo "scale=2;$correctAnswer/$answersTaken*100" | bc)
        else
                correctAnswer=0
                percentage=0
        fi
        intPercentage=${percentage%.*} # convert floating number to int

        ## if user took test
        if [[ $answersTaken -eq 0 ]]; then
                exitState
        elif [[ $takeTest == "true" ]]; then
                echo -e "You answered $correctAnswer/$answersTaken questions correctly. This means...."
                if [[ $intPercentage -ge 70 ]]; then
                        echo -e "You passed the test with- $percentage% in total!\n.You should test yourself in higher level now"
                 else
                        echo -e "Unfortunately you did not pass the test, you answered $percentage% questions correctly\n Try again?"
                fi
        else
                echo -e "You answered $correctAnswer/$answersTaken questions correctly"
                if [[ $intPercentage -ge 90 ]]; then
                        echo -e "WOW! You are really good- $percentage% in total!\n.You should test yourself in higher level now"
                elif [[ $intPercentage -ge 70 ]]; then
                        echo -e "You did very well - $percentage% in total!\n.However, can you reach 90%?"
                elif [[ $intPercentage -ge 50 ]]; then
                        echo -e "You did good - $percentage% in total!\nThat's half way to 100%!"
                else
                        echo -e "$percentage% in total!\nKeep practising!"
                fi
        fi
}
testStarts() {
        answersTaken=0
        setDifficulty # set difficulty
        testTaken=true
        LOOP=20 # default Test questiosn amount
        correctAnswers=0
        base=$1
        convertTo=$2
        correctAnswer=0
        for ((index=0;index<$LOOP;index++));
        do
        	if [[ $index == 0 ]]; then
                        clear
                        echo -e "There is 20 questions in test. Reach result 70% or higher, to pass"
                        echo -e "Press ENTER to start when you are ready! (or [q] to quit)"
                        read uasd
                        ifQuit "$uasd"
                else
                        echo -e "Press ENTER to continue (or [q] to quit)"
                        read uasd
                        ifQuit "$uasd"
                fi

        clear
        number=$RANDOM
        let "number%=$RANGE"
        holdOldNumber=$number
        random=$(( ( RANDOM % 3 )  + 1 ))
        case $random in
                1)      base=2
                        toBinary
                        convertTo="binary" ;;
                2)      base=8
                        toOctal
                        convertTo="octal" ;;
                3)      base=16
                        toHexademical
                        convertTo="hexademical" ;;
                  esac
        random=$(( ( RANDOM % 2 )  + 1 ))
        case $random in
                1)
                        echo -e "Decimal $holdOldNumber to $convertTo is..."
                        read userAnswer;
                        ifQuit "$userAnswer"
                        if [[ "$userAnswer" == $answer ]]; then
                                        let correctAnswer=$correctAnswer+1
                                        echo -e "Correct!"
                        else
                                        echo -e "Wrong. Correct answer was : $answer"
                        fi
                        let answersTaken=answersTaken+1
                ;;
                2)
                        convertTo="${convertTo^}"
                        echo -e "$convertTo $answer to decimal is..."
                        read userAnswer;
                        ifQuit "$userAnswer"
                        if [[ "$userAnswer" == "$holdOldNumber" ]]; then
                                        let correctAnswer=$correctAnswer+1
                                        echo -e "Correct!"
                        else
                                        echo -e "Wrong. Correct answer was : $holdOldNumber"
                        fi
                        let answersTaken=answersTaken+1
                ;;
                *) "Unexpected error occured\nPlease take a screenshot and email to navuscoorp@gmail" ;;
                esac
        echo -e "\nCurrent result: $correctAnswer / $((index+1))\n"
 done
}

#__main__ = __Start__
clear
printFirstOptions
outputAnswer
