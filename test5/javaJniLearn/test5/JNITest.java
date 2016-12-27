package test5;

import java.lang.annotation.Native;

//javah -classpath D:\MyWork\test5\bin -d G:\ -jni test5.JNITest
public class JNITest {
	public native final void sayHellow();

	public static void main(String[] args) {
		System.loadLibrary("JNITest");
		JNITest jniDemo = new JNITest();
		jniDemo.sayHellow();

	}

}
