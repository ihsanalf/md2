import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * 
 * @author M. Hazman Ihsan A.
 * @NPM 1406623682
 * @ver 1.1
 */
public class CRT {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int sumCase = Integer.parseInt(br.readLine());

		/*
		 * Looping input
		 */
		for (int i = 0; i < sumCase; i++) {
			int eq = Integer.parseInt(br.readLine());
			int[] arr_a = new int[eq];
			int[] arr_m = new int[eq];
			int[] arr_M = new int[eq];
			int m = 1;
			int x = 0;
			
			/*
			 * Looping all the equations to obtain ai and mi value
			 */
			for (int j = 0; j < eq; j++) {
				String[] tmp = br.readLine().split(" ");
				arr_a[j] = Integer.parseInt(tmp[0]);
				arr_m[j] = Integer.parseInt(tmp[1]);
				m *= arr_m[j]; //while looping the m value is timed with curent mi
			}
			
			/*
			 * After the m value is obtained, we can find the Mi value
			 */
			for (int k = 0; k < eq; k++) {
				arr_M[k] = m / arr_m[k];
			}
			
			/*
			 * Calculate the x value by continously adding with ai, Mi, and inverse of Mi mod mi
			 */
			for (int l = 0; l < eq; l++) {
				int tmpM = arr_M[l]; //temporary value of Mi
				int tmpm = arr_m[l]; //temporary value of mi
				
				int inverse = Inverse(tmpM, tmpm);
				x += (arr_a[l] * arr_M[l] * inverse) % m; //mod the current times value by m in case the value is too big
			}
			System.out.println(x % m); //mod the x value by m in case the x value is too big
		}
	}

	/*
	 * Processing input & output
	 */
	public static int Inverse(int source, int target)	{
		/*
		 *Menyimpan input yang asli 
		 */
		int Ai = source;
		int Mi = target;
		boolean swapped = false;
		/*
		 * Swap posisi jika Ai lebih kecil dari Mi
		 */
		if (source < target) {
			int temp = source;
			source = target;
			target = temp;
			swapped = !swapped;
		}
		/*
		 * Index 0 stores GCD 
		 * Index 1 stores prev Y 
		 * Index 2 stores before-prev Y 
		 * Index 3 stores prev quotient 
		 * Index 4 stores number of steps needed to compute the GCD 
		 */
		int[] result = { 0, 1, 0, 0, 0 };

		/*
		 * Menggunakan yang telah di swap(jika swap)
		 */
		result = euclidR(source, target, result);
		
		/*
		 * Menggunakan yang asli
		 */
		int inverse = 0;
		
		int temp = result[1];
		int newY = result[1] * result[3] + result[2];
		result[2] = temp;
		if(swapped)	{
			inverse = newY;
			if ((inverse * Ai) - (result[2] * Mi) < 0)
				inverse = -inverse;
		}
		else {
			inverse = result[2];
			if ((inverse * Ai) - (newY * Mi) < 0)	{
				inverse = -inverse;
			}
		}
		while(inverse < 0) inverse += Mi;
		return inverse;
	}

	/**
	 * 
	 * @param source angka pertama
	 * @param target angka kedua
	 * @param result array input (detail posisi diatas)
	 * @return array hasil
	 */
	public static int[] euclidR(int source, int target, int[] result) {
		/*
		 * Menyimpan data yang dibutuhkan
		 */
		int quotient = source / target;
		int remainder = source % target;
		int[] container = result;
		/*
		 * Jika hasil mod 0, maka sisa sebelumnya adalah GCD
		 */
		if (remainder == 0) {
			container[0] = target;
			container[3] = quotient;
			return container;
		}
		/*
		 * update container
		 */
		container = euclidR(target, remainder, result);
		/*
		 * update steps
		 */
		container[4] += 1;

		/*
		 * jika step belum melebihi 1 (mulai dari 0) maka menggunakan nilai default
		 */
		if (container[4] < 2) {
			container[3] = quotient;
			return container;
		}
		/*
		 * Mengisikan index 1 dengan nilai baru,
		 * index 2 diisi nilai sebelumnya
		 * index 3 diisi hasil pembagian sekarang
		 */
		int temp = container[1];
		container[1] = container[1] * container[3] + container[2];
		container[2] = temp;
		container[3] = quotient;
		return container;
	}
}
