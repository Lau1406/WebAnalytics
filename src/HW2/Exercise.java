package HW2;

import data.RecordSet;

/**
 *
 * @author sjef
 */
public abstract class Exercise {
    protected RecordSet recordSet;
    public void setRecordSet(RecordSet recordSet){
        this.recordSet = recordSet;
    }
    
    public abstract void run();
}
