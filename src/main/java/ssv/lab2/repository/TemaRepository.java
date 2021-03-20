package ssv.lab2.repository;

import ssv.lab2.domain.*;
import ssv.lab2.validation.*;

public class TemaRepository extends AbstractCRUDRepository<String, Tema> {
    public TemaRepository(Validator<Tema> validator) {
        super(validator);
    }
}

