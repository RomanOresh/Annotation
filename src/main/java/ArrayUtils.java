import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface MethodInfo {
    String methodName();
    String returnType();
    String description();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Author {
    String name() default "Anonymous author";
    String surname() default "";
}

public class ArrayUtils {

    @MethodInfo(methodName = "findMax",
            returnType = "int",
            description = "This method is used to find maximal value in array")
    @Author()
    public int findMax(int[] array) {
        int max = array[0];
        for (int i : array) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }

    @MethodInfo(methodName = "findMin",
            returnType = "int",
            description = "This method is used to find minimal value in array")
    @Author(name = "John",surname = "Doe")
    public int findMin(int[] array) {
        int min = array[0];
        for (int i : array) {
            if (i < min) {
                min = i;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        ArrayUtils arrayUtils = new ArrayUtils();
        int[] array = new int[]{10,19,3,5,16,54,3,12,0,77};
        arrayUtils.findMin(array);
        arrayUtils.findMax(array);

        try {
            System.out.println("Метод findMax аннотация MethodInfo и Author");
            MethodInfo methodInfo = ArrayUtils.class.getDeclaredMethod("findMax", int[].class).getAnnotation(MethodInfo.class);
            System.out.println(methodInfo.methodName());
            System.out.println(methodInfo.returnType());
            System.out.println(methodInfo.description());
            Author author = ArrayUtils.class.getDeclaredMethod("findMax", int[].class).getAnnotation(Author.class);
            System.out.println(author.name());
            System.out.println(author.surname());

            System.out.println("Метод findMin аннотация MethodInfo и Author");
            methodInfo = ArrayUtils.class.getDeclaredMethod("findMin", int[].class).getAnnotation(MethodInfo.class);
            System.out.println(methodInfo.methodName());
            System.out.println(methodInfo.returnType());
            System.out.println(methodInfo.description());
            author = ArrayUtils.class.getDeclaredMethod("findMin", int[].class).getAnnotation(Author.class);
            System.out.println(author.name());
            System.out.println(author.surname());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

}
