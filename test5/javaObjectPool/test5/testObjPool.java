package test5;

/**
 * ≤‚ ‘∂‘œÛ≥ÿ
 * 
 * @author tantian
 * */
public class testObjPool {

	public static void main(String[] args) {

		javaObjectPool objPool = new javaObjectPool();

		humanModle hum = new humanModle();
		objPool.createPool(hum);

		humanModle[] obj = new humanModle[60];
		try {
			for (int i = 0; i < 50; i++) {
				obj[i] = (humanModle) objPool.getObject();
			}
			for (int i = 0; i < 20; i++) {
				objPool.returnObj(obj[i]);
			}
			for (int i = 0; i < 20; i++) {
				obj[i] = (humanModle) objPool.getObject();
			}
			obj[11] = (humanModle) objPool.getObject();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < 10; i++) {
			StringBuffer sbu = new StringBuffer();
			sbu.append(i);
			obj[i].setBody(sbu.toString());
		}

		for (int i = 0; i < 10; i++) {
			System.out.println(obj[i].getBody());
		}

		try {
			objPool.closeObjectPool();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		obj = null;

	}
}
