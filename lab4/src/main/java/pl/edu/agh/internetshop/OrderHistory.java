package pl.edu.agh.internetshop;

import java.util.ArrayList;
import java.util.List;

public class OrderHistory {

    private List<HistoryLog> logs;

    public OrderHistory(List<HistoryLog> logs) {
        this.logs = logs;
    }

    public void addLog(HistoryLog log) {
        logs.add(log);
    }

    public List<HistoryLog> search(SearchStrategy strategy) {
        List<HistoryLog> result = new ArrayList<>();
        for (HistoryLog log : logs) {
            if (strategy.filter(log)) {
                result.add(log);
            }
        }

        return result;
    }

    public List<HistoryLog> getLogs() {
        return logs;
    }
}
