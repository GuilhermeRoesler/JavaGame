import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    int standCounter = 0;
    public boolean attackCanceled = false;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);

        this.keyH = keyH;

        screenX = (gp.screenWidth - gp.tileSize) / 2;
        screenY = (gp.screenHeight - gp.tileSize) / 2;

        solidArea.x = 8;
        solidArea.y = 30;
        solidArea.width = 32;
        solidArea.height = 20;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        attackArea.width = 36;
        attackArea.height = 36;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";

        // PLAYER STATUS
        level = 1;
        maxLife = 6;
        life = maxLife;
        strength = 1;
        dexterity = 1;
        exp = 0;
        nextLevelExp = 5;
        coin = 0;
        currentWeapon = new SwordNormal(gp);
        currentShield = new ShieldWood(gp);
        attack = getAttack();
        defense = getDefense();
    }

    public int getAttack() {
        return strength * currentWeapon.attackValue;
    }

    public int getDefense() {
        return dexterity * currentShield.defenseValue;
    }

    public void getPlayerImage() {
        up1 = setup(Constants.PLAYER_PATH, "boy_up_1.png", gp.tileSize, gp.tileSize);
        up2 = setup(Constants.PLAYER_PATH, "boy_up_2.png", gp.tileSize, gp.tileSize);
        down1 = setup(Constants.PLAYER_PATH, "boy_down_1.png", gp.tileSize, gp.tileSize);
        down2 = setup(Constants.PLAYER_PATH, "boy_down_2.png", gp.tileSize, gp.tileSize);
        left1 = setup(Constants.PLAYER_PATH, "boy_left_1.png", gp.tileSize, gp.tileSize);
        left2 = setup(Constants.PLAYER_PATH, "boy_left_2.png", gp.tileSize, gp.tileSize);
        right1 = setup(Constants.PLAYER_PATH, "boy_right_1.png", gp.tileSize, gp.tileSize);
        right2 = setup(Constants.PLAYER_PATH, "boy_right_2.png", gp.tileSize, gp.tileSize);
    }

    public void getPlayerAttackImage() {
        attackUp1 = setup(Constants.PLAYER_PATH, "boy_attack_up_1.png", gp.tileSize, gp.tileSize * 2);
        attackUp2 = setup(Constants.PLAYER_PATH, "boy_attack_up_2.png", gp.tileSize, gp.tileSize * 2);
        attackDown1 = setup(Constants.PLAYER_PATH, "boy_attack_down_1.png", gp.tileSize, gp.tileSize * 2);
        attackDown2 = setup(Constants.PLAYER_PATH, "boy_attack_down_2.png", gp.tileSize, gp.tileSize * 2);
        attackLeft1 = setup(Constants.PLAYER_PATH, "boy_attack_left_1.png", gp.tileSize * 2, gp.tileSize);
        attackLeft2 = setup(Constants.PLAYER_PATH, "boy_attack_left_2.png", gp.tileSize * 2, gp.tileSize);
        attackRight1 = setup(Constants.PLAYER_PATH, "boy_attack_right_1.png", gp.tileSize * 2, gp.tileSize);
        attackRight2 = setup(Constants.PLAYER_PATH, "boy_attack_right_2.png", gp.tileSize * 2, gp.tileSize);
    }

    public void update() {
        if (attacking) {
            attacking();
        } else if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.enterPressed) {
            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.rightPressed) {
                direction = "right";
            } else if (keyH.leftPressed) {
                direction = "left";
            }

            // CHECK TILE COLLISION
            collisionOn = false;
            gp.collisionM.checkTile(this);

            // CHECK OBJECT COLLISION
            int objIndex = gp.collisionM.checkObject(this, true);
            pickUpObject(objIndex);

            // CHECK NPC COLLISION
            int npcIndex = gp.collisionM.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            // CHECK MONSTER COLLISION
            int monsterIndex = gp.collisionM.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            // CHECK EVENT COLLISION
            gp.eHandler.checkEvent();

            if (!collisionOn && !keyH.enterPressed) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                }
            }

            if (keyH.enterPressed && !attackCanceled) {
                attacking = true;
                spriteCounter = 0;
            }

            attackCanceled = false;
            gp.keyH.enterPressed = false;

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        } else {
            standCounter++;

            if (standCounter > 20) {
                spriteNum = 1;
                standCounter = 0;
            }
        }
        if (invincible) {
            invincibleCounter++;

            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void attacking() {
        spriteCounter++;

        if (spriteCounter <= 5) {
            spriteNum = 1;
        } else if (spriteCounter > 5 && spriteCounter <= 25) {
            spriteNum = 2;

            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            switch (direction) {
                case "up":
                    worldY -= attackArea.height;
                    break;
                case "down":
                    worldY += attackArea.height;
                    break;
                case "left":
                    worldX -= attackArea.width;
                    break;
                case "right":
                    worldX += attackArea.width;
                    break;
            }

            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            int monsterIndex = gp.collisionM.checkEntity(this, gp.monster);
            damageMonster(monsterIndex);

            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        } else if (spriteCounter > 25) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void pickUpObject(int i) {
    }

    public void interactNPC(int i) {
        if (gp.keyH.enterPressed) {
            if (i != 999) {
                attackCanceled = true;
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }
    }

    public void contactMonster(int i) {
        if (i != 999) {
            if (!invincible) {
                life--;
                invincible = true;
                gp.playSFX(6);
            }
        }
    }

    public void damageMonster(int i) {
        if (i != 999) {
            if (!gp.monster[i].invincible) {
                gp.playSFX(5);
                gp.monster[i].life--;
                gp.monster[i].invincible = true;
                gp.monster[i].damageReaction();

                if (gp.monster[i].life <= 0) {
                    gp.monster[i].dying = true;
                }
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction) {
            case "up":
                if (!attacking) {
                    if (spriteNum == 1) {
                        image = up1;
                    } else if (spriteNum == 2) {
                        image = up2;
                    }
                } else {
                    tempScreenY = screenY - gp.tileSize;
                    if (spriteNum == 1) {
                        image = attackUp1;
                    } else if (spriteNum == 2) {
                        image = attackUp2;
                    }
                }
                break;
            case "down":
                if (!attacking) {
                    if (spriteNum == 1) {
                        image = down1;
                    } else if (spriteNum == 2) {
                        image = down2;
                    }
                } else {
                    if (spriteNum == 1) {
                        image = attackDown1;
                    } else if (spriteNum == 2) {
                        image = attackDown2;
                    }
                }
                break;
            case "left":
                if (!attacking) {
                    if (spriteNum == 1) {
                        image = left1;
                    } else if (spriteNum == 2) {
                        image = left2;
                    }
                } else {
                    tempScreenX = screenX - gp.tileSize;
                    if (spriteNum == 1) {
                        image = attackLeft1;
                    } else if (spriteNum == 2) {
                        image = attackLeft2;
                    }
                }
                break;
            case "right":
                if (!attacking) {
                    if (spriteNum == 1) {
                        image = right1;
                    } else if (spriteNum == 2) {
                        image = right2;
                    }
                } else {
                    if (spriteNum == 1) {
                        image = attackRight1;
                    } else if (spriteNum == 2) {
                        image = attackRight2;
                    }
                }
                break;
        }

        if (invincible) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        }

        g2.drawImage(image, tempScreenX, tempScreenY, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        if (Constants.IS_COLLISION_VISIBLE) {
            g2.setColor(Color.RED);
            g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
        }
    }
}
