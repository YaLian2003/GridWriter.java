//Yangyang Lian

public class GridWriterDriver {

    //main method
    public static void main(String[] args){


     //create new oject of GridWriter
    GridWriter gridWriter = new GridWriter(40, 50);


    //my Circle
    gridWriter.add(new MyCircle(10, 10, 9));

    //My Rectangle
    gridWriter.add(new MyRectangle(40, 0, 10, 10));

        //method to display grid Writer
        gridWriter.display();
        try {
            for (int i = 0; i < gridWriter.size(); i++) {
                System.out.println(gridWriter.get(i).getArea());
            }
        }
        //call exception
        catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }
}
