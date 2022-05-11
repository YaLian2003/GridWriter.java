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

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
public class GridItem {
    protected int x;
    protected int y;

    public int getX() {return x;}
    public void setX(int value) {x = value;}

    public int getY() {return y;}
    public void setY(int value) {y = value;}


    //method to calcualte the area
    public double getArea() {
        return 0;
    }

    //method to check if it is contains points or not
    public boolean containsPoint(int xValue, int yValue) {
        return x == xValue && y == yValue;
    }
}

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
public class GridWriter {

    //GRid Items
    private GridItem items[];
    private int size;
    private int rows;
    private int columns;

    //define initial capacity
    private static final int INITIAL_CAPACITY = 4;

    //constructor for grid writer

    //constructor for Grid Writer
    public GridWriter(int r, int c) {
        items = new GridItem[INITIAL_CAPACITY];
        size = 0;
        rows = r;
        columns = c;
    }


    //method to add grid items to grid writers

    public void add(GridItem item) {

        if (size == items.length) {
            doubleItemCapacity();
        }

        // store the item size
        items[size] = item;

        size++;
    }

    //method to display grid

    public void display() {
        int count;

        for (int r = rows; r >= 0; r--) {

            for (int c = 0; c < columns; c++) {

                count = 0;

                for (int i = 0; i < size; i++) {
                    if (items[i].containsPoint(c, r)) {
                        count++;
                    }
                }

                if (count == 0) {
                    System.out.print(" .");
                } else {
                    System.out.print(" " + count);
                }
            }

            System.out.println();
        }
    }

    //method to double the item capacity
    private void doubleItemCapacity() {

        GridItem temp[] = new GridItem[items.length * 2];

        for (int i = 0; i < items.length; i++) {
            temp[i] = items[i];
        }

        items = temp;
    }

    //method to return the size of the number of GridItems stored in the GridWriter
    public int size() {
        return size;
    }

    //method to store the number of items store in the grid
    public GridItem get(int index) throws IndexOutOfBoundsException {
        if (items.length > 2) {
            throw new IndexOutOfBoundsException("WARNING: Can only use 2" +
                    " GridItem objects in this program.");
        }
        return items[index];
    }
}
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
public class MyCircle extends GridItem {


    //radius of circle
    private int radius;

    //constructor for My Circle
    public MyCircle(int xValue, int yValue, int r) {
        x = xValue;
        y = yValue;
        radius = r;
    }

    //get area of the circle
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    //boolean method to check contains point
    public boolean containsPoint(int xValue, int yValue) {
        double dx = x - xValue;
        double dy = y - yValue;
        double distance = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));

        //return distance as a radius
        return distance <= radius;
    }
}
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
public class MyRectangle extends GridItem {


    //height of rectangle
    private int height;
    //width of rectangle
    private int width;

    //constructor for My Rectangle
    public MyRectangle(int xValue, int yValue, int w, int h) {
        x = xValue;
        y = yValue;
        width = w;
        height = h;
    }

    //get area method
    public double getArea() {
        return height * width;
    }

    //method to check rectangle contains point or not
    public boolean containsPoint(int xValue, int yValue) {
        return   xValue >= x &&
                xValue <= x + width &&
                yValue >= y &&
                yValue <= y + height;
    }
}
