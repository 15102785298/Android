package test5;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import AnnValidate.Length;
import AnnValidate.Validation;

public class Validate {

	public enum ValidateType {
		Validation, Required, Length, Email, Phone;
	}

	@SuppressWarnings("rawtypes")
	public static <T> boolean validate(T t) throws IllegalArgumentException,
			IllegalAccessException {
		boolean isOk = true;
		Class modle = t.getClass();
		Field allField[] = modle.getDeclaredFields();// 获取到所有的变量
		for (Field field : allField) {// 遍历所有变量
			Annotation allAnnotation[] = field.getDeclaredAnnotations();// 获得这个变量的所有注释
			List<Annotation> list = Arrays.asList(allAnnotation);
			if (!isContain(list)) {
				continue;
			}
			field.setAccessible(true);
			Object obj = field.get(t);
			for (Annotation annotation : allAnnotation) {
				switch (ValidateType.valueOf(StringUtils.substringAfterLast(
						annotation.annotationType().toString(), "."))) {
				case Length:
					isOk = validateLength(obj.toString(), (Length) annotation);
					break;
				case Required:
					isOk = validateRequired(obj.toString());
					break;
				case Email:
					isOk = validateEmail(obj.toString());
					break;
				case Phone:
					isOk = validateEmail(obj.toString());
					break;
				default:
					break;
				}
			}
		}
		return isOk;
	}

	private static boolean isContain(List<Annotation> list) {
		boolean isCon = false;
		String s = StringUtils.substringAfterLast(Validation.class.toString(),
				" ");
		isCon = list.toString().contains(s);
		return isCon;
	}

	private static boolean validateRequired(String valiString) {
		boolean isOk = true;
		isOk = !valiString.isEmpty();
		return isOk;
	}

	private static boolean validateLength(String valiString, Length length) {
		boolean isOk = true;
		if (valiString.length() < length.minLength())
			isOk = false;
		if (valiString.length() > length.maxLength())
			isOk = false;
		return isOk;
	}

	private static boolean validateEmail(String valiString) {
		boolean isOk = true;
		String regex;
		regex = "";
		isOk = valiString.matches(regex);
		return isOk;
	}

	@SuppressWarnings("unused")
	private static boolean validatePhone(String valiString) {
		boolean isOk = true;
		String regex="";
		isOk = valiString.matches(regex);
		return isOk;
	}

}
