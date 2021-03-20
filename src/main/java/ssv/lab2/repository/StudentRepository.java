package ssv.lab2.repository;

import ssv.lab2.domain.*;
import ssv.lab2.validation.*;

public class StudentRepository extends AbstractCRUDRepository<String, Student> {
    public StudentRepository(Validator<Student> validator) {
        super(validator);
    }
}

