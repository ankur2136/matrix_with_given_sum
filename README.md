# matrix_with_given_sum
Check if a boolean 2D matrix is possible given sum of rows and columns. 

#Build
1. $make clean
2. $make
3. $./checkmatrix

#Time Complexity
Overall O(n*n)

O(n.log(n)) for sorting colums_sum for first time
O(n*n) for for traversing each row and then updating corresponding column value
[Note: For each iteration over row element we update the column remaining sum.
We sorted the column sum in the begining. So after each update the column sum would remain nearly sorted. With max one set of possible value being  interchanged. So instead of sorting this array again, we can check the position of first occurance of mis-sort and swap those values.

Merge sort at each step would otherwise have costed O(n*n*log(n))]

