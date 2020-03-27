package thread.synchronize;

import java.util.*;

/**
 * 剧院抢座位
 *
 * @author 李昭
 */
public class ThreaterSeats {
    public static void main(String[] args) {
        Random random = new Random();
        List<Integer> available = new ArrayList<>();
        List<Integer> cus01 = new ArrayList<>();
        List<Integer> cus02 = new ArrayList<>();
        Set<Integer> set01 = new HashSet<>();
        Set<Integer> set02 = new HashSet<>();
        for (int i = 0; i < 10; ++i) {
            available.add(i);
            set01.add((int) random.nextInt(10));
            set02.add((int) random.nextInt(10));
        }
        cus01.addAll(set01);
        cus02.addAll(set02);
        Theater theater = new Theater(available);
        Customer c1 = new Customer(theater,cus01);
        Customer c2 = new Customer(theater,cus02);
        Thread t1 = new Thread(c1);
        Thread t2 = new Thread(c2);
        t1.start();
        t2.start();
    }
}
class Theater {
    private List<Integer> remainSeat;      //剩余座位

    public List<Integer> getRemainSeat() {
        return remainSeat;
    }

    public void setRemainSeat(List<Integer> remainSeat) {
        this.remainSeat = remainSeat;
    }

    public Theater(List<Integer> remainSeat) {
        this.remainSeat = remainSeat;
    }


    public boolean getTickets(List<Integer> seat) {
        System.out.println(remainSeat);
        if (seat.size() > remainSeat.size())
            return false;
        List<Integer> copy = new ArrayList<>();
        synchronized (this) {
            copy.addAll(remainSeat);
            copy.removeAll(seat);
            if (remainSeat.size() - copy.size() == seat.size()) {
                remainSeat = copy;
                return true;
            }
        }
        return false;
    }
}

class Customer implements Runnable {

    private Theater theater;
    private List<Integer> seats;
    public Customer(Theater theater, List<Integer> seats) {
        this.theater = theater;
        this.seats = seats;
    }

    @Override
    public void run() {

        boolean tickets;
        if (seats.size() > theater.getRemainSeat().size()) {
            System.out.println("购买"+seats.size()+"张,剩余"+theater.getRemainSeat().size()+"张");
            return;
        }
        tickets = theater.getTickets(seats);
        if (tickets) {
            System.out.println("出票成功"+Thread.currentThread().getName()+"购买了"+seats);
        } else {
            System.out.println("购买"+seats.size()+"张,剩余"+theater.getRemainSeat().size()+"张");
        }
    }
}