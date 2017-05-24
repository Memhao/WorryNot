package licence.meme.worrynot.models;

/**
 * Created by xander on 24.05.2017.
 */

public class MethodChangedEvent {
    private  Method method;
    public MethodChangedEvent(Method newMethod){
        this.method = newMethod;
    }
    public Method getMethod(){
        return this.method;
    }
}
