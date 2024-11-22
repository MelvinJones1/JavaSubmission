package entity;


import java.util.ArrayList;
import java.util.List;

public class AdoptionEvent {
    private  int id;
    private String eventName;
    private String eventDate;
    private List<IAdoptable> participants;

    public AdoptionEvent(String eventName, String eventDate) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.participants = new ArrayList<>();
    }
    // Getter and Setter for 'id'
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void hostEvent() {
        System.out.println("Hosting event: " + eventName + " on " + eventDate);
    }

    public void registerParticipant(IAdoptable participant) {
        participants.add(participant);
    }

    public void listParticipants() {
        for (IAdoptable participant : participants) {
            System.out.println(participant);
        }
    }
}
