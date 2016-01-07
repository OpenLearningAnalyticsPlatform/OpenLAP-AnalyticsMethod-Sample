package example;

import DataSet.OLAPDataSet;
import DataSet.OLAPPortConfiguration;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import core.AnalyticsMethod;
import core.exceptions.AnalyticsMethodInitializationException;

import java.io.IOException;
import java.io.InputStream;

/**
 * Class with main method to be executed as example of the WikiWordCounterAnalyticsMethod
 */
public class ExecutionExample {

    public static final String DATASET_RESOURCE_PATH = "/DataSetInputExampleWikiWordCount.json";
    public static final String CONFIGURATION_RESOURCE_PATH = "/ConfigurationExampleWikiWordCount";


    // An execution example for the WikiWordCounterAnalyticsMethod
    public static void main(String[] args) {
        ExecutionExample context = new ExecutionExample();
        ObjectMapper mapper = new ObjectMapper();
        // Prettify the output
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        try {
            //Load input OLAPDataSet
            OLAPDataSet inputDataSet = mapper.
                    readValue(context.loadResourceFile(DATASET_RESOURCE_PATH),
                            OLAPDataSet.class);
            //Load configuration
            OLAPPortConfiguration inputConfiguration = mapper.
                    readValue(context.loadResourceFile(CONFIGURATION_RESOURCE_PATH),
                            OLAPPortConfiguration.class);
            //Instantiate the WikiWordCounterAnalyticsMethod
            AnalyticsMethod method = new WikiWordCounterAnalyticsMethod();
            //Initialize with the input and configuration
            method.initialize(inputDataSet,inputConfiguration);
            //Execute the WikiWordCounterAnalyticsMethod
            method.execute();
            //Output the result in console
            System.out.println("Output of running the WikiWordCounterAnalyticsMethod:");
            System.out.println(mapper.writeValueAsString(method.getOutput()));
        } catch (AnalyticsMethodInitializationException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private InputStream loadResourceFile(String configurationResourcePath) {
        return this.getClass().getResourceAsStream(configurationResourcePath);
    }


}
