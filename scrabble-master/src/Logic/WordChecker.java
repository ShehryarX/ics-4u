package Logic;

import Assets.Graphics.Cell;
import GUI.Location;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

public class WordChecker {
    static ArrayList<Cell> ufCell = new ArrayList<>();
    static boolean isFirst = true;

    private static boolean isConnectedToCenter(JLabel[][] cells) {
        HashSet<Cell> visited = new HashSet<>();

        Queue<Cell> queue = new LinkedList<>();
        Cell center = (Cell) cells[7][7];
        visited.add(center);
        queue.add(center);

        while (!queue.isEmpty()) {
            Cell toSearch = queue.remove();
            Cell[] neighbours = new Cell[4];

            Location location = toSearch.getLocationOnBoard();
            int row = location.getRow();
            int col = location.getCol();

            if (row != 0)
                neighbours[0] = (Cell) cells[row - 1][col];
            if (col != 0)
                neighbours[1] = (Cell) cells[row][col - 1];
            if (row != 14)
                neighbours[2] = (Cell) cells[row + 1][col];
            if (col != 14)
                neighbours[3] = (Cell) cells[row][col + 1];

            for (Cell curr : neighbours) {
                if (curr != null && !curr.isEmpty() && !visited.contains(curr)) {
                    visited.add(curr);
                    queue.add(curr);
                }
            }
        }

        int cnt = 0;
        for (int i = 0; i < 15; i++)
            for (int j = 0; j < 15; j++)
                if (!((Cell) cells[i][j]).isEmpty())
                    ++cnt;

        return cnt == visited.size();
    }

    // private ArrayList<Cell> updated;
    // Checks if the new tiles are all placed in one direction
    public static Result validPlacement(ArrayList<Cell> updated, JLabel[][] cells) {
        // creating another list of updated
        Cell[] temp = new Cell[updated.size()];
        for (int i = 0; i < updated.size(); i++) {
            temp[i] = updated.get(i);
        }

        Cell centerCell = (Cell) cells[7][7];

        if (centerCell.isEmpty() || !isConnectedToCenter(cells)) {
            return new Result(false);
        }
        if(updated.size()==1) {
        	if(updated.get(0).compareTo(centerCell)==0) {
        		return new Result(false);
        	}
        	else {
        		return isValidWord(updated.get(0), cells);
        	}
        }
        Location initialLocation = null;
        try {
         initialLocation= updated.get(0).getLocationOnBoard();
        } catch(Exception e) {
        	return new Result(false);
        }
        
        boolean colMatch = true;//true when one letter
        for (int i = 1; i < updated.size(); i++) {
            Location location = updated.get(i).getLocationOnBoard();

            if (location.getCol() != initialLocation.getCol()) {
                colMatch = false;
                break;
            }
        }

        if (!colMatch) {
            for (int i = 1; i < updated.size(); i++) {
                Location location = updated.get(i).getLocationOnBoard();

                if (location.getRow() != initialLocation.getRow()) {
                    return new Result(false);
                }
            }
        }

        Collections.sort(updated);

        if (colMatch) {
            // adding all the other letters in between
            Cell top = updated.get(0);
            Location topLocation = top.getLocationOnBoard();
            int col = topLocation.getCol();

            int firstRow = topLocation.getRow() - 1;

            while (firstRow > -1) {
                Cell current = (Cell) cells[firstRow][col];

                if (!current.isEmpty()) {
                    firstRow--;
                } else {
                    break;
                }
            }

            Cell bottom = updated.get(updated.size() - 1);
            Location bottomLocation = bottom.getLocationOnBoard();

            int lastRow = bottomLocation.getRow() + 1;

            while (lastRow < 15) {
                Cell current = (Cell) cells[lastRow][col];

                if (!current.isEmpty()) {
                    lastRow++;
                } else {
                    break;
                }
            }
            updated = new ArrayList<Cell>();
            for (int i = firstRow + 1; i < lastRow; i++) {
                if (updated.indexOf((Cell) cells[i][col]) < 0) {
                    updated.add((Cell) cells[i][col]);
                }
            }

        } else { // all in same row

            Cell left = updated.get(0);
            Location leftLocation = left.getLocationOnBoard();
            int row = leftLocation.getRow();

            int firstCol = leftLocation.getCol() - 1;

            while (firstCol > -1) {
                Cell current = (Cell) cells[row][firstCol];

                if (!current.isEmpty()) {
                    firstCol--;
                } else {
                    break;
                }
            }

            Cell right = updated.get(updated.size() - 1);
            Location rightLocation = right.getLocationOnBoard();

            int lastCol = rightLocation.getCol() + 1;

            while (lastCol < 15) {
                Cell current = (Cell) cells[row][lastCol];
                if (!current.isEmpty()) {
                    lastCol++;
                } else {
                    break;
                }
            }
            updated = new ArrayList<Cell>();
            for (int i = firstCol + 1; i < lastCol; i++) {
                if (updated.indexOf((Cell) cells[row][i]) < 0) {
                    updated.add((Cell) cells[row][i]);
                }
            }

        }

        ArrayList<String> subWords = new ArrayList<String>();
        if (colMatch) {
            // find horizontal subwords
            for (int i = 0; i < temp.length; i++) {
                int row = temp[i].getLocationOnBoard().getRow();
                int currentCol = temp[i].getLocationOnBoard().getCol();
                currentCol--;
                while (currentCol > 0 && !((Cell) cells[row][currentCol]).isEmpty()) {
                    currentCol--;
                }
                currentCol++;
                String wrd = "";
                while (currentCol < 15 && !((Cell) cells[row][currentCol]).isEmpty()) {
                    wrd += ((Cell) cells[row][currentCol]).getTile().getLetter();
                    currentCol++;
                }
                if (wrd.length() > 1) {
                    subWords.add(wrd);
                }

            }
        } else {
            // find vertical subwords
            for (int i = 0; i < temp.length; i++) {
                int col = temp[i].getLocationOnBoard().getCol();
                int currentRow = temp[i].getLocationOnBoard().getRow();
                currentRow--;
                while (currentRow > 0 && !((Cell) cells[currentRow][col]).isEmpty()) {
                    currentRow--;
                }
                currentRow++;
                String wrd = "";
                while (currentRow < 15 && !((Cell) cells[currentRow][col]).isEmpty()) {
                    wrd += ((Cell) cells[currentRow][col]).getTile().getLetter();
                    currentRow++;
                }
                if (wrd.length() > 1) {
                    subWords.add(wrd);
                }

            }
        }
        Collections.sort(updated);

        StringBuilder possibleWord = new StringBuilder("");

        for (Cell cell : updated) {
            possibleWord.append(cell.getTile().getLetter());
        }
        
        boolean validMainWord = isWordInDictionary(possibleWord.toString());
        if (validMainWord) {
            // check the rest of the sub words
            for (int i = 0; i < subWords.size(); i++) {
                if (!isWordInDictionary(subWords.get(i))) {
                    return new Result(false);
                }
            }
            	return new Result(true, scoreTally(updated, cells), possibleWord.toString());
            
            
        }
        return new Result(false);
    }

    private static Result isValidWord(Cell letter,JLabel[][]cells) {
    	Location l = letter.getLocationOnBoard();
    	int row = l.getRow();
    	int col = l.getCol();
    	String wrd1 = "";
    	String wrd2 = "";
    	Cell[] neighbours = new Cell[4];
    	if (row != 0)
            neighbours[0] = (Cell) cells[row - 1][col];
        if (col != 0)
            neighbours[1] = (Cell) cells[row][col - 1];
        if (row != 14)
            neighbours[2] = (Cell) cells[row + 1][col];
        if (col != 14)
            neighbours[3] = (Cell) cells[row][col + 1];
        boolean colsMatch = false;
        boolean rowsMatch = false;
        if(neighbours[0]!=null&&!neighbours[0].isEmpty()) {
        	colsMatch = true;
        }
        if(neighbours[2]!=null&&!neighbours[2].isEmpty()) {
        	colsMatch = true;
        }
        if(neighbours[1]!=null&&!neighbours[1].isEmpty()) {
        	rowsMatch = true;
        }
        if(neighbours[3]!=null&&!neighbours[3].isEmpty()) {
        	rowsMatch = true;
        }
        if(colsMatch) {
        	int currentRow = row;
        	currentRow--;
        	while (currentRow > 0 && !((Cell) cells[currentRow][col]).isEmpty()) {
        		currentRow--;
            }
        	currentRow++;
        	while(currentRow <15 && !((Cell) cells[currentRow][col]).isEmpty()) {
        		
        		wrd1+=((Cell) cells[currentRow][col]).getTile().getLetter();
        		currentRow++;
            }
        }
        if(rowsMatch) {
        	int currentCol = col;
        	currentCol--;
        	while (currentCol > 0 && !((Cell) cells[row][currentCol]).isEmpty()) {
        		currentCol--;
            }
        	currentCol++;
        	while(currentCol <15 && !((Cell) cells[row][currentCol]).isEmpty()) {
        		
        		wrd2+=((Cell) cells[row][currentCol]).getTile().getLetter();
        		currentCol++;
            }
        }
        ArrayList<Cell> updated = new ArrayList<Cell>();
        updated.add(letter);
        if(wrd1.length()>0&&wrd2.length()>0) {
        	if(isWordInDictionary(wrd1)&&isWordInDictionary(wrd2)) {
        		return new Result(true, scoreTally(updated, cells), wrd1);
        	}
        }
        else if(wrd1.length()>0) {
        	if(isWordInDictionary(wrd1)) {
        		return new Result(true, scoreTally(updated, cells), wrd1);
        	}
        }
        else {
        	if(isWordInDictionary(wrd2)) {
        		return new Result(true, scoreTally(updated, cells), wrd2);
        	}
        }
    	return new Result(false);
    }
    private static boolean isWordInDictionary(String word) {
    	try {
    		Dictionary dic = new Dictionary();
    		return dic.isValidWord(word);
    	}
    	catch (Exception e) {
            e.printStackTrace();
        }
    	return false;
    	
    	/*
        try {
            URL url = new URL("https://en.oxforddictionaries.com/search?utf8=&filter=dictionary&query=" + word);
            StringBuilder rawHTML = new StringBuilder("");
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

            String curr = "";

            while ((curr = br.readLine()) != null) {
                rawHTML.append(curr);
            }

            return rawHTML.indexOf("No exact matches") < 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
        */
    }

    // SCORE TALLY
    public static int scoreTally(ArrayList<Cell> updated, JLabel[][] cells) {
        int total = 0;
        Collections.sort(updated);
        Location initialLocation = updated.get(0).getLocationOnBoard();
        boolean colMatch = true;
        for (int i = 1; i < updated.size(); i++) {
            Location location = updated.get(i).getLocationOnBoard();

            if (location.getCol() != initialLocation.getCol()) {
                colMatch = false;
                break;
            }
        }

        if (colMatch) {
            // Main word is vertical
            // find the very top index
            int col = updated.get(0).getLocationOnBoard().getCol();
            int currentRow = updated.get(0).getLocationOnBoard().getRow();
            currentRow--;
            while (currentRow > -1 && !((Cell) (cells[currentRow][col])).isEmpty()) {
                currentRow--;
            }
            currentRow++;
            // Adding up score for main word
            int M = 1; // multipliers if any
            while (currentRow < 15 && !((Cell) (cells[currentRow][col])).isEmpty()) {
                Cell currCell = (Cell) cells[currentRow][col];
                if (currCell.isDoubleLetter()) {
                    total += currCell.getTile().getValue() * 2;
                } else if (currCell.isTripleLetter()) {
                    total += currCell.getTile().getValue() * 3;
                } else if (currCell.isTripleWord()) {
                    total += currCell.getTile().getValue();
                    M *= 3;
                } else if (currCell.isDoubleWord()) {
                    total += currCell.getTile().getValue();
                    M *= 2;
                } else {
                    total += currCell.getTile().getValue();
                }
                currentRow++;
            }
            total *= M;

            // Finding subwords
            for (int i = 0; i < updated.size(); i++) {
                int wordScore = 0;
                M = 1;
                int currentCol = updated.get(i).getLocationOnBoard().getCol();
                int row = updated.get(i).getLocationOnBoard().getRow();
                if ((currentCol - 1 > 0 && !((Cell) cells[row][currentCol - 1]).isEmpty())
                        || (currentCol + 1 < 15 && !((Cell) cells[row][currentCol + 1]).isEmpty())) {
                    currentCol--;
                    while (currentCol > 0 && !((Cell) cells[row][currentCol]).isEmpty()) {
                        currentCol--;
                    }
                    currentCol++;
                    String wrd = "";
                    while (currentCol < 15 && !((Cell) cells[row][currentCol]).isEmpty()) {
                        Cell currCell = (Cell) cells[row][currentCol];
                        if (currCell.isDoubleLetter()) {
                            wordScore += currCell.getTile().getValue() * 2;
                        } else if (currCell.isTripleLetter()) {
                            wordScore += currCell.getTile().getValue() * 3;
                        } else if (currCell.isTripleWord()) {
                            wordScore += currCell.getTile().getValue();
                            M *= 3;
                        } else if (currCell.isDoubleWord()) {
                            wordScore += currCell.getTile().getValue();
                            M *= 2;
                        } else {
                            wordScore += currCell.getTile().getValue();
                        }
                        currentCol++;
                    }
                    total += wordScore * M;
                }

            }

        } else {
            // Main word is vertical
            // find the very top index
            int currentCol = updated.get(0).getLocationOnBoard().getCol();
            int row = updated.get(0).getLocationOnBoard().getRow();
            currentCol--;
            while (currentCol > -1 && !((Cell) (cells[row][currentCol])).isEmpty()) {
                currentCol--;
            }
            currentCol++;
            // Adding up score for main word
            int M = 1; // multipliers if any
            while (currentCol < 15 && !((Cell) (cells[row][currentCol])).isEmpty()) {
                Cell currCell = (Cell) cells[row][currentCol];
                if (currCell.isDoubleLetter()) {
                    total += currCell.getTile().getValue() * 2;
                } else if (currCell.isTripleLetter()) {
                    total += currCell.getTile().getValue() * 3;
                } else if (currCell.isTripleWord()) {
                    total += currCell.getTile().getValue();
                    M *= 3;
                } else if (currCell.isDoubleWord()) {
                    total += currCell.getTile().getValue();
                    M *= 2;
                } else {
                    total += currCell.getTile().getValue();
                }
                currentCol++;
            }
            total *= M;

            // Finding subwords
            for (int i = 0; i < updated.size(); i++) {
                int wordScore = 0;
                M = 1;
                int col = updated.get(i).getLocationOnBoard().getCol();
                int currentRow = updated.get(i).getLocationOnBoard().getRow();
                if ((currentRow - 1 > 0 && !((Cell) cells[currentRow - 1][col]).isEmpty())
                        || (currentRow + 1 < 15 && !((Cell) cells[currentRow + 1][col]).isEmpty())) {
                    currentRow--;
                    while (currentRow > 0 && !((Cell) cells[currentRow][col]).isEmpty()) {
                        currentRow--;
                    }
                    currentRow++;
                    String wrd = "";
                    while (currentRow < 15 && !((Cell) cells[currentRow][col]).isEmpty()) {
                        Cell currCell = (Cell) cells[currentRow][col];
                        if (currCell.isDoubleLetter()) {
                            wordScore += currCell.getTile().getValue() * 2;
                        } else if (currCell.isTripleLetter()) {
                            wordScore += currCell.getTile().getValue() * 3;
                        } else if (currCell.isTripleWord()) {
                            wordScore += currCell.getTile().getValue();
                            M *= 3;
                        } else if (currCell.isDoubleWord()) {
                            wordScore += currCell.getTile().getValue();
                            M *= 2;
                        } else {
                            wordScore += currCell.getTile().getValue();
                        }
                        currentRow++;
                    }
                    total += wordScore * M;
                }

            }
        }
        return total;
    }

}