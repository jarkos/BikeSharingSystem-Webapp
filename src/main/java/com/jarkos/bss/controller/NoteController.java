package com.jarkos.bss.controller;

import com.jarkos.bss.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by Jarek on 2015-12-27.
 */
@Controller
public class NoteController {

    @Autowired
    private NoteService noteService;
}
