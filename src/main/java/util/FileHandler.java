package util;

import java.util.Collection;

/**
 * @param <T>
 * @author Harshit Raj
 */
public interface FileHandler<T extends FileData> {
    boolean writecsv(String colName, String fileName, Collection<T> data);

}
