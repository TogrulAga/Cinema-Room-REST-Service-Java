package cinema;

public class Stats {
    private int current_income;
    private int number_of_available_seats = 81;
    private int number_of_purchased_tickets = 0;

    public Stats() {}

    public int getCurrent_income() {
        return current_income;
    }

    public void setCurrent_income(int current_income) {
        this.current_income = current_income;
    }

    public int getNumber_of_available_seats() {
        return number_of_available_seats;
    }

    public void setNumber_of_available_seats(int number_of_available_seats) {
        this.number_of_available_seats = number_of_available_seats;
    }

    public int getNumber_of_purchased_tickets() {
        return number_of_purchased_tickets;
    }

    public void setNumber_of_purchased_tickets(int number_of_purchased_tickets) {
        this.number_of_purchased_tickets = number_of_purchased_tickets;
    }

    public void addSoldTicket(Seat seat) {
        current_income += seat.getPrice();
        number_of_available_seats--;
        number_of_purchased_tickets++;
    }

    public void returnTicket(Seat ticket) {
        current_income -= ticket.getPrice();
        number_of_available_seats++;
        number_of_purchased_tickets--;
    }
}
