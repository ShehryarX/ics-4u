package Assets;

import Assets.Graphics.Tile;

import java.util.Collections;
import java.util.Stack;

public class TileBag {
    private Stack<Tile> tiles;

    public TileBag() {
        this.tiles = new Stack<>();
        populate();
        shuffle();
    }

    public boolean isEmpty() {
        return tiles.size() == 0;
    }

    public int getSize() {
        return this.tiles.size();
    }

    public Tile nextTile() {
        return this.tiles.pop();
    }

    private void shuffle() {
        Collections.shuffle(tiles);
    }

    private void populate() {
        int[] NUM_OF_TILES = {9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1};

        for (char letter = 'a'; letter <= 'z'; letter++) {
            int frequency = NUM_OF_TILES[letter - 'a'];

            for (int i = 0; i < frequency; i++) {
                Tile tile = new Tile(letter);
                this.tiles.add(tile);
            }
        }

        // TODO: ADD BLANK TILES
        //for(int i = 0; i < 2; i++) {
        //    Tile tile = new Tile(' ');
        //    this.tiles.add(tile);
        //}
    }

    public void giveBack(Tile[] tiles) {
        Tile[] newTiles = new Tile[7];
        for (int i = 0; i < tiles.length; i++) {
            this.tiles.push(tiles[i]);
        }

        shuffle();
    }
}