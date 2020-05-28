package ai.brace;

public class Main
{
    public static void main(String[] args)
    {
        Task[] tasks = new Task[]{
                new Task1(),
                new Task2(),
                new Task3(),
                new Task4()
        };
        for(Task task : tasks) {
            task.runTask();
        }
    }
}
