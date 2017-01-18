package sadaka.com.example.android.sadaka.activities;

import java.util.ArrayList;


/**
 * Created by aisha on 1/18/2017.
 */
public class HistoryRecords {
    ArrayList<HistoryListItem> history;

    public HistoryRecords() {

    }

    public HistoryRecords(ArrayList<HistoryListItem> history) {
        this.history = history;

    }

    public void setHistory(ArrayList<HistoryListItem> history) {
        this.history = history;
    }

    public ArrayList<HistoryListItem> getHistory() {
        return this.history;
    }

    public void addHistory(HistoryListItem e) {
        history.add(e);

    }
}
