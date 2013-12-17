package DataHolder;

public class FileInfo {
    public final String name;
    public final String type;
    public final String path;
    public final long size;

    public FileInfo(String name, String type, String path, long size) {
        this.name = name;
        this.type = type;
        this.path = path;
        this.size = size;
    }
}
