package ai.brace;

/**
 * task interface to define all constants for tasks and prototype methods
 * @author peterliu
 */
public interface Task {
    String fileName1 = "a1.json";
    String fileName2 = "a2.json";
    String textArrayfieldName = "textArray";
    String uuidfieldName = "uuid";
    String lastModifiedfieldName = "lastModified";
    String keyForData = "textdata";

    void runTask();

    default void printName(){
        System.out.println("=====Running " + this.getClass().getCanonicalName());
    }
}
