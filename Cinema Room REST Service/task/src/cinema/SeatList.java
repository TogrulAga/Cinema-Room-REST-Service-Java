package cinema;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SeatList {
    private int total_rows;
    private int total_columns;
    private final List<Seat> available_seats = new ArrayList<>();

    public SeatList() {}

    public SeatList(int total_rows, int total_columns) {
        this.total_rows = total_rows;
        this.total_columns = total_columns;

        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                if (i < 4) {
                    available_seats.add(new Seat(i, j, 10));
                } else {
                    available_seats.add(new Seat(i, j, 8));
                }
            }
        }
    }

    public Seat sellSeat(int row, int column) {
        Seat seat;
        for (int i = 0; i < available_seats.size(); i++) {
            seat = available_seats.get(i);
            if (seat.getRow() == row && seat.getColumn() == column) {
                available_seats.remove(i);
                return seat;
            }
        }

        return null;
    }

    public void addSeat(Seat seat) {
        available_seats.add(seat);
    }

    public int getTotal_rows() {
        return total_rows;
    }

    public void setTotal_rows(int total_rows) {
        this.total_rows = total_rows;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public void setTotal_columns(int total_columns) {
        this.total_columns = total_columns;
    }

    public List<Seat> getAvailable_seats() {
        return available_seats;
    }
}
