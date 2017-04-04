package nl.utwente.hmi.avatar.behaviourPlanner;

import com.fasterxml.jackson.databind.JsonNode;
import hmi.unityembodiments.UnityEmbodiment;
import lombok.extern.slf4j.Slf4j;
import nl.utwente.hmi.middleware.MiddlewareListener;
import nl.utwente.hmi.middleware.stomp.STOMPMiddleware;

import static nl.utwente.hmi.middleware.helpers.JsonNodeBuilders.object;

@Slf4j
public class ASAPThread extends Thread implements MiddlewareListener {

    public enum Status {
        STARTING("Starting up the application."),
        WAITING_FOR_AGENTSPEC("Waiting for agentspec to configure the rest. Configuring might take some time after receiving AgentSpec."),
        RUNNING("Configured & Running."),
        ERROR("Unknown Error");

        private final String descr;
        private Status(final String descr) {
            this.descr = descr;
        }

        @Override
        public String toString() {
            return descr;
        }
    }

    public boolean running;
    ASAPStarter aa;

    public ASAPThread(ASAPStarter aa) {
        this.aa = aa;
        this.running = true;
    }

    public void run() {
        STOMPMiddleware middleware = new STOMPMiddleware("127.0.0.1", 61613, "/topic/CleVRCmdFeedback", "/topic/AsapState");
        middleware.addListener(this);

        long lastExecution = 0;

        while (running) {
            if((System.currentTimeMillis() - lastExecution) >= 1000) {
                lastExecution = System.currentTimeMillis();
                /*
                 * 	{
					    "msgType": "ApplicationState"
					    "state": "State" // aa.status.toString()
					    "message": "A message " aa.status.name()
					}
                 */
                JsonNode msg = object(
                        UnityEmbodiment.AUPROT_PROP_MSGTYPE, UnityEmbodiment.AUPROT_MESSAGE_APPLICATION_STATE,
                        UnityEmbodiment.AUPROT_PROP_STATE, aa.status.name(),
                        UnityEmbodiment.AUPROT_PROP_MESSAGE, aa.status.toString()).end();

                middleware.sendData(msg);
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void receiveData(JsonNode jn) {
        System.out.println("/topic/CleVRCmdFeedback: "+jn.toString());

        if (jn.has("msgType") && jn.get("msgType").asText().equals("StopScenario")) {
            System.exit(0);
        }

        if (jn.has("msgType") && jn.get("msgType").asText().equals("StartScenario")) {
            aa.Init(jn.get("scenarioId").asText());
        }
        // could handle stopping/restarting here...
    }

}
