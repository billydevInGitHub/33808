package billydev;

public class TcpDumpBean {
    String controlMsg="";
    String returnMsg="";

    public String getControlMsg() {
        return controlMsg;
    }

    public void setControlMsg(String controlMsg) {
        this.controlMsg = controlMsg;
    }

    public synchronized String triggerTcpDump(int timeInSeconds)  {
        Msg returnMsg=new Msg();
        Thread thread= new ClientJobThread("TcpDump",
                "cmd",
                "",
                controlMsg,
                timeInSeconds,
                returnMsg
        );
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return returnMsg.getContent();
    }
}
