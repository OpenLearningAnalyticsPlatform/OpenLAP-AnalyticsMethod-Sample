package example;

import DataSet.OLAPColumnDataType;
import DataSet.OLAPDataColumnFactory;
import DataSet.OLAPDataSet;
import core.AnalyticsMethod;
import exceptions.OLAPDataColumnException;

import java.io.InputStream;

/**
 * Word counter example Analytics Method
 */
public class WikiWordCounterAnalyticsMethod extends AnalyticsMethod {


    public WikiWordCounterAnalyticsMethod() {

        this.setInput(new OLAPDataSet());
        this.setOutput(new OLAPDataSet());

        try {
            this.getInput().addOLAPDataColumn(
                    OLAPDataColumnFactory.createOLAPDataColumnOfType("words_in", OLAPColumnDataType.STRING, true)
            );
            this.getInput().addOLAPDataColumn(
                    OLAPDataColumnFactory.createOLAPDataColumnOfType("entries", OLAPColumnDataType.STRING, true)
            );
            this.getOutput().addOLAPDataColumn(
                    OLAPDataColumnFactory.createOLAPDataColumnOfType("words_out",OLAPColumnDataType.STRING, false)
            );
            this.getOutput().addOLAPDataColumn(
                    OLAPDataColumnFactory.createOLAPDataColumnOfType("count",OLAPColumnDataType.INTEGER, false)
            );
        } catch (OLAPDataColumnException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void implementationExecution(OLAPDataSet olapDataSet) {

        // Iterate over each word of the column of the arrays
        for (Object word :
                this.getInput().getColumns().get("words_in").getData())
        {
            int count = 0;
            // iterate over each of the entries and search for the word, if found, add 1 to counter
            for (Object entry :
                    this.getInput().getColumns().get("entries").getData())
            {
                count = count + wordInEntry((String) word, (String) entry);
            }
            getOutput().getColumns().get("words_out").getData().add(word);
            getOutput().getColumns().get("count").getData().add(count);
        }

    }

    /**
     * A function that returns the number of times that a word is on a string
     * @param word the word to be searched
     * @param entry the entry to search the word into
     * @return number of times word is in entry
     */
    private int wordInEntry(String word, String entry) {
        // Clever trick to count sub-strings :D
        return (entry.length() - entry.replace(word,"").length())/word.length();
    }

    @Override
    public Boolean hasPMML() {
        return false;
    }

    @Override
    public InputStream getPMMLInputStream() {
        return null;
    }
}
