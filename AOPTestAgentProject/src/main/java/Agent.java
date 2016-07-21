/**
 * Created by mohanakumar on 20/07/16.
 */


import java.lang.instrument.Instrumentation;
public class Agent {
    public static void premain(String agentArguments, Instrumentation instrumentation) {

        System.out.println("IN PREMAIN FUNCTION");
        instrumentation.addTransformer(new SimpleTransformer());

    }
}
