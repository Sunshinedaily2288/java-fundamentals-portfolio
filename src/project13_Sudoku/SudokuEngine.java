package project13_Sudoku;

public class SudokuEngine {
    private SudokuModel model = new SudokuModel();

    public String placeNumber(int r, int c, int num) {
        if (num < 1 || num > 4) return "INVALID_RANGE";
        if (model.getCell(r, c) != 0) return "OCCUPIED";
        if (model.isValidMove(r, c, num)) {
            model.setCell(r, c, num);
            return model.isFull() ? "COMPLETE" : "SUCCESS";
        }
        return "RULE_VIOLATION";
    }

    public void reset() { model.resetBoard(); }
    public int[] requestHint() { return model.findHint(); }
}





