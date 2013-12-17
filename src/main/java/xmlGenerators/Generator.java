package xmlGenerators;

import DataHolder.FolderInfo;

/**
 * Created with IntelliJ IDEA.
 * User: shot
 * Date: 17.12.13
 * Time: 18:38
 * To change this template use File | Settings | File Templates.
 */
public interface Generator {
    public void generate(FolderInfo foldInfo, String outputPath);
}
