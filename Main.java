import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {	
	public static void main(String args[]) throws FileNotFoundException{		    
        Scanner scanner = new Scanner(new File("input.txt"));
        String num = scanner.nextLine();
        int size = Integer.parseInt(num);
        
        String line = scanner.nextLine();
        String[] row = line.split(",");
        String nline = scanner.nextLine();
        String[] column = nline.split(",");

        scanner.close();
        
        if (row.length != size && column.length != size) {
        	throw new IllegalArgumentException("Input size and individual sums length dont match");
        }
        
        Integer [] row_sum = new Integer [size]; 
        Integer [] column_sum = new Integer [size]; 
        for (int j=0; j<size; j++) {
        	row_sum[j] = Integer.parseInt(row[j]);
        	column_sum[j] = Integer.parseInt(column[j]);
        }
        
//        long startTime = System.currentTimeMillis();
        isMatrixPossible(size, row_sum, column_sum);
//        long endTime = System.currentTimeMillis();
//        System.out.println(endTime-startTime);
	}
	
	public static void isMatrixPossible(int size, Integer[] row_sum, Integer[] column_sum) {
		Pair [] column_sum_pair = new Pair [size]; 
		
		for (int i=0; i < size ; i++) {
			column_sum_pair[i] = new Pair(i, column_sum[i]);
		}
		
        PrintWriter writer;
		try {
			writer = new PrintWriter("output.txt", "UTF-8");
			writer.println("1");
		
			Arrays.sort(column_sum_pair);
			for (int i = 0 ; i < size; i++) {				
				if (row_sum[i] > size) {
					writer.close();
					writer = new PrintWriter("output.txt", "UTF-8");
					writer.write('0');
					writer.close();
					return;
				}
							
				int[] out = new int[size];
				for (int j = 0; j<row_sum[i]; j++) {
					column_sum_pair[j].value--;
					if (column_sum_pair[j].value < 0) {
						writer.close();
						writer = new PrintWriter("output.txt", "UTF-8");
						writer.write('0');
						writer.close();
						return;
					}
					out[column_sum_pair[j].index] = 1;
				}
				writer.println(Arrays.toString(out).replace("[", "").replace("]", ""));
				
				//In-place Sorting
				int i1 = column_sum_pair[row_sum[i]-1].value;
				int i2 = column_sum_pair[row_sum[i]].value;				
				
				if ( i2 > i1 ) {  //Sorting required else already sorted 
					int first_index = row_sum[i]-1;
					while (first_index >= 0 && column_sum_pair[first_index].value == i1) {
						first_index--;
					}
					first_index++;
					
					int last_index = row_sum[i];
					while (last_index < size && column_sum_pair[last_index].value == i2) {
						last_index++;
					}
					last_index--;
					
					Pair [] temp = new Pair [row_sum[i]-first_index];
					for (int j=0; j < temp.length; j++) {
						temp[j] = column_sum_pair[first_index+j];
					}
					for (int j=0; j <= last_index-row_sum[i]; j++) {
						column_sum_pair[first_index+j] = column_sum_pair[row_sum[i]+j];
					}
					for (int j=0; j<temp.length;j++) {
						column_sum_pair[last_index-temp.length+j+1] = temp[j];
					}					
				}
			}

			for (int j=0 ;j<size; j++) {
				if (column_sum_pair[j].value != 0) {
					writer.close();
					writer = new PrintWriter("output.txt", "UTF-8");
					writer.write('0');
					writer.close();
					return;
				}
			}
			
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}  
	}
	
	public static void printPairs(Pair [] p) {
		for (int i=0; i<p.length; i++) {
			System.out.print(p[i].value);
			System.out.print(" ");			
		}
		System.out.println("");		
	}
}
