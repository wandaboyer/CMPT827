  #!/bin/bash          
for i in 10 16 20 24 30 32 49 
do  
	for j in 1 2 3 4
	do
		echo q_${i}_0${j}
		java JavaMain q_${i}_0${j}
	done
 done
