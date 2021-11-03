import jade.core.Agent;
import jade.lang.acl.ACLMessage;

public class myagent extends Agent {
    double number;
    int amount_of_numbers=1;
    @Override
    protected void setup() {
        super.setup();
        Object[] args = getArguments();
        number = Double.valueOf((String) args[0]);
        //System.out.println(number);
        addBehaviour(new mybehaviour(this));

    }
    @Override
    protected void takeDown() {
        super.takeDown();
        System.out.println("work is done");
    }
}
