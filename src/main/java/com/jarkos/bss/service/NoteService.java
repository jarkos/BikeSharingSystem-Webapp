package com.jarkos.bss.service;

import com.jarkos.bss.persistance.dao.NoteDao;
import com.jarkos.bss.persistance.entity.Bike;
import com.jarkos.bss.persistance.entity.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Jarek on 2015-12-27.
 */
@Service
@Transactional
public class NoteService {

    @Autowired
    private NoteDao noteDao;

    public Note findNoteById(Integer id) {
        return noteDao.findNoteById(id);
    }

    public void saveNewNote(Note note) {
        noteDao.save(note);
    }

    public void deleteNote(Note note) {
        noteDao.delete(note);
    }

    public void updateNote(Note note){
        noteDao.update(note);
    }

    public List<Note> findAllNotesForBike(int bikeId){

        return noteDao.findAllNotesForBike(bikeId);
    }
}
