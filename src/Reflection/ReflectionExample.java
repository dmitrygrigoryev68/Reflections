package Reflection;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;

public class ReflectionExample {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
//        Reflection.Test test = new Reflection.Test();
//        Class clazz1 = test.getClass();
        Class clazz = Test.class;
   //     Class clazz3 = Class.forName("Reflection.Reflection.Test");

        Test test = (Test) clazz.newInstance();

        // вызвать метод по имени у заданного объекта
       // Method method = clazz.getMethod("foo");
       // System.out.println(method.toString());
       // method.invoke(test);

//        // установить поле по имени у заданного объекта
        Field field = clazz.getDeclaredField("num");
        field.getModifiers();
        field.setAccessible(true);
        field.set(test, 100);
        System.out.println(test);

        // выводим название пакета
        Package pack = clazz.getPackage();
        System.out.println("package " + pack.getName() + ";");

        // начинаем декларацию класса с модификаторов
       int modifiers = clazz.getModifiers();
      System.out.print(getModifiers(modifiers));
        // выводим название класса
       System.out.print("class " + clazz.getSimpleName() + " ");

        // выводим название родительского класса
        System.out.print("extends " + clazz.getSuperclass().getSimpleName() + " ");
        // выводим интерфейсы, которые реализует класс
        Class[] interfaces = clazz.getInterfaces();
       for (int i = 0, size = interfaces.length; i < size; i++) {
            System.out.print(i == 0 ? "implements " : ", ");
            System.out.print(interfaces[i].getSimpleName());
        }
        System.out.println(" {");

        // выводим поля класса
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            System.out.println("\t" + getModifiers(f.getModifiers()) +
                    getType(f.getType()) + " " + f.getName() + ";");
        }

        // выводим констукторы класса
        Constructor[] constructors = clazz.getDeclaredConstructors();
        for (Constructor c : constructors) {
            System.out.print("\t" + getModifiers(c.getModifiers()) +
                    clazz.getSimpleName() + "(");
            System.out.print(getParameters(c.getParameterTypes()));
            System.out.println(") { }");
        }

        // выводим методы класса
        Method[] methods = clazz.getDeclaredMethods();
        for (Method m : methods) {
            // получаем аннотации
            Annotation[] annotations = m.getAnnotations();
            System.out.print("\t");
            for (Annotation a : annotations) {
                System.out.print("@" + a.annotationType().getSimpleName() + " ");
            }
            System.out.println();

            System.out.print("\t" + getModifiers(m.getModifiers()) +
                    getType(m.getReturnType()) + " " + m.getName() + "(");
            System.out.print(getParameters(m.getParameterTypes()));
            System.out.println(") { }");
        }

        System.out.println("}");

   }
    static String getModifiers(int mod) {
        String modifiers = "";
        if (Modifier.isPublic(mod)) {
            modifiers += "public ";
        }
        if (Modifier.isProtected(mod)) {
            modifiers += "protected ";
        }
        if (Modifier.isPrivate(mod)) {
            modifiers += "private ";
        }
        if (Modifier.isStatic(mod)) {
            modifiers += "static ";
        }
        if (Modifier.isAbstract(mod)) {
            modifiers += "abstract ";
        }

        return modifiers;
    }

    static String getType(Class clazz) {
        String type = clazz.isArray() ? clazz.getComponentType().getSimpleName()
                : clazz.getSimpleName();
        if (clazz.isArray()) {
            type += "[]";
        }
        return type;
    }

    static String getParameters(Class[] params) {
        String param = "";
        for (int i = 0, size = params.length; i < size; i++) {
            if (i > 0) {
                param += ", ";
            }
            param += getType(params[i]) + " param" + i;
        }
        return param;
    }


}

class Test implements Serializable, Cloneable {
    private int num;

    public Test() {

    }

    public Test(Object obj) {
    }

    @Deprecated
    protected static void counter(String[] array) {
    }

    public void foo() {
        System.out.println("FOO");
    }

    @Override
    public String toString() {
        return "Reflection.Test{" +
                "num=" + num +
                '}';
    }
}