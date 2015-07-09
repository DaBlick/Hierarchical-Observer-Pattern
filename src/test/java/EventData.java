import com.barfly.hobservable.HObservable;
import com.barfly.hobservable.LogLevel;
import com.barfly.hobservable.LoggingEventData;

/**
 *
 * @author jonathanodgis
 */
public class EventData implements LoggingEventData {

    private Object data;
    private LogLevel logLevel;
    
    public EventData(Object data, LogLevel loglevel) {
        this(data);
        this.logLevel = loglevel;
    }

    public EventData(Object data) {
        this.data = data;
        this.logLevel = LogLevel.WARN;
    }

    @Override
    public String toString() {
        return this.data.toString();
    }

    @Override
    public String logString(HObservable hObservable) {
        return hObservable.getObservableID();
    }

    @Override
    public LogLevel getLogLevel() {
        return this.logLevel;
    }
}
