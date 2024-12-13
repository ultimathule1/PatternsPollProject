package dev;

import dev.facade.PollLifecycleFacade;
import dev.poll.Poll;

public class Main {
    public static void main(String[] args) {
        PollLifecycleFacade facade = new PollLifecycleFacade();
        Poll poll = facade.createPoll();
        facade.makeAnalyzePoll(facade.getUserResponses(poll));
    }
}
