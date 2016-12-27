package javaThreadTest;

public class vectorAdd {
	@SuppressWarnings("unused")
	private static vectorModel vector1;
	@SuppressWarnings("unused")
	private static vectorModel vector2;
	private static vectorModel res;

	@SuppressWarnings("static-access")
	public static vectorModel Add(vectorModel a, vectorModel b) {
		res = new vectorModel();
		int intres[] = { 0, 0, 0, 0, 0, 0, 0, 0 };
		int num1[] = a.getVector();
		int num2[] = b.getVector();

		for (int i = 0; i < 8; i++) {
			vectorAddThread thread = new vectorAddThread(num1[i], num2[i]);
			thread.start();
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			intres[i] = thread.getRse();
		}
		res.setVector(intres);
		return res;
	}

	public static int Add(int a, int b) {
		return a + b;
	}
}
