package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SeatController {
    private final SeatList seatList = new SeatList(9, 9);
    private final List<Purchase> soldSeats = new ArrayList<>();
    private final Stats stats = new Stats();

    @GetMapping("/seats")
    public SeatList getSeats() {
        return seatList;
    }

    @PostMapping("/purchase")
    public Purchase purchase(@RequestBody Seat seat) {
        if (seat.getRow() < 1 || seat.getRow() > 9 || seat.getColumn() < 1 || seat.getColumn() > 9) {
            throw new TicketException("The number of a row or a column is out of bounds!", HttpStatus.BAD_REQUEST);
        }

        Seat seatWithPrice = seatList.sellSeat(seat.getRow(), seat.getColumn());

        if (seatWithPrice == null) {
            throw new TicketException("The ticket has been already purchased!", HttpStatus.BAD_REQUEST);
        }

        Purchase purchase = new Purchase(seatWithPrice);
        soldSeats.add(purchase);
        stats.addSoldTicket(seatWithPrice);

        return purchase;
    }

    @PostMapping("/return")
    public ReturnedTicket returnTicket(@RequestBody Token token) {
        Purchase purchase;
        for (int i = 0; i < soldSeats.size(); i++) {
            purchase = soldSeats.get(i);
            if (purchase.getToken().equals(token.getToken())) {
                seatList.addSeat(purchase.getTicket());
                soldSeats.remove(i);
                stats.returnTicket(purchase.getTicket());
                return new ReturnedTicket(purchase.getTicket());
            }
        }

        throw new TicketException("Wrong token!", HttpStatus.BAD_REQUEST);

    }

    @PostMapping("/stats")
    public Stats stats(@RequestParam(required = false) String password) {
        if (!"super_secret".equals(password)) {
            throw new TicketException("The password is wrong!", HttpStatus.UNAUTHORIZED);
        }

        return stats;
    }

    @ExceptionHandler(TicketException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomErrorMessage> handleFlightNotFound(
            TicketException e, WebRequest request) {

        CustomErrorMessage body = new CustomErrorMessage(e.getLocalizedMessage());

        return new ResponseEntity<>(body, e.getHttpStatus());
    }
}
