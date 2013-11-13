  #!/bin/bash          
for i in 10 16 20 24 30 32 49 
do  
	for j in 1 2 3 4
	do
		 minisat ./3D/q_${i}_0${j}.cnf > ./3D/results/q_${i}_0${j}.sol
		echo "done " q_${i}_0${j}.cnf
	done
 done
