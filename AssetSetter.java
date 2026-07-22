public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
    }

    public void setNPC() {
        gp.npc[0] = new OldMan(gp);
        gp.npc[0].worldX = gp.tileSize * 21;
        gp.npc[0].worldY = gp.tileSize * 21;
    }

    public void setMonster() {
        int xs[] = { 21, 23, 24, 34, 38 };
        int ys[] = { 38, 42, 37, 42, 42 };

        for (int i = 0; i < xs.length; i++) {
            gp.monster[i] = new GreenSlime(gp);
            gp.monster[i].worldX = gp.tileSize * xs[i];
            gp.monster[i].worldY = gp.tileSize * ys[i];
        }
    }
}
