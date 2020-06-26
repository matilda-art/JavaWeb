import java.io.File;
import java.util.Scanner;

/**
 * @program: IO：IO流的使用
 * @description
 * @author: matilda
 * @create: 2020-06-25 11:22
 **/
public class FileDemo2 {
    public static void main(String[] args) {
        //File file = new File("测试目录\\a.txt");

        //模拟文件重命名的操作
        /*File dest = new File("测试目录\\b.txt");
        boolean b = file.renameTo(dest);
        System.out.println(b);*/

        //模拟剪切-粘贴的操作
        /*File dest = new File("a.txt");
        boolean b = file.renameTo(dest);
        System.out.println(b);*/

        /*Scanner scanner = new Scanner(System.in);
        {
            File file = new File("测试目录\\a.txt");
            file.delete();
            System.out.println("等待一会，观察文件是否已被删除");
            scanner.nextLine();
        }

        {
            File file = new File("测试目录\\b.txt");
            file.deleteOnExit();
        }*/

        File file = new File("测试目录\\非空目录");
        /*File[] files = file.listFiles();
        for (File f:files) {
            System.out.println(f.getAbsolutePath());
        }*/

        String[] list = file.list();
        System.out.println(list);
        System.out.println(list.length);
        for (String s:list) {
            System.out.println(s);
        }
    }
}

//1.Windows上的删除到“回收站”本质是什么操作呢？
//Windows有一个特殊的目录，平时的删除只是把文件剪切-粘贴到这个特殊目录下了
//2.非空目录的删除怎么处理？
//需要先把目录的文件全删除掉，才能删除该目录
//3.deleteOnExit() 方法的作用
//delete()：该文件理解被删除
//deleteOnExit()：该文件不是立即删除，是等JVM退出的时候再删除
