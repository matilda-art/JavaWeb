import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: IO：IO流的使用
 * @description
 *
 * 广度优先遍历 —— 队列
 * @author: matilda
 * @create: 2020-06-25 14:47
 **/
public class ScanDirBroad {
    public static void main(String[] args) {
        File root = new File("测试目录\\非空目录");
        //广度优先遍历一定用到队列
        Queue<File> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            File node = queue.poll();
            if (node.isDirectory()) {
                System.out.println(node.getAbsolutePath() + "\\");
            } else if (node.isFile()) {
                System.out.println(node.getAbsolutePath());
            }

            //找到node的所有孩子
            if (node.isDirectory()) {
                File[] children = node.listFiles();
                if (children != null) {
                    for (File child : children) {
                        if (child.isDirectory()) {
                            queue.offer(child);
                        } else if (child.isFile()) {
                            System.out.println(child.getAbsolutePath());
                        }
                    }
                }
            }
        }
    }
}

//1.删除非空目录 —— 遍历目录，首先删除所有的孩子（还需要遍历删除其他的部分）
//  （如何删除一棵树）
//2.如何进行目录的复制 —— 遍历目录
//  分别对子孙中的普通文件 or 目录进行复制
//  （树的复制）
//3.在某个目录下查找文件：
//  1.查找名字是XXX的文件
//  2.查找内容中带有XXX的文件
//  3.或者其他文件
//  （树的查找）
