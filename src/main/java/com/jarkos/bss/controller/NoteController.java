package com.jarkos.bss.controller;

import com.jarkos.bss.persistance.entity.Bike;
import com.jarkos.bss.persistance.entity.Note;
import com.jarkos.bss.service.BikeService;
import com.jarkos.bss.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by Jarek on 2015-12-27.
 */
@Controller
public class NoteController {
    private static final Logger log = LoggerFactory.getLogger(NoteController.class);

    @Autowired
    private NoteService noteService;

    @Autowired
    private BikeService bikeService;

    @RequestMapping("/admin/bikes/{id}/notes")
    public String getNotesListForBike(@PathVariable int id, Model model) {
        log.debug("getNotesListForBike");
        Bike b = bikeService.findBikeById(id);
        model.addAttribute("notes", b.getNotes());
        model.addAttribute("bike", b);
        return "bike-notes";
    }

    @RequestMapping(value = "/admin/bikes/{id}/notes/create", method = RequestMethod.GET)
    public String addNoteForm(@PathVariable int id, Model model) {
        Bike bike = bikeService.findBikeById(id);
        Note note = new Note();
        model.addAttribute("note", note);
        model.addAttribute("bike", bike);
        return "note-create";
    }

    @RequestMapping(value = "/admin/bikes/{id}/notes/create", method = RequestMethod.POST)
    public String addNote(@Valid Note note, @PathVariable int id) {
        log.debug("editBike, id={}", id);
        Bike bike = bikeService.findBikeById(id);
        bike.getNotes().add(note);
        bikeService.updateBike(bike);
        note.setBike(bike);
        noteService.updateNote(note);
        return "redirect:/admin/bikes?edited=true";
    }

    @RequestMapping(value = "/admin/bikes/{bikeId}/notes/{noteId}/delete", method = RequestMethod.GET)
    public String deleteNoteForm(@PathVariable int bikeId, @PathVariable int noteId) {
        if (noteId != 0 && bikeId !=0) {
            Note noteToDelete = noteService.findNoteById(noteId);
            if (noteToDelete != null) {
                noteService.deleteNote(noteToDelete);
            } else {
                log.debug("deleteNote, id={}", noteId);
                System.out.printf("ERROR. Nie ma notatki o takim ID: " + noteId);
            }
            return "redirect:/admin/bikes/{id}/notes?deleted=true";
        }
        return "redirect:/admin/bikes/{id}/notes?deleted=false";
    }

}
