package dev.joseluisgs.service.storage;

import java.io.File;
import java.util.List;

public interface CsvStorage<T, EX extends Throwable> {
    List<T> readFromFile(File file) throws EX;
}
