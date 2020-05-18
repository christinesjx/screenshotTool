package shape;

public class ShapeFactory {

    public Shape getShape(ShapeType shapeType, int x, int y, int x1, int y2) {
        if (shapeType == null) {
            return null;
        }
        if (shapeType == ShapeType.ARROW) {
            return new Arrow(x, y, x1, y2);

        } else if (shapeType == ShapeType.OVAL) {
            return new Oval(x, y, x1, y2);

        } else if (shapeType == ShapeType.RECTANGLE) {
            return new Rectangle(x, y, x1, y2);
        }

        return null;
    }


}