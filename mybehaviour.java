import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import java.util.concurrent.TimeUnit;

class mybehaviour extends Behaviour {
    boolean is_alive = false;
    myagent agent;
    String receive_name="Agent1";
    mybehaviour(myagent agent){
        this.agent=agent;
    }
    @Override
    public void action() {
        String agent_consider=agent.getName();
        char[] agent_name_massive=agent_consider.toCharArray();
        boolean i=(agent_name_massive[5]=='1');
        boolean j=(agent_name_massive[6]=='@');

        if(!i || !j){
            ACLMessage msg=new ACLMessage(ACLMessage.INFORM);
            msg.addReceiver(new AID(receive_name, AID.ISLOCALNAME));
            msg.setContent(String.valueOf(agent.number));
            agent.send(msg);
            System.out.println(agent.getName()+" sent a message "+msg.getContent());
            is_alive=true;
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            getMessage();
        }
    }

    @Override
    public boolean done() {
        return is_alive;
    }
    protected void getMessage(){
        ACLMessage msg=agent.receive();
        while(msg!=null) {
            //if(msg!=null){
            agent.number = agent.number + Double.valueOf(msg.getContent());
            agent.amount_of_numbers++;
            System.out.println(agent.getName() + " got a message " + msg.getContent());
            msg=agent.receive();
        }
        if(msg==null){
            System.out.println("ariphmetic mean is " + agent.number / agent.amount_of_numbers);
            is_alive=true;
        }
        //} else{
            //System.out.println("no messages");
            //System.out.println(agent.number/agent.count);

    }
}
/*точно рабочий класс
public class mybehaviour extends Behaviour {
    boolean is_alive = false;

    @Override
    public void action() {
        System.out.println("hello world");
        is_alive=true;
    }

    @Override
    public boolean done() {
        return is_alive;
    }
}*/
