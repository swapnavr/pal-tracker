package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {
    private HashMap<Long, TimeEntry> timeEntries = new HashMap<Long, TimeEntry>();

    long currentId = 1L;
    public boolean getId(){
      return true;
    }

    public void delete(long id) {
        timeEntries.remove(id);
    }


    public List<TimeEntry> list() {

        return new ArrayList<>(timeEntries.values());
    }

    public TimeEntry create(TimeEntry timeEntry) {

        Long id = currentId++;
        TimeEntry newTimeEntry = new TimeEntry();
        newTimeEntry.setId(id);
        newTimeEntry.setDate(timeEntry.getDate());
        newTimeEntry.setHours(timeEntry.getHours());
        newTimeEntry.setProjectId(timeEntry.getProjectId());
        newTimeEntry.setUserId(timeEntry.getUserId());

        timeEntries.put(id, newTimeEntry);

        return newTimeEntry;
    }

    public TimeEntry find(long timeEntryId) {

        return timeEntries.get(timeEntryId);
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {

        if(timeEntries.get(id) == null){
            return null;
        }

        TimeEntry existingTimeEntity = timeEntries.get(id);
        existingTimeEntity.setUserId(timeEntry.getUserId());
        existingTimeEntity.setProjectId(timeEntry.getProjectId());
        existingTimeEntity.setHours(timeEntry.getHours());
        existingTimeEntity.setDate(timeEntry.getDate());

       // timeEntries.replace(id, timeEntry);

            return existingTimeEntity;

    }
}
