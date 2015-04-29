package aka;

public class Page {
    private String target;

    public Page() { }
    
    public String getTarget() { return target; }
    
    public boolean getReloaded() { return target != null; }
    
    public void setTarget(String value) { target = value; }
    
    }
