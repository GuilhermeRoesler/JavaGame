public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new Key();
        gp.obj[0].worldX = 23 * Constants.TILE_SIZE;
        gp.obj[0].worldY = 7 * Constants.TILE_SIZE;

        gp.obj[1] = new Key();
        gp.obj[1].worldX = 23 * Constants.TILE_SIZE;
        gp.obj[1].worldY = 40 * Constants.TILE_SIZE;

        gp.obj[2] = new Key();
        gp.obj[2].worldX = 38 * Constants.TILE_SIZE;
        gp.obj[2].worldY = 8 * Constants.TILE_SIZE;

        gp.obj[3] = new Door();
        gp.obj[3].worldX = 10 * Constants.TILE_SIZE;
        gp.obj[3].worldY = 11 * Constants.TILE_SIZE;

        gp.obj[4] = new Door();
        gp.obj[4].worldX = 8 * Constants.TILE_SIZE;
        gp.obj[4].worldY = 28 * Constants.TILE_SIZE;

        gp.obj[5] = new Door();
        gp.obj[5].worldX = 12 * Constants.TILE_SIZE;
        gp.obj[5].worldY = 22 * Constants.TILE_SIZE;

        gp.obj[6] = new Chest();
        gp.obj[6].worldX = 10 * Constants.TILE_SIZE;
        gp.obj[6].worldY = 7 * Constants.TILE_SIZE;
    }
}
