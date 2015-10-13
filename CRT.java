import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class CRT {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int sumCase = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < sumCase; i++) {
			int eq = Integer.parseInt(br.readLine());
			int[] arr_a = new int[eq];
			int[] arr_m = new int[eq];
			int[] arr_M = new int[eq];
			int m = 1;
			int x = 0;
			
			for(int j = 0; j < eq; j++) {
				String[] tmp = br.readLine().split(" ");
				arr_a[j] = Integer.parseInt(tmp[0]);
				arr_m[j] = Integer.parseInt(tmp[1]);
				m *= arr_m[j];
			}
			
			for(int k = 0; k < eq; k++) {
				arr_M[k] = m/arr_m[k];
			}
			
			for(int l = 0; l < eq; l++) {
				int tmpM = arr_M[l];
				//System.out.println(tmpM);
				int tmpm = arr_m[l];
				//System.out.println(tmpm);
				//x += " + " + arr_a[l] + "*" + arr_M[l] + "*" + inverse(tmpM, tmpm);
				x += arr_a[l] * arr_M[l] * inverse(tmpM, tmpm);
			}
			System.out.println(x);
		}
	}
	public static int inverse(int source, int target)	{
		/*
		 *Menyimpan input yang asli 
		 */
		int Ai = source;
		int Mi = target;
		
		/*
		 * Swap posisi jika Ai lebih kecil dari Mi
		 */
		if (source < target) {
			source = target;
			target = Ai;
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

		int inverse = result[1] * result[3] + result[2];
		/*
		 * Menggunakan yang asli
		 */
		if ((inverse * Ai) - (result[1] * Mi) < 0)
			inverse = -inverse;
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
