package cinema;

import java.util.UUID;

public class Purchase {
    private UUID token;
    private Seat ticket;

    public Purchase() {}

    public Purchase(Seat seat) {
        this.ticket = seat;
        this.token = UUID.randomUUID();
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public Seat getTicket() {
        return ticket;
    }

    public void setTicket(Seat ticket) {
        this.ticket = ticket;
    }
}
