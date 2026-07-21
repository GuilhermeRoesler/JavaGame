public class Heart extends Object {
    public Heart() {
        name = "Heart";
        image = Utils.readImage(Constants.OBJECTS_PATH, "heart_full.png");
        image2 = Utils.readImage(Constants.OBJECTS_PATH, "heart_half.png");
        image3 = Utils.readImage(Constants.OBJECTS_PATH, "heart_blank.png");
    }
}
