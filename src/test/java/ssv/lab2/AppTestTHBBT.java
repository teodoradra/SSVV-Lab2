package ssv.lab2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ssv.lab2.domain.*;
import ssv.lab2.repository.*;
import ssv.lab2.service.Service;
import ssv.lab2.validation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class AppTestTHBBT {
    private StudentXMLRepository studentXMLRepository;
    private NotaXMLRepository notaXMLRepository;
    private TemaXMLRepository temaXMLRepository;

    private Service service;

    @Before
    public void setup() {
        Validator<Student> vs = new StudentValidator();
        Validator<Nota> ns = new NotaValidator();
        Validator<Tema> ts = new TemaValidator();

        studentXMLRepository = new StudentXMLRepository(vs, "src/test/java/testing/studenti.xml");
        notaXMLRepository = new NotaXMLRepository(ns, "src/test/java/testing/note.xml");
        temaXMLRepository = new TemaXMLRepository(ts, "src/test/java/testing/teme.xml");
        service = new Service(studentXMLRepository, temaXMLRepository, notaXMLRepository);

    }

    @After
    public void tearDown() {
        try {
            String defaultFileContent = new String(Files.readAllBytes(Paths.get("src/test/java/testing/defaultFile.xml")), StandardCharsets.UTF_8);

            PrintWriter printWriter = new PrintWriter("src/test/java/testing/studenti.xml");

            printWriter.print(defaultFileContent);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddStudentSuccess() {
        assertEquals(service.saveStudent("1", "Diana", 935), 1);
    }

    @Test
    public void testAddStudentFailure() {
        assertEquals(service.saveStudent("2", "Teodora", 934), 1);
        assertEquals(service.saveStudent("2", "Teodora", 934), 0);
    }

    @Test
    public void TC1_saveStudent() {
        assertEquals(service.saveStudent("0", "Teodora", 934), 1);
    }

    @Test
    public void TC2_saveStudent() {
        assertEquals(service.saveStudent(null, "Teodora", 934), 0);
    }

    @Test
    public void TC3_saveStudent() {
        assertEquals(service.saveStudent("0", "Teodora", 938), 0);
    }


    @Test
    public void TC4_saveStudent() {
        assertEquals(service.saveStudent("0", "Teodora", 939), 0);
    }

    @Test
    public void TC5_saveStudent() {
        assertEquals(service.saveStudent("0", "Teodora", 111), 1);
    }

    @Test
    public void TC6_saveStudent() {
        assertEquals(service.saveStudent("1", "Teodora", 109), 0);
    }

    @Test
    public void TC7_saveStudent() {
        assertEquals(service.saveStudent("2", "Teodora", 937), 1);
    }

    @Test
    public void TC8_saveStudent() {
        assertEquals(service.saveStudent("3", "Teodora", 938), 0);
    }

    @Test
    public void TC9_saveStudent() {
        assertEquals(service.saveStudent("4", "Teodora", 400), 1);
    }

    @Test
    public void TC10_saveStudent() {
        assertEquals(service.saveStudent("5", "Teodora", -1), 0);
    }

    @Test
    public void TC11_saveStudent() {
        assertEquals(service.saveStudent("6", "akljklfjsf", 123), 1);
    }

    @Test
    public void TC12_saveStudent() {
        assertEquals(service.saveStudent("7", "", 123), 0);
    }

    @Test
    public void TC13_saveStudent() {
        assertEquals(service.saveStudent("8", null, 124), 0);
    }
}
