package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;



@RestController
@RequestMapping("/TimeEntry")
public class TimeEntryController {

    @Autowired
    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {

        TimeEntry res = timeEntryRepository.create(timeEntryToCreate);

        return new ResponseEntity<TimeEntry>(res,HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {

        TimeEntry res = timeEntryRepository.find(timeEntryId);
        HttpStatus httpStatus = HttpStatus.OK;
        if(res == null){
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<TimeEntry>(res,httpStatus);
    }

    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> res = timeEntryRepository.list();
        return new ResponseEntity<List<TimeEntry>>(res, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity update(long timeEntryId, TimeEntry expected) {
        TimeEntry res = timeEntryRepository.update(timeEntryId, expected);
        HttpStatus httpStatus = HttpStatus.OK;
        if(res == null){
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity(res, httpStatus);
    }
    @DeleteMapping
    public ResponseEntity delete(long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);
        return new ResponseEntity((HttpStatus.NO_CONTENT));
    }
}
