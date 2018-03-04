 package finalProject;

 import javafx.geometry.Pos;
 import javafx.scene.layout.GridPane;
 import javafx.scene.layout.VBox;
 import javafx.scene.paint.Color;
 import javafx.scene.shape.Rectangle;

 public class Schedule {

     public VBox addSchedule() {
         VBox schedule = new VBox();
         schedule.setAlignment(Pos.TOP_CENTER);

         Rectangle task1 = new Rectangle (100, 100, 100, 50);
         task1.setFill(Color.BLUE);

         Rectangle task2 = new Rectangle (100, 200, 100, 50);
         task2.setFill(Color.RED);

         Rectangle task3 = new Rectangle (100, 300, 100, 50);
         task3.setFill(Color.GREEN);

         Rectangle task4 = new Rectangle (100, 400, 100, 50);
         task4.setFill(Color.PURPLE);

         schedule.getChildren().addAll(task1, task2, task3, task4);
         return schedule;
     }

     public VBox addCurrentSchedule() {
         VBox currSchedule = new VBox();
         currSchedule.setAlignment(Pos.TOP_CENTER);

         Rectangle task1 = new Rectangle (200, 100, 100, 50);
         task1.setFill(Color.YELLOW);

         Rectangle task2 = new Rectangle (200, 200, 100, 50);
         task2.setFill(Color.SILVER);

         Rectangle task3 = new Rectangle (200, 300, 100, 50);
         task3.setFill(Color.BLACK);

         Rectangle task4 = new Rectangle (200, 400, 100, 50);
         task4.setFill(Color.VIOLET);

         currSchedule.getChildren().addAll(task1, task2, task3, task4);
         return currSchedule;
     }

     public GridPane combineSchedules(VBox idealSchedule, VBox currSchedule) {
         GridPane schedule = new GridPane();
         schedule.setAlignment(Pos.TOP_CENTER);
         schedule.add(idealSchedule, 1, 1);
         schedule.add(currSchedule,2, 1);
         return schedule;
     }
}
