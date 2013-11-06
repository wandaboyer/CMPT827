  #!/bin/bash          
#for i in 10 16 20 24 30 32 49 
#do  
#	for j in 1 2 3 4
#	do
		# minisat ./2D/q_${i}_0${j}.cnf >> ./2D/results/q_${i}_0${j}.sol
		minisat ./2D/q_49_03.cnf >> ./2D/results/q_49_03.sol.early
		# echo "done "+q_${i}_0${j}.cnf
		echo "done "q_49_03.cnf
#	done
# done
