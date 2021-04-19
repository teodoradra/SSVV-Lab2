package ssv.lab2;

import org.junit.Before;
import org.junit.Test;
import ssv.lab2.domain.*;
import ssv.lab2.repository.*;
import ssv.lab2.service.*;
import ssv.lab2.validation.*;

import java.io.PrintWriter;

import static org.junit.Assert.assertEquals;

public class IncrementalTests {
    private Service service;


    @Before
    public void setUp() throws Exception {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        PrintWriter writer = new PrintWriter("studenti.xml");
        writer.print("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                "<Entitati>\n" +
                "    <student ID=\"1\">\n" +
                "        <Nume>Teodora</Nume>\n" +
                "        <Grupa>933</Grupa>\n" +
                "    </student>\n" +
                "</Entitati>\n");
        writer.close();

        writer = new PrintWriter("teme.xml");
        writer.print("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                "<Entitati>\n" +
                "<tema ID=\"1\">\n" +
                "<Descriere>Best</Descriere>\n" +
                "<Deadline>7</Deadline>\n" +
                "<Startline>6</Startline>\n" +
                "</tema>\n" +
                "</Entitati>\n");
        writer.close();

        writer = new PrintWriter("note.xml");
        writer.print("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                "<Entitati>\n" +
                "</Entitati>\n");
        writer.close();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");

        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

        service = new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    @Test
    public void testAddStudentTD(){
        assertEquals(1, service.saveStudent("gh", "teo", 231));
    }

    @Test
    public void testAddAssignmentAddStudentTD(){
        assertEquals(1, service.saveTema("2", "Integrale", 7, 5));
        assertEquals(1, service.saveStudent("2", "Alexandra", 935));
    }

    @Test
    public void testAddAllTD(){
        assertEquals(1, service.saveStudent("3", "Marius", 231));
        assertEquals(1, service.saveTema("3", "best", 8, 5));
        assertEquals(1, service.saveNota("3", "3", 8, 7, "ok"));
    }

}
