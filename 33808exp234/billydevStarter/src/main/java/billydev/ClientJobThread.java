package billydev;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ClientJobThread extends Thread {
    private String threadGivenName;
    private String script;
    private String parameters;
    private String controlMsg;
    private Msg returnMsg;


    public ClientJobThread(String inputName,
                           String script,
                           String parameters,
                           String controlMsg,
                           int timeInSeconds,
                           Msg returnMsg) {
        this.threadGivenName = inputName;
        this.script = script;
        this.parameters = parameters;
        this.controlMsg = controlMsg;
        this.returnMsg=returnMsg;
    }

    public void run() {

        /*
         * One Job thread
         * 1. first  trigger the job
         *    The job thread will get the result and output it;
         *
         * 2. Trigger a standalone JobControlThread and check the MMQ, when get Cancel message, just cancel the job;
         *    lock the agentMessageQueue
         *    dequeue the message when match this  job !!
         *        if need cancel the job, cancel is, add result to Agent message queue
         * 3. release lock and loop checking if there is output from the job ( means it is running)				 *
         *				 *
         * 4.  if job complete naturally, then JobThread add result to agent message queue as well !!
         *     jobThread also need to interrupt the JobControlThread
         *
         *
         */
        //first trigger the job
        try {
            /*
             * first we need deque the message to get detailed job information
             */


            final Process p = Runtime.getRuntime().exec(script + " " + parameters);
            //todo: once the job working, we can remove the following hard coded job script details
            //final Process p = Runtime.getRuntime().exec("/home/billy/billydev/tempdev/181123-1-30800Exp003_ControlLongRunningProcess/endlessloop.sh");

            class ClientJobControlThread extends Thread {
                public void run() {
                    while (true) {
                        try {
                            if (controlMsg.equals("STOP")) {
                                p.destroy();//todo: how to get the result of destroy .
                                break; //should break the while loop
                            } else {
                                Thread.currentThread().sleep(200);
                            }
                        } catch (InterruptedException e) {
                            p.destroy();
                            break;
                        }
                    }
                }
            }

            Thread jobControlThread = new ClientJobControlThread();
            jobControlThread.start();

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            /*
             * If job not complete, then reader suppose block here
             * so when readLine got null, stream suppose to be ended
             * todo: check some jobs without any output in spool
             */
            line = reader.readLine();
                returnMsg.setContent(line);
                System.out.println(returnMsg.getContent());


            //when job completed normally,stop the jobControlThread as well
            jobControlThread.interrupt();
            System.out.println("ClientJobThread:  JobControlThread is interrupted");


            try {
                Thread.currentThread().sleep(2 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
