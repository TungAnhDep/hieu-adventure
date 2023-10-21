package main;

import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;
import java.util.Random;

import object.SuperObject;
import tile.TileImageLoader;
import tile.TileManager;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp=gp;
    }
    /*
    public void setObject(){
        gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldX = 23 * gp.tileSize;
        gp.obj[0].worldY = 7 * gp.tileSize;

        gp.obj[1] = new OBJ_Key();
        gp.obj[1].worldX = 23*gp.tileSize;
        gp.obj[1].worldY = 40 * gp.tileSize;

        gp.obj[2] = new OBJ_Key();
        gp.obj[2].worldX = 37*gp.tileSize;
        gp.obj[2].worldY = 7 * gp.tileSize;

        gp.obj[3] = new OBJ_Door();
        gp.obj[3].worldX = 10*gp.tileSize;
        gp.obj[3].worldY = 9 * gp.tileSize;

        gp.obj[4] = new OBJ_Door();
        gp.obj[4].worldX = 8*gp.tileSize;
        gp.obj[4].worldY = 28 * gp.tileSize;

        gp.obj[5] = new OBJ_Door();
        gp.obj[5].worldX = 12*gp.tileSize;
        gp.obj[5].worldY = 22* gp.tileSize;

        gp.obj[6] = new OBJ_Chest();
        gp.obj[6].worldX = 10*gp.tileSize;
        gp.obj[6].worldY = 7 * gp.tileSize;
    }
*/
    public void setKeyRandom(){
        Random random = new Random();
        int numKeys = 20;

        for(int i=0; i<numKeys;i++){
            int x = random.nextInt(gp.maxWorldCol);
            int y = random.nextInt(gp.maxWorldRow);



            SuperObject newKey = new OBJ_Key();

            newKey.worldX = x * gp.tileSize;
            newKey.worldY = y * gp.tileSize;

            boolean validKey = true;
            for (SuperObject existingKey : gp.obj) {
                if (existingKey instanceof OBJ_Key && newKey.distanceTo(existingKey) < 10) {
                    validKey = false;
                    break;
                }
            }

            if (validKey && isValidKeyPlacement(newKey)) {
                gp.obj.add(newKey);
                System.out.println("Generated key at (" + x + ", " + y + ")");
            } else {
                System.out.println("Invalid key. Regenerating...");
                i--; // Retry generating a key
            }
        }
    }

    public boolean isValidKeyPlacement(SuperObject newKey){
        for(int i=0;i< gp.tileM.layerNum; i++){

            int tileNum = gp.tileM.mapTileNum[i][newKey.worldX/gp.tileSize][newKey.worldY/gp.tileSize];
            // divine by tilesize to get x,y or col and row
            if(tileNum != -1){
                if(gp.tileM.tile[i][tileNum].collision == true){
                    return false;
                }
            }

        }
        return true;
    }
}
