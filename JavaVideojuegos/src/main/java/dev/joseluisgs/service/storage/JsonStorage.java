package dev.joseluisgs.service.storage;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface JsonStorage<K, T, Ex extends Throwable> {
    void saveToFile(Map<K, List<T>> mapa, File file) throws Ex;

}
