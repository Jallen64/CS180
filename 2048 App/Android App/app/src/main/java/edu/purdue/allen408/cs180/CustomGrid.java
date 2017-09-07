package edu.purdue.allen408.cs180;

/**
 * Created by jaall on 4/20/2017.
 */

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

/**
 * CustomGrid class that takes care of handling the 4x4 main grid of the game
 *
 * @author Sahil Pujari (pujari@purdue.edu)
 * @author Tori Shurman (vshurman@purdue.edu)
 */
class CustomGrid extends BaseAdapter {

    //Stores the cells in the grid
    private TextView[][] cellGrid;
    //Checks if a cell is pre-occupied
    private boolean[][] isOccupied;

    /**
     * Constructor to do initializations
     */
    CustomGrid() {
        cellGrid = new TextView[4][4];
        isOccupied = new boolean[4][4];
    }

    /**
     * Get a View that displays the data at the specified position in the data set.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible.
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView b = new TextView(MainActivity.getAppContext());

        int xPosition = position / 4;
        int yPosition = position % 4;

        if (isOccupied[xPosition][yPosition]) return cellGrid[xPosition][yPosition];

        isOccupied[xPosition][yPosition] = true;
        cellGrid[position / 4][position % 4] = b;
        b.setText("");
        b.setTextSize(35);
        b.setTypeface(null, Typeface.BOLD);
        b.setGravity(Gravity.CENTER);
        b.setBackground(MainActivity.getAppContext().getDrawable(R.drawable.grid_cell));
        b.setLayoutParams(new GridView.LayoutParams(147, 147));
        return b;
    }

    /**
     * Get the count of total cells in the grid
     *
     * @return The count for total cells to initialize the grid with
     */
    public final int getCount() {
        return 16;
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    public final Integer getItem(int position) {
        return 1;
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    public final long getItemId(int position) {
        return position;
    }

    /**
     * Update the grid to display as per the provided board
     *
     * @param board - the current snapshot of the board
     */
    @SuppressLint("SetTextI18n")
    void updateGrid(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != 0) {
                    cellGrid[i][j].setText(board[i][j] + "");
                    //updateCellTextAndColor(board[i][j], cellGrid[i][j]);
                } else if (board[i][j] == 0 && !cellGrid[i][j].getText().toString().equals("0")) {
                    cellGrid[i][j].setText("");
//                    updateCellTextAndColor(board[i][j], cellGrid[i][j]);
                }
            }
        }
    }

    /**
     * Reset the View Grid
     */
    @SuppressWarnings("unused")
    void reset() {
        for (TextView[] gridRow : cellGrid) {
            for (TextView aGridRow : gridRow) {
                aGridRow.setText("");
                aGridRow.setBackground(MainActivity.getAppContext().getDrawable(R.drawable.grid_cell));
            }
        }
    }
}
