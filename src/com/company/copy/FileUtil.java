package com.company.copy;

import java.io.*;


public class FileUtil  {
    private File srcFile;
    private File desFile;

    public FileUtil(String src, String des) {
        this(new File(src),new File(des));
    }

    public FileUtil(File srcFile, File desFile) {
        this.srcFile = srcFile;
        this.desFile = desFile;
    }

    public boolean copyDir() throws Exception {
        try {
            this.copyImpl(this.srcFile);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    public void copyImpl (File file) throws Exception {
        if (file.isDirectory()){
            File[] results = file.listFiles();
            if (results != null){
                for (File result : results){
                    copyImpl(result);
                }
            }
        }else {
            String newFilePath = file.getPath().replace(this.srcFile.getPath()+ File.separator,"");
            //System.out.println("拷贝的目标文件："+newFilePath);
            File newFile = new File(this.desFile, newFilePath);
            //System.out.println(newFile.getParentFile()+File.separator+newFile.getName());
            this.copyFileImpl(file, newFile);
        }
    }

    public boolean copyFileImpl(File srcFile, File desFile) throws IOException {
        if (!desFile.getParentFile().exists()){
            desFile.getParentFile().mkdirs();
        }

        byte [] date = new byte[1024];
        try (InputStream inputStream = new FileInputStream(srcFile);
             OutputStream outputStream = new FileOutputStream(desFile)) {
            int len = 0;
            while ((len = inputStream.read(date)) != -1){
                outputStream.write(date,0,len);
            }
            //inputStream.transferTo(outputStream);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public boolean copy() throws IOException {

        if (!this.srcFile.exists()){
            System.out.println("拷贝的源文件不存在！");
            return false;
        }

        return this.copyFileImpl(this.srcFile, this.desFile);
    }


}
