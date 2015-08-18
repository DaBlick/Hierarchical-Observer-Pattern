package builderpatternexample;

import com.barfly.hobservable.BaseHObservable;
import static com.barfly.hobservable.CollectionEventDataEnum.ADD;

/**
 *
 * @author jonathanodgis
 */
public class BuilderPatternExample
{
        public static void main(String[] args)
        {
            Object eventData = "Event";
            //Event that has an enum (like collection observable)
            Event event1 = new Event.EventBuilder(new BaseHObservable("Observable", null), eventData)
                    .eventDataEnum(ADD)
                    .build();
            
            //Event that doesn't need an enum (like the standard observable events)
            Event event2 = new Event.EventBuilder(new BaseHObservable("Observable", null), eventData)
                    .build();
        } 
}
