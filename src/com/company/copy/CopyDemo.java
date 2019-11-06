package com.company.copy;


import java.io.File;

/**
 * 拷贝文件路径
 */
public class CopyDemo {
    public static void main(String[] args) throws Exception {
        if (args.length != 2){
            System.out.println("命令执行错误，执行结构：java CopyDemo 源文件路径 目标路径");
            System.exit(1);
        }
        long start = System.currentTimeMillis();
        FileUtil fileUtil = new FileUtil(args[0], args[1]);
        if (new File(args[0]).isFile()){
            System.out.println(fileUtil.copy() ? "文件拷贝成功" : "文件拷贝失败");
        }else {
            System.out.println(fileUtil.copyDir() ? "目录拷贝成功" : "目录拷贝失败");
        }


        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
