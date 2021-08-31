package com.balavignesh.restdemo;
import java.io.File;
import java.util.Arrays;
import java.util.List;
public class FileRenameUtility {
    public static void main(String[] a) {
        System.out.println("FileRenameUtility");
        FileRenameUtility renameUtility = new FileRenameUtility();
        renameUtility.fileRename("/Users/BalaVignesh/Desktop/New Songs");
    }

    private void fileRename(String folder){
        File file = new File(folder);
        System.out.println("Reading this "+file.toString());
        System.out.println("Reading file.isDirectory() "+file.isDirectory());
        if(file.isDirectory()){
            File[] files = file.listFiles();
            List<File> filelist = Arrays.asList(files);
            filelist.forEach(f->{
                if(!f.isDirectory() && f.getName().contains("-5StarMusiQ.Com")){
                    System.out.println(f.getAbsolutePath());
                    String newName = f.getAbsolutePath().replace("-5StarMusiQ.Com","");
                    boolean isRenamed = f.renameTo(new File(newName));
                    if(isRenamed)
                        System.out.println(String.format("Renamed this file %s to  %s",f.getName(),newName));
                    else
                        System.out.println(String.format("%s file is not renamed to %s",f.getName(),newName));


                }
            });

        }
    }
}
