
package test5;

public class testExpMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Object obj=new testException("dd");
		
		try {
			try {
				throw (Throwable)obj;
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				System.out.println(e.getCause());
				
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
